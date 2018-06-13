package com.baizhi.bpf.springbootcmfz.entity;

import java.io.Serializable;

public class Audio implements Serializable {
    private String id;
    private String name;
    private String size;
    private String url;
    private String audioTime;
    private String md5Code;
    private Integer issueId;

    public Audio(String id, String name, String size, String url, String audioTime, String md5Code, Integer issueId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.url = url;
        this.audioTime = audioTime;
        this.md5Code = md5Code;
        this.issueId = issueId;
    }

    public Audio() {
        super();
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", audioTime='" + audioTime + '\'' +
                ", md5Code='" + md5Code + '\'' +
                ", issueId=" + issueId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(String audioTime) {
        this.audioTime = audioTime;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }
}
