package com.baizhi.bpf.cmfz.serviceimpl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.bpf.cmfz.commmon.LogAnnotation;
import com.baizhi.bpf.cmfz.commmon.UserAnnotation;
import com.baizhi.bpf.cmfz.dao.UserDAO;
import com.baizhi.bpf.cmfz.entity.User;
import com.baizhi.bpf.cmfz.service.UserService;
import com.baizhi.bpf.cmfz.vo.ProvinceVO;
import com.baizhi.bpf.cmfz.vo.UserVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 分页查询方法
     *
     * @param userVO 需要提供被查询的页码--page与页面数据条数--pageSize
     * @return 返回包含分页数据的vo对象
     */
    @Override
    public UserVO queryUserInThePage(UserVO userVO) {
        Integer counts = userDAO.countAllUser();
        userVO.setTotal(counts);
        Integer page = userVO.getPage();
        Integer pageSize = userVO.getPageSize();
        Integer beginRow = (page - 1) * pageSize;
        List<User> rows = userDAO.selectAllUserInthePage(beginRow, pageSize);
        userVO.setRows(rows);
        return userVO;
    }

    /**
     * 查询性别男的地区注册情况
     *
     * @return
     */
    @Override
    public List<ProvinceVO> queryAllMaleUserInTheProvince() {
        return userDAO.selectAllMaleUserInProvince();
    }

    /**
     * 查询女用户的地区注册情况
     *
     * @return
     */
    @Override
    public List<ProvinceVO> queryAllFemaleUserInTheProvince() {
        return userDAO.selectAllFemaleUserInProvince();
    }

    @Override
    public Integer[] queryUserRecentyThreeWeek() throws ParseException {
          Map<String,Integer[]> map = new HashMap<String,Integer[]>();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String format = sdf.format(date);
//        Date parse = sdf.parse(format);
//        Long oneWeekEnd = countTime(parse.getTime(), 7 * 1000 * 60 * 60 * 24L);
//        Long oneWeekBegin = countTime(parse.getTime(), 14* 1000 * 60 * 60 * 24L);
//        Long twoWeekEnd = countTime(parse.getTime(), 14* 1000 * 60 * 60 * 24L);
//        Long twoWeekBegin = countTime(parse.getTime(), 21* 1000 * 60 * 60 * 24L);
//        Long threeWeekEnd = countTime(parse.getTime(), 21* 1000 * 60 * 60 * 24L);
//        Long threeWeekBegin = countTime(parse.getTime(), 28* 1000 * 60 * 60 * 24L);
//        Integer oneCounts = userDAO.selectUserBetweenTime(oneWeekBegin/100, oneWeekEnd/100);
//        Integer twoCounts = userDAO.selectUserBetweenTime(twoWeekBegin/100, twoWeekEnd/100);
//        Integer threeCounts = userDAO.selectUserBetweenTime(threeWeekBegin/100, threeWeekEnd/100);
//        map.put("count",new Integer[]{oneCounts,twoCounts,threeCounts});
        Integer oneWeekCounts = userDAO.selectUserBetweenTime(0,7);
        Integer twoWeekCounts = userDAO.selectUserBetweenTime(7,14);
        Integer threeWeekCounts = userDAO.selectUserBetweenTime(14,21);
        return new Integer[]{threeWeekCounts,twoWeekCounts,oneWeekCounts};
    }
    //自定义用户信息导出
    @Override
    @LogAnnotation(name="导出所有用户信息")
    public void customExportUserMessage(String titles, String fields, ServletOutputStream out) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        List<User> users = userDAO.selectAllUser();
        Workbook workbook = exportUserMessage(titles, fields, users);
        workbook.write(out);
        workbook.close();
    }
    //自定义用户信息导入
    @Override
    @LogAnnotation(name="上传用户信息文件批量更新用户数据")
    public void inportUserMessage(MultipartFile userMessageFile) throws IOException, NoSuchFieldException, IllegalAccessException {
        String oldFilename = userMessageFile.getOriginalFilename();

        String nowFilename=UUID.randomUUID().toString().replaceAll("-","")+oldFilename.substring(oldFilename.lastIndexOf("."));
        //现将文件写到本地磁盘中
        File file = new File("E:\\apache-tomcat-7.0.70\\webapps\\cmfzuserdata\\" + nowFilename);
        userMessageFile.transferTo(file);
        if(file.exists()){
            Map<String, String> fieldMap = getClazzAnnotationsAndFields(User.class);
            //文件上传完毕后尝试获取workbook对象
            FileInputStream fileInputStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            //获取sheet对象总数
            int sheetLists = workbook.getNumberOfSheets();
            ArrayList<User> userList = new ArrayList<>();
            for(int i=0;i<sheetLists;i++){
                //获取sheet
                HSSFSheet sheet = workbook.getSheetAt(i);
                //通过sheet对象获取row对象总数,最大单元格所在下标,所以单元格数量需要+1
                int allRowNum = sheet.getLastRowNum()+1;
                //获取标题行
                HSSFRow titleRow = sheet.getRow(0);
                short lastCellNum = titleRow.getLastCellNum();
                ArrayList<String> titles = new ArrayList<String>();
                for (int j=0;j<lastCellNum;j++){
                    HSSFCell cell = titleRow.getCell(j);
                    String value = cell.getStringCellValue();
                    titles.add(value);
                }
                System.out.println(titles);
                //第一行为标题行，尝试获取下列数据行
                //j为所在行
                for(int j=allRowNum;j>1;j--){
                    User user = new User();
                    HSSFRow dataRow = sheet.getRow(j - 1);
                    for (int k=0;k<lastCellNum;k++){
                        HSSFCell cell = dataRow.getCell(k);
                        //获得对象的属性名
                        String fieldName = fieldMap.get(titles.get(k));
                        Field field = user.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Class<?> type = field.getType();
////                        if(type.getName()=="java.util.Date"){
////                            field.set(user,cell.getDateCellValue());
////                        }else if(type.getName()=="java.lang.String"){
////                            field.set(user,cell.getStringCellValue());
////                        }else if(type.getName()=="java.lang.Integer"){
////                            field.set(user,(int)cell.getNumericCellValue());
////                        }else if(type.getName()=="java.lang.Double"){
////                            field.set(user,cell.getNumericCellValue());
////                        }else if(type.getName()=="java.lang.Boolean"){
////                            field.set(user,cell.get);
//                        }
                        Object obj=null;

                        try {
                            obj=cell.getStringCellValue();
                            if(obj!=null){
                                user=setUserFileds(user, field, obj);
                                continue;
                            }
                        } catch (Exception e) {
//                            e.printStackTrace();
                        }
                        try {

                            obj=cell.getBooleanCellValue();
                            if(obj!=null){
                                user=setUserFileds(user, field, obj);
                                continue;
                            }
                        } catch (Exception e) {

//                            e.printStackTrace();
                        }
                        try {

                            obj=cell.getDateCellValue();
                            if(obj!=null){
                                user=setUserFileds(user, field, obj);
                                continue;
                            }
                        } catch (Exception e) {

//                            e.printStackTrace();
                        }
                        try {
                            double numericCellValue = cell.getNumericCellValue();
                            user=setUserFileds(user, field,numericCellValue);
                            continue;
                        } catch (Exception e) {
//                            e.printStackTrace();
                        };
                    };

                    userList.add(user);
                };
            };
            userDAO.updateUserStatts(userList);
//            System.out.println(userList.size()+"list size--------------------------------------------");
//            for (User u:userList
//                 ) {
//                System.out.println(u+"---------------------------------------------");
//            }
        };
    }




    //计算时间戳差值
    private Long countTime(Long nowTimeStamp,Long subTimeStamp){
        return nowTimeStamp-subTimeStamp;
    }

    //用户信息导出]
    private Workbook exportUserMessage(String titles,String fields,List<User> users) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //处理标题，与属性值
        String[] titleList = titles.split(",");
        String[] fieldList = fields.split(",");
        System.out.println((titleList.length==fieldList.length)+"two lenth is equals--------------------------------------");
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建时间格式化对象
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short timeFormat = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(timeFormat);

        HSSFSheet sheet1 = workbook.createSheet("sheet1");

        //创建标题行
        HSSFRow titleRow = sheet1.createRow(0);
        for(int i=0;i<titleList.length;i++){
            HSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellValue(titleList[i]);
        }

        //填充数据
        for(int i=0;i<users.size();i++){
            HSSFRow dataRow = sheet1.createRow(i+1);
            for(int j=0;j<fieldList.length;j++){
                HSSFCell cell = dataRow.createCell(j);
                //拼接字符串的到get方法
                String getMethod="get"+fieldList[j].substring(0,1).toUpperCase()+fieldList[j].substring(1);
                //获得get方法的返回值
                Object returnInvoke = User.class.getMethod(getMethod, null).invoke(users.get(i), null);
                //判断返回值的类型是否为日期类型
                if(returnInvoke instanceof Date){
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue((Date)returnInvoke);
                    sheet1.setColumnWidth(j,18*256);
                }else if(returnInvoke instanceof Integer){
                    cell.setCellValue((Integer)returnInvoke);
                }else if(returnInvoke instanceof Double){
                    cell.setCellValue((Double)returnInvoke);
                }else if(returnInvoke instanceof String){
                    cell.setCellValue((String) returnInvoke);
                }else if(returnInvoke instanceof Boolean){
                    cell.setCellValue((Boolean)returnInvoke);
                }else if (returnInvoke instanceof Calendar){
                    cell.setCellValue((Calendar)returnInvoke);
                }else{
                    cell.setCellValue((RichTextString) returnInvoke);
                }
            }
        }

        return workbook;
    }

    //获得目标对象的注解与属性值
    private Map<String,String> getClazzAnnotationsAndFields(Class clazz){
        HashMap<String, String> map = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(int i=0;i<declaredFields.length;i++){
            map.put(declaredFields[i].getAnnotation(UserAnnotation.class).name(),declaredFields[i].getName());
        }
        return map;
    }

    //给对象属性赋值
    private User setUserFileds(User user,Field field,Object value) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> type = field.getType();
        String typeName = type.getName();
        if(typeName.endsWith("byte")||typeName.endsWith("Byte")){
            field.set(user,Byte.parseByte(new java.text.DecimalFormat("0").format(value)));
        }else if(typeName.endsWith("short")||typeName.endsWith("Short")){
            field.set(user,Short.parseShort(new java.text.DecimalFormat("0").format(value)));
        }else if(typeName.endsWith("int")||typeName.endsWith("Integer")){
            field.set(user,Integer.parseInt(new java.text.DecimalFormat("0").format(value)));
        }else if(typeName.endsWith("long")||typeName.endsWith("Long")){
            field.set(user,Long.parseLong(new java.text.DecimalFormat("0").format(value)));
        }else if(typeName.endsWith("char")||typeName.endsWith("Character")){
            field.set(user,(char)value);
        }else if(typeName.endsWith("float")||typeName.endsWith("Float")){
            field.set(user,(float)value);
        }else if(typeName.endsWith("double")||typeName.endsWith("Double")){
            field.set(user,(double)value);
        }else if(typeName.equals("java.util.Date")){
            field.set(user,(Date)value);
        }else if(typeName.endsWith("String")){
            field.set(user,(String)value);
        }else{
            field.set(user,null);
        }
        return user;
    }


    @Override
    public User userLogin(String phoneNum, String password, String code) {
        User user=null;
        if(password==null){
            //链接redis，判断验证码是否正确
            user = userDAO.queryUserByPhone(phoneNum);
        }else{
            User dbUser=userDAO.queryUserByPhone(phoneNum);
            if(dbUser!=null&&dbUser.getPassword().equals(password))user=dbUser;
        }
        return user;
    }

    @Override
    public User registerUser(String phoneNum, String password) {
        User user =null;
        User dbUser = userDAO.queryUserByPhone(phoneNum);
        if(dbUser==null){
            user=new User();
            //生成随机盐
            user.setSalt("qwer");
            user.setPassword(password);
            user.setPhoneNum(phoneNum);
            userDAO.insertUserPhoneAndUserPassword(user);
        }
        return user;
    }


    @Override
    public User updateUserMessage(User user,byte[] photo) throws IOException {
        ByteBuffer bs = ByteBuffer.wrap(photo);
        String name = "aaaa.img";//设置文件路径
        FileChannel fc=null;
        try {
            fc= new FileOutputStream(name).getChannel();
            fc.write(bs);
            user.setHeadPic(name);
            userDAO.updateUserMessage(user);
        }catch (IOException e) {
            e.printStackTrace();
            user=null;
        }
            fc.close();
        return user;
    }


    @Override
    public void getPhoneMessageCode(String phone) throws ClientException {
        String messageCode = "1234";
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAIVklKMao8KhdB";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "zHNTbj3TFbEC9PkSpL9wM5HGIqXxdY";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("后期项目CMFZ短信验证");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_136383183");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\""+messageCode+"\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }
        //将messageCode存入redis
    }

    @Override
    public void checkPhoneCodeIsRight(String phone, String code) {
        //链接redis获取手机号
        System.out.println(phone);
    }

    @Override
    public List<User> getUserListInNumber(Integer uid) {
        List<User> users = userDAO.queryUserMember(uid);
        Random random = new Random();
        List<User> userList = new ArrayList<User>();
        String s="";
        if(users.size()>=6){
        for (int i = 0; i <7 ; i++) {
            int index=random.nextInt(users.size());
            if(s.indexOf(index)!=-1){
                userList.add(users.get(index));
                s+=index;
            }else{
                i--;
            }

        }
        }else{
            userList=users;
        }
        return userList;
    }
}
