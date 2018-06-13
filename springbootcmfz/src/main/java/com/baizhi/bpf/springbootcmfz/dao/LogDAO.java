package com.baizhi.bpf.springbootcmfz.dao;

import com.baizhi.bpf.springbootcmfz.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {
    public List<Log> selAllLogMessage();

    public void insertLog(Log log);

    public Integer countAllLog();


    public List<Log> selAllLogInPage(@Param("beginRow") Integer beginRow, @Param("pageSize") Integer pageSize);
}
