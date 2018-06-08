import com.baizhi.bpf.cmfz.dao.UserDAO;
import com.baizhi.bpf.cmfz.entity.User;
import com.baizhi.bpf.cmfz.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestUserDAO {
    @Test
    public void testQueryAllUserInPage(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.selectAllUserInthePage(0,5));
    }

    @Test
    public void testCountUser(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.countAllUser());
    }

    @Test
    public void testQueryAllMaleUserInProvince(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.selectAllMaleUserInProvince());
    }

    @Test
    public void testQueryAllFeMaleUserInProvince(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.selectAllFemaleUserInProvince());
    }

    @Test
    public void testTimeDown() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        Date parse = sdf.parse(format);
        System.out.println(new Date().getTime());
    }

    @Test
    public void testSelUserNearlyThreeWeek() throws ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserService us = (UserService) ctx.getBean("userServiceImpl");
//        Map<String, Integer[]> map = us.queryUserRecentyThreeWeek();
//        for (Integer s:map.get("count")
//             ) {
//            System.out.println(s);
//        }
        System.out.println(us.queryUserRecentyThreeWeek());
    }

    @Test
    public void testUpdate(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setStatus("y");
        User user2= new User();
        user2.setId(2);
        user2.setStatus("y");
        users.add(user);
        users.add(user2);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        userDAO.updateUserStatts(users);
    }
    @Test
    public void testLogin(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        System.out.println(userDAO.queryUserByPhone("12345678977"));
    }

    @Test
    public void testInsert(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        User user = new User();
        user.setPhoneNum("95865493721");
        user.setPassword("e6e407b1edb2cca3def82992c8ef32d9");
        user.setSalt("qwer");
        userDAO.insertUserPhoneAndUserPassword(user);
        System.out.println(user);

    }
    @Test
    public void testUpdateUserMessage(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        User user = new User();
        user.setId(1);
        user.setSign("哈哈哈");
        user.setDate(new Date());
        user.setUserName("我是神医");
        userDAO.updateUserMessage(user);
    }



}
