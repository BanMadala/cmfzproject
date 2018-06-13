package com.baizhi.bpf.springbootcmfz.service;


import com.baizhi.bpf.springbootcmfz.entity.Picture;
import com.baizhi.bpf.springbootcmfz.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    public PictureVO queryAllPicture(PictureVO pictureVO);
    public void uploadPicture(MultipartFile file,String dirpath,Picture picture) throws IOException;
    public void removePicture(Integer id,String picturePath);
    public void modifyPicMessage(Picture picture);

    public List<Picture> queryAllNeedPicture();


}
