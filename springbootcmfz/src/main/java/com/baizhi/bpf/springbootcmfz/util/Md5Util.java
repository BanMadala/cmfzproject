package com.baizhi.bpf.springbootcmfz.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    public static String getMd5String(String str){
        String md5=null;
        if(str!=null){
            md5 = DigestUtils.md5Hex(str);
        }
        return md5;
    }

    public static Boolean checkPassIsRigth(String pass,String dbPass){
        Boolean b=false;
        if(dbPass.equals(getMd5String(pass)))b=true;
        return b;
    }

}
