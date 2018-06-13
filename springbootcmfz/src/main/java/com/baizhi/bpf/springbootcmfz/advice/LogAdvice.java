package com.baizhi.bpf.springbootcmfz.advice;


import com.baizhi.bpf.springbootcmfz.commmon.LogAnnotation;
import com.baizhi.bpf.springbootcmfz.dao.LogDAO;
import com.baizhi.bpf.springbootcmfz.entity.Log;
import com.baizhi.bpf.springbootcmfz.entity.Manager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Aspect
public class LogAdvice {
    @Autowired
    private LogDAO logDAO;

    public LogDAO getLogDAO() {
        return logDAO;
    }

    public void setLogDAO(LogDAO logDAO) {
        this.logDAO = logDAO;
    }
    //    @Pointcut(value = "@annotation(com.baizhi.bpf.springbootcmfz.commmon.LogAnnotation)")
//    public void pc1forLogAdvice(){
//
//    }

    @Around(value = "@annotation(com.baizhi.bpf.springbootcmfz.commmon.LogAnnotation)")
    public Object getMylogAdvice(ProceedingJoinPoint pjp) throws Throwable {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
//        LogDAO logDAO = (LogDAO)ctx.getBean("logDAO");

//        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        Object manager = sra.getRequest().getSession().getAttribute("manager");
//        Manager ma=(Manager)manager;
//        //管理员信息提取
//        String name = ma.getAccount();
        String name = "神医";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        MethodSignature ms = (MethodSignature)pjp.getSignature();
        Method method = ms.getMethod();
        //获取方法信息
        String methodName = method.getAnnotation(LogAnnotation.class).name();
        //开始时间
        Date beginTime=new Date();


        Object proceed=null;
        String msn="";
        try {
            proceed= pjp.proceed();

            msn = "运行成功";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            msn = "运行失败";
        }
        Date endTime=new Date();
        String log="管理员"+name+"在"+sdf.format(beginTime)+"时间开始执行"+methodName+"方法，在"+sdf.format(endTime)+"时间执行结束，共耗时长"+(endTime.getTime()-beginTime.getTime())/1000+"S,"+msn;
        System.out.println(log);
        Log l = new Log();
        l.setText(log);
//        l.setManagerAccount(ma.getAccount());
        l.setManagerAccount("神医");
        l.setCreateTime(new Date());
        logDAO.insertLog(l);
        return proceed;
    }
}
