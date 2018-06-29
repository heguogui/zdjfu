package com.hz.zdjfu.application.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * [类功能说明]
 *  金额工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class AmountUtils {

    /**
     * BigDecimal 转化字符串
     * @param amount
     * @return
     */
    public static String formatAmount(BigDecimal amount) {

        if (amount == null) {
            return " 0.00";
        }
        String mResult = String.valueOf(amount);
        return mResult;
    }


    /**
     * 获取小数点位数
     * @param content
     * @param scale
     * @return
     */
    public static float formatAmount(String content,int scale) {
        try{
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "精度不能为0");
            }
            if(TextUtils.isEmpty(content)){
                return 0.0f;
            }
            BigDecimal decimal = new BigDecimal(content);
            return decimal.setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
        }catch (Exception e){
            return 0.0f;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String getCommaFormat(String value) {

        try {
            return parseMoney(",###,##0.00", new BigDecimal(value));
        } catch (Exception e) {
            return "0.00";
        }

    }

    //自定义数字格式方法
    public static String getFormat(String style, BigDecimal value) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(style);// 将格式应用于格式化器
        return df.format(value.doubleValue());
    }

    /*  BigDecimal bd=new BigDecimal(123456789);
     System.out.println(mf.parseMoney(",###,###",bd)); //out: 123,456,789

     System.out.println(mf.parseMoney("##,####,###",bd)); //out: 123,456,789

     System.out.println(mf.parseMoney("######,###",bd)); //out: 123,456,789

     System.out.println(mf.parseMoney("#,##,###,###",bd)); //out: 123,456,789

     System.out.println(mf.parseMoney(",###,###.00",bd)); //out: 123,456,789.00

     System.out.println(mf.parseMoney(",###,##0.00",bd)); //out: 123,456,789.00

     BigDecimal bd=new BigDecimal(0);
     System.out.println(mf.parseMoney(",###,###",bd)); //out: 0

     System.out.println(mf.parseMoney(",###,###.00",bd)); //out: .00

     System.out.println(mf.parseMoney(",###,##0.00",bd)); //out: 0.00*/
    public static String parseMoney(String pattern, BigDecimal bd) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(bd);
    }

    /**
     * 三个数相乘
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static String mul3(String v1, String v2, String v3) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal b3 = new BigDecimal(v3);
        return b1.multiply(b2).multiply(b3).toString();
    }

    /**
     * 两个数相加
     * @param v1
     * @param v2
     * @return
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }


    /**
     * 两个数相减
     * @param v1
     * @param v2
     * @return
     */
    public static String sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }



    /**
     * 两个数相加
     * @param v1
     * @param v2
     * @return
     */
    public static Long addLong(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).longValue();
    }


    /**
     * 两个数相减
     * @param v1
     * @param v2
     * @return
     */
    public static Long subLong(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).longValue();
    }


    /**
     * 两个数相乘
     * @param v1
     * @param v2
     * @return
     */
    public static String mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * 两个数相除
     * @param v1
     * @param v2
     * @param scale 精确度
     * @return
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "精度不能为0");
        }

        if(!TextUtils.isEmpty(v1)&&!TextUtils.isEmpty(v2)){
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
        }else {
            return "0.00";
        }
    }


    /**
     * 两个数相除
     * @param v1
     * @param v2
     * @param scale 精确度
     * @return
     */
    public static float divFloat(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "精度不能为0");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 提供精确的小数位四舍五入处理
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "精度不能为0");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 两个数据是否相等
     * @param v1
     * @param v2
     * @return
     */
    public static boolean compareTo(String v1, String v2){
        if(TextUtils.isEmpty(v1)||TextUtils.isEmpty(v2)){
            throw new IllegalArgumentException("数据不能为空");
        }
        BigDecimal mV1 = new BigDecimal(v1);
        BigDecimal mV2 = new BigDecimal(v2);
        if(mV1.compareTo(mV2)==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断大小
     * @param v1
     * @param v2
     * @return true  大于 其他 false
     */
    public static boolean compareToGreater(String v1, String v2){
        if(TextUtils.isEmpty(v1)||TextUtils.isEmpty(v2)){
            throw new IllegalArgumentException("数据不能为空");
        }
        BigDecimal mV1 = new BigDecimal(v1);
        BigDecimal mV2 = new BigDecimal(v2);
        if(mV1.compareTo(mV2)==1){
            return true;
        }else {
            return false;
        }
    }

    //截取小数点前两位
    public static  String sqlitDoubleStr(double amount){

        try {
            String mAmount =String.valueOf(amount);
            if(!TextUtils.isEmpty(mAmount)){
                if(mAmount.contains(".")){
                    String[] mSplit =mAmount.split("\\.");
                    return mSplit[0];
                }else{
                    return mAmount;
                }
            }else{
                return "0";
            }
        }catch (Exception e){
            return "0";
        }
    }


}
