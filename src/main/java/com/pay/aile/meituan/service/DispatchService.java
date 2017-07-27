package com.pay.aile.meituan.service;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.jpa.Distribution;
import com.pay.aile.meituan.bean.jpa.DistributionStatusEnum;
import com.pay.aile.meituan.bean.jpa.Order;
import com.pay.aile.meituan.bean.jpa.Platform;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.platform.ShippingOrderBean;
import com.pay.aile.meituan.bean.platform.ShippingStatusEnum;
import com.pay.aile.meituan.bean.push.PushOrderShipping;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.client.TakeawayClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: DispatchService 此处填写需要参考的类
 * @version 2017年7月20日 上午10:53:22
 * @author chao.wang
 */
@Service
public class DispatchService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JpaClient jpaClient;
    @Resource
    private TakeawayClient takeawayClient;

    public void dispatchStatusPush(String shopId, @Valid ShippingOrderBean bean) {

        long updateTime = System.currentTimeMillis();
        PushOrderShipping pushShipping = new PushOrderShipping();
        pushShipping.setName(bean.getDispatcherName());
        pushShipping.setPhone(bean.getDispatcherMobile());
        pushShipping.setOrderId(bean.getOrderId().toString());
        pushShipping.setUpdateTime(updateTime);

        Distribution distribution = new Distribution();
        distribution.setDistributionName(bean.getDispatcherName());
        distribution.setPhone(bean.getDispatcherMobile());
        distribution.setUpdateAt(bean.getTime());
        distribution.setOrder(new Order(bean.getOrderId().toString()));
        distribution.setShop(new Shop(shopId, Platform.getInstance()));
        distribution.setUpdateAt(updateTime);
        switch (ShippingStatusEnum.get(bean.getShippingStatus())) {
        case sended:// 配送单发往配送
            pushShipping.setDistributionStatus(DistributionStatusEnum.tobeAssigned.getCode());
            distribution.setStatus(DistributionStatusEnum.tobeAssigned);
            break;
        case shipperConfirm:// 配送单已确认(骑手接单)
            pushShipping.setDistributionStatus(DistributionStatusEnum.tobeFetched.getCode());
            distribution.setStatus(DistributionStatusEnum.tobeFetched);
            break;
        case shipperGet:// 骑手已取餐
            pushShipping.setDistributionStatus(DistributionStatusEnum.arrived.getCode());
            distribution.setStatus(DistributionStatusEnum.arrived);
            break;
        case shipperArrive:// 骑手已送达
            pushShipping.setDistributionStatus(DistributionStatusEnum.completed.getCode());
            distribution.setStatus(DistributionStatusEnum.completed);
            break;
        case cancel:// 配送单已取消
            pushShipping.setDistributionStatus(DistributionStatusEnum.cancelled.getCode());
            distribution.setStatus(DistributionStatusEnum.cancelled);
            break;
        default:
            break;

        }
        JSONObject pushResult = null;
        try {
            logger.info("dispatchStatusPush 推送的消息={}", pushShipping);
            pushResult = takeawayClient.pushOrderCancel(MeituanConfig.getRegistrationId(shopId),
                    JsonFormatUtil.toJSONString(pushShipping));
            logger.info("dispatchStatusPush 推送结果={}", pushResult);
        } catch (Exception e) {
            logger.error("dispatchStatusPush 处理美团推送的配送单状态变更失败！orderId={}", bean.getOrderId());
        }
        if (pushResult == null || !"0".equals(pushResult.getString("code"))) {
            logger.error("dispatchStatusPush 极光推送失败!msg={},pushBean={}",
                    pushResult == null ? "" : pushResult.getString("msg"), bean);
        }

        try {

            // TODO 调用jpa接口保存或修改
        } catch (Exception e) {
            logger.error("dispatchStatusPush 保存或修改配送单失败!,bean={}", bean, e);
            throw new RuntimeException("保存配送单失败!");
        }

    }
}
