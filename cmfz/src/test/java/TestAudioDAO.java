import com.baizhi.bpf.cmfz.dao.AudioDAO;
import com.baizhi.bpf.cmfz.entity.Audio;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class TestAudioDAO {
    @Test
    public void testSelInIssue(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        AudioDAO audioDAO = (AudioDAO) ctx.getBean("audioDAO");
        System.out.println(audioDAO.selectOneAudioById("iop"));

    }


    @Test
    public void testInsert(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        AudioDAO audioDAO = (AudioDAO) ctx.getBean("audioDAO");
        Audio audio = new Audio();
        audio.setId(UUID.randomUUID().toString().replaceAll("-",""));
        audio.setAudioTime("5min");
        audio.setIssueId(5);
        audio.setUrl("#");
        audio.setSize("10MB");
        audio.setMd5Code("#");
        audio.setName("test");
        audioDAO.insertAudio(audio);
    }


    @Test
    public void testDel(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        AudioDAO audioDAO = (AudioDAO) ctx.getBean("audioDAO");
        audioDAO.delAudio("ebc953ea913647beb1d106f374c904a5");
    }
    @Test
    public void testQuery2(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        AudioDAO audioDAO = (AudioDAO) ctx.getBean("audioDAO");
        System.out.println(audioDAO.getAllAudioUnderIssue(1));
    }



}
