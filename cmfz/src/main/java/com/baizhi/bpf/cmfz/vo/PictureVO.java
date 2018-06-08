package com.baizhi.bpf.cmfz.vo;

import com.baizhi.bpf.cmfz.entity.Picture;

import java.io.Serializable;
import java.util.List;

public class PictureVO implements Serializable {
    //收集分页数据
    private Integer total;
    private List<Picture> rows;
    private Integer page;
    private Integer pageSize;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Picture> getRows() {
        return rows;
    }

    public void setRows(List<Picture> rows) {
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

    @Override
    public String toString() {
        return "PiotureVO{" +
                "total=" + total +
                ", rows=" + rows +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }

    public PictureVO(Integer total, List<Picture> rows, Integer page, Integer pageSize) {
        this.total = total;
        this.rows = rows;
        this.page = page;
        this.pageSize = pageSize;
    }

    public PictureVO() {
        super();
    }

}
