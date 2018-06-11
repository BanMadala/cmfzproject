package com.baizhi.bpf.controller;


import com.baizhi.bpf.entity.User;
import com.baizhi.bpf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


   @Autowired
    UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/queryAllUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<User> queryAllUser(Integer id){
        log.debug("测试logback是否正常");

        return  userService.queryAllUser(id);
    }

    @RequestMapping(value = "/addUser")
    public String insertUser(String name, HttpServletRequest req){
        User user = new User();
        user.setName(name);
        userService.addUser(user);
        req.setAttribute("user",user);
        return "index";
    }
}
