import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestDEmo {
    @Test
    public void testDemo(){
        for (int i = 0; i <10 ; i++) {
            for (int j = 10-i; j >i ; j--) {
                System.out.print("*");
            }
            System.out.println();
        };new HashMap();
    }

    @Test
    public void testTime() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d=new Date();
        String date=sdf.format(d);
        Thread.sleep(3000);
        Date d2=new Date();
        String date2=sdf.format(d2);
        System.out.println((d2.getTime()-d.getTime())/1000);
    }
}
