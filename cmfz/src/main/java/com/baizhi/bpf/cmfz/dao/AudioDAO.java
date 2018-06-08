package com.baizhi.bpf.cmfz.dao;

import com.baizhi.bpf.cmfz.entity.Audio;

import java.util.List;

public interface AudioDAO {
    public List<Audio> selectAllAudioInTheIssue(Integer parentId);

    public void insertAudio(Audio audio);

    public void delAudio(String audioId);

    public Audio selectOneAudioById(String audioId);

    public List<Audio> selectAllAUdio();

    public List<Audio> getAllAudioUnderIssue(Integer issueId);

}
