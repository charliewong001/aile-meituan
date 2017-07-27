package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.service.OrderService;
import com.pay.aile.meituan.util.JsonFormatUtil;

/**
 *
 * @Description: 订单接口
 * @see: OrderController 此处填写需要参考的类
 * @version 2017年7月11日 下午3:46:10
 * @author chao.wang
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /** 本地order服务 */
    @Resource
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     *
     * @Description 商户取消订单
     * @param platformId
     * @param shopId
     * @param orderId
     * @param reason
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/cancelOrder")
    public String cancelOrder(String shopId, Long orderId, String reasonCode, String reason) {
        try {
            orderService.cancelOrder(shopId, orderId, reasonCode, reason);
            return JsonFormatUtil.getSuccessJson().toJSONString();
        } catch (Exception e) {
            logger.error("cancelOrder error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson().toJSONString();
        }
    }

    /**
     *
     * @Description 确认订单
     * @param platformId
     * @param shopId
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/confirmOrder")
    public JSONObject confirmOrder(String shopId, Long orderId) {
        try {
            orderService.confirmOrder(shopId, orderId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("confirmOrder error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 退款确认
     * @param shopId
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/refundOrderConfirm")
    public JSONObject refundOrderConfirm(String shopId, Long orderId, String reason) {
        try {
            orderService.refundOrderConfirm(shopId, orderId, reason);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("refundOrderConfirm error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 退款拒绝
     * @param shopId
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/refundOrderRefuse")
    public JSONObject refundOrderRefuse(String shopId, Long orderId, String reason) {
        try {
            orderService.refundOrderRefuse(shopId, orderId, reason);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("refundOrderRefuse error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }
}
