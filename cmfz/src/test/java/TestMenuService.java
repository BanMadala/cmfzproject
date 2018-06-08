import com.baizhi.bpf.cmfz.service.MenuService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMenuService {
    @Test
    public void testSelectSelectAll(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        MenuService menuService = (MenuService) ctx.getBean("menuServiceImpl");
        System.out.println(menuService.queryAllMenu());
    }
}
