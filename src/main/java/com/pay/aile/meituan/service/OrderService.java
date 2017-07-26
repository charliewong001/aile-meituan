package com.pay.aile.meituan.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.OrderItem;
import com.pay.aile.meituan.bean.jpa.OrderStatusEnum;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.jpa.StatusEnum;
import com.pay.aile.meituan.bean.platform.CancelOrderBean;
import com.pay.aile.meituan.bean.platform.NewOrderBean;
import com.pay.aile.meituan.bean.platform.NewOrderDetailBean;
import com.pay.aile.meituan.bean.platform.PayTypeEnum;
import com.pay.aile.meituan.bean.platform.RefundOrderBean;
import com.pay.aile.meituan.bean.push.PushCancelOrder;
import com.pay.aile.meituan.bean.push.PushNewOrder;
import com.pay.aile.meituan.bean.push.PushNewOrderItem;
import com.pay.aile.meituan.bean.push.PushRefundOrder;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.client.TakeawayClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderCancelRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderConfirmRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderRefundAcceptRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderRefundRejectRequest;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: OrderService 此处填写需要参考的类
 * @version 2017年7月18日 上午10:24:44
 * @author chao.wang
 */
@Service
public class OrderService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");
    private static final String OK = "{\"data\":\"OK\"}";
    private Logger logger = LoggerFactory.getLogger(getClass());
    /** jpa项目order接口 */
    @Resource
    private JpaClient jpaClient;
    /** 推送接口 */
    @Resource
    private TakeawayClient takeawayClient;

    /**
     *
     * @Description 商家拒绝订单
     * @param shopId
     * @param orderId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void cancelOrder(String shopId, Long orderId, String reasonCode,
            String reason) {
        // 通过shopId获取appAuthToken
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        CipCaterTakeoutOrderCancelRequest request = new CipCaterTakeoutOrderCancelRequest();
        request.setOrderId(orderId);
        request.setRequestSysParams(sysParams);
        request.setReasonCode(reasonCode);
        request.setReason(reason);
        String result = "";
        try {
            result = request.doRequest();

        } catch (Exception e) {
            logger.error("cancelOrder error!orderId={},result={}", orderId,
                    result, e);
            throw new RuntimeException("调用美团取消订单失败!" + e.getMessage());
        }
        if (OK.equals(result)) {
            logger.info("cancelOrder success!orderId={}", orderId);
            try {
                Order order = new Order();
                order.setOrderId(orderId.toString());
                order.setStatus(OrderStatusEnum.mt_business_confirmed);
                jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
            } catch (Exception e) {
                logger.error("修改订单状态失败!orderId={}", orderId, e);
                throw new RuntimeException(
                        "美团取消订单成功,修改本地订单状态失败!" + e.getMessage());
            }
        } else {
            logger.info("cancelOrder fail!orderId={},result={}", orderId,
                    result);
            throw new RuntimeException(
                    String.format("调用美团取消订单失败!result={%s}", result));
        }

    }

    /**
     *
     * @Description 处理美团推送的已取消订单
     * @param shopId
     * @param orderId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void cancelOrderPush(String shopId, @Valid CancelOrderBean bean) {
        Long orderId = bean.getOrderId();
        // 向POS推送取消订单信息
        PushCancelOrder pushCancelOrder = new PushCancelOrder();
        pushCancelOrder.setOrderId(orderId.toString());
        pushCancelOrder.setReason(bean.getReason());
        pushCancelOrder.setUpdateTime(sdf.format(new Date()));
        // TODO 推送

        try {
            Order order = new Order();
            order.setOrderId(orderId.toString());
            order.setStatus(OrderStatusEnum.mt_cancelled);
            jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
            // TODO 调用接口保存RefundOrder?
        } catch (Exception e) {
            logger.error("修改订单状态失败!orderId={}", orderId, e);
            throw new RuntimeException("美团取消订单成功,修改本地订单状态失败!" + e.getMessage());
        }
    }

    /**
     *
     * @Description 接单
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void confirmOrder(String shopId, Long orderId) {
        // 通过shopId获取appAuthToken
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        CipCaterTakeoutOrderConfirmRequest request = new CipCaterTakeoutOrderConfirmRequest();
        request.setOrderId(orderId);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            result = request.doRequest();
        } catch (Exception e) {
            logger.error("confirmOrder error!orderId={},result={}", orderId,
                    result, e);
            throw new RuntimeException("调用美团确认订单失败!" + e.getMessage());
        }
        if (OK.equals(result)) {
            logger.info("confirmOrder success!orderId={}", orderId);
            try {
                Order order = new Order();
                order.setOrderId(orderId.toString());
                order.setStatus(OrderStatusEnum.mt_business_confirmed);
                jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
            } catch (Exception e) {
                logger.error("修改订单状态失败!orderId={}", orderId, e);
                throw new RuntimeException(
                        "美团确认订单成功,修改本地订单状态失败!" + e.getMessage());
            }
        } else {
            logger.info("confirmOrder fail!orderId={},result={}", orderId,
                    result);
            throw new RuntimeException(
                    String.format("调用美团确认订单失败!result={%s}", result));
        }
    }

    /**
     *
     * @Description 处理推送的已完成订单
     * @param baseBean
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void finishOrderPush(String shopId, @Valid NewOrderBean bean) {
        // 修改本地库中订单状态
        Order order = new Order(bean.getOrderId().toString());
        order.setStatus(OrderStatusEnum.get(bean.getStatus().toString()));
        jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
    }

    /**
     *
     * @Description 推送并保存新订单
     * @param orderPushBean
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void newOrderPush(String shopId, @Valid NewOrderBean newOrderBean) {
        // TODO 根据shopId获取门店是否设置了自动接单,若自动接单,则最后要调用美团接单接口
        boolean autoConfirm = MeituanConfig.isAutoConfirmOrder(shopId);
        List<NewOrderDetailBean> newOrderDetaiList = newOrderBean
                .getDetailList();
        PushNewOrder pushOrder = new PushNewOrder();
        List<PushNewOrderItem> pushOrderItemList = new ArrayList<PushNewOrderItem>();
        Order order = new Order();
        List<OrderItem> itemList = new ArrayList<OrderItem>();
        for (NewOrderDetailBean detail : newOrderDetaiList) {
            // 数据库entity
            BigDecimal price = new BigDecimal(detail.getPrice().toString());
            BigDecimal quantity = new BigDecimal(detail.getQuantity());
            BigDecimal boxPrice = new BigDecimal(
                    detail.getBox_price().toString());
            BigDecimal boxNum = new BigDecimal(detail.getBox_num().toString());
            BigDecimal totalAmount = price.multiply(quantity)
                    .add(boxPrice.multiply(boxNum));
            OrderItem item = new OrderItem();
            item.setFoodName(detail.getFood_name());
            item.setOrder(order);
            item.setPrice(price);
            item.setQuantity(quantity.intValue());
            item.setTotalAmount(totalAmount);
            itemList.add(item);
            // 推送到POS的bean
            PushNewOrderItem pushOrderItem = new PushNewOrderItem();
            pushOrderItem.setFoodName(detail.getFood_name());
            pushOrderItem.setPrice(detail.getPrice().toString());
            pushOrderItem.setQuantity(detail.getQuantity());
            pushOrderItem.setTotalAmount(totalAmount.toString());
            pushOrderItemList.add(pushOrderItem);
        }

        // 推送到POS的订单信息
        pushOrder.setAddress(newOrderBean.getRecipientAddress());
        pushOrder.setConsignee(newOrderBean.getRecipientName());
        pushOrder.setDaySn(newOrderBean.getDaySeq());
        pushOrder.setDescription(newOrderBean.getCaution());
        pushOrder.setHasInvoiced(newOrderBean.getHasInvoiced().toString());
        pushOrder.setInvoiceTitle(newOrderBean.getInvoiceTitle());
        pushOrder.setMealsNumber("");// 订餐次数,无此字段
        pushOrder.setOnlinePaid(PayTypeEnum.onlinePay(newOrderBean.getPayType())
                ? StatusEnum.ENABLE.getStatus().toString()
                : StatusEnum.DISENABLE.getStatus().toString());
        pushOrder.setOrderCreateTime(newOrderBean.getCtime().toString());
        pushOrder.setPhone(newOrderBean.getRecipientPhone());
        pushOrder.setOrderId(newOrderBean.getOrderId().toString());
        // 若自动下单,则推送的订单状态为商家已确认
        pushOrder.setStatus(
                autoConfirm ? OrderStatusEnum.mt_business_confirmed.getCode()
                        : OrderStatusEnum
                                .get(newOrderBean.getStatus().toString())
                                .getText());
        pushOrder.setTotalPrice(newOrderBean.getTotal().toString());
        pushOrder.setItemList(pushOrderItemList);
        String pushOrderJson = JsonFormatUtil.toJSONString(pushOrder);
        JSONObject pushResult = null;
        try {
            String registrationId = MeituanConfig.getRegistrationId(shopId);
            logger.info("推送信息 pushOrder={}", pushOrderJson);
            pushResult = takeawayClient.pushNewOrder(registrationId,
                    pushOrderJson);
        } catch (Exception e) {
            logger.error("pushSaveNewOrder 极光推送失败!pushOrder={}", pushOrder);
        }
        if (pushResult == null || !"0".equals(pushResult.getString("code"))) {
            logger.error("pushSaveNewOrder 极光推送失败!msg={},pushOrder={}",
                    pushResult == null ? "" : pushResult.getString("msg"),
                    pushOrder);
        }
        // 订单信息
        order.setOrderId(newOrderBean.getOrderId().toString());
        order.setAddress(newOrderBean.getRecipientAddress());
        order.setConsignee(newOrderBean.getRecipientName());
        order.setDaySn(Long.valueOf(newOrderBean.getDaySeq()));
        order.setDeliverFee(
                BigDecimal.valueOf(newOrderBean.getShippingFee() == null ? 0D
                        : newOrderBean.getShippingFee()));
        order.setDeliverTime(newOrderBean.getDeliveryTime().toString());
        order.setDescription(newOrderBean.getCaution());
        order.setHasInvoiced(StatusEnum.get(newOrderBean.getHasInvoiced()));
        order.setIncome(BigDecimal.valueOf(newOrderBean.getTotal()));// 商铺实收
        order.setInvoiceTitle(newOrderBean.getInvoiceTitle());
        order.setItemList(itemList);
        order.setLatitude(newOrderBean.getLatitude());
        order.setLongitude(newOrderBean.getLongitude());
        order.setOnlinePaid(PayTypeEnum.onlinePay(newOrderBean.getPayType())
                ? StatusEnum.ENABLE : StatusEnum.DISENABLE);
        order.setOrderCreateTime(newOrderBean.getCtime().toString());
        order.setOriginalPrice(
                BigDecimal.valueOf(newOrderBean.getOriginalPrice()));
        order.setPackageFee(BigDecimal.ZERO);// 美团没有餐盒费字段
        order.setPhone(newOrderBean.getRecipientPhone());
        order.setShop(new Shop(shopId));
        order.setStatus(
                OrderStatusEnum.get(newOrderBean.getStatus().toString()));
        order.setTotalPrice(BigDecimal.valueOf(newOrderBean.getTotal()));
        order.setUserId("");

        jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
        Long orderId = newOrderBean.getOrderId();
        // 若自动接单,则调用接单接口
        if (autoConfirm) {
            try {
                confirmOrder(shopId, orderId);
            } catch (Exception e) {
                logger.error("pushSaveNewOrder 自动接单失败,orderId={}", orderId, e);
                // TODO 异常补偿措施
            }
        }
    }

    /**
     *
     * @Description 同意退款
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void refundOrderConfirm(String shopId, Long orderId, String reason) {
        // 1.调用美团同意退款接口2.修改本地库中退款单状态3.修改本地库中订单状态
        // 根据shopId获取appAuthToken
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        CipCaterTakeoutOrderRefundAcceptRequest request = new CipCaterTakeoutOrderRefundAcceptRequest();
        request.setOrderId(orderId);
        request.setReason(reason);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            result = request.doRequest();
        } catch (Exception e) {
            logger.error("refundOrderConfirm error!orderId={},result={}",
                    orderId, result, e);
            throw new RuntimeException("调用美团确认退款失败!" + e.getMessage());
        }
        if (OK.equals(result)) {
            logger.info("refundOrderConfirm success!orderId={}", orderId);
            try {
                Order order = new Order();
                order.setOrderId(orderId.toString());
                order.setStatus(OrderStatusEnum.mt_cancelled);
                jpaClient.saveOrUpdateOrder(JSON.toJSONString(order));
            } catch (Exception e) {
                logger.error("修改订单状态失败!orderId={}", orderId, e);
                throw new RuntimeException(
                        "美团确认退款成功,修改本地订单状态失败!" + e.getMessage());
            }
            // TODO 调用jpa接口修改退款单状态
        } else {
            logger.info("refundOrderConfirm fail!orderId={},result={}", orderId,
                    result);
            throw new RuntimeException(
                    String.format("调用美团确认退款失败!result={%s}", result));
        }
    }

    /**
     *
     * @Description 订单退款推送消息处理
     * @param baseBean
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void refundOrderPush(String shopId, @Valid RefundOrderBean bean) {
        // 1.推送到POS
        // 2.保存到本地
        PushRefundOrder pushRefundOrder = new PushRefundOrder();
        pushRefundOrder.setOrderId(bean.getOrderId().toString());
        pushRefundOrder.setReason(bean.getReason());
        pushRefundOrder.setRefundStatus(bean.getNotifyType());
        pushRefundOrder.setShopId(shopId);
        pushRefundOrder.setUpdateTime(sdf.format(new Date()));
        // 推送
        JSONObject pushResult = null;
        try {
            logger.info("推送信息 refundOrderPush={}", pushRefundOrder);
            pushResult = takeawayClient.pushRefundOrder(
                    JsonFormatUtil.toJSONString(pushRefundOrder));
        } catch (Exception e) {
            logger.error("refundOrderPush 极光推送失败!pushOrder={}",
                    pushRefundOrder);
        }
        if (pushResult == null || !"0".equals(pushResult.getString("code"))) {
            logger.error("refundOrderPush 极光推送失败!msg={},pushOrder={}",
                    pushResult == null ? "" : pushResult.getString("msg"),
                    pushRefundOrder);
        }
        // TODO 1.查询本地是否已有此退单记录,若有则更新,若无则保存
    }

    /**
     *
     * @Description 拒绝退款
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void refundOrderRefuse(String shopId, Long orderId, String reason) {
        // 1.调用美团同意退款接口2.修改本地库中退款单状态3.修改本地库中订单状态
        // 根据shopId获取appAuthToken
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        CipCaterTakeoutOrderRefundRejectRequest request = new CipCaterTakeoutOrderRefundRejectRequest();
        request.setOrderId(orderId);
        request.setReason(reason);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            result = request.doRequest();
        } catch (Exception e) {
            logger.error("refundOrderRefuse error!orderId={},result={}",
                    orderId, result, e);
            throw new RuntimeException("调用美团拒绝退款失败!" + e.getMessage());
        }
        if (OK.equals(result)) {
            logger.info("refundOrderRefuse success!orderId={}", orderId);
            // TODO 修改退款单的状态为拒绝
        } else {
            logger.info("refundOrderRefuse fail!orderId={},result={}", orderId,
                    result);
            throw new RuntimeException(
                    String.format("调用美团拒绝退款失败!result={%s}", result));
        }
    }
}
