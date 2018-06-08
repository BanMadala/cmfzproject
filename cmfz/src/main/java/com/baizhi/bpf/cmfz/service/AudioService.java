package com.baizhi.bpf.cmfz.service;

import com.baizhi.bpf.cmfz.entity.Audio;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

public interface AudioService {
    public void uploadAudio(MultipartFile audioFile,Audio audio) throws IOException;

    public void download(Audio audio,ServletOutputStream out) throws IOException;

    public void removeAudio(Audio audio);

    public List<Audio> getAllAudioUnderThe(Integer issueId);
}
