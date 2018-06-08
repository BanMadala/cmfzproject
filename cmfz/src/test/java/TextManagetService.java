import com.baizhi.bpf.cmfz.service.ManagerService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TextManagetService {
    @Test
    public void testlogin(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        ManagerService managerServiceImpl = (ManagerService) ctx.getBean("managerServiceImpl");
        System.out.println(managerServiceImpl.login("神医","123456"));
    }
}
