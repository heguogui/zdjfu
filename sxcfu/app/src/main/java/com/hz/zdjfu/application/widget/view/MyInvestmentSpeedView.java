package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;

/**
 * [类功能说明]
 *线圈
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class MyInvestmentSpeedView extends View {

    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔 ,多少天的数字 如：26
    private Paint mTextPaint,mTextBFPaint;
    // 画字体的画笔：多少"天到期"文字字体
    private Paint mTextNHPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 多少天到期的总进度
    private int mTotalProgress = 100;
    // 多少天到期的当前进度
    private int mProgress = 0;

    // 当前投资订单的状态
    private String currentStatus;
    private String currentStatusOther;
    private String days;// 多少天数
    private int mType;//类型
    public MyInvestmentSpeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TasksCompletedView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.TasksCompletedView_circleradius, 60);
        mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10);
        // 实心圆的颜色
        mCircleColor = typeArray.getColor(R.styleable.TasksCompletedView_circleColor, 0xFFFFFFFF);
        // 实心圆外面进度条的颜色
        mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0xFFFFFFFF);

        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    private void initVariable() {

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        //mCirclePaint.setStyle(Paint.Style.FILL);
        // mCirclePaint.setStrokeWidth(mStrokeWidth/2);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mStrokeWidth);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        //   多少天的数字字体
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getContext().getResources().getColor(R.color.gold7));
        // mTextPaint.setTextSize(mRadius / 2);
        mTextPaint.setTextSize(30);
        // 多少天到期的字体颜色
        mTextNHPaint = new Paint();
        mTextNHPaint.setAntiAlias(true);
        mTextNHPaint.setStyle(Paint.Style.FILL);
        mTextNHPaint.setColor(getContext().getResources().getColor(R.color.gray5));
        mTextNHPaint.setTextSize(28);


        mTextBFPaint = new Paint();
        mTextBFPaint.setAntiAlias(true);
        mTextBFPaint.setStyle(Paint.Style.FILL);
        mTextBFPaint.setColor(getContext().getResources().getColor(R.color.gold7));
        // mTextPaint.setTextSize(mRadius / 2);
        mTextBFPaint.setTextSize(30);


        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        // 圆形里面的圆形框
        RectF ovalOUT = new RectF();
		/*ovalOUT.left = (mXCenter - mRingRadius+30);
		ovalOUT.top = (mYCenter - mRingRadius+30);
		ovalOUT.right = mRingRadius * 2 + (mXCenter - mRingRadius-30);
		ovalOUT.bottom = mRingRadius * 2 + (mYCenter - mRingRadius-30);*/

        ovalOUT.left = (mXCenter - mRingRadius);
        ovalOUT.top = (mYCenter - mRingRadius);
        ovalOUT.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        ovalOUT.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);

        // canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        // drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter,
        // 360 度就是逆时针
        // -90 度就是顺时针
        canvas.drawArc(ovalOUT, -90, 360, false, mCirclePaint);

        // 最外面动态的加载圆形框
        RectF oval = new RectF();
        oval.left = (mXCenter - mRingRadius);
        oval.top = (mYCenter - mRingRadius);
        oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);

        mRingPaint.setColor(getResources().getColor(R.color.gray3));
        canvas.drawArc(oval, -90,360, false, mRingPaint);

        if(TextUtils.equals(Constants.PAY_STATUS_99999,currentStatus)){ // 加载中
            canvas.drawArc(oval, -90, 0 , false, mRingPaint);
            mTextNHPaint.setColor(getContext().getResources().getColor(R.color.gray5));
            canvas.drawText(getContext().getResources().getString(R.string.myinvest_wait_loading), mXCenter - mXCenter / 2-5, mYCenter + mTxtWidth/4, mTextNHPaint);
        }
        // 多少天到期
        else if(TextUtils.equals(Constants.PAY_STATUS_5,currentStatus)||TextUtils.equals(Constants.PAY_STATUS_7,currentStatus)||TextUtils.equals(Constants.PAY_STATUS_71,currentStatus)){//履行中

            String txt = days + "";
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            // canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint);
            // // 画圆弧，第二个参数为：起始角度，第三个为跨的角度(负数：逆时针，正数：顺时针)，第四个为true的时候是实心，false的时候为空心
            mTextNHPaint.setColor(getContext().getResources().getColor(R.color.red6));
            mRingPaint.setColor(getContext().getResources().getColor(R.color.gold7));
            canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint);

            // int start, int end, float x, float y
            canvas.drawText(txt, mXCenter - mTxtWidth / 2-5, mYCenter-9 , mTextPaint);
            mTextNHPaint.setColor(getContext().getResources().getColor(R.color.gray13));
            float mEndDayWidth = mTextNHPaint.measureText(getContext().getResources().getString(R.string.myinvest_day_end), 0,getContext().getResources().getString(R.string.myinvest_day_end).length());
//            canvas.drawText(getContext().getResources().getString(R.string.myinvest_day_end),mXCenter - mXCenter / 2+10, mYCenter + mTxtHeight/2, mTextNHPaint);
            canvas.drawText(getContext().getResources().getString(R.string.myinvest_day_end),mXCenter - mEndDayWidth / 2, mYCenter + mTxtHeight/2, mTextNHPaint);

        }else if(TextUtils.equals(Constants.PAY_STATUS_6,currentStatus)){ // 已回款
            mRingPaint.setColor(getContext().getResources().getColor(R.color.gray13));
            canvas.drawArc(oval, -90, 360 , false, mRingPaint);
            mTextNHPaint.setColor(getContext().getResources().getColor(R.color.gray13));
            float mbackWidth = mTextNHPaint.measureText(getContext().getResources().getString(R.string.myinvest_tag_backmoney_title), 0,getContext().getResources().getString(R.string.myinvest_tag_backmoney_title).length());
//            canvas.drawText(getContext().getResources().getString(R.string.myinvest_tag_backmoney_title), mXCenter - mXCenter / 2 , mYCenter+10, mTextNHPaint);
            canvas.drawText(getContext().getResources().getString(R.string.myinvest_tag_backmoney_title), mXCenter - mbackWidth / 2 , mYCenter+10, mTextNHPaint);

        }else if(TextUtils.equals(Constants.PAY_STATUS_4,currentStatus)){ // 锁定期

            if(mType==1){//债转
                String txt = days + "";
                mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
                // canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint);
                // // 画圆弧，第二个参数为：起始角度，第三个为跨的角度(负数：逆时针，正数：顺时针)，第四个为true的时候是实心，false的时候为空心
                mTextNHPaint.setColor(getContext().getResources().getColor(R.color.red6));
                mRingPaint.setColor(getContext().getResources().getColor(R.color.gold7));
                canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint);

                // int start, int end, float x, float y
                canvas.drawText(txt, mXCenter - mTxtWidth / 2-5, mYCenter-9 , mTextPaint);
                mTextNHPaint.setColor(getContext().getResources().getColor(R.color.gray13));
                float mEndDayWidth = mTextNHPaint.measureText(getContext().getResources().getString(R.string.myinvest_day_end), 0,getContext().getResources().getString(R.string.myinvest_day_end).length());
//            canvas.drawText(getContext().getResources().getString(R.string.myinvest_day_end),mXCenter - mXCenter / 2+10, mYCenter + mTxtHeight/2, mTextNHPaint);
                canvas.drawText(getContext().getResources().getString(R.string.myinvest_day_end),mXCenter - mEndDayWidth / 2, mYCenter + mTxtHeight/2, mTextNHPaint);

            }else if(mType==2){//直投
                mRingPaint.setColor(getContext().getResources().getColor(R.color.green0));
                canvas.drawArc(oval, -90, 360 , false, mRingPaint);
                mTextNHPaint.setColor(getContext().getResources().getColor(R.color.green0));
                float mbackWidth = mTextNHPaint.measureText(getContext().getResources().getString(R.string.myinvest_tag_lock_title), 0,getContext().getResources().getString(R.string.myinvest_tag_lock_title).length());
//            canvas.drawText(getContext().getResources().getString(R.string.myinvest_tag_backmoney_title), mXCenter - mXCenter / 2 , mYCenter+10, mTextNHPaint);
                canvas.drawText(getContext().getResources().getString(R.string.myinvest_tag_lock_title), mXCenter - mbackWidth / 2 , mYCenter+10, mTextNHPaint);
            }


        }
    }

    // 还剩多少天到期,回款中,已回款,待支付
    public void setProgress(int progress,String status,String dayVal,int type) {
        mProgress = progress;
        currentStatus = status;
        days = dayVal;
        // Log.e("ContcurrentStatus==",currentStatus);

//		invalidate();
        mType =type;
        postInvalidate();
    }
}
