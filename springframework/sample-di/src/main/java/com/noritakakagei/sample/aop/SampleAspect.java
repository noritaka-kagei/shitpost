package com.noritakakagei.sample.aop;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Implemented AOP class
 */
@Aspect
@Component
public class SampleAspect {
    private static final Logger logger = Logger.getLogger(SampleAspect.class.getName());

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     */
    // @Before("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.log(Level.INFO, "===== Called Before Advice func. =====");
        outputDate();
        logger.log(Level.INFO, String.format("Method: %s", joinPoint.getSignature().getName()));
        logger.log(Level.INFO, "======================================");
    }

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     */
    // @After("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.log(Level.INFO, "===== Called After Advice func. =====");
        outputDate();
        logger.log(Level.INFO, String.format("Method: %s", joinPoint.getSignature().getName()));
        logger.log(Level.INFO, "======================================");
    }

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.log(Level.INFO, "===== Called Around Advice func. =====");
        outputDate();
        logger.log(Level.INFO, "[BEFORE] executing advice(method).");
        Object result = joinPoint.proceed();
        logger.log(Level.INFO, "[AFTER] executing advice(method).");
        return result;
    }

    /**
     * Common function for outputing executed date.
     */
    private void outputDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        logger.log(Level.INFO, String.format("Executed date: %s",formatter.format(date)));
    }
}
