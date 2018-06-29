/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * [类功能说明]
 * 时间工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_YM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_DATE_YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FEN_MAO_FORMAT =new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat DEFAULT_DATE_MDHM_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_YM_DATE_CHINA_FORMAT = new SimpleDateFormat("yyyy年MM月");
    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }


    public static Date getYMDate(String content){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            return df.parse(content);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Date getYMDayDate(String content){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            return df.parse(content);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static String getYMStr(String content){
        try {
            return getYMStr(DEFAULT_DATE_YMD_FORMAT.parse(content));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getMDHMStr(String content){
        try {
            return getMDHMStr(DEFAULT_DATE_FORMAT.parse(content));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static String getYMStr(Date month){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
            return df.format(month);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getYMDStr(Date month){
        try {
            return DEFAULT_DATE_YMD_FORMAT.format(month);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getMDHMStr(Date month){
        try {
            return DEFAULT_DATE_MDHM_FORMAT.format(month);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static String changeDayStr(Date month){
        try {
            return DEFAULT_YM_DATE_FORMAT.format(month);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getYMDtime(long timeInMillis) {
        if(timeInMillis==0){
            return "";
        }
        return getTime(timeInMillis, DEFAULT_DATE_YMD_FORMAT);
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getYMtime(long timeInMillis) {
        if(timeInMillis==0){
            return "";
        }
        return getTime(timeInMillis, DEFAULT_YM_DATE_FORMAT);
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getYMtimeStr(long timeInMillis) {
        if(timeInMillis==0){
            return "";
        }
        return getTime(timeInMillis,DEFAULT_YM_DATE_CHINA_FORMAT);
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getHMMtime(long timeInMillis) {
        if(timeInMillis==0){
            return "";
        }
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getMDHMtime(long timeInMillis) {
        if(timeInMillis==0){
            return "";
        }
        return getTime(timeInMillis, DEFAULT_DATE_MDHM_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }


    /**
     * 日期逻辑
     *
     * @param date 时间
     * @return
     */
    public static String timeLogic(Date date) {

        String str = DEFAULT_DATE_FORMAT.format(date);
        String hourTime = DATE_FORMAT_DATE.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        long now = calendar.getTimeInMillis();
        calendar.setTime(date);
        long past = calendar.getTimeInMillis();

        // 相差的秒数
        long time = (now - past) / 1000;

        StringBuffer sb = new StringBuffer();
        if (time > 0 && time < 60) { // 1分钟内
            return sb.append(time + "秒前").toString();
        } else if (time > 60 && time < 3600) {//1小时内
            return sb.append(time / 60 + "分钟前").toString();
        } else if (time >= 3600 && time < 3600 * 24) {
            return hourTime;
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            return sb.append("昨天" + hourTime).toString();
        } else {
            return str;
        }

    }


    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(long startdate,long enddate) throws ParseException
    {
        if(startdate==0||enddate==0){
            return 0;
        }
        try {
            long between_days=(enddate-startdate)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        }catch (Exception e){

        }
        return 0;
    }


    public static String changeTime(long timeInMillis){
        return getTime(timeInMillis,DATE_FEN_MAO_FORMAT);
    }


    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            int day =timeDistance + (day2-day1);
            if(day<0){
                return 0;
            }else{
                return timeDistance + (day2-day1) ;
            }
        }
        else    //不同年
        {
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            int day =day2-day1;
            if(day<0){
                day =0;
            }
            return day;
        }
    }


}
