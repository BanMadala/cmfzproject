package com.baizhi.bpf.cmfz.serviceimpl;

import com.baizhi.bpf.cmfz.commmon.LogAnnotation;
import com.baizhi.bpf.cmfz.dao.AudioDAO;
import com.baizhi.bpf.cmfz.dao.IssueDAO;
import com.baizhi.bpf.cmfz.entity.Audio;
import com.baizhi.bpf.cmfz.entity.Issue;
import com.baizhi.bpf.cmfz.service.IssueService;
import com.baizhi.bpf.cmfz.vo.IssueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {
    @Autowired
    private AudioDAO audioDAO;

    public AudioDAO getAudioDAO() {
        return audioDAO;
    }

    public void setAudioDAO(AudioDAO audioDAO) {
        this.audioDAO = audioDAO;
    }

    @Autowired
    private IssueDAO issueDAO;

    public IssueDAO getIssueDAO() {
        return issueDAO;
    }

    public void setIssueDAO(IssueDAO issueDAO) {
        this.issueDAO = issueDAO;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public IssueVO queryAllIssueInPage(IssueVO issueVO) {
        Integer pageSize = issueVO.getPageSize();
        Integer page = issueVO.getPage();
        Integer bedinRow=(page-1)*pageSize;
        //设置总条数
        List<Issue> issues = issueDAO.selectAllCounts();
        issueVO.setTotal(issues==null?0:issues.size());
        List<Issue> list = issueDAO.selectAllIssue(bedinRow, pageSize);
        issueVO.setRows(list);
        System.out.println(issueVO+"in queryAllInpage--------------------------");
        return issueVO;
    }

    @Override
    public List<Issue> queryAllIssue() {
        return issueDAO.selectAllCounts();
    }

    @Override
    @LogAnnotation(name="创建专辑")
    public void createIssue(MultipartFile issueImg,Issue issue) throws IOException {
        String realPath = "E:\\apache-tomcat-7.0.70\\webapps\\cmfzaudio";
        String fileType=issueImg.getOriginalFilename().substring(issueImg.getOriginalFilename().lastIndexOf("."));
        String realFileName=UUID.randomUUID().toString().replaceAll("-","");

            issueImg.transferTo(new File(realPath+"\\"+realFileName+fileType));
            issue.setImg(realFileName+fileType);
            issueDAO.insertIssue(issue);

    }

    @Override
    @LogAnnotation(name="移除专辑及所有子章节文件")
    public void removceIssue(Integer issueId) {
        List<Audio> audios = audioDAO.selectAllAudioInTheIssue(issueId);
        for (Audio audio:audios
             ) {
            File file=new File("E:\\apache-tomcat-7.0.70\\webapps\\cmfzaudio\\modeleide"+"\\"+audio.getUrl());
            if(file.exists()&&file.isFile()){
                if(file.delete()){
                    audioDAO.delAudio(audio.getId());
                }
            }else{
                audioDAO.delAudio(audio.getId());
            }
        }
        issueDAO.deleteIssue(issueId);

    }

    @Override
    @LogAnnotation(name="更新专辑信息")
    public void modifyIssue(Issue issue) {
        issueDAO.updateIssue(issue);
    }

    @Override
    public List<Issue> getAllIssueForSelect() {
        return issueDAO.selectAllIssueIdAndName();
    }

    @Override
    public List<Issue> getAllIssueForShow() {
        return issueDAO.selectAllIssueForShow();
    }
}
