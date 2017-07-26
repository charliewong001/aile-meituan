package com.pay.aile.meituan.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.pay.aile.meituan.web..*.*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        logger.info("*****logAspect start CLASS:{},METHOD:{},PARAMS:{}",
                joinPoint.getTarget().getClass().getName(),
                ms.getMethod().getName(), joinPoint.getArgs());
        Object ret = joinPoint.proceed(joinPoint.getArgs());
        logger.info(
                "*****logAspect end CLASS:{},METHOD:{},RETURN:{},USETIME:{}ms",
                joinPoint.getTarget().getClass().getName(),
                ms.getMethod().getName(), ret,
                System.currentTimeMillis() - start);
        return ret;
    }
}
