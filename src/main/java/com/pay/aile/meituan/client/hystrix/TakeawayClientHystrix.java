package com.pay.aile.meituan.client.hystrix;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.client.TakeawayClient;

/**
 *
 * @Description: 依赖服务降级
 * @see: TakeawayClientHystrix 此处填写需要参考的类
 * @version 2017年7月21日 下午4:03:36
 * @author chao.wang
 */
@Component
public class TakeawayClientHystrix implements TakeawayClient {

    @Override
    public JSONObject pushNewOrder(String orderJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject pushRefundOrder(String orderJson) {
        // TODO Auto-generated method stub
        return null;
    }

}
