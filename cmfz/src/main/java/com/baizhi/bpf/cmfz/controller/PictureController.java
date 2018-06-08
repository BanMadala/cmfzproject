package com.baizhi.bpf.cmfz.controller;


import com.baizhi.bpf.cmfz.entity.Picture;
import com.baizhi.bpf.cmfz.service.PictureService;
import com.baizhi.bpf.cmfz.util.Md5Util;
import com.baizhi.bpf.cmfz.vo.PictureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    //查询所有图片
    @RequestMapping(value = "/queryAllPicture",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PictureVO queryAllPicture(Integer page,String rows){
        PictureVO pictureVO = new PictureVO();
        pictureVO.setPageSize(Integer.parseInt(rows));
        pictureVO.setPage(page);
        return pictureService.queryAllPicture(pictureVO);
    }

    //图片上传
    @RequestMapping(value = "/uploadPicture",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map uploadPicture(MultipartFile pictureFile,String pictureName,String message,HttpServletRequest req) throws IOException {
        HashMap map = new HashMap();
        String realPath = "E:\\apache-tomcat-7.0.70\\webapps\\cmfzimg";
        byte[] bytes = pictureFile.getBytes();
        String fileMd5 = Md5Util.getMd5String(bytes.toString());
        //得到文件类型
        //String fileType = pictureFile.getContentType();
        String fileType=pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
        //真实文件名
        //String realFileName = UUID.randomUUID().toString().replaceAll("-","")+"."+fileType;
        String realFileName = UUID.randomUUID().toString().replaceAll("-","")+fileType;
        //存储文件信息
        Picture picture = new Picture();//保存文件信息
        picture.setPictureName(pictureName);
        picture.setMessage(message);
        picture.setMd5Code(fileMd5);
        picture.setPicturePath(realFileName);
        picture.setStatus("n");
        picture.setSize(pictureFile.getSize()/1024+"k");
        try {
            pictureService.uploadPicture(pictureFile,realPath,picture);
            map.put("message", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "图片上传失败");
        }
        System.out.println("文件信息---------------------------------------"+picture);
        return map;
    }


    //删除目标图片
    @RequestMapping(value = "/delPicture",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map removcePicture(Integer pictureId,String picturePath){
        HashMap<String, Object> map = new HashMap<>();
        try {
            pictureService.removePicture(pictureId,picturePath);
            System.out.println("文件id:-------------"+pictureId);
            System.out.println("真实的文件路径:-------------"+picturePath);
            map.put("message", "删除成功");
        } catch(Exception e) {
            e.printStackTrace();
            map.put("message","删除失败");
        }
        return map;
    }


    //修改图片信息
    @RequestMapping(value = "/updatePicture",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map updatePicture(Picture picture){
        System.out.println(picture+"--------------message in pictureUpdateController----------------------");
        HashMap map = new HashMap();
        try {
            pictureService.modifyPicMessage(picture);
            map.put("message","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","修改失败");
        }
        return map;
    }
}
