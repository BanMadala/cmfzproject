package com.baizhi.bpf.springbootcmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Menu implements Serializable {
    /**
     * 菜单表的对象实体
     */
    private Integer id;
    private String title;
    private String iconCls;
    private String url;
    private Integer parentId;
    private List<Menu> childs;
    //准备字段
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready1;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready2;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready3;

    public Menu(Integer id, String title, String iconCls, String url, Integer parentId, List<Menu> childs) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.url = url;
        this.parentId = parentId;
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(title, menu.title) &&
                Objects.equals(iconCls, menu.iconCls) &&
                Objects.equals(url, menu.url) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(childs, menu.childs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, iconCls, url, parentId, childs);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChilds() {
        return childs;
    }

    public void setChilds(List<Menu> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", childs=" + childs +
                '}';
    }

    public Menu() {
        super();
    }
}
