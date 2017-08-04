package com.pay.aile.meituan.client.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Food;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.client.JpaClient;

@Component
public class JpaClientHystrix implements JpaClient {

    @Override
    public JSONObject bathSaveOrUpdate(String foodJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject bathUpdateOrderStatus(String orderList) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Shop> findList(String shopJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Food> findLst(String foodJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order findOrder(String orderJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findValidList(String platformCode, String shopId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject saveOrUpdate(String foodJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject saveOrUpdateDistribution(String distributionJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject saveOrUpdateOrder(String orderJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject saveOrUpdateRefundOrder(String refundOrderJson) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONObject saveOrUpdateShop(String shopJson) {
        // TODO Auto-generated method stub
        return null;
    }

}
