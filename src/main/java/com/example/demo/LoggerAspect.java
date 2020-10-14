package com.example.demo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {


    @Before("execution(* com.example.demo.UserService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint);
    }

}
