package com.stackroute.favouriteservice.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution (* com.stackroute.favouriteservice.controller.*.*(..))")
	public void beforelog(JoinPoint joinPoint) {
		logger.info("Inside Before Advice ......");
		logger.info("Target object method : "+ joinPoint.getSignature().getName());
	}

	@After("within(com.stackroute.favouriteservice.controller..*)")
	public void afterlog(JoinPoint joinPoint) {
		logger.info("Inside After Advice ......");
		logger.info("Target object method : "+ joinPoint.getSignature().getName());
	}

	@AfterReturning("within(com.stackroute.favouriteservice.controller..*)")
	public void afterreturninglog(JoinPoint joinPoint) {
		logger.info("Inside After Throwing Advice ......");
		logger.info("Target object method : "+ joinPoint.getSignature().getName());
	}
		
	@AfterThrowing("within(com.stackroute.favouriteservice.controller..*)")
	public void afterthrowinglog(JoinPoint joinPoint) {
		logger.info("Inside After Throwing Advice ......");
		logger.info("Target object method : "+ joinPoint.getSignature().getName());
	}
}
