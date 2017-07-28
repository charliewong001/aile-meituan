package com.pay.aile.meituan.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.Constants;
import com.pay.aile.meituan.bean.jpa.Platform;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.jpa.StatusEnum;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutPoiCloseRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutPoiOpenRequest;

@Service
public class ShopService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private JpaClient jpaClient;

    /**
     *
     * @Description 设置店铺为自动接单
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void autoConfirmOrder(String shopId) {
        MeituanConfig.setAutoConfirmOrder(shopId);
        Shop shop = new Shop(shopId);
        shop.setAutomaticStatus(StatusEnum.ENABLE);
        jpaClient.saveOrUpdateShop(JsonFormatUtil.toJSONString(shop));
    }

    /**
     *
     * @Description 设置门店为休息
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void close(String shopId) {
        // 根据shopId获取到token
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        CipCaterTakeoutPoiCloseRequest request = new CipCaterTakeoutPoiCloseRequest();
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("close 设置门店为休息,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("close 设置门店为休息,返回值={}", result);
        } catch (Exception e) {
            logger.error("meituan shop close error!,shopId={},result={}", shopId, result, e);
            throw new RuntimeException("close error!" + e.getMessage());
        }
        if (Constants.OK.equals(result)) {
            Shop shop = new Shop(shopId, Platform.getInstance());
            shop.setOpenLevel("0");
            JSONObject saveResult = null;
            try {
                logger.info("close 修改门店状态bean={}", shop);
                saveResult = jpaClient.saveOrUpdateShop(JsonFormatUtil.toJSONString(shop));
                logger.info("close 修改门店状态，返回值={}", saveResult);
            } catch (Exception e) {
                logger.error("close 修改门店状态失败,shopId={}", shopId, e);
                throw new RuntimeException("设置门店为休息失败");
            }
        } else {
            logger.error("close 美团设置门店为休息失败,返回值错误,shopId={}", shopId);
            throw new RuntimeException("设置门店为休息失败");
        }
    }

    /**
     *
     * @Description 设置门店为营业
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void open(String shopId) {
        // 根据shopId获取到token
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());

        CipCaterTakeoutPoiOpenRequest request = new CipCaterTakeoutPoiOpenRequest();
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("open 设置门店为营业,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("open 设置门店为营业,返回值={}", result);
        } catch (Exception e) {
            logger.error("meituan shop open error!,shopId={},result={}", shopId, result, e);
            throw new RuntimeException("设置门店为营业!" + e.getMessage());
        }
        if (Constants.OK.equals(result)) {
            Shop shop = new Shop(shopId, Platform.getInstance());
            shop.setOpenLevel("1");
            JSONObject saveResult = null;
            try {
                logger.info("open 修改门店状态bean={}", shop);
                saveResult = jpaClient.saveOrUpdateShop(JsonFormatUtil.toJSONString(shop));
                logger.info("open 修改门店状态，返回值={}", saveResult);
            } catch (Exception e) {
                logger.error("close 修改门店状态失败,shopId={}", shopId, e);
                throw new RuntimeException("设置门店为营业失败" + e.getMessage());
            }
        } else {
            logger.error("close 美团设置门店为休息失败,返回值错误,shopId={}", shopId);
            throw new RuntimeException("设置门店为休息失败");
        }
    }

    /**
     *
     * @Description 设置店铺为不自动接单
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void unAutoConfirmOrder(String shopId) {
        MeituanConfig.removeAutoConfirmOrder(shopId);
        Shop shop = new Shop(shopId);
        shop.setAutomaticStatus(StatusEnum.DISENABLE);
        jpaClient.saveOrUpdateShop(JsonFormatUtil.toJSONString(shop));
    }

}
