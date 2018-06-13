package com.baizhi.bpf.springbootcmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Picture implements Serializable {
    private Integer id;
    private String pictureName;
    private String picturePath;
    private String message;
//    @JsonSerialize(using =DateSerialize.class)
//    @JsonDeserialize(using = DateDeserize.class)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;
    private String status;
    private String size;
    private String md5Code;
//    @JsonIgnore
    @JSONField(serialize = false)
    private String ready2;

    public Picture(Integer id, String pictureName, String picturePath, String message, Date uploadTime, String status, String size, String md5Code) {
        this.id = id;
        this.pictureName = pictureName;
        this.picturePath = picturePath;
        this.message = message;
        this.uploadTime = uploadTime;
        this.status = status;
        this.size = size;
        this.md5Code = md5Code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", pictureName='" + pictureName + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", message='" + message + '\'' +
                ", uploadTime=" + uploadTime +
                ", status='" + status + '\'' +
                ", size='" + size + '\'' +
                ", md5Code='" + md5Code + '\'' +
                '}';
    }

    public Picture() {
        super();
    }
}
