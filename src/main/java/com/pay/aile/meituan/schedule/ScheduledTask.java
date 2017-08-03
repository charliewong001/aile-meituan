package com.pay.aile.meituan.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.aile.meituan.service.HeartbeatService;
import com.pay.aile.meituan.service.OrderService;

/**
 *
 * @Description: 定时任务
 * @see: ScheduledTask 此处填写需要参考的类
 * @version 2017年7月24日 下午5:33:14
 * @author chao.wang
 */
@Component
public class ScheduledTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private HeartbeatService heartbeatService;
    @Resource
    private OrderService orderService;

    /**
     *
     * @Description 异常订单处理
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    // @Scheduled(cron = "0 0,30 * * * ?")
    public void exceptionOrderProcess() {
        logger.info("exceptionOrderProcess 异常订单处理 start...");
        try {
            orderService.exceptionOrderSync();
        } catch (Exception e) {
            logger.error("exceptionOrderProcess 异常订单处理 error!", e);
        }
        logger.info("exceptionOrderProcess 异常订单处理  end...");
    }

    /**
     *
     * @Description 每24小时上报心跳数据
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    // @Scheduled(cron = "0 0 1 * * ?")
    public void reportToMeituanEvery24Hours() {
        logger.info("reportToMeituanEvery24Hours 24小时补充数据上报 start...");
        try {
            heartbeatService.oneDayReport();
        } catch (Exception e) {
            logger.error("reportToMeituanEvery24Hours 24小时补充数据上报 error!", e);
        }
        logger.info("reportToMeituanEvery24Hours 24小时补充数据上报 end...");
    }
}
