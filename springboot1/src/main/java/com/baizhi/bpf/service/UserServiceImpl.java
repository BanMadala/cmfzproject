package com.baizhi.bpf.service;

import com.baizhi.bpf.dao.UserMapper;
import com.baizhi.bpf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(User user) {

        userMapper.insertSelective(user);
    }



    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<User> queryAllUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);

    }


}
