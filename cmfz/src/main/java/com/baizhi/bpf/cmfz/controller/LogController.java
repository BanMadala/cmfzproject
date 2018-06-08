package com.baizhi.bpf.cmfz.controller;


import com.baizhi.bpf.cmfz.service.LogService;
import com.baizhi.bpf.cmfz.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping(value="/queryLogs",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LogVO queryLogs(Integer page,String rows){
        LogVO logVO = new LogVO();
        logVO.setPage(page);
        logVO.setPageSize(Integer.parseInt(rows));
        logVO=logService.queryAllVOInPage(logVO);
        return logVO;
    }







}
