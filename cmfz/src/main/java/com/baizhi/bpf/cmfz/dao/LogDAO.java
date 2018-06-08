package com.baizhi.bpf.cmfz.dao;

import com.baizhi.bpf.cmfz.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {
    public List<Log> selAllLogMessage();

    public void insertLog(Log log);

    public Integer countAllLog();


    public List<Log> selAllLogInPage(@Param("beginRow") Integer beginRow, @Param("pageSize") Integer pageSize);
}
