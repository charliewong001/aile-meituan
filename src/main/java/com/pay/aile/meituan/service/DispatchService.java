package com.pay.aile.meituan.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.Constants;
import com.pay.aile.meituan.bean.jpa.Distribution;
import com.pay.aile.meituan.bean.jpa.DistributionStatusEnum;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.Platform;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.platform.ShippingOrderBean;
import com.pay.aile.meituan.bean.platform.ShippingStatusEnum;
import com.pay.aile.meituan.bean.platform.ZbShippingFeeBaseBean;
import com.pay.aile.meituan.bean.platform.ZbShippingFeeBean;
import com.pay.aile.meituan.bean.push.PushOrderShipping;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.client.TakeawayClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveredRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveringRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderZbDispatchConfirmRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderZbDispatchPrepareRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderZbDispatchTipUpdateRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderZbShippingFeeQueryRequest;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: DispatchService 此处填写需要参考的类
 * @version 2017年7月20日 上午10:53:22
 * @author chao.wang
 */
@Service
public class DispatchService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JpaClient jpaClient;
    @Resource
    private TakeawayClient takeawayClient;

    public void dispatchStatusPush(String shopId, @Valid ShippingOrderBean bean) {

        long updateTime = System.currentTimeMillis();
        // 推送bean
        PushOrderShipping pushShipping = new PushOrderShipping();
        pushShipping.setName(bean.getDispatcherName());
        pushShipping.setPhone(bean.getDispatcherMobile());
        pushShipping.setOrderId(bean.getOrderId().toString());
        pushShipping.setUpdateTime(updateTime);

        // 配送信息bean
        Distribution distribution = new Distribution();
        distribution.setDistributionName(bean.getDispatcherName());
        distribution.setPhone(bean.getDispatcherMobile());
        distribution.setOrder(new Order(bean.getOrderId().toString()));
        distribution.setShop(new Shop(shopId, Platform.getInstance()));
        distribution.setUpdateAt(updateTime);
        switch (ShippingStatusEnum.get(bean.getShippingStatus())) {
        case sended:// 配送单发往配送
            pushShipping.setDistributionStatus(DistributionStatusEnum.tobeAssigned.getCode());
            distribution.setStatus(DistributionStatusEnum.tobeAssigned);
            break;
        case shipperConfirm:// 配送单已确认(骑手接单)
            pushShipping.setDistributionStatus(DistributionStatusEnum.tobeFetched.getCode());
            distribution.setStatus(DistributionStatusEnum.tobeFetched);
            break;
        case shipperGet:// 骑手已取餐
            pushShipping.setDistributionStatus(DistributionStatusEnum.arrived.getCode());
            distribution.setStatus(DistributionStatusEnum.arrived);
            break;
        case shipperArrive:// 骑手已送达
            pushShipping.setDistributionStatus(DistributionStatusEnum.completed.getCode());
            distribution.setStatus(DistributionStatusEnum.completed);
            break;
        case cancel:// 配送单已取消
            pushShipping.setDistributionStatus(DistributionStatusEnum.cancelled.getCode());
            distribution.setStatus(DistributionStatusEnum.cancelled);
            break;
        default:
            break;

        }
        // 保存或修改配送信息
        JSONObject result = null;
        try {
            logger.info("dispatchStatusPush 保存或修改配送信息 bean={}", distribution);
            result = jpaClient.saveOrUpdateDistribution(JsonFormatUtil.toJSONString(distribution));
            logger.info("dispatchStatusPush 保存或修改配送信息  返回结果={}", result);
        } catch (Exception e) {
            logger.error("dispatchStatusPush 保存或修改配送单失败!,bean={}", bean, e);
            throw new RuntimeException("保存配送单失败!");
        }

        // 推送消息
        JSONObject pushResult = null;
        try {
            logger.info("dispatchStatusPush 推送的消息={}", pushShipping);
            pushResult = takeawayClient.pushOrderCancel(MeituanConfig.getRegistrationId(shopId),
                    JsonFormatUtil.toJSONString(pushShipping));
            logger.info("dispatchStatusPush 推送结果={}", pushResult);
        } catch (Exception e) {
            logger.error("dispatchStatusPush 处理美团推送的配送单状态变更失败！orderId={}", bean.getOrderId());
        }
        if (pushResult == null || !"0".equals(pushResult.getString("code"))) {
            logger.error("dispatchStatusPush 极光推送失败!msg={},pushBean={}",
                    pushResult == null ? "" : pushResult.getString("msg"), bean);
        }

    }

    /**
     *
     * @Description 商家自配送-已送达
     * @param shopId
     * @param orderId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void selfDelivered(String shopId, Long orderId) {
        CipCaterTakeoutOrderDeliveredRequest request = new CipCaterTakeoutOrderDeliveredRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setOrderId(orderId);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("delivered 美团商家自配送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("delivered 美团商家自配送,result={}", result);
        } catch (Exception e) {
            logger.error("delivered 美团商家自配送错误!orderId={}", orderId, e);
            throw new RuntimeException("美团商家自配送错误！orderId=".concat(orderId.toString()));
        }
        if (Constants.OK.equals(result)) {
            logger.info("delivered 美团商家自配送成功，orderId={}", orderId);
            // 保存配送单
            Distribution distribution = new Distribution();
            distribution.setUpdateAt(System.currentTimeMillis());
            distribution.setStatus(DistributionStatusEnum.completed);
            distribution.setOrder(new Order(orderId.toString()));
            distribution.setShop(new Shop(shopId, Platform.getInstance()));
            JSONObject saveResult = null;
            try {
                logger.info("delivered 保存配送信息 bean={}", distribution);
                saveResult = jpaClient.saveOrUpdateDistribution(JsonFormatUtil.toJSONString(distribution));
                logger.info("delivered 保存配送信息  返回结果={}", saveResult);
            } catch (Exception e) {
                logger.error("delivered 保存配送单失败!orderId={}", orderId, e);
                throw new RuntimeException("保存配送单失败!");
            }
        } else {
            logger.error("delivering 美团商家自配送失败，orderId={}", orderId);
        }
    }

    /**
     * @Description 订单自配送
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void selfDelivering(String shopId, Long orderId, String name, String phone) {
        CipCaterTakeoutOrderDeliveringRequest request = new CipCaterTakeoutOrderDeliveringRequest();
        request.setCourierName(name);
        request.setCourierPhone(phone);
        request.setOrderId(orderId);
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("delivering 美团商家自配送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("delivering 美团商家自配送,result={}", result);
        } catch (Exception e) {
            logger.error("delivering 美团商家自配送错误!orderId={}", orderId, e);
            throw new RuntimeException("美团商家自配送错误！orderId=".concat(orderId.toString()));
        }
        if (Constants.OK.equals(result)) {
            logger.info("delivering 美团商家自配送成功，orderId={}", orderId);
            // 保存配送单
            Distribution distribution = new Distribution();
            distribution.setDistributionName(name);
            distribution.setPhone(phone);
            distribution.setUpdateAt(System.currentTimeMillis());
            distribution.setStatus(DistributionStatusEnum.delivering);
            distribution.setOrder(new Order(orderId.toString()));
            distribution.setShop(new Shop(shopId, Platform.getInstance()));
            JSONObject saveResult = null;
            try {
                logger.info("delivering 保存配送信息 bean={}", distribution);
                saveResult = jpaClient.saveOrUpdateDistribution(JsonFormatUtil.toJSONString(distribution));
                logger.info("delivering 保存配送信息  返回结果={}", saveResult);
            } catch (Exception e) {
                logger.error("delivering 保存配送单失败!orderId={}", orderId, e);
                throw new RuntimeException("保存配送单失败!");
            }
        } else {
            logger.error("delivering 美团商家自配送失败，orderId={}", orderId);
        }
    }

    /**
     *
     * @Description 众包配送确认下单
     * @param shopId
     * @param orderId
     * @param tipAmount
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void zbDispatchConfirm(String shopId, Long orderId, Double tipAmount) {
        CipCaterTakeoutOrderZbDispatchConfirmRequest request = new CipCaterTakeoutOrderZbDispatchConfirmRequest();

        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setRequestSysParams(sysParams);
        request.setOrderId(orderId);
        request.setTipAmount(tipAmount);
        String result = "";
        try {
            logger.info("zbDispatchConfirm 众包配送确认下单,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("zbDispatchConfirm 众包配送确认下单,result={}", result);
        } catch (Exception e) {
            logger.error("zbDispatchConfirm 众包配送确认下单错误!orderId={}", orderId, e);
            throw new RuntimeException("众包配送确认下单错误！orderId=".concat(orderId.toString()));
        }
        if (!Constants.OK.equals(result)) {
            logger.info("zbDispatchConfirm 众包配送确认下单错误 返回值错误!");
            throw new RuntimeException("众包配送确认下单错误！orderId=".concat(orderId.toString()));
        }
    }

    /**
     *
     * @Description 众包配送预下单
     * @param shopId
     * @param orderId
     * @param shippingFee
     *            配送费
     * @param timAmount
     *            小费
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String zbDispatchPrepare(String shopId, Long orderId, Double shippingFee, Double tipAmount) {
        CipCaterTakeoutOrderZbDispatchPrepareRequest request = new CipCaterTakeoutOrderZbDispatchPrepareRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setRequestSysParams(sysParams);
        request.setOrderId(orderId);
        request.setShippingFee(shippingFee);
        request.setTipAmount(tipAmount);
        String result = "";
        try {
            logger.info("zbDispatchPrepare 众包配送预下单,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("zbDispatchPrepare 众包配送预下单,result={}", result);
        } catch (Exception e) {
            logger.error("zbDispatchPrepare 众包配送预下单错误!orderId={}", orderId, e);
            throw new RuntimeException("众包配送预下单错误！orderId=".concat(orderId.toString()));
        }
        return result;
    }

    /**
     *
     * @Description 加小费
     * @param shopId
     * @param orderId
     * @param tipAmount
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void zbDispatchTipUpdate(String shopId, Long orderId, Double tipAmount) {
        CipCaterTakeoutOrderZbDispatchTipUpdateRequest request = new CipCaterTakeoutOrderZbDispatchTipUpdateRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setOrderId(orderId);
        request.setRequestSysParams(sysParams);
        request.setTipAmount(tipAmount);
        String result = "";
        try {
            logger.info("zbDispatchTipUpdate 众包配送加小费,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("zbDispatchTipUpdate 众包配送加小费,result={}", result);
        } catch (Exception e) {
            logger.error("zbDispatchTipUpdate 众包配送加小费错误!orderId={}", orderId, e);
            throw new RuntimeException("众包配送加小费错误！orderId=".concat(orderId.toString()));
        }
        if (!Constants.OK.equals(result)) {
            logger.info("zbDispatchTipUpdate 众包配送加小费错误 返回值错误!");
            throw new RuntimeException("众包配送加小费错误！orderId=".concat(orderId.toString()));
        }
    }

    /**
     *
     * @Description 查询众包配送费
     * @param orderId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public List<ZbShippingFeeBean> zbShippingFeeQuery(String shopId, String orderId) {
        CipCaterTakeoutOrderZbShippingFeeQueryRequest request = new CipCaterTakeoutOrderZbShippingFeeQueryRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setOrderIds(orderId);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("zbShippingFeeQuery 查询众包配送费,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("zbShippingFeeQuery 查询众包配送费,result={}", result);
        } catch (Exception e) {
            logger.error("zbShippingFeeQuery 查询众包配送费错误!orderId={}", orderId, e);
            throw new RuntimeException("查询众包配送费错误！orderId=".concat(orderId.toString()));
        }
        List<ZbShippingFeeBean> fees = null;
        try {
            fees = JSONObject.parseObject(result, ZbShippingFeeBaseBean.class).getData();
        } catch (Exception e) {
            logger.error("zbShippingFeeQuery 查询众包配送返回值错误,orderId={}", orderId);
            throw new RuntimeException("查询众包配送返回值错误");
        }
        return fees;
    }
}
