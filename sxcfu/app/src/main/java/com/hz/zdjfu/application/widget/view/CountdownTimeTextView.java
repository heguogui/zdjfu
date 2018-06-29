package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;

import java.util.Map;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/11 0011
 */
public class CountdownTimeTextView extends android.support.v7.widget.AppCompatTextView implements Runnable{


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

    private TextView txt,txtenjoy;
    private LinearLayout layout;
    private Map data;
    private String showText;


    public CountdownTimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.CountdownTimeTextView);
        array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public CountdownTimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.CountdownTimeTextView);
        array.recycle(); // 一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public CountdownTimeTextView(Context context) {
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
     * @param txt 可以点击的按钮
     * @param layout 把自己倒计时view隐藏
     */
    public void setTimes(long period, TextView txt, TextView txtenjoy, LinearLayout layout,Map data,String str) {

        this.txt = txt;
        this.txtenjoy = txtenjoy;
        this.layout = layout;
        this.showText = str;

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

        // Log.e("willIssueTime==", year+"年"+month+"月"+day+"天"+hour+"时"+minute+"分"+second+"秒");

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

    public void setTimes(int[] times, TextView txt, TextView txtenjoy,LinearLayout layout,Map data,String str) {

        this.txt = txt;
        this.layout = layout;
        this.txtenjoy = txtenjoy;
        this.data = data;
        this.showText = str;

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

        String strTime = this.showText +""+ mhourStr + ": " + mminStr + ": "+ msecondStr;

        this.setText(strTime);

        /*if (mday <= 0 && mhour == 0 && mmin == 0 && msecond == 0) {
            return;
        }*/

        if ( mhour == 0 && mmin == 0 && msecond == 0) {

            if(null != txt)
            {
                this.txt.setVisibility(View.VISIBLE);
            }
            // 倒计时按钮隐藏
            if(layout!=null){
                this.layout.setVisibility(View.GONE);
            }
            if(txtenjoy!=null){
                this.txtenjoy.setVisibility(View.GONE);
            }
            if(null != data){
                this.data.put("willIssueTime","0");
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
