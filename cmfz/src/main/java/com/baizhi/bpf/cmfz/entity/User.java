package com.baizhi.bpf.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.bpf.cmfz.commmon.UserAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    @UserAnnotation(name="编号")
    private Integer id;
    @UserAnnotation(name="手机号")
    private String phoneNum;
    @UserAnnotation(name="用户名")
    private String userName;
    @UserAnnotation(name = "密码")
    private String password;
    @UserAnnotation(name="用户盐")
    private String salt;
    @UserAnnotation(name="法名")
    private String dharmaName;
    @UserAnnotation(name="省份")
    private String province;
    @UserAnnotation(name="城市")
    private String city;
    @UserAnnotation(name="性别")
    private String sex;
    @UserAnnotation(name="签名")
    private String sign;
    @UserAnnotation(name="头像路径")
    private String headPic;
    @UserAnnotation(name="用户是否冻结")
    private String status;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UserAnnotation(name="生日")
    private Date date;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UserAnnotation(name="注册日期")
    private Date regDate;
    @UserAnnotation(name="所属上师")
    private Integer masterId;

    public User() {
        super();
    }

    public User(Integer id, String phoneNum, String userName, String password, String salt, String dharmaName, String province, String city, String sex, String sign, String headPic, String status, Date date, Date regDate, Integer masterId) {
        this.id = id;
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.dharmaName = dharmaName;
        this.province = province;
        this.city = city;
        this.sex = sex;
        this.sign = sign;
        this.headPic = headPic;
        this.status = status;
        this.date = date;
        this.regDate = regDate;
        this.masterId = masterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNum='" + phoneNum + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                ", sign='" + sign + '\'' +
                ", headPic='" + headPic + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", regDate=" + regDate +
                ", masterId=" + masterId +
                '}';
    }
}
