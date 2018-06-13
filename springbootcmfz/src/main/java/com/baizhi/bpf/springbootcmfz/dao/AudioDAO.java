package com.baizhi.bpf.springbootcmfz.dao;

import com.baizhi.bpf.springbootcmfz.entity.Audio;

import java.util.List;

public interface AudioDAO {
    public List<Audio> selectAllAudioInTheIssue(Integer parentId);

    public void insertAudio(Audio audio);

    public void delAudio(String audioId);

    public Audio selectOneAudioById(String audioId);

    public List<Audio> selectAllAUdio();

    public List<Audio> getAllAudioUnderIssue(Integer issueId);

}
