package com.baizhi.bpf.springbootcmfz.dao;

import com.baizhi.bpf.springbootcmfz.entity.Issue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueDAO {
    public List<Issue> selectAllIssue(@Param("beginRow") Integer beginRow,@Param("pageSize") Integer pageSize);
    public List<Issue> selectAllCounts();
    public void insertIssue(Issue issue);
    public void deleteIssue(Integer id);
    public void updateIssue(Issue issue);

    public List<Issue> selectAllIssueIdAndName();

    //返回所有专辑
    public List<Issue> selectAllIssueForShow();

}
