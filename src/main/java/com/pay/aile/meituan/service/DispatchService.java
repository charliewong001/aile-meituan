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
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.client.TakeawayClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveredRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveringRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDispatchCancelRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDispatchRequest;
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

    /**
     *
     * @Description 取消配送 此接口主要应用众包配送方式，送达之前都可以取消，其它美团配送方式，只能15分钟后无骑手接单情况下取消。
     * @param shopId
     * @param orderId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void dispatchCancel(String shopId, Long orderId) {
        CipCaterTakeoutOrderDispatchCancelRequest request = new CipCaterTakeoutOrderDispatchCancelRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setRequestSysParams(sysParams);
        request.setOrderId(orderId);
        String result = "";
        try {
            logger.info("zbDispatchCancel 取消配送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("zbDispatchCancel 取消配送,result={}", result);
        } catch (Exception e) {
            logger.error("zbDispatchCancel 取消配送错误!orderId={}", orderId, e);
            throw new RuntimeException("取消配送错误！orderId=".concat(orderId.toString()));
        }
        if (!Constants.ok.equalsIgnoreCase(result)) {
            logger.info("zbDispatchCancel 取消配送错误 返回值错误!");
            throw new RuntimeException("取消配送错误！orderId=".concat(orderId.toString()));
        }
    }

    /**
     *
     * @Description 发送美团专送配送
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void dispatchShip(String shopId, Long orderId) {
        CipCaterTakeoutOrderDispatchRequest request = new CipCaterTakeoutOrderDispatchRequest();
        RequestSysParams sysParams = new RequestSysParams(MeituanConfig.getSignkey(),
                MeituanConfig.getAppAuthToken(shopId));
        request.setRequestSysParams(sysParams);
        request.setOrderId(orderId);
        String result = "";
        try {
            logger.info("dispatchShip 发送美团专送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("dispatchShip 发送美团专送,result={}", result);
        } catch (Exception e) {
            logger.error("dispatchShip 发送美团专送错误!orderId={}", orderId, e);
            throw new RuntimeException("发送美团专送错误！orderId=".concat(orderId.toString()));
        }
        if (!Constants.ok.equalsIgnoreCase(result)) {
            logger.info("dispatchShip 发送美团专送错误 返回值错误!");
            throw new RuntimeException("发送美团专送错误！orderId=".concat(orderId.toString()));
        }
    }

    /**
     *
     * @Description 美团配送状态变化回调
     * @param shopId
     * @param bean
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void dispatchStatusPush(String shopId, @Valid ShippingOrderBean bean) {

        long updateTime = System.currentTimeMillis();

        // 配送信息bean
        Distribution distribution = new Distribution();
        distribution.setDistributionName(bean.getDispatcherName());
        distribution.setPhone(bean.getDispatcherMobile());
        distribution.setOrder(new Order(bean.getOrderId().toString()));
        distribution.setShop(new Shop(shopId, Platform.getInstance()));
        distribution.setUpdateAt(updateTime);
        switch (ShippingStatusEnum.get(bean.getShippingStatus())) {
        case sended:// 配送单发往配送
            distribution.setStatus(DistributionStatusEnum.tobeAssigned);
            break;
        case shipperConfirm:// 配送单已确认(骑手接单)
            distribution.setStatus(DistributionStatusEnum.tobeFetched);
            break;
        case shipperGet:// 骑手已取餐
            distribution.setStatus(DistributionStatusEnum.arrived);
            break;
        case shipperArrive:// 骑手已送达
            distribution.setStatus(DistributionStatusEnum.completed);
            break;
        case cancel:// 配送单已取消
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
        result.put("updateTime", updateTime);
        JSONObject pushResult = null;
        try {
            pushResult = takeawayClient.pushDistribution(MeituanConfig.getRegistrationId(shopId),
                    JsonFormatUtil.toJSONString(result));
            logger.info("dispatchStatusPush 推送结果={}", pushResult);
        } catch (Exception e) {
            logger.error("dispatchStatusPush 处理美团推送的配送单状态变更失败！orderId={}", bean.getOrderId(), e);
        }
        if (pushResult == null || !Constants.pushOK.equals(pushResult.getString("code"))) {
            logger.error("dispatchStatusPush 极光推送失败!msg={},pushBean={}",
                    pushResult == null ? "" : pushResult.getString("msg"), result);
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
            logger.info("selfDelivered 美团商家自配送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("selfDelivered 美团商家自配送,result={}", result);
        } catch (Exception e) {
            logger.error("selfDelivered 美团商家自配送错误!orderId={}", orderId, e);
            throw new RuntimeException("美团商家自配送错误！orderId=".concat(orderId.toString()));
        }
        if (Constants.ok.equalsIgnoreCase(result)) {
            logger.info("selfDelivered 美团商家自配送成功，orderId={}", orderId);
            // 保存配送单
            Distribution distribution = new Distribution();
            distribution.setUpdateAt(System.currentTimeMillis());
            distribution.setStatus(DistributionStatusEnum.completed);
            distribution.setOrder(new Order(orderId.toString()));
            distribution.setShop(new Shop(shopId, Platform.getInstance()));
            JSONObject saveResult = null;
            try {
                logger.info("selfDelivered 保存配送信息 bean={}", distribution);
                saveResult = jpaClient.saveOrUpdateDistribution(JsonFormatUtil.toJSONString(distribution));
                logger.info("selfDelivered 保存配送信息  返回结果={}", saveResult);
            } catch (Exception e) {
                logger.error("selfDelivered 保存配送单失败!orderId={}", orderId, e);
                throw new RuntimeException("保存配送单失败!");
            }
        } else {
            logger.error("selfDelivered 美团商家自配送失败，orderId={}", orderId);
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
            logger.info("selfDelivering 美团商家自配送,request={}", JsonFormatUtil.toJSONString(request));
            result = request.doRequest();
            logger.info("selfDelivering 美团商家自配送,result={}", result);
        } catch (Exception e) {
            logger.error("selfDelivering 美团商家自配送错误!orderId={}", orderId, e);
            throw new RuntimeException("美团商家自配送错误！orderId=".concat(orderId.toString()));
        }
        if (Constants.ok.equalsIgnoreCase(result)) {
            logger.info("selfDelivering 美团商家自配送成功，orderId={}", orderId);
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
                logger.info("selfdelivering 保存配送信息 bean={}", distribution);
                saveResult = jpaClient.saveOrUpdateDistribution(JsonFormatUtil.toJSONString(distribution));
                logger.info("selfdelivering 保存配送信息  返回结果={}", saveResult);
            } catch (Exception e) {
                logger.error("selfdelivering 保存配送单失败!orderId={}", orderId, e);
                throw new RuntimeException("保存配送单失败!");
            }
        } else {
            logger.error("selfdelivering 美团商家自配送失败，orderId={}", orderId);
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
        if (!Constants.ok.equalsIgnoreCase(result)) {
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
        if (!Constants.ok.equalsIgnoreCase(result)) {
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
            logger.error("zbShippingFeeQuery 查询众包配送返回值错误,orderId={}", orderId, e);
            throw new RuntimeException("查询众包配送返回值错误");
        }
        return fees;
    }
}
