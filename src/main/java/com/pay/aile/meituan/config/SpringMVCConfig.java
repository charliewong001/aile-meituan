package com.pay.aile.meituan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pay.aile.meituan.interceptor.NotifyValidateInterceptor;

/**
 *
 * @Description: Spring MVC配置
 * @see: SpringMVCConfig 此处填写需要参考的类
 * @version 2017年7月21日 下午2:26:38
 * @author chao.wang
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NotifyValidateInterceptor()).addPathPatterns("/notify/*");
    }

}
