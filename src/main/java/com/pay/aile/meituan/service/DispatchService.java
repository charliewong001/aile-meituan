package com.pay.aile.meituan.service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.pay.aile.meituan.bean.jpa.Distribution;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.platform.ShippingOrderBean;
import com.pay.aile.meituan.client.JpaClient;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: DispatchService 此处填写需要参考的类
 * @version 2017年7月20日 上午10:53:22
 * @author chao.wang
 */
@Service
public class DispatchService {

    @Resource
    private JpaClient jpaClient;

    public void dispatchStatusPush(String ePoiId, @Valid ShippingOrderBean bean) {

        Distribution distribution = null;
        // TODO 首先根据订单号查询有没有配送单记录,若有则修改订单,若没有则保存订单
        boolean first = true;
        if (first) {
            distribution = new Distribution();
        }
        distribution.setDistributionName(bean.getDispatcherName());
        distribution.setPhone(bean.getDispatcherMobile());
        distribution.setUpdateAt(bean.getTime());
        distribution.setOrder(new Order(bean.getOrderId().toString()));
        distribution.setShop(new Shop(ePoiId));
        // TODO 调用jpa接口保存或修改
    }
}
