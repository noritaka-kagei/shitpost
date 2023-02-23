package com.noritakakagei.sample.aop;

import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     */
    // @Before("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("===== Called Before Advice func. =====");
        outputDate();
        System.out.println(String.format("Method: %s", joinPoint.getSignature().getName()));
        System.out.println("======================================");
    }

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     */
    // @After("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("===== Called After Advice func. =====");
        outputDate();
        System.out.println(String.format("Method: %s", joinPoint.getSignature().getName()));
        System.out.println("======================================");
    }

    /**
     * Target Advice(method): com.noritakakagei.sample.di.*Greet.greeting(args)
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.noritakakagei.sample.di.*Greet.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===== Called Around Advice func. =====");
        outputDate();
        System.out.println("[BEFORE] executing advice(method).");
        Object result = joinPoint.proceed();
        System.out.println("[AFTER] executing advice(method).");
        return result;
    }

    /**
     * Common function for outputing executed date.
     */
    private void outputDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        System.out.println(String.format("Executed date: %s",formatter.format(date)));
    }
}
