import com.baizhi.bpf.cmfz.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class TestPoi {
    @Test
    public void testCreateExcel() throws IOException {
        Workbook workBook = new HSSFWorkbook();
        Sheet sheet1 = workBook.createSheet("sheet1");
        Row row = sheet1.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("第一行第一列第一个单元格");

        CellStyle cellStyle = workBook.createCellStyle();
        sheet1.setColumnWidth(0,7000);
        Font font = workBook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");

        workBook.write(new FileOutputStream("D://测试1.xls"));

    }
    @Test
    public void testReflection(){
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field field:declaredFields
             ) {
            System.out.println(field.getType().getName());
                    String getMethod="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
            System.out.println(getMethod);
        }
//        for (Field field:declaredFields
//             ) {
//            System.out.println(field.getDeclaredAnnotation(UserAnnotation.class).name());
//        }
    }


    @Test
    public void testCatsInt(){
        Double d=65.0;
        int i= Integer.parseInt(new java.text.DecimalFormat("0").format(d));
        System.out.println(i);
    }
}
