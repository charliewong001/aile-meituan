package com.pay.aile.meituan.interceptor;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pay.aile.meituan.sdk.MeituanConfig;

/**
 *
 * @Description: 美团回调接口签名验证和心跳支持
 * @see: NotifyValidateInterceptor 此处填写需要参考的类
 * @version 2017年7月21日 下午2:19:55
 * @author chao.wang
 */
public class NotifyValidateInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        logger.info("meituan notify request, url:{}", uri);
        Map<String, String[]> params = request.getParameterMap();
        // 若无值,即为心跳检测
        if (params == null || params.isEmpty()) {
            logger.info("params is empty,heartbeat");
            response.setStatus(200);
            response.getWriter().print("{\"data\":\"OK\"}");
            return false;
        }
        String sign = createSign(params);
        String requestSign = request.getParameter("sign");
        if (StringUtils.hasText(sign) && StringUtils.hasText(requestSign) && sign.equals(requestSign)) {
            logger.info("meituan notify request url:{} 验证签名通过!", uri);
            return true;
        } else {
            logger.error("meituan notify request url:{},验证签名失败!", uri);
            response.setStatus(401);
            response.getWriter().print("{\"data\":\"SIGN FAIL\"}");
            return false;
        }
    }

    /**
     * 根据secretKey和参数列表生成sign
     *
     * @param secret
     *            secretKey
     * @param params
     *            参数列表
     */
    private String createSign(Map<String, String[]> params) {
        // 自然排序
        Set<String> sortedParams = new TreeSet<String>();
        sortedParams.addAll(params.keySet());

        StringBuilder strB = new StringBuilder();
        // 排除sign和空值参数
        for (String key : sortedParams) {
            if (key.equalsIgnoreCase("sign")) {
                continue;
            }
            String[] valueList = params.get(key);
            if (valueList != null && valueList.length > 0) {
                String value = valueList[0];
                if (value != null && !value.isEmpty()) {
                    strB.append(key).append(value);
                }
            }
        }

        String source = MeituanConfig.getSignkey() + strB.toString();
        return createSign(source);
    }

    /**
     * 生成新sign
     *
     * @param str
     *            字符串
     * @return String
     */
    private String createSign(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

}
