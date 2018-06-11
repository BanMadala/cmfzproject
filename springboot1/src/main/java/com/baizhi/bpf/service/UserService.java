package com.baizhi.bpf.service;

import com.baizhi.bpf.entity.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public List<User> queryAllUser(Integer id);
}
