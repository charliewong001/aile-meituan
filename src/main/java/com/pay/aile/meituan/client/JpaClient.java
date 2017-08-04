package com.pay.aile.meituan.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Food;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.client.hystrix.JpaClientHystrix;

/**
 *
 * @Description: jpa服务接口
 * @see: OrderJapClient 此处填写需要参考的类
 * @version 2017年7月18日 上午9:37:17
 * @author chao.wang
 */
@FeignClient(value = "jpa-client", fallback = JpaClientHystrix.class)
public interface JpaClient {

    /**
     *
     * @Description 批量保存菜品
     * @param foodJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/food/bathSaveOrUpdate")
    public JSONObject bathSaveOrUpdate(@RequestParam(value = "foodJson", defaultValue = "{}") String foodJson);

    /**
     *
     * @Description 批量修改订单状态
     * @param orderList
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/bathUpdateOrderStatus")
    public JSONObject bathUpdateOrderStatus(@RequestParam(value = "orderList", defaultValue = "{}") String orderList);

    /**
     *
     * @Description 查询店铺
     * @param shopJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/shop/findList")
    public List<Shop> findList(@RequestParam(value = "shopJson", defaultValue = "{}") String shopJson);

    /**
     *
     * @Description 查询菜品
     * @param foodJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/food/findList")
    public List<Food> findLst(@RequestParam(value = "foodJson", defaultValue = "{}") String foodJson);

    /**
     *
     * @Description 查询订单
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/findOrder")
    public Order findOrder(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 查询未完结订单列表
     * @param platformCode
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/findValidList")
    public List<Order> findValidList(@RequestParam(value = "platformCode") String platformCode,
            @RequestParam(value = "shopId") String shopId);

    /**
     *
     * @Description 保存菜品
     * @param foodJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/food/saveOrUpdate")
    public JSONObject saveOrUpdate(@RequestParam(value = "foodJson", defaultValue = "{}") String foodJson);

    /**
     *
     * @Description 保存配送信息
     * @param distributionJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/saveOrUpdateDistribution")
    public JSONObject saveOrUpdateDistribution(
            @RequestParam(value = "distributionJson", defaultValue = "{}") String distributionJson);

    /**
     *
     * @Description 保存订单信息
     * @param orderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/saveOrUpdateOrder")
    public JSONObject saveOrUpdateOrder(@RequestParam(value = "orderJson", defaultValue = "{}") String orderJson);

    /**
     *
     * @Description 保存退款信息
     * @param refundOrderJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/order/saveOrUpdateRefundOrder")
    public JSONObject saveOrUpdateRefundOrder(
            @RequestParam(value = "refundOrderJson", defaultValue = "{}") String refundOrderJson);

    /**
     *
     * @Description 保存店铺信息
     * @param shopJson
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/shop/saveOrUpdateShop")
    public JSONObject saveOrUpdateShop(@RequestParam(value = "shopJson", defaultValue = "{}") String shopJson);
}
