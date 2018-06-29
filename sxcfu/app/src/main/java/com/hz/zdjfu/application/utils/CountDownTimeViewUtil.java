package com.hz.zdjfu.application.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/11 0011
 */
public class CountDownTimeViewUtil extends CountDownTimer {

    private final long yearLevelValue = 365*24*60*60*1000 ;
    private final long monthLevelValue = 30*24*60*60*1000 ;
    private final long dayLevelValue = 24*60*60*1000 ;
    private final long hourLevelValue = 60*60*1000 ;
    private final long minuteLevelValue = 60*1000 ;
    private final long secondLevelValue = 1000 ;
    private TextView mShowTv;
    private boolean isfinish =false;
    /**
     * 构造函数
     * @param millisInFuture 总时间
     * @param countDownInterval 每次减时间
     * @param showTv 显示View
     */
    public CountDownTimeViewUtil(long millisInFuture, long countDownInterval,TextView showTv) {
        super(millisInFuture, countDownInterval);
        this.mShowTv =showTv;

    }

    @Override
    public void onTick(long millisUntilFinished) {

        mShowTv.setText(getHours(millisUntilFinished));
        if (isfinish){
            mShowTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinish() {


    }



    public int getYear(long period){
        return (int) (period/yearLevelValue);
    }
    public int getMonth(long period){
        return (int) (period/monthLevelValue);
    }
    public int getDay(long period){
        return (int) (period/dayLevelValue);
    }
    public int getHour(long period){
        return (int) (period/hourLevelValue);
    }
    public int getMinute(long period){
        return (int) (period/minuteLevelValue);
    }
    public int getSecond(long period){
        return (int) (period/secondLevelValue);
    }



    public  String getHours(long period) {

        int year = getYear(period) ;
        int month = getMonth(period - year*yearLevelValue) ;
        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue) ;
        int minute = getMinute(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue) ;
        int second = getSecond(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue - minute*minuteLevelValue) ;

        String rHour = "";
        String rMin = "";
        String rSs = "";
        // 时
        if (hour < 10) {
            rHour = "0" + hour;
        } else {
            rHour = hour + "";
        }
        // 分
        if (minute < 10) {
            rMin = "0" + minute;
        } else {
            rMin = minute + "";
        }
        // 秒
        if (second < 10) {
            rSs = "0" + second;
        } else {
            rSs = second + "";
        }
        if(day==0&&hour==0&&minute==0&&second==1){
            isfinish =true;
        }

        String strTime =null;
        if(day<1){
            strTime =  rHour + "时" + rMin + "分"+ rSs+"秒";
        }else{
            strTime = day+"天"+ rHour + "时" + rMin + "分"+ rSs+"秒";
        }

        return strTime;

    }
}
