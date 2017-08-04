package com.pay.aile.meituan.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.client.hystrix.TakeawayClientHystrix;

/**
 *
 * @Description: takeaway接口
 * @see: TakeawayClient 此处填写需要参考的类
 * @version 2017年7月21日 下午3:03:30
 * @author chao.wang
 */
@FeignClient(value = "takeaway", fallback = TakeawayClientHystrix.class)
public interface TakeawayClient {

    /**
     *
     * @Description
     * @param registrationId
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/shop/pushAuthorization")
    public JSONObject pushAuthorization(@RequestParam(value = "shopJson", defaultValue = "{}") String shopJson);

    /**
     *
     * @Description 推送配送状态
     * @param registrationId
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/pushDistribution")
    public JSONObject pushDistribution(@RequestParam(value = "registrationId", defaultValue = "") String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 新订单推送
     * @param platformCode
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/pushNewOrder")
    public JSONObject pushNewOrder(@RequestParam(value = "registrationId", defaultValue = "") String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 推送取消订单
     * @param registrationId
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/pushOrderCancel")
    public JSONObject pushOrderCancel(@RequestParam(value = "registrationId", defaultValue = "") String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 推送订单状态变化
     * @param registrationId
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/pushOrderChange")
    public JSONObject pushOrderChange(@RequestParam(value = "registrationId", defaultValue = "") String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 推送退款订单
     * @param registrationId
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/pushRefundOrder")
    public JSONObject pushRefundOrder(@RequestParam(value = "registrationId", defaultValue = "") String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

}
