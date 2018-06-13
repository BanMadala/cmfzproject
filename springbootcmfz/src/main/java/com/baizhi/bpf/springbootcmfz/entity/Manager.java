package com.baizhi.bpf.springbootcmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Manager implements Serializable {
    /**
     * 管理员表的对象实体
     */
    private Integer id;
    private String account;
    private String pass;
    private String nickName;
//    @JsonDeserialize(using = DateDeserize.class)
//    @JsonSerialize(using = DateSerialize.class)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regTime;
    private String photoSrc;
    private String isLogoff;
    private String salt;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready1;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready2;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public String getIsLogoff() {
        return isLogoff;
    }

    public void setIsLogoff(String isLogoff) {
        this.isLogoff = isLogoff;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Manager(Integer id, String account, String pass, String nickName, Date regTime, String photoSrc, String isLogoff, String salt) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.nickName = nickName;
        this.regTime = regTime;
        this.photoSrc = photoSrc;
        this.isLogoff = isLogoff;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regTime=" + regTime +
                ", photoSrc='" + photoSrc + '\'' +
                ", isLogoff='" + isLogoff + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    public Manager() {
        super();
    }
}


