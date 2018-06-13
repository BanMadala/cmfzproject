package com.baizhi.bpf.springbootcmfz.vo;

import java.io.Serializable;

public class UserFields implements Serializable {
    private String id;
    private String text;

    public UserFields() {
    }

    public UserFields(String id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "UserFields{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
