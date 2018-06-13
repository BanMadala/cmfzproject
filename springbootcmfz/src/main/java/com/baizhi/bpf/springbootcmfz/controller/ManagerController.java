package com.baizhi.bpf.springbootcmfz.controller;

import com.baizhi.bpf.springbootcmfz.entity.Manager;
import com.baizhi.bpf.springbootcmfz.service.ManagerService;
import com.baizhi.bpf.springbootcmfz.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    public ManagerService getManagerService() {
        return managerService;
    }

    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }


    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map login(String username, String pass, String enCode) {
        Map map = new HashMap();
        if (enCode != null) {
            Manager manager = managerService.login(username, pass);
            if (manager == null) {
                map.put("message", MessageUtil.LOGIN_ERROR);
            } else {
                map.put("message", MessageUtil.LOGIN_OK);
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                requestAttributes.getRequest().getSession().setAttribute("manager", manager);
            }
            return map;
        } else {
            map.put("message", "登录失败，请输入正确的验证码");
            return map;
        }
    }

    @RequestMapping(value = "existMS", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map exitMs() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestAttributes.getRequest().getSession().removeAttribute("manager");
        HashMap<String, String> map = new HashMap<>();
        map.put("message","退出成功");
        return map;
    }

}
