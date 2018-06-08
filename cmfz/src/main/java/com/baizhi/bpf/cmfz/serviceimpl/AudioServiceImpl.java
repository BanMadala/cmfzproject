package com.baizhi.bpf.cmfz.serviceimpl;

import com.baizhi.bpf.cmfz.commmon.LogAnnotation;
import com.baizhi.bpf.cmfz.dao.AudioDAO;
import com.baizhi.bpf.cmfz.entity.Audio;
import com.baizhi.bpf.cmfz.service.AudioService;
import com.baizhi.bpf.cmfz.util.Md5Util;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDAO audioDAO;

    public AudioDAO getAudioDAO() {
        return audioDAO;
    }

    public void setAudioDAO(AudioDAO audioDAO) {
        this.audioDAO = audioDAO;
    }

    @Override
    @LogAnnotation(name="章节音频文件上传")
    public void uploadAudio(MultipartFile audioFile, Audio audio) throws IOException {
        String realPath = "E:\\apache-tomcat-7.0.70\\webapps\\cmfzaudio\\modeleide";
        byte[] fileBytes=null;

            fileBytes=audioFile.getBytes();

        String fileType=audioFile.getOriginalFilename().substring(audioFile.getOriginalFilename().lastIndexOf("."));
        String realFileName = UUID.randomUUID().toString().replaceAll("-", "")+fileType;
        Long size=audioFile.getSize()/1024/1024;

            audioFile.transferTo(new File(realPath+"\\"+realFileName));
            audio.setUrl(realFileName);
            audio.setMd5Code(Md5Util.getMd5String(new String(fileBytes)));
            audio.setSize(size+"MB");
            audio.setId(UUID.randomUUID().toString().replaceAll("-",""));
            audioDAO.insertAudio(audio);


    }

    @Override
    @LogAnnotation(name="章节音频文件下载")
    public void download(Audio audio,ServletOutputStream out) throws IOException {

            File downFile = new File("E:\\apache-tomcat-7.0.70\\webapps\\cmfzaudio\\modeleide" + "\\" + audio.getUrl());
            out.write(FileUtils.readFileToByteArray(downFile));
            out.close();

    }

    @Override
    @LogAnnotation(name="删除章节音频文件")
    public void removeAudio(Audio audio) {
        File file=new File("E:\\apache-tomcat-7.0.70\\webapps\\cmfzaudio\\modeleide"+"\\"+audio.getUrl());
        if(file.exists()&&file.isFile()){
            if(file.delete()){
                audioDAO.delAudio(audio.getId());
            }
        }else{
            audioDAO.delAudio(audio.getId());
            System.out.println("文件不存在，可能已经更改或者已经被删除--------------------------------");
        }
    }


    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Audio> getAllAudioUnderThe(Integer issueId) {
        return audioDAO.getAllAudioUnderIssue(issueId);
    }
}
