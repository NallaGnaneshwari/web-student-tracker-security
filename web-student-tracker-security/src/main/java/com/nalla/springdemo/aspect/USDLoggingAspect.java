package com.nalla.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class USDLoggingAspect {

	//set the logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	//setup pointcut declarations
	@Pointcut("execution(* com.nalla.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}

	@Pointcut("execution(* com.nalla.springdemo.service.*.*(..))")
	private void forServicePackage() {}

	@Pointcut("execution(* com.nalla.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}


	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}


	//add @Before advice
	@Before("forAppFlow()")
	public void befire(JoinPoint theJoinPoint) {

		//display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("======>> in @Before: calling method: " + theMethod);

		//display the arguments to the method - get the arguments & loop thru and display the args
		Object[] args = theJoinPoint.getArgs();

		for(Object tempArg: args) {
			myLogger.info("======>> argument: " + tempArg);
		}


	}

	//add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

		//display the method name that we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("======>> in @AfterReturning: from method: " + theMethod);

		myLogger.info("======>> result: "+theResult);
	}
}
