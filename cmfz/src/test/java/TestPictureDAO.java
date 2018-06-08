import com.baizhi.bpf.cmfz.dao.PictureDAO;
import com.baizhi.bpf.cmfz.entity.Picture;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPictureDAO {
    @Test
    public void testSelectAllPicture(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PictureDAO pictureDAO = (PictureDAO) ctx.getBean("pictureDAO");
        System.out.println(pictureDAO.selectAllPicture());
    }

    @Test
    public void testAddPicture(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PictureDAO pictureDAO = (PictureDAO) ctx.getBean("pictureDAO");
        Picture picture = new Picture();
        picture.setPictureName("根性");
        picture.setPicturePath("1514862261954.jpg");
        picture.setMessage("火影忍者");
        picture.setSize("23.7k");
        picture.setMd5Code("#");
        picture.setStatus("y");
        System.out.println(picture);
        pictureDAO.addPicture(picture);
        System.out.println(picture);
    }

    @Test
    public void testDelPicture(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PictureDAO pictureDAO = (PictureDAO) ctx.getBean("pictureDAO");
        pictureDAO.delPicture(6);

    }

    @Test
    public void testUpdatePicture(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PictureDAO pictureDAO = (PictureDAO) ctx.getBean("pictureDAO");
        Picture picture = new Picture();
        picture.setId(7);
        picture.setPictureName("DSM");
        picture.setMessage("测试用图片");
        picture.setStatus("y");
        pictureDAO.updataPicture(picture);
    }

    @Test
    public void testUpdate(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        PictureDAO pictureDAO = (PictureDAO) ctx.getBean("pictureDAO");
        System.out.println(pictureDAO.selectAllNeedShowPage());
    }


}
