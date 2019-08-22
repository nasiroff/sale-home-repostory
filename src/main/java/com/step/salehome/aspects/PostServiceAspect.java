package com.step.salehome.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostServiceAspect {
    private static Logger logger = LogManager.getLogger(PostServiceAspect.class);

    @Around("execution(* com.step.salehome.service.PostService.getRecentlyPost(..))")
    public Object getRecentlyPost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Sen bele deyildin qabaqlar");
        Object o = proceedingJoinPoint.proceed();
        logger.info("YAdindan cixibmi o caglar?");
        return o;
    }
}