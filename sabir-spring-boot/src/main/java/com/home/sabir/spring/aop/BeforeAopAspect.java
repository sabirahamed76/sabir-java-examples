package com.home.sabir.spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class BeforeAopAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.home.sabir.springboot.AOP.*(..)) ")
    private void anyStudentService() {
    	LOGGER.info("pointcut=====");
    } // the pointcut signature
 
    @Before(value = "execution(* com.home.sabir.springboot.AOP.*.*(..))")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("before execution of {}", joinPoint);
    }
}