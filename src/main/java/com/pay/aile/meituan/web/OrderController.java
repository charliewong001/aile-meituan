package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @Description 确认订单
     * @param platformId
     * @param shopId
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/acceptOrder")
    public JSONObject acceptOrder(@RequestParam String shopId, @RequestParam String orderId) {
        try {
            orderService.confirmOrder(shopId, Long.valueOf(orderId));
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("acceptOrder error!shopId={},orderId={}", shopId, orderId, e);
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
    @RequestMapping(value = "/acceptRefunds")
    public JSONObject acceptRefunds(@RequestParam String shopId, @RequestParam String orderId,
            @RequestParam String reason) {
        try {
            orderService.refundOrderConfirm(shopId, Long.valueOf(orderId), reason);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("acceptRefunds error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 商户取消订单
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public JSONObject cancelOrder(@RequestParam String shopId, @RequestParam String orderId,
            @RequestParam String reasonCode, @RequestParam(name = "reasonCode", required = false) String reason) {
        try {
            orderService.cancelOrder(shopId, Long.valueOf(orderId), reasonCode, reason);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("cancelOrder error!shopId={},orderId={}", shopId, orderId, e);
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
    @RequestMapping(value = "/refuseRefunds")
    public JSONObject refuseRefunds(@RequestParam String shopId, @RequestParam String orderId,
            @RequestParam String reason) {
        try {
            orderService.refundOrderRefuse(shopId, Long.valueOf(orderId), reason);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("refundOrderRefuse error!shopId={},orderId={}", shopId, orderId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }
}
