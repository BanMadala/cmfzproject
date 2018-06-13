package com.baizhi.bpf.springbootcmfz.controller;

import com.baizhi.bpf.springbootcmfz.entity.Audio;
import com.baizhi.bpf.springbootcmfz.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/audio")
@RestController
public class AudioController {
    @Autowired
    private AudioService audioService;

    public AudioService getAudioService() {
        return audioService;
    }

    public void setAudioService(AudioService audioService) {
        this.audioService = audioService;
    }


    @RequestMapping(value = "/uploadAudio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map uploadAudio(MultipartFile audioFile,Audio audio){
        Map map = new HashMap();
        try {
            audioService.uploadAudio(audioFile,audio);
            map.put("message","上传章节成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","上传章节失败");
        }
        return map;
    }

    @RequestMapping(value="/removeAudio",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map removeAudio(Audio audio){
        Map map = new HashMap();
        try {
            audioService.removeAudio(audio);
            map.put("message","删除成功");
        } catch (Exception e){
            e.printStackTrace();
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping(value="/downloadAudio",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(Audio audio,HttpServletResponse res){
        ServletOutputStream outputStream =null;
        try {
            outputStream = res.getOutputStream();
            String fn = audio.getName() + audio.getUrl().substring(audio.getUrl().lastIndexOf("."));
            String fileName=URLEncoder.encode(fn,"UTF-8");
            res.setHeader("Content-Disposition", "attachment;filename="+fileName);
            res.setContentType("audio/mpeg");
            audioService.download(audio,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //服务端接口,查询该专辑下的所有子专辑
    @RequestMapping(value = "/wen",produces = "alllication/json;cahrset=Utf-8")
    public List<Audio> getIssueAllChildre(Integer id,Integer uid){
        List<Audio> audios=null;
        if(uid!=null){
            audios = audioService.getAllAudioUnderThe(id);
        }
        return audios;
    }



}
