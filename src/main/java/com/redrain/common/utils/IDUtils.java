package com.redrain.common.utils;

import java.util.Random;


public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 商品id生成
     */
    public static long genGoodsId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上5位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return new Long(str);
    }


    /**
     * 订单id生成 毫秒值后跟着5位随机数
     */
    public static long genOrderId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上5位随机数
        Random random = new Random();
        int end2 = random.nextInt(99999);
        //如果不足两位前面补0
        String str = millis + String.format("%05d", end2);
        return new Long(str);
    }

    /**
     * 团长或团员id生成 毫秒值后跟着5位随机数
     */
    public static long genGrouponId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上5位随机数
        Random random = new Random();
        int end2 = random.nextInt(99999);
        //如果不足两位前面补0
        String str = millis + String.format("%05d", end2);
        return new Long(str);
    }


}
