package com.pay.aile.meituan.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.platform.CancelOrderBean;
import com.pay.aile.meituan.bean.platform.NewOrderBean;
import com.pay.aile.meituan.bean.platform.NewOrderDetailBean;
import com.pay.aile.meituan.bean.platform.NewOrderExtraBean;
import com.pay.aile.meituan.bean.platform.RefundOrderBean;
import com.pay.aile.meituan.bean.platform.ShippingOrderBean;
import com.pay.aile.meituan.service.DispatchService;
import com.pay.aile.meituan.service.OrderService;

/**
 *
 * @Description: 美团回调
 * @see: MeituanNotifyController 此处填写需要参考的类
 * @version 2017年7月18日 下午3:59:01
 * @author chao.wang
 */
@RestController
@RequestMapping("/notify")
public class MeituanNotifyController {

    private static final String OK = "{\"data\":\"OK\"}";
    private static final String NOT_OK = "{\"data\":\"NOT OK\"}";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** 订单服务 */
    @Resource
    private OrderService orderService;
    /** 订单服务 */
    @Resource
    private DispatchService dispatchService;

    /**
     *
     * @Description 推送取消订单
     * @param developerId
     * @param ePoiId
     * @param sign
     * @param orderCancel
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "cancelOrder")
    public String cancelOrder(@RequestParam String developerId,
            @RequestParam String ePoiId, @RequestParam String sign,
            @RequestParam String orderCancel) {
        try {
            CancelOrderBean bean = JSONObject.parseObject(orderCancel,
                    CancelOrderBean.class);
            orderService.cancelOrderPush(ePoiId, bean);
            return OK;
        } catch (Exception e) {
            logger.error("cancelOrder error!已取消订单推送处理失败,data={}", orderCancel,
                    e);
            return NOT_OK;
        }
    }

    /**
     *
     * @Description 推送已完成订单
     * @param developerId
     * @param ePoiId
     * @param sign
     * @param order
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "finishOrder")
    public String finishOrder(@RequestParam String developerId,
            @RequestParam String ePoiId, @RequestParam String sign,
            @RequestParam String order) {
        try {
            NewOrderBean bean = JSONObject.parseObject(order,
                    NewOrderBean.class);
            orderService.finishOrderPush(ePoiId, bean);
            return OK;
        } catch (Exception e) {
            logger.error("finishOrder error!已完成订单推送处理失败,data={}", order, e);
            return NOT_OK;
        }
    }

    /**
     *
     * @Description 云端心跳
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/heartbeat")
    public String heartbeat() {
        return OK;
    }

    /**
     *
     * @Description 推送新订单
     * @param developerId
     * @param ePoiId
     * @param sign
     * @param order
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/newOrder")
    public String newOrder(@RequestParam String developerId,
            @RequestParam String ePoiId, @RequestParam String sign,
            String order) {
        try {
            NewOrderBean bean = JSONObject.parseObject(order,
                    NewOrderBean.class);
            List<NewOrderDetailBean> detailList = JSONArray
                    .parseArray(bean.getDetail(), NewOrderDetailBean.class);
            List<NewOrderExtraBean> extrasList = JSONArray
                    .parseArray(bean.getExtras(), NewOrderExtraBean.class);
            bean.setDetailList(detailList);
            bean.setExtrasList(extrasList);
            bean.setDetail(null);
            bean.setExtras(null);
            orderService.newOrderPush(ePoiId, bean);
            return OK;
        } catch (Exception e) {
            logger.error("newOrder error!新订单推送处理失败,data={}", order, e);
            return NOT_OK;
        }
    }

    /**
     *
     * @Description 推送退款订单
     * @param developerId
     * @param ePoiId
     * @param sign
     * @param orderRefund
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "refundOrder")
    public String refundOrder(@RequestParam String developerId,
            @RequestParam String ePoiId, @RequestParam String sign,
            @RequestParam String orderRefund) {
        try {
            RefundOrderBean bean = JSONObject.parseObject(orderRefund,
                    RefundOrderBean.class);
            orderService.refundOrderPush(ePoiId, bean);
            return OK;
        } catch (Exception e) {
            logger.error("refundOrder error!退款订单推送处理失败,data={}", orderRefund,
                    e);
            return NOT_OK;
        }
    }

    /**
     *
     * @Description 推送配送状态
     * @param developerId
     * @param ePoiId
     * @param sign
     * @param shippingStatus
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "shippingStatus")
    public String shippingStatus(@RequestParam String developerId,
            @RequestParam String ePoiId, @RequestParam String sign,
            @RequestParam String shippingStatus) {
        try {
            ShippingOrderBean bean = JSONObject.parseObject(shippingStatus,
                    ShippingOrderBean.class);
            dispatchService.dispatchStatusPush(ePoiId, bean);
            return OK;
        } catch (Exception e) {
            logger.error("shippingStatus error!推送配送状态处理失败,data={}",
                    shippingStatus, e);
            return NOT_OK;
        }
    }

}
