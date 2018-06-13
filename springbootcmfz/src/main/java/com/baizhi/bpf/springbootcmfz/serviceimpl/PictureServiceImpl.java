package com.baizhi.bpf.springbootcmfz.serviceimpl;

import com.baizhi.bpf.springbootcmfz.commmon.LogAnnotation;
import com.baizhi.bpf.springbootcmfz.dao.PictureDAO;
import com.baizhi.bpf.springbootcmfz.entity.Picture;
import com.baizhi.bpf.springbootcmfz.service.PictureService;
import com.baizhi.bpf.springbootcmfz.vo.PictureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }


    //分页查询
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PictureVO queryAllPicture(PictureVO pictureVo) {
        //先查询所有数据条数
        List<Picture> pictures = pictureDAO.selectAllPicture();
        //设置总数据条数
        pictureVo.setTotal(pictures.size());
        //计算起始条目
        Integer beginRow=(pictureVo.getPage()-1)*pictureVo.getPageSize();
        //获取当前页数据
        List<Picture> list = pictureDAO.selectAllPictureInThePage(beginRow, pictureVo.getPageSize());
        pictureVo.setRows(list);
        return pictureVo;
    }



    @Override
    @LogAnnotation(name="轮播图文件上传")
    public void uploadPicture(MultipartFile file, String dirpath,Picture picture) throws IOException {
        System.out.println(picture+"begin------------------");

            //先验证文件是否已经存在
            List<Picture> pictureList = pictureDAO.selectAllPicture();
            for (Picture p:pictureList
                 ){
                if(p.getMd5Code().equals(picture.getMd5Code()))throw new RuntimeException("图片已经存在");
            }
            //设置好上传路径和文件名
            file.transferTo(new File(dirpath+"\\"+picture.getPicturePath()));
            pictureDAO.addPicture(picture);

        System.out.println(picture+"end--------------------------------");
    }

    @Override
    @LogAnnotation(name="删除轮播图")
    public void removePicture(Integer id, String picturePath) {
        File picture=new File("E:\\apache-tomcat-7.0.70\\webapps\\cmfzimg"+"\\"+picturePath);
        if(picture.exists()&&picture.isFile()){
            if(picture.delete()){
                pictureDAO.delPicture(id);
                System.out.println("删除文件成功----------------------------");
            }else{
                System.out.println("删除文件失败------------------------------------");
                throw new RuntimeException("删除文件失败");
            }
        }else{
            System.out.println("图片不存在-----------------------------");
            throw new RuntimeException("图片不存在");
        }


    }
    //修改所有图片
    @Override
    @LogAnnotation(name="修改轮播图信息")
    public void modifyPicMessage(Picture picture) {

            pictureDAO.updataPicture(picture);
    }


    @Override
    public List<Picture> queryAllNeedPicture() {
        return pictureDAO.selectAllNeedShowPage();
    }
}
