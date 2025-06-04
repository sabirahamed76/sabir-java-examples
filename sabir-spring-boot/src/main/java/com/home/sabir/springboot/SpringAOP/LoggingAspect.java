package com.home.sabir.springboot.SpringAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.home.sabir.springboot.SpringAOP.*.*(..))") 
	public static void serviceMethods() {
		System.out.println("Pointcut Started...");		
	} 

	@Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        serviceMethods();
        System.out.println("Method execution started...");
        System.out.println("Executing: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.home.sabir.springboot.SpringAOP.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Method executed successfully. Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.home.sabir.springboot.SpringAOP.*.*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        System.out.println("Exception occurred: " + ex.getMessage());
    }

    @Around("execution(* com.home.sabir.springboot.SpringAOP.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before method execution...");
        Object result = joinPoint.proceed();
        System.out.println("After method execution...");
        return result;
    }
}