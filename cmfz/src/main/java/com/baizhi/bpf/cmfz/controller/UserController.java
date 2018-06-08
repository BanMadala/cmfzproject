package com.baizhi.bpf.cmfz.controller;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.bpf.cmfz.commmon.UserAnnotation;
import com.baizhi.bpf.cmfz.entity.Issue;
import com.baizhi.bpf.cmfz.entity.Picture;
import com.baizhi.bpf.cmfz.entity.User;
import com.baizhi.bpf.cmfz.service.IssueService;
import com.baizhi.bpf.cmfz.service.PictureService;
import com.baizhi.bpf.cmfz.service.UserService;
import com.baizhi.bpf.cmfz.util.IndexPageMessageUtil;
import com.baizhi.bpf.cmfz.vo.ProvinceVO;
import com.baizhi.bpf.cmfz.vo.UserFields;
import com.baizhi.bpf.cmfz.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private IssueService issueService;


    public IssueService getIssueService() {
        return issueService;
    }

    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public UserService getUserService() {

        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    /**
     * @param page 目标也页
     * @param rows 每页条数
     * @return 装在数据的分页对象
     */
    @RequestMapping(value = "/queryAllUserByPage", produces = "application/json;charset=utf-8")
    public UserVO queryAllUser(Integer page, String rows) {
        UserVO userVO = new UserVO();
        userVO.setPage(page);
        userVO.setPageSize(Integer.parseInt(rows));
        return userService.queryUserInThePage(userVO);
    }


    @RequestMapping(value = "/queryAllMaleUserByProvince", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProvinceVO> queryAllMaleUserByProvince() {
        return userService.queryAllMaleUserInTheProvince();
    }


    @RequestMapping(value = "/queryAllFemalUserByProvince", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProvinceVO> queryAllFemalUserByProvince() {
        return userService.queryAllFemaleUserInTheProvince();
    }

    @RequestMapping(value = "/queryUserInTheNearThreadWeek", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map queryUserInTheNearThreadWeek() throws ParseException {
        HashMap map = new HashMap();
        map.put("week", new String[]{"前一周", "上一周", "本周"});
//        map.put("counts",new Integer[]{new Random().nextInt(100),new Random().nextInt(100),new Random().nextInt(100)});
        Integer[] counts = userService.queryUserRecentyThreeWeek();
        map.put("counts", counts);
        return map;
    }

    //    @RequestMapping(value="/customExportUserMessage",produces = "application/vnd.ms-excel")
    @RequestMapping(value = "/customExportUserMessage", produces = "application/vnd.ms-excel")
    public void customExportUserMessagfe(String titles, String fields, HttpServletResponse res) throws IOException {
        ServletOutputStream outputStream = res.getOutputStream();
        System.out.println(titles + "========================" + fields);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
            res.setContentType("application/application/vnd.ms-excel");
            String fileName = "用户数据" + sdf.format(new Date()) + ".xls";
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            userService.customExportUserMessage(titles, fields, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/queryAllFiledAndAnnotations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserFields> queryAllUserFields() {
        ArrayList<UserFields> list = new ArrayList<>();
        //获取包括已经被封装的属性
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field f : declaredFields
                ) {
            UserFields uf = new UserFields();
            uf.setId(f.getName());
            uf.setText(f.getDeclaredAnnotation(UserAnnotation.class).name());
            list.add(uf);
        }
        return list;
    }

    @RequestMapping(value = "/inportUserMessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map inportUserMessage(MultipartFile userMessaageFile, HttpServletResponse res) {
        HashMap map = new HashMap();
//        System.out.println(userMessaageFile+" in inportUserMessage---------------------------------------------");
        try {
            userService.inportUserMessage(userMessaageFile);
            map.put("message", "信息批量插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "信息批量插入失败");
        }
        return map;
    }

    //    服务端接口------------------------------------------------------------------------------------
    @RequestMapping(value = "/first_page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map indexPageInterface(Integer uid, String type, String sub_type) {
//        JsonObject jsonObject = new JsonObject();
        HashMap<String, Object> map = new HashMap<>();
        if (uid != null) {//判断是否登录
            if (type != null) {//判断请求是否完整
                switch (type) {//判断请求种类 all/wen/si
                    case IndexPageMessageUtil.TYPE_ALL:
                        System.out.println("用户访问首页,调用业务查询轮播图，文章与专辑");
                        List<Picture> headers = pictureService.queryAllNeedPicture();
                        List<Issue> albums = issueService.getAllIssueForShow();
                        map.put("header",headers);//查询到的轮播图
                        map.put("album",albums);//查询到的专辑
                        map.put("artical","artical");//查询到的文章
                        break;
                    case IndexPageMessageUtil.TYPE_WEN:
                        System.out.println("用户访问专辑页，调用业务查询专辑");
                        List<Issue> al = issueService.getAllIssueForShow();
                        map.put("album",al);//查询到的专辑;
                        break;
                    case IndexPageMessageUtil.TYPE_SI:
                        System.out.println("用户访问文章页面，需要用户选择功能");
                        if (sub_type != null) {
                            if (IndexPageMessageUtil.SUB_TYPE_SSYJ.equals(sub_type)) {
                                System.out.println("查询当前上师文章");
                                map.put("artical","artical");//调用业务查询当前所属上师的文章
                            }
                            if (IndexPageMessageUtil.SUB_TYPE_XMFY.equals(sub_type)) {
                                System.out.println("查询文章排行");
                                map.put("artical","artical");//调用业务查询所有上师文章，以热度排序
                            }
                        } else {
                            System.out.println("返回首页");
                            List<Picture> headers2 = pictureService.queryAllNeedPicture();
                            List<Issue> albums2 = issueService.getAllIssueForShow();
                            map.put("header",headers2);//查询到的轮播图
                            map.put("album",albums2);//查询到的专辑
                            map.put("artical","artical");//查询到的文章
                        }
                        break;
                    default:
                        System.out.println("请求数据错误，返回登录界面?");
                        break;
                }
            } else {

            }
            map.put("message", "测试成功");
//            jsonObject.addProperty("message","测试成功");


        } else {
//            jsonObject.addProperty("message","请登录");
            System.out.println("=============================================");
            map.put("message", "请登录");
        }
        return map;

    }



    //登录接口
    @RequestMapping(value="/userLogin",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //可能需要返回一个User的子类对象方便使用多态?或者返回自己拼接的字符串
    public Object userLogin(String phone,String password,String code){
        HashMap map = new HashMap();
        User user=null;
        if(phone==null||(password==null&&code==null)){
            map.put("error","-200");
            map.put("errmsg","请输入完整的用户信息");
        }else{
            if(password==null){
                user=userService.userLogin(phone, null, code);
                if(user==null){
                    map.put("error", "-200");
                    map.put("errmsg","抱歉您输入的验证码不正确");
                }
            }else{
               user= userService.userLogin(phone, password,null);
                if(user==null){
                    map.put("error", "-200");
                    map.put("errmsg","抱歉您输入的账号密码不正确");
                }
            }
        }
        if(user==null){
        return map;
        }else{
        return user;
        }
    }


    //注册接口
    @RequestMapping(value = "userRegist",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object userRegist(String phone,String password){
        HashMap<String, String> map = new HashMap<>();
        User user =null;
        if(phone!=null&&password!=null){
            user=userService.registerUser(phone, password);
            if(user==null){
                map.put("errno","-200");
                map.put("error_msg","抱歉，该手机号已经被注册");
            }
        }else{
            map.put("errno","-200");
            map.put("error_msg","用户信息不完整，请再次填写");
        }
        if(user==null){
        return map;
        }else{
        return user;
        }
    }

    //修改个人信息接口
    @RequestMapping(value = "/usermodify",produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object modifyUserMessage(Integer uid,String gender,byte[] photo,String location,
    String description,String nickname,String province,String city,String password) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        User user = new User();
        user.setId(uid);
        user.setSex(gender);
        user.setSign(description);
        user.setUserName(nickname);
        user.setProvince(province);
        user.setCity(city);
        user.setPassword(password);
        user = userService.updateUserMessage(user, photo);
        if(user==null){
            map.put("errno","-200");
            map.put("error_msg","用户信息修改失败");
        return map;
        }else{
            return user;
        }
    }

    //获取短信验证
    @RequestMapping(value = "/obtain",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getMessageCode(String phone) throws ClientException {
        userService.getPhoneMessageCode(phone);
    }

    @RequestMapping(value="/checkPhoneMessageCode",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map checkPhoneCodeIsRignt(String phone,String code){
        HashMap<String, String> map = new HashMap<>();
        try {
            userService.checkPhoneCodeIsRight(phone,code);
            map.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","error");
        }
        return map;
    }

    @RequestMapping(value="/queryMember",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> queryMember(Integer uid){
        return userService.getUserListInNumber(uid);
    }


}
