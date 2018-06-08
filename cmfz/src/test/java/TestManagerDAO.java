import com.baizhi.bpf.cmfz.dao.ManagerDAO;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestManagerDAO {
    @Test
    public void testSelectManager(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        ManagerDAO managerDAO = (ManagerDAO) ctx.getBean("managerDAO");
        System.out.println(managerDAO.selectManager("神医"));
    }
}
