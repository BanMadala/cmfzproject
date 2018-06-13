package com.baizhi.bpf.springbootcmfz.serviceimpl;


import com.baizhi.bpf.springbootcmfz.dao.LogDAO;
import com.baizhi.bpf.springbootcmfz.entity.Log;
import com.baizhi.bpf.springbootcmfz.service.LogService;
import com.baizhi.bpf.springbootcmfz.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDAO;

    public LogDAO getLogDAO() {
        return logDAO;
    }

    public void setLogDAO(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public LogVO queryAllVOInPage(LogVO logVO) {

        Integer page = logVO.getPage();
        Integer pageSize = logVO.getPageSize();
        Integer beginRow=(page-1)*pageSize;

        Integer totals = logDAO.countAllLog();
        List<Log> rows = logDAO.selAllLogInPage(beginRow, pageSize);
        logVO.setRows(rows);
        logVO.setTotal(totals);
        return logVO;
    }
















}
