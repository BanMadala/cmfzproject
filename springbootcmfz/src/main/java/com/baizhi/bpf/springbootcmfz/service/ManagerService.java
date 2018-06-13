package com.baizhi.bpf.springbootcmfz.service;

import com.baizhi.bpf.springbootcmfz.entity.Manager;

public interface ManagerService {
    public Manager login(String username,String userPass);
}
