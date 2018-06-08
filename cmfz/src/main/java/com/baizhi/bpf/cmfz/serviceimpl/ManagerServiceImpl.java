package com.baizhi.bpf.cmfz.serviceimpl;

import com.baizhi.bpf.cmfz.dao.ManagerDAO;
import com.baizhi.bpf.cmfz.entity.Manager;
import com.baizhi.bpf.cmfz.service.ManagerService;
import com.baizhi.bpf.cmfz.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS,timeout = -1)
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDAO managerDAO;

    public ManagerDAO getManagerDAO() {
        return managerDAO;
    }

    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    @Override
    public Manager login(String username, String userPass) {
        if(username==null||username==null)return null;
        Manager ma=null;
        Manager manager = managerDAO.selectManager(username);
        if(manager!=null){
            String dbPass=manager.getPass();
            String salt = manager.getSalt();
            if(Md5Util.checkPassIsRigth(salt+userPass,dbPass))ma=manager;
        }
        return ma;
    }
}
