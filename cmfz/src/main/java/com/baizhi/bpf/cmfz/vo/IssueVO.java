package com.baizhi.bpf.cmfz.vo;

import com.baizhi.bpf.cmfz.entity.Issue;

import java.io.Serializable;
import java.util.List;

public class IssueVO implements Serializable {
    //收集分页数据
    private Integer total;
    private List<Issue> rows;
    private Integer page;
    private Integer pageSize;

    public IssueVO(Integer total, List<Issue> rows, Integer page, Integer pageSize) {
        this.total = total;
        this.rows = rows;
        this.page = page;
        this.pageSize = pageSize;
    }

    public IssueVO() {
        super();
    }


    @Override
    public String toString() {
        return "IssueVO{" +
                "total=" + total +
                ", rows=" + rows +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Issue> getRows() {
        return rows;
    }

    public void setRows(List<Issue> rows) {
        this.rows = rows;
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
}
