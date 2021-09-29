package com.redrain.common.utils;

import com.redrain.common.exception.CustomerException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/9 22:44
 */
public class SaltUtils {

    /**
     * 普通MD5
     *
     * @param input
     * @return
     * @author daniel
     * @time 2016-6-11 下午8:00:28
     */
    public static String getMd5Str(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new CustomerException("请检查jdk");
        } catch (Exception e) {
            throw new CustomerException("salt生成异常");
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static String getRandomStrForSalt() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int randomNum;
        char randomChar;
        Random random = new Random();
        // StringBuffer类型的可以append增加字符
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < 13; i++) {
            // 可生成[0,n)之间的整数，获得随机位置
            randomNum = random.nextInt(base.length());
            // 获得随机位置对应的字符
            randomChar = base.charAt(randomNum);
            // 组成一个随机字符串
            str.append(randomChar);
        }
        return str.toString();
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String randomStr = getRandomStrForSalt();
//        System.out.println(randomStr);
//        System.out.println("123456");
//        System.out.println(getMd5Str("123456" + randomStr)); //06d67ba5c756d6202cf21a4576d95d7e
//    }
}
