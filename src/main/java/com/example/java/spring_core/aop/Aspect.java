package com.example.java.spring_core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Before("execution(* com.example.java.spring_core.aop.ServiceAspect.methodAspect(..))")
    public void before(JoinPoint joinPoint) {
        log.info("before của aspect được gọi với jointPoint: {}", joinPoint.toString());
    }

    @After("execution(* com.example.java.spring_core.aop.ServiceAspect.methodAspect(..))")
    public void after(JoinPoint joinPoint) {
        log.info("after của aspect được gọi với jointPoint: {}", joinPoint.toString());
    }

    @AfterReturning("execution(* com.example.java.spring_core.aop.ServiceAspect.methodAspect(..))")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning của aspect được gọi với jointPoint: {}", joinPoint.toString());
    }

    @AfterThrowing("execution(* com.example.java.spring_core.aop.ServiceAspect.methodAspect(..))")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing của aspect được gọi với jointPoint: {}", joinPoint.toString());
    }

    @Around("execution(* com.example.java.spring_core.aop.ServiceAspect.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around, trước khi proceed với jointPoint : {}", joinPoint.toString());

        joinPoint.proceed();

        log.info("around, sau khi proceed với jointPoint : {}", joinPoint.toString());

    }
}
