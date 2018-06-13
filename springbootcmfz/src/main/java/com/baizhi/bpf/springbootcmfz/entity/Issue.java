package com.baizhi.bpf.springbootcmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Issue implements Serializable {
    /**
     * 专辑对象实体
     */
    private Integer id;
    private String name;
//    @JsonDeserialize(using = DateDeserize.class)
//    @JsonSerialize(using = DateSerialize.class)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Double score;
    private String author;
    private String img;
    private Integer counts;
    private String brief;
    private List<Audio> children;
    private String audioTime;
    private String md5Code;

    public Issue(Integer id, String name, Date createDate, Double score, String author, String img, Integer counts, String brief, List<Audio> children, String audioTime, String md5Code) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.score = score;
        this.author = author;
        this.img = img;
        this.counts = counts;
        this.brief = brief;
        this.children = children;
        this.audioTime = audioTime;
        this.md5Code = md5Code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<Audio> getChildren() {
        return children;
    }

    public void setChildren(List<Audio> children) {
        this.children = children;
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

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", img='" + img + '\'' +
                ", counts=" + counts +
                ", brief='" + brief + '\'' +
                ", children=" + children +
                ", audioTime='" + audioTime + '\'' +
                ", md5Code='" + md5Code + '\'' +
                '}';
    }

    public Issue() {
        super();
    }
}
