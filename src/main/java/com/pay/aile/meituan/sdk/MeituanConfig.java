package com.pay.aile.meituan.sdk;

import java.io.InputStream;
import java.util.Properties;

public class MeituanConfig {

    private static final String developerId;// 平台ID

    private static final String signKey;// 进行SHA1加签的key

    static {
        Properties properties = new Properties();
        try {
            InputStream in = MeituanConfig.class
                    .getResourceAsStream("/application.properties");
            properties.load(in);
            developerId = properties.getProperty("meituan_developerId");
            signKey = properties.getProperty("meituan_signKey");
        } catch (Exception e) {
            throw new RuntimeException("加载文件异常", e);
        }
    }

    /**
     *
     * @Description 获取appAuthToken
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static String getAppAuthToken(String shopId) {
        //        String appAuthToken = RedisUtil
        //                .get(Constants.mtRedisAuthTokenPrefix.concat(shopId));
        //        if (!StringUtils.hasText(appAuthToken)) {
        //            throw new IllegalArgumentException("门店授权码为空,请确认是否已绑定");
        //        }
        //        return appAuthToken;
        return "b95fd49fdf0fd424821a5ba39c707b895c1d5150ab9f1866f01d9d69f02638da";
    }

    public static String getDeveloperId() {
        return developerId;
    }

    /**
     *
     * @Description 获取设备号
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static String getDeviceNo(String shopId) {
        //        return RedisUtil.get(Constants.mtRedisDeviceNoPrefix.concat(shopId));
        return "device-meituan";
    }

    /**
     *
     * @Description 获取极光推送注册号
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static String getRegistrationId(String shopId) {
        //        return RedisUtil
        //                .get(Constants.mtRedisRegistrationidPrefix.concat(shopId));
        return "registration-meituan";
    }

    public static String getSignkey() {
        return signKey;
    }

    /**
     *
     * @Description 判断是否设置了自动接单
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static boolean isAutoConfirmOrder(String shopId) {
        //        return StringUtils.hasText(RedisUtil
        //                .get(Constants.mtRedisAutoConfirmOrderPrefix.concat(shopId)));
        return false;
    }

    /**
     *
     * @Description 删除appAuthToken
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeAppAuthToken(String shopId) {
        //        RedisUtil.remove(Constants.mtRedisAuthTokenPrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除美团店铺自动接单的设置
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeAutoConfirmOrder(String shopId) {
        //        RedisUtil
        //                .remove(Constants.mtRedisAutoConfirmOrderPrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除deviceNo
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeDeviceNo(String shopId) {
        //        RedisUtil.remove(Constants.mtRedisDeviceNoPrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除极光推送注册号
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeRegistrationId(String shopId) {
        //        RedisUtil.remove(Constants.mtRedisRegistrationidPrefix.concat(shopId));
    }

    /**
     *
     * @Description 保存appAuthToken
     * @param shopId
     * @param appAuthToken
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void setAppAuthToken(String shopId, String appAuthToken) {
        //        RedisUtil.set(Constants.mtRedisAuthTokenPrefix.concat(shopId),
        //                appAuthToken);
    }

    public static void setAutoConfirmOrder(String shopId) {
        //        RedisUtil.set(Constants.mtRedisAutoConfirmOrderPrefix.concat(shopId),
        //                "true");
    }

    /**
     *
     * @Description 保存设备号
     * @param shopId
     * @param deviceNo
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void setDeviceNo(String shopId, String deviceNo) {
        //        RedisUtil.set(Constants.mtRedisDeviceNoPrefix.concat(shopId), deviceNo);
    }

    /**
     *
     * @Description 保存极光推送注册号
     * @param shopId
     * @param registrationId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void setRegistrationId(String shopId, String registrationId) {
        //        RedisUtil.set(Constants.mtRedisRegistrationidPrefix.concat(shopId),
        //                registrationId);
    }

    private MeituanConfig() {
    }
}
