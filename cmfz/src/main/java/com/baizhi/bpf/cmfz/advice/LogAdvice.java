package com.baizhi.bpf.cmfz.advice;


import com.baizhi.bpf.cmfz.commmon.LogAnnotation;
import com.baizhi.bpf.cmfz.dao.LogDAO;
import com.baizhi.bpf.cmfz.entity.Log;
import com.baizhi.bpf.cmfz.entity.Manager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogAdvice implements MethodInterceptor {
//    @Autowired
//    private LogDAO logDAO;
//
//    public LogDAO getLogDAO() {
//        return logDAO;
//    }
//
//    public void setLogDAO(LogDAO logDAO) {
//        this.logDAO = logDAO;
//    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        LogDAO logDAO = (LogDAO)ctx.getBean("logDAO");

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object manager = sra.getRequest().getSession().getAttribute("manager");
        Manager ma=(Manager)manager;
        //管理员信息提取
        String name = ma.getAccount();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //获取方法信息
        String methodName = methodInvocation.getMethod().getAnnotation(LogAnnotation.class).name();
        //开始时间
        Date beginTime=new Date();


        Object proceed=null;
        String message="";
        try {
            proceed= methodInvocation.proceed();

            message = "运行成功";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "运行失败";
        }
        Date endTime=new Date();
        String log="管理员"+name+"在"+sdf.format(beginTime)+"时间开始执行"+methodName+"方法，在"+sdf.format(endTime)+"时间执行结束，共耗时长"+(endTime.getTime()-beginTime.getTime())/1000+"S,"+message;
        System.out.println(log);
        Log l = new Log();
        l.setText(log);
        l.setManagerAccount(ma.getAccount());
        l.setCreateTime(new Date());
        logDAO.insertLog(l);
        return proceed;
    }
}
