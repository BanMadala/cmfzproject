package com.baizhi.bpf.cmfz.commmon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//表明注解位置
@Target(ElementType.FIELD)
//表明注解生效时机
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    public String name();
}
