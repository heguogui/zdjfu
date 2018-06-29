package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.AmountUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class CircleProgressView extends View{

    private static final String TAG = "CircleProgressBar";

    private float mMaxProgress = 100.0f;

    private float mProgress = 30.0f;

    private final int mCircleLineStrokeWidth = 8;

    private final int mTxtStrokeWidth = 2;

    // 画圆所在的距形区域
    private final RectF mRectF;

    private final Paint mPaint;

    private final Context mContext;

    private String mTxtHint1;

    private String mTxtHint2;

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置画笔相关属性
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.gray3));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y
        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectF, -90, 360, false, mPaint);

//        if(mProgress==0){
//            mPaint.setColor(getResources().getColor(R.color.gray3));
//        }else{
//            mPaint.setColor(getResources().getColor(R.color.gold7));
//        }

        mPaint.setColor(getResources().getColor(R.color.gold7));

        //绘制进度
        float mScale = mProgress / mMaxProgress;
        float progressBar = AmountUtils.formatAmount(String.valueOf(mScale),4)*360;
        canvas.drawArc(mRectF, -90,progressBar, false, mPaint);

        // 绘制进度文案显示
       // if(mProgress>0){
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            StringBuffer buffer = new StringBuffer();
            try{
                String mContext =String.valueOf(AmountUtils.formatAmount(String.valueOf(mProgress),2));
                String[] context =mContext.split("\\.");
                int mLength =context[1].length();
                buffer.append(context[0]);
                if(mLength==0){
                    buffer.append(".00");
                }else if(mLength==1){
                    buffer.append("."+context[1]+"0");
                }else if(mLength==2) {
                    buffer.append("."+context[1]);
                }
            }catch (Exception e){

            }

//            String text = AmountUtils.formatAmount(String.valueOf(mProgress),2)+"%";
            String text = buffer.toString()+"%";
            int textHeight = height / 4;
            mPaint.setTextSize((3*textHeight)/4);
            int textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 4, mPaint);

            if (!TextUtils.isEmpty(mTxtHint1)) {
                mPaint.setStrokeWidth(mTxtStrokeWidth);
                text = mTxtHint1;
                textHeight = height / 8;
                mPaint.setTextSize(textHeight);
                mPaint.setColor(getResources().getColor(R.color.gold7));
                textWidth = (int) mPaint.measureText(text, 0, text.length());
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, width / 2 - textWidth / 2, height / 4 + textHeight / 2, mPaint);
            }

            if (!TextUtils.isEmpty(mTxtHint2)) {
                mPaint.setStrokeWidth(mTxtStrokeWidth);
                text = mTxtHint2;
                textHeight = height / 8;
                mPaint.setTextSize(textHeight);
                textWidth = (int) mPaint.measureText(text, 0, text.length());
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, mPaint);
            }

      // }
    }

    public float getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public String getmTxtHint1() {
        return mTxtHint1;
    }

    public void setmTxtHint1(String mTxtHint1) {
        this.mTxtHint1 = mTxtHint1;
    }

    public String getmTxtHint2() {
        return mTxtHint2;
    }

    public void setmTxtHint2(String mTxtHint2) {
        this.mTxtHint2 = mTxtHint2;
    }


}
