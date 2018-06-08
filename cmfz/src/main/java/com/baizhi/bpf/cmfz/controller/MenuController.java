package com.baizhi.bpf.cmfz.controller;

import com.baizhi.bpf.cmfz.entity.Menu;
import com.baizhi.bpf.cmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value="/queryAllMenu",produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Menu> queryAllMenu(){
        return menuService.queryAllMenu();
    }

}
