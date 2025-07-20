package com.sseemitter.sample.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.sseemitter.sample.controller..*)")
    public void controllerPackagePointcut() {
    }

    @Before("controllerPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments = {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "controllerPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "controllerPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with cause = {}", joinPoint.getSignature(), error.getCause() != null ? error.getCause() : "NULL");
    }
}