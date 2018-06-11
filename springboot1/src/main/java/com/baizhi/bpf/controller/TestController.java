package com.baizhi.bpf.controller;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String testController(String name){
        return name + "HelloWorld";
    }

    @RequestMapping(value = "/testjsp")
    public String testAcessJSP(){
        System.out.println("HelloWorld!!!!!!");
        return "index";
    }


}
