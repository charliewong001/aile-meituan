package com.pay.aile.meituan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HystrixAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.pay.aile.meituan.client.hystrix..*.*(..))")
    public void hystrixLog() {
    }

    @Before("hystrixLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        logger.info("*****HystrixAspect start CLASS:{},METHOD:{},PARAMS:{}",
                joinPoint.getTarget().getClass().getName(),
                ms.getMethod().getName(), joinPoint.getArgs());

    }
}
