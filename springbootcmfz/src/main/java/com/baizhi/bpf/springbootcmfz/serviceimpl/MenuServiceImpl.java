package com.baizhi.bpf.springbootcmfz.serviceimpl;

import com.baizhi.bpf.springbootcmfz.dao.MenuDAO;
import com.baizhi.bpf.springbootcmfz.entity.Menu;
import com.baizhi.bpf.springbootcmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS,timeout = -1)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDAO menuDAO;

    public MenuDAO getMenuDAO() {
        return menuDAO;
    }

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Override
    public List<Menu> queryAllMenu() {
        return menuDAO.selectAllMenu();
    }
}
