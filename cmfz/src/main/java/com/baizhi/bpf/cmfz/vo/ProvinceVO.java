package com.baizhi.bpf.cmfz.vo;

import java.io.Serializable;

public class ProvinceVO  implements Serializable {
    /**
     * this vo is for mapping counts of users which in the province
     */
    private String name;//省名
    private Integer value;//根据性别查询出的数量

    public ProvinceVO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProvinceVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public ProvinceVO() {
        super();
    }

}
