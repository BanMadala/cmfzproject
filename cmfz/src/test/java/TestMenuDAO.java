import com.baizhi.bpf.cmfz.dao.MenuDAO;
import com.baizhi.bpf.cmfz.entity.Menu;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMenuDAO {
    @Test
    public void testQueryMenu(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        MenuDAO menuDAO = (MenuDAO) ctx.getBean("menuDAO");
        List<Menu> menus = menuDAO.selectAllMenu();
        for (Menu menu:menus
             ) {
            System.out.println(menu);
            for (Menu m:menu.getChilds()
                 ) {
                System.out.println(m);
            }
        }
    }
}
