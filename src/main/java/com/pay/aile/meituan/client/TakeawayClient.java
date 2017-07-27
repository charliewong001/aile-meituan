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
@FeignClient(value = "aile-takeaway", fallback = TakeawayClientHystrix.class)
public interface TakeawayClient {

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
    public JSONObject pushNewOrder(@RequestParam String registrationId,
            @RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    @RequestMapping(value = "/pushOrderChange")
    public JSONObject pushOrderChange(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    @RequestMapping(value = "/pushRefundOrder")
    public JSONObject pushRefundOrder(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);
}
