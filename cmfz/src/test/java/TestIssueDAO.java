import com.baizhi.bpf.cmfz.dao.IssueDAO;
import com.baizhi.bpf.cmfz.entity.Issue;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIssueDAO {
    @Test
    public void testSeleclALL(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        IssueDAO issueDAO = (IssueDAO) ctx.getBean("issueDAO");
        System.out.println(issueDAO.selectAllIssue(0,3));
    }
    @Test
    public void testInsert(){

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        IssueDAO issueDAO = (IssueDAO) ctx.getBean("issueDAO");
        Issue issue = new Issue();
        issue.setName("神医");
        issue.setScore(4D);
        issue.setAuthor("joker");
        issue.setBrief("王猛师兄");
        issue.setImg("#");
        issue.setCounts(0);
        issueDAO.insertIssue(issue);
    }

    @Test
    public void testDel(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        IssueDAO issueDAO = (IssueDAO) ctx.getBean("issueDAO");
        issueDAO.deleteIssue(3);
    }

    @Test
    public void testUpdate(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        IssueDAO issueDAO = (IssueDAO) ctx.getBean("issueDAO");
        Issue issue = new Issue();
        issue.setId(5);
        issue.setName("神医");
        issue.setScore(4D);
        issue.setAuthor("joker");
        issue.setBrief("王猛师兄");
        issue.setImg("#");
        issue.setCounts(0);
        issueDAO.updateIssue(issue);

    }


}
