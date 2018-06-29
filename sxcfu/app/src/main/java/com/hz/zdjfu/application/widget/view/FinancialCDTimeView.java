package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.data.bean.ZTProductBean;
import com.hz.zdjfu.application.modle.financial.ZTFinancialAdapter;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/1 0001.
 */

public class FinancialCDTimeView extends android.support.v7.widget.AppCompatTextView implements Runnable{


    Paint mPaint; // 画笔,包含了画几何图形、文本等的样式和颜色信息
    private int[] times;
    private long mday, mhour, mmin, msecond;// 天，小时，分钟，秒
    private boolean run = false; // 是否启动了

    private final long yearLevelValue = 365*24*60*60*1000 ;
    private final long monthLevelValue = 30*24*60*60*1000 ;
    private final long dayLevelValue = 24*60*60*1000 ;
    private final long hourLevelValue = 60*60*1000 ;
    private final long minuteLevelValue = 60*1000 ;
    private final long secondLevelValue = 1000 ;

    private List<ZTProductBean> data;
    private  ZTFinancialAdapter mAdapter;
    private  FinancialCDTimeView mView;
    public FinancialCDTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.CountdownTimeTextView);
        array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public FinancialCDTimeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.CountdownTimeTextView);
        array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public FinancialCDTimeView(Context context) {
        super(context);
    }


    private String getDifference(long period) {

        //根据毫秒差计算时间差
        String result = "" ;

        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
        int year = getYear(period) ;
        int month = getMonth(period - year*yearLevelValue) ;
        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue) ;
        int minute = getMinute(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue) ;
        int second = getSecond(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue - minute*minuteLevelValue) ;

        result = year+"年"+month+"月"+day+"天"+hour+"时"+minute+"分"+second+"秒";
        return result;
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


    public int[] getTimes() {
        return times;
    }

    /**
     * @param period 毫秒
     */
    public void setTimes(long period,List<ProductBean> data) {


        int year = getYear(period) ;
        int month = getMonth(period - year*yearLevelValue) ;
        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue) ;
        int minute = getMinute(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue) ;
        int second = getSecond(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue - minute*minuteLevelValue) ;

        mday = day;
        mhour = hour + day*24;
        mmin = minute;
        msecond = second;

    }


    /**
     * @param period 毫秒
     */
    public void setTimes(long period, List<ZTProductBean> data, ZTFinancialAdapter adapter,FinancialCDTimeView view) {

        this.mAdapter =adapter;
        this.data =data;
        this.mView =view;

        int year = getYear(period) ;
        int month = getMonth(period - year*yearLevelValue) ;
        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue) ;
        int minute = getMinute(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue) ;
        int second = getSecond(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue - minute*minuteLevelValue) ;

        mday = day;
        mhour = hour + day*24;
        mmin = minute;
        msecond = second;

    }



    // 设置新的 倒计时 时间
    public void setPeriod(long period){

        int year = getYear(period) ;
        int month = getMonth(period - year*yearLevelValue) ;
        int day = getDay(period - year*yearLevelValue - month*monthLevelValue) ;
        int hour = getHour(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue) ;
        int minute = getMinute(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue) ;
        int second = getSecond(period - year*yearLevelValue - month*monthLevelValue - day*dayLevelValue - hour*hourLevelValue - minute*minuteLevelValue) ;

        mday = day;
        mhour = hour + day*24;
        mmin = minute;
        msecond = second;

    }

    public void setTimes(int[] times) {

        this.times = times;
        mday = times[0];
        mhour = times[1];
        mmin = times[2];
        msecond = times[3];
    }

    /**
     * 倒计时计算
     */
    private void ComputeTime() {
        msecond--;
        if (msecond < 0) {
            mmin--;
            msecond = 59;
            if (mmin < 0) {
                mmin = 59;
                mhour--;
                if (mhour < 0) {
                    // 倒计时结束
                    mhour = 59;
                    // mday--;
                }
            }
        }
    }

    public boolean isRun() {
        return this.run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {

        ComputeTime();

        String mhourStr = mhour+"";
        String mminStr = mmin+"";
        String msecondStr = msecond+"";

        if(mhourStr.length()<2){
            mhourStr = "0"+mhourStr;
        }
        if(mminStr.length()<2){
            mminStr = "0"+mminStr;
        }
        if(msecondStr.length()<2){
            msecondStr = "0"+msecondStr;
        }

        String strTime =  mhourStr + ": " + mminStr + ": "+ msecondStr;

        this.setText(strTime);

        if ( mhour == 0 && mmin == 0 && msecond == 0) {
            if(null != data){
                Integer pos = (Integer) mView.getTag();
                ZTProductBean  bean= data.get(pos);
                bean.setCountDown(0);
                bean.setStatus(4);
                data.set(pos,bean);
                mAdapter.notifyDataSetChanged();
            }
            run = false;
            return;
        }else{
            // 标示已经启动
            run = true;
        }

        postDelayed(this, 1000);
    }
}
