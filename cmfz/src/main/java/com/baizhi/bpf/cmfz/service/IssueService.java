package com.baizhi.bpf.cmfz.service;

import com.baizhi.bpf.cmfz.entity.Issue;
import com.baizhi.bpf.cmfz.vo.IssueVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IssueService {
    public IssueVO queryAllIssueInPage(IssueVO issueVO);
    public List<Issue> queryAllIssue();

    public void createIssue(MultipartFile issueImg,Issue issue) throws IOException;
    public void removceIssue(Integer issueId);
    public void modifyIssue(Issue issue);

    public List<Issue> getAllIssueForSelect();

    public List<Issue> getAllIssueForShow();

}
