package com.pay.aile.meituan.bean;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: Constants 此处填写需要参考的类
 * @version 2017年7月14日 下午1:57:11
 * @author chao.wang
 */
public class Constants {

    /** 美团门店authToken redis缓存前缀 */
    public static final String mtRedisAuthTokenPrefix = "aile-meituan-authToken-";

    /** 美团商家设备号 redis缓存前缀 */
    public static final String mtRedisDeviceNoPrefix = "aile-meituan-deviceNo-";

    /** 美团店铺自动接单 redis缓存前缀 */
    public static final String mtRedisAutoConfirmOrderPrefix = "aile-meituan-autoConfirmOrder-";

    /** 极光推送注册号 redis缓存前缀 */
    public static final String mtRedisRegistrationidPrefix = "aile-meituan-registrationId-";

    /** 美团返回正确 */
    public static final String ok = "{\"data\":\"ok\"}";
}
