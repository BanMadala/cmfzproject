package com.baizhi.bpf.cmfz.controller;


import com.baizhi.bpf.cmfz.entity.Audio;
import com.baizhi.bpf.cmfz.entity.Issue;
import com.baizhi.bpf.cmfz.service.IssueService;
import com.baizhi.bpf.cmfz.vo.IssueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    public IssueService issueService;

    public IssueService getIssueService() {
        return issueService;
    }

    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value="/queryAllIssue",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IssueVO queryAllIssue(Integer page,String rows){
        IssueVO issueVO = new IssueVO();
        issueVO.setPage(page);
        issueVO.setPageSize(Integer.parseInt(rows));
        issueVO = issueService.queryAllIssueInPage(issueVO);
        System.out.println(issueVO+"-----------------------------------in issueController");
        return issueVO;
    }

    @RequestMapping(value="/createIssue",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map createIssue(MultipartFile issueImg,Issue issue){
        Map map=new HashMap();
        try {
            issueService.createIssue(issueImg,issue);
            map.put("message","添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "添加失败");
        }
        return map;
    }


    @RequestMapping(value="/delIssue",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map removceIssue(Integer id){
        Map map=new HashMap();
        issueService.removceIssue(id);
        map.put("message","删除成功");
        return map;
    }


    @RequestMapping(value = "/updateIssue",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map modifyMap(Issue issue){
        Map map=new HashMap();
        try {
            issueService.modifyIssue(issue);
            map.put("message","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","修改失败");
        }
        return map;
    }




    @RequestMapping(value="/test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Issue> textIssue(){
        return issueService.queryAllIssue();
    }

    @RequestMapping(value="/getAllIssueNameAndId",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Issue> getAllIsuueForSelect(){
        return issueService.getAllIssueForSelect();
    }



}
