package com.hz.zdjfu.application.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/4 0004.
 */

public class TimeAllUtils {


    public static final SimpleDateFormat DEFAULT_YM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_DATE_YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FEN_MAO_FORMAT =new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat DEFAULT_DATE_MDHM_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_YM_DATE_CHINA_FORMAT = new SimpleDateFormat("yyyy年MM月");
    private TimeAllUtils() {
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


    public static String changeTime(long timeInMillis){
        return getTime(timeInMillis,DATE_FEN_MAO_FORMAT);
    }


}
