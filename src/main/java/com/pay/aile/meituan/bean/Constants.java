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
    /** 极光推送注册号 redis缓存前缀 */
    public static final String mtRedisRegistrationidPrefix = "wm_shop_mt_";

    public static final String mtRedisAuthPhonePrefix = "aile-meituan-authPhone-";

    public static final String mtRedisAuthChannelPrefix = "aile-meituan-authChannel-";
    /** 美团返回正确 */
    public static final String mtOk = "{\"data\":\"ok\"}";
    /** 内网调用返回成功 */
    public static final String OK = "0000";
}
