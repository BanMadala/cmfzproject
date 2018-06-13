package com.baizhi.bpf.springbootcmfz.vo;

import com.baizhi.bpf.springbootcmfz.entity.Log;

import java.io.Serializable;
import java.util.List;

public class LogVO implements Serializable {
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private List<Log> rows;

    public LogVO(Integer page, Integer pageSize, Integer total, List<Log> rows) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

    public LogVO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Log> getRows() {
        return rows;
    }

    public void setRows(List<Log> rows) {
        this.rows = rows;
    }


    @Override
    public String toString() {
        return "LogVO{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}
