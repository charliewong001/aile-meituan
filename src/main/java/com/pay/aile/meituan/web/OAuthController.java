package com.pay.aile.meituan.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Platform;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.jpa.User;
import com.pay.aile.meituan.bean.platform.ShopInfoBean;
import com.pay.aile.meituan.bean.platform.ShopInfoBean.ShopInfo;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.client.TakeawayClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.service.FoodService;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutPoiInfoQueryRequest;

/**
 *
 * @Description: 授权绑定解绑接口
 * @see: OAuthController 此处填写需要参考的类
 * @version 2017年7月20日 下午4:00:15
 * @author chao.wang
 */
@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${meituan_authUrl}")
    private String authUrl;// 美团授权页面

    @Value("${meituan_authCallback}")
    private String authCallbackUrl;// 我方授权token通知地址

    @Value("${meituan_unbindUrl}")
    private String unbindUrl;// 美团门店解绑页面

    @Value("${meituan_unbindCallback}")
    private String unbindCallbackUrl;// 我方解绑结果通知地址

    @Resource
    private FoodService foodService;

    @Resource
    private TakeawayClient takeawayClient;

    @Resource
    private JpaClient jpaClient;
    private final String ret_success = "{data:\"success\"}";// 美团回调返回

    /**
     *
     * @Description 绑定回调告知appAuthToken
     * @param ePoiId
     *            即我方shopId
     * @param appAuthToken
     *            店铺token
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/authCallback")
    public String authCallback(@RequestParam String ePoiId, @RequestParam String appAuthToken) {
        // 将appAuthToken存入redis
        MeituanConfig.setAppAuthToken(ePoiId, appAuthToken);
        Shop shop = new Shop(ePoiId, Platform.getInstance());
        try {
            User user = new User();
            user.setPhone(MeituanConfig.getPhone(ePoiId));
            user.setRegistrationId(MeituanConfig.getRegistrationId(ePoiId));
            user.setLastUpdateTime(new Date());
            shop.setUser(user);
            shop.setRegistrationId(user.getRegistrationId());
            shop.setChannel(MeituanConfig.getChannel(ePoiId));
            shop.setPhone(user.getPhone());
            logger.info("pushAuthorization bean = {}", shop);
            JSONObject pushResult = takeawayClient.pushAuthorization(JsonFormatUtil.toJSONString(shop));
            logger.info("pushAuthorization result={}", pushResult);
        } catch (Exception e) {
            logger.error("authCallback->pushAuthrization error!授权回调后进行用户店铺信息推送失败!", e);
        } finally {
            MeituanConfig.removeChannel(ePoiId);
            MeituanConfig.removePhone(ePoiId);
        }
        // 查询店铺信息
        ShopInfoBean shopInfoBean = null;
        String result = "";
        try {
            CipCaterTakeoutPoiInfoQueryRequest request = new CipCaterTakeoutPoiInfoQueryRequest();
            RequestSysParams sysParams = new RequestSysParams();
            sysParams.setAppAuthToken(appAuthToken);
            sysParams.setSecret(MeituanConfig.getSignkey());
            request.setEPoiIds(ePoiId);
            request.setRequestSysParams(sysParams);
            logger.info("authCallback 查询店铺信息,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            shopInfoBean = JSONObject.parseObject(result, ShopInfoBean.class);
        } catch (Exception e) {
            logger.error("authCallback 查询店铺信息错误!", e);
        } finally {
            logger.info("authCallback 查询店铺信息,result={}", result);
        }
        JSONObject saveResult = null;
        try {
            ShopInfo shopInfo = shopInfoBean.getData().get(0);
            shop.setShopName(shopInfo.getName());
            shop.setAddress(shopInfo.getAddress());
            shop.setIsOnline(shopInfo.getIsOnline().toString());
            shop.setInvoiceSupport(shopInfo.getInvoiceSupport().toString());
            shop.setLatitude(shopInfo.getLatitude());
            shop.setLongitude(shopInfo.getLongitude());
            shop.setShippingFee(new BigDecimal(shopInfo.getShippingFee().toString()));
            shop.setOpenLevel(shopInfo.getIsOpen().toString());
            shop.setInvoiceMinAmount(new BigDecimal(shopInfo.getInvoiceMinPrice()));
            shop.setBusinessHours(shopInfo.getOpenTime());
            logger.info("authCallback 保存店铺信息,bean={}", shop);
            saveResult = jpaClient.saveOrUpdateShop(JsonFormatUtil.toJSONString(shop));
        } catch (Exception e) {
            logger.error("authCallback 保存店铺信息失败", e);
        } finally {
            logger.info("authCallback 保存店铺信息,result={}", saveResult);
        }
        // 进行菜品映射
        try {
            foodService.mapFoodToDish(ePoiId);
        } catch (Exception e) {
            logger.error("authCallback->mapFoodToDish error!授权回调后进行菜品映射失败,shopId={}", ePoiId, e);
        }
        return ret_success;
    }

    /**
     *
     * @Description 获取美团绑定授权页面地址
     * @param shopId
     *            门店ID
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/getAuthUrl")
    public void getAuthUrl(@RequestParam String shopId, @RequestParam String deviceNo, @RequestParam String customerNo,
            @RequestParam String registrationId, @RequestParam String phone, @RequestParam String channel,
            HttpServletResponse response) {
        MeituanConfig.setRegistrationId(shopId, registrationId);// 将极光推送注册号保存到缓存
        MeituanConfig.setPhone(shopId, phone);
        MeituanConfig.setChannel(shopId, channel);
        String developerId = MeituanConfig.getDeveloperId();
        String signKey = MeituanConfig.getSignkey();
        String callbackUrl = "";
        try {
            callbackUrl = URLEncoder.encode(authCallbackUrl, "UTF-8");
        } catch (Exception e) {
            logger.error("authCallbackUrl encode error!,authCallbackUrl={}", authCallbackUrl, e);
        }
        String url = authUrl + "?developerId=" + developerId + "&businessId=2&ePoiId=" + shopId + "&signKey=" + signKey
                + "&callbackUrl=" + callbackUrl;
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            logger.error("getAuthUrl sendRedirect error!");
        }
    }

    /**
     *
     * @Description 获取门店解绑地址
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/getUnbindUrl")
    public String getUnbindUrl(@RequestParam String shopId) {
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        String signKey = MeituanConfig.getSignkey();
        String url = unbindUrl + "?signKey=" + signKey + "&businessId=2&appAuthToken=" + appAuthToken;
        return url;
    }

    /**
     *
     * @Description 门店解绑结果通知
     * @param bussinessId
     * @param developerId
     * @param epoiId
     * @param timestamp
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/unbindCallback")
    public String unbindCallback(@RequestParam String businessId, @RequestParam String developerId,
            @RequestParam String epoiId, @RequestParam String timestamp) {
        String localDeveloperId = MeituanConfig.getDeveloperId();
        if (!"2".equals(businessId)) {
            return "{data:\"bussinessId not correct!\"}";
        }
        if (!localDeveloperId.equals(developerId)) {
            return "{data:\"developerId not correct!\"}";
        }
        MeituanConfig.removeAppAuthToken(epoiId);// 删除appAuthToken
        MeituanConfig.removeRegistrationId(epoiId);// 删除极光推送注册号
        return ret_success;

    }
}
