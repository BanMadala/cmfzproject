package com.baizhi.bpf.springbootcmfz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//配置了该注解后才能使javaBean配置生效
@EnableAutoConfiguration
@MapperScan("com.baizhi.bpf.springbootcmfz.dao")
public class APP {
    public static void main(String[] args) {
        SpringApplication.run(APP.class, args);
    }
}
