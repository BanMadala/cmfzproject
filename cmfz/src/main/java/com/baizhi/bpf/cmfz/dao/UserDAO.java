package com.baizhi.bpf.cmfz.dao;

import com.baizhi.bpf.cmfz.entity.User;
import com.baizhi.bpf.cmfz.vo.ProvinceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    public List<User> selectAllUserInthePage(@Param("beginRow") Integer beginRow, @Param("pageSize") Integer pageSize);

    public Integer countAllUser();

    public List<ProvinceVO> selectAllMaleUserInProvince();

    public List<ProvinceVO> selectAllFemaleUserInProvince();

//    public Integer selectUserBetweenTime(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    public Integer selectUserBetweenTime(@Param("beginDay") Integer beginDay, @Param("endDay") Integer endDay);

    public List<User> selectAllUser();


    public void updateUserStatts(List<User> users);


    //根据用户名查询用户信息
    public User queryUserByPhone(String phoneNum);

    public void insertUserPhoneAndUserPassword(User user);

    public void updateUserMessage(User user);

    public List<User> queryUserMember(Integer uid);
}
