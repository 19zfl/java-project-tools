package com.zfl19.configs.utils;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc 工具类
 * @author 19zfl
 */
@Slf4j
public class MyTools {

    public static void main(String[] args){
        System.out.println(SecureUtil.md5("123456"));
    }

    /**
     * @desc 判断字符串是否有长度
     * @author wujiangbo
     */
    public static boolean hasLength(String str) {
        return org.springframework.util.StringUtils.hasLength(str);
    }

    /**
     * @desc 获取token值
     * @author wujiangbo
     */
    public static String getLoginToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description: 将本地图片转成Base64数据
     */
    public static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.error("图片转Base64数据时发生异常:{}", e.getLocalizedMessage());
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return "data:img/jpg;base64," + encoder.encode(Objects.requireNonNull(data));
    }

    /*
     * @Description: 验证密码，必须是6-18位长度，必须由数字和字母组成，并且要同时含有数字和字母，且长度要在6-18位之间
     */
    public static boolean checkPassword(String str){
        boolean checkResult = false;
        if(!StringUtils.isBlank(str)){
            String regEx = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证字母和数字，必须是6-18位长度
     */
    public static boolean checkNumChar(String str){
        boolean checkResult = false;
        if(!StringUtils.isBlank(str)){
            String regEx = "^[0-9A-Za-z]{6,18}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证手机号
     */
    public static boolean checkPhone(String phone){
        boolean checkResult = false;
        if(!StringUtils.isBlank(phone)){
            if(phone.length() == 11){
                String regEx = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0, 5-9]))\\d{8}$";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(phone);
                if(m.matches()){
                    checkResult = true;
                }
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证邮箱格式
     */
    public static boolean checkEmail(String email){
        boolean checkResult = false;
        if(!StringUtils.isBlank(email)){
            String regEx = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(email);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    /**
     * @Description: 获取指定位数的随机整数
     */
    public synchronized static String getRandomNum(int num) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= num; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    /**
     * @description: 获取指定串中随机指定位数的字符串
     */
    public synchronized static String getRandomChar(int num) {
        //先定义取值范围
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < num; i++) {
            value.append(chars.charAt((int)(Math.random() * chars.length())));
        }
        return value.toString();
    }

    /*
     * @Description: 获取当前时间字符串(yyyyMMddHHmmss)
     */
    public synchronized static String getCurrentTimeStr() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /*
     * @Description: 根据MultipartFile文件获取图片Base64数据
     */
    public static String MutipartFileToBase64(MultipartFile file) {
        String base64EncoderImg = "";
        try {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            base64EncoderImg = "data:img/jpg;base64," + base64Encoder.encode(file.getBytes());
        } catch (Exception e) {
            log.error("根据MultipartFile文件获取Base64数据，异常：{}", e);
        }
        return base64EncoderImg;
    }


    //随机获取指定区间的整数
    public synchronized static String getRandomDotString(int min, int max) {
        Random rand = new Random();
        StringBuffer result = new StringBuffer();
        result.append(rand.nextInt(max - min + 1) + min);
        return result.toString();
    }
}
