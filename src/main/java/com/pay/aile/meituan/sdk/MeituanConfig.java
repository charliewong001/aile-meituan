package com.pay.aile.meituan.sdk;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.StringUtils;

import com.pay.aile.meituan.bean.Constants;
import com.pay.aile.meituan.util.JedisClusterUtils;

public class MeituanConfig {

    private static final String developerId;// 平台ID

    private static final String signKey;// 进行SHA1加签的key
    private static final int oneDaySec = 24 * 60 * 60;

    static {
        Properties properties = new Properties();
        try {
            InputStream in = MeituanConfig.class.getResourceAsStream("/application.properties");
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
        String appAuthToken = JedisClusterUtils.getString(Constants.mtRedisAuthTokenPrefix.concat(shopId));
        if (!StringUtils.hasText(appAuthToken)) {
            throw new IllegalArgumentException("门店授权码为空,请确认是否已绑定");
        }
        return appAuthToken;
    }

    /**
     *
     * @Description 获取channel
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static String getChannel(String shopId) {
        return JedisClusterUtils.getString(Constants.mtRedisAuthChannelPrefix.concat(shopId));
    }

    public static String getDeveloperId() {
        return developerId;
    }

    /**
     *
     * @Description 获取电话号码
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static String getPhone(String shopId) {
        return JedisClusterUtils.getString(Constants.mtRedisAuthPhonePrefix.concat(shopId));
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
        Object o = JedisClusterUtils.hashGet(Constants.mtRedisRegistrationidPrefix, shopId);
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    public static String getSignkey() {
        return signKey;
    }

    /**
     *
     * @Description 删除appAuthToken
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeAppAuthToken(String shopId) {
        JedisClusterUtils.delKey(Constants.mtRedisAuthTokenPrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除channel
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeChannel(String shopId) {
        JedisClusterUtils.delKey(Constants.mtRedisAuthChannelPrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除phone
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removePhone(String shopId) {
        JedisClusterUtils.delKey(Constants.mtRedisAuthPhonePrefix.concat(shopId));
    }

    /**
     *
     * @Description 删除极光推送注册号
     * @param shopId
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void removeRegistrationId(String shopId) {
        JedisClusterUtils.hashDel(Constants.mtRedisRegistrationidPrefix, shopId);
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
        JedisClusterUtils.saveString(Constants.mtRedisAuthTokenPrefix.concat(shopId), appAuthToken);
    }

    /**
     *
     * @Description 设置channel
     * @param shopId
     * @param phone
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void setChannel(String shopId, String channel) {
        JedisClusterUtils.saveString(Constants.mtRedisAuthChannelPrefix.concat(shopId), channel, oneDaySec);
    }

    /**
     *
     * @Description 设置电话号码
     * @param shopId
     * @param phone
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public static void setPhone(String shopId, String phone) {
        JedisClusterUtils.saveString(Constants.mtRedisAuthPhonePrefix.concat(shopId), phone, oneDaySec);
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
        JedisClusterUtils.hashSet(Constants.mtRedisRegistrationidPrefix, shopId, registrationId);
    }
}
