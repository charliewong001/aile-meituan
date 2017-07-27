package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.service.HeartbeatService;
import com.pay.aile.meituan.util.JsonFormatUtil;

/**
 *
 * @Description: 心跳检测接口
 * @see: HeartbeatController 此处填写需要参考的类
 * @version 2017年7月20日 下午4:37:46
 * @author chao.wang
 */
@RestController
@RequestMapping(value = "/heartbeat")
public class HeartbeatController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /** 心跳上报服务 */
    @Resource
    private HeartbeatService heartbeatService;

    /**
     *
     * @Description 30s心跳上报
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping(value = "/halfSecReport")
    public JSONObject halfSecReport(String[] shopIds) {
        try {
            heartbeatService.halfSecReport(shopIds);
            return JsonFormatUtil.getFailureJson();
        } catch (Exception e) {
            logger.error("halfSecReport error!", e);
            return JsonFormatUtil.getFailureJson();
        }
    }
}
