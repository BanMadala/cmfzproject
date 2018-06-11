package com.baizhi.bpf.ajsept;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

//声明该类为一个java配置类,并且为spring的通知类
@Configuration
@Aspect
public class MyAsjept {
    //切入点
    @Pointcut(value = "execution(* com.baizhi.bpf.service.*Impl.*(..))")
    public void MyPointCut(){

    }
    //前置通知
    @Before(value = "MyPointCut()")
    public void MyBefore(JoinPoint jp){
        System.out.println("这是个一个前置通知");
    }
    //后置通知
    @After(value = "MyPointCut()")
    public void myAfter(JoinPoint jp){
        System.out.println("这是一个后置通知");
    }
    //环绕通知
    @Around(value = "MyPointCut()")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        Object target = pjp.getTarget();
        System.out.println("这是一个环绕通知");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        System.out.println(methodName);

        return pjp.proceed();

    }



}
