import com.baizhi.bpf.cmfz.dao.LogDAO;
import com.baizhi.bpf.cmfz.entity.Log;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestLogDAO {
    @Test
    public void testLogDAO(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        LogDAO logDAO = (LogDAO) ctx.getBean("logDAO");
        Log log = new Log();
        log.setManagerAccount("神医");
        log.setText("这是一条测试信息");
        log.setCreateTime(new Date());
        logDAO.insertLog(log);
    }
    @Test
    public void testQueryALl(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        LogDAO logDAO = (LogDAO) ctx.getBean("logDAO");
        System.out.println(logDAO.selAllLogMessage());
    }
}
