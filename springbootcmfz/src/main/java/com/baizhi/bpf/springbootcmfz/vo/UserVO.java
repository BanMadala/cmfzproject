package com.baizhi.bpf.springbootcmfz.vo;

import com.baizhi.bpf.springbootcmfz.entity.User;

import java.io.Serializable;
import java.util.List;

public class UserVO implements Serializable {
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private List<User> rows;

    public UserVO(Integer page, Integer pageSize, Integer total, List<User> rows) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

    public UserVO() {
        super();
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

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }
}
