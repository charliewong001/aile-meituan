package com.pay.aile.meituan.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Food;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.client.hystrix.JpaClientHystrix;

/**
 *
 * @Description: jpa服务接口
 * @see: OrderJapClient 此处填写需要参考的类
 * @version 2017年7月18日 上午9:37:17
 * @author chao.wang
 */
@FeignClient(value = "aile-takeaway-jpa", fallback = JpaClientHystrix.class)
public interface JpaClient {

    @RequestMapping(value = "/food/bathSaveOrUpdate")
    public JSONObject bathSaveOrUpdate(@RequestParam(value = "foodJson", defaultValue = "{}") String foodJson);

    @RequestMapping(value = "/food/findList")
    public List<Food> findLst(@RequestParam(value = "foodJson", defaultValue = "{}") String foodJson);

    @RequestMapping(value = "/order/findOrder")
    public Order findOrder(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    @RequestMapping(value = "/order/saveOrUpdateOrder")
    public JSONObject saveOrUpdateOrder(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    @RequestMapping(value = "/shop/saveOrUpdateShop")
    public JSONObject saveOrUpdateShop(@RequestParam(value = "shopJson", defaultValue = "{}") String shopJson);
}
