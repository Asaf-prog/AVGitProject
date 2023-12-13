package com.maven.test.avgitproject.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    @Pointcut("execution(* com.maven.test.avgitproject.controller.*.*(..))")
    private void forControllerPackage(){}

    @Before("execution(public * login*(..))")
    public void beforeLogin() {
        System.out.println("\n============> Login:");
    }

    @Before("forControllerPackage()")
    public void beforeAnyFunctionInThePackage() {
        System.out.println("\n============> Arrive:");
    }
    @Before("forControllerPackage()")
    public void beforeAnyFunctionInThePackageController() {
        System.out.println("\n============> Arrive -> controller:");
    }
}
