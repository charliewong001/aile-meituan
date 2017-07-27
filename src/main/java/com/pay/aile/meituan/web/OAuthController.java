package com.pay.aile.meituan.web;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pay.aile.meituan.bean.Constants;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.service.FoodService;

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
        MeituanConfig.setAppAuthToken(Constants.mtRedisAuthTokenPrefix.concat(ePoiId), appAuthToken);
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
    public String getAuthUrl(@RequestParam String shopId, @RequestParam String deviceNo,
            @RequestParam String customerNo, @RequestParam String registrationId) {
        // 将deviceNo存入缓存,或者在其他地方进行授权之前就存入缓存
        MeituanConfig.setDeviceNo(shopId, deviceNo);
        MeituanConfig.setRegistrationId(shopId, registrationId);// 将极光推送注册号保存到缓存
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
        return url;
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
        String url = unbindUrl + "?signKey=" + signKey + "&businessId=1&appAuthToken=" + appAuthToken;
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
    public String unbindCallback(@RequestParam String bussinessId, @RequestParam String developerId,
            @RequestParam String epoiId, @RequestParam String timestamp) {
        String localDeveloperId = MeituanConfig.getDeveloperId();
        if (!"2".equals(bussinessId)) {
            return "{data:\"bussinessId not correct!\"}";
        }
        if (!localDeveloperId.equals(developerId)) {
            return "{data:\"developerId not correct!\"}";
        }
        MeituanConfig.removeAppAuthToken(epoiId);// 删除appAuthToken
        MeituanConfig.removeDeviceNo(epoiId);// 删除deviceNo
        MeituanConfig.removeAutoConfirmOrder(epoiId);// 删除自动接单的配置
        MeituanConfig.removeRegistrationId(epoiId);// 删除极光推送注册号
        return ret_success;

    }
}
