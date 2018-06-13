package com.baizhi.bpf.springbootcmfz.service;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.bpf.springbootcmfz.entity.User;
import com.baizhi.bpf.springbootcmfz.vo.ProvinceVO;
import com.baizhi.bpf.springbootcmfz.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface UserService {
    public UserVO queryUserInThePage(UserVO userVO);

    public List<ProvinceVO> queryAllMaleUserInTheProvince();

    public List<ProvinceVO> queryAllFemaleUserInTheProvince();

    public Integer[] queryUserRecentyThreeWeek() throws ParseException;

    public void customExportUserMessage(String titles, String fields, ServletOutputStream out) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException;

    public void inportUserMessage(MultipartFile userMessageFile) throws IOException, NoSuchFieldException, IllegalAccessException;

    public User userLogin(String phoneNum,String password,String code);

    public User registerUser(String phoneNum,String password);

    public User updateUserMessage(User user, byte[] photo) throws IOException;

    public void getPhoneMessageCode(String phone) throws ClientException;

    public void checkPhoneCodeIsRight(String phone,String code);

    public List<User> getUserListInNumber(Integer uid);
}
