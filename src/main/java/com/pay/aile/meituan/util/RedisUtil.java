package com.pay.aile.meituan.util;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private static final Charset charset = Charset.forName("UTF-8");
    private static JedisSentinelPool jedisSentinelPool;

    public static String get(String key) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            String result = jedis.get(key);
            return result;
        } catch (Exception e) {
            log.error("getValue error " + key, e);
            return "";
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> clazz) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            return (T) JDKSerializeUtil.deserialize(jedis.get(key.getBytes(charset)));
        } catch (Exception e) {
            log.error("getValue error " + key, e);
            return null;
        }
    }

    public static void remove(String key) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            log.error("removeValue error!key={}", key, e);
        }
    }

    public static void set(String key, Object value) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            jedis.set(key.getBytes(charset), JDKSerializeUtil.serialize(value));
        } catch (Exception e) {
            log.error("getValue error " + key, e);
        }
    }

    public static void set(String key, String value) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            jedis.set(key, value);
        } catch (Exception e) {
            log.error("setValue error " + key, e);
        }
    }

    public static void setEx(String key, Object value, int seconds) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            jedis.setex(key.getBytes(charset), seconds, JDKSerializeUtil.serialize(value));
        } catch (Exception e) {
            log.error("getValue error " + key, e);
        }
    }

    public static void setEx(String key, String value, int seconds) {
        Assert.hasText(key, "key不能为空");
        try (Jedis jedis = getJedisPool().getResource()) {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("setValue error " + key, e);
        }
    }

    private static JedisSentinelPool getJedisPool() {
        if (jedisSentinelPool == null) {
            jedisSentinelPool = SpringContextUtil.getBean(JedisSentinelPool.class);
        }
        return jedisSentinelPool;
    }
}
