package com.pay.aile.meituan.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

@Component("jpushUtil")
public class JPushUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jpush_master_secret}")
    private String masterSecret;
    @Value("${jpush_app_key}")
    private String appKey;

    private JPushClient jpush;

    public boolean sendAndroid(String msg, String title, Map<String, String> extras, String... deviceToken) {
        setClient();
        try {
            // 调用Jpush的sendAndroidNotificationWithRegistrationID进行定向消息推送
            PushResult result = jpush.sendAndroidNotificationWithRegistrationID(title, msg, extras, deviceToken);
            logger.info("JPush sendAndroid result : {} ", result);
            return result.isResultOK();
        } catch (APIConnectionException e) {
            logger.error("sendAndroid connect error", e);
        } catch (APIRequestException e) {
            logger.error("sendAndroid request error", e);
        }
        return false;
    }

    private void setClient() {
        if (jpush == null) {
            jpush = new JPushClient(masterSecret, appKey);
        }
    }
}
