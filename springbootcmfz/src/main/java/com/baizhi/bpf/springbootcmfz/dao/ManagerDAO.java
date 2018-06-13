package com.baizhi.bpf.springbootcmfz.dao;

import com.baizhi.bpf.springbootcmfz.entity.Manager;

public interface ManagerDAO {
    public Manager selectManager(String managerName);
}
