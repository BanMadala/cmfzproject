package com.baizhi.bpf.springbootcmfz.dao;

import com.baizhi.bpf.springbootcmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureDAO {
    public List<Picture> selectAllPicture();
    public void addPicture(Picture picture);
    public void delPicture(Integer pictureId);
    public void updataPicture(Picture picture);
    public List<Picture> selectAllPictureInThePage(@Param("beginRow") Integer beginRow, @Param("pageSize") Integer pageSize);

    //查询需要展示的轮播图
    public List<Picture> selectAllNeedShowPage();
}
