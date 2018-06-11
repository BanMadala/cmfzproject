package com.baizhi.bpf;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//添加入口类注解 @Configu
@SpringBootApplication
@MapperScan(value = "com.baizhi.bpf.dao")
public class APP {
    public static void main(String[] args) {
        SpringApplication.run(APP.class, args);
    }
}
