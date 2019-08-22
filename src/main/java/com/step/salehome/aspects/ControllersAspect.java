package com.step.salehome.aspects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllersAspect {
    private static Logger logger = LogManager.getLogger(PostServiceAspect.class);

    @Around("execution(* com.step.salehome.controller.*.*(..))")
    public Object getRecentlyPost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long currentTimeMilis = System.currentTimeMillis();
        logger.info(proceedingJoinPoint.getSignature().getDeclaringTypeName()+" controller's "+proceedingJoinPoint.getSignature().getName()+" started at " + currentTimeMilis);
        Object o = proceedingJoinPoint.proceed();
        logger.info(proceedingJoinPoint.getSignature().getDeclaringTypeName()+" controller's "+proceedingJoinPoint.getSignature().getName()+" ended " + (System.currentTimeMillis() - currentTimeMilis) + " milis late");
        return o;
    }

}
