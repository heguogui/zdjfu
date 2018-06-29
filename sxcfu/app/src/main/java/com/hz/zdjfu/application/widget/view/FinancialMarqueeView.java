package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.FinancialMarqueeBean;
import com.hz.zdjfu.application.utils.ScreenUtils;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [类功能说明]
 *理财产品跑马灯
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class FinancialMarqueeView extends ViewFlipper {


    private Context mContext;
    private List<FinancialMarqueeBean> notices;
    private boolean isSetAnimDuration = false;
    private MarqueeView.OnItemClickListener onItemClickListener;

    private MarqueeView.OnDisplayChagnedListener mListener;


    private int interval = 2000;
    private int animDuration = 500;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    private boolean singleLine = false;
    private int gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    private static final int TEXT_GRAVITY_LEFT = 0, TEXT_GRAVITY_CENTER = 1, TEXT_GRAVITY_RIGHT = 2;

    public FinancialMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (notices == null) {
            notices = new ArrayList<>();
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = ScreenUtils.px2sp(mContext, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        int gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, TEXT_GRAVITY_LEFT);
        switch (gravityType) {
            case TEXT_GRAVITY_CENTER:
                gravity = Gravity.CENTER;
                break;
            case TEXT_GRAVITY_RIGHT:
                gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        typedArray.recycle();

        setFlipInterval(interval);

        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    // 根据公告字符串列表启动轮播
    public void startWithList(List<FinancialMarqueeBean> notices) {
        if (notices == null || notices.isEmpty()) return;
        setNotices(notices);
        start();
    }


    // 启动轮播
    public boolean start() {
        if (notices == null || notices.size() == 0) return false;
        removeAllViews();
        for (int i = 0; i < notices.size(); i++) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.view_financial_warquee_layout,null);
            ImageView mPhote =mView.findViewById(R.id.financial_warquee_photo_iv);
            TextView mContent=mView.findViewById(R.id.financial_warquee_content_tv);
            TextView mTime =mView.findViewById(R.id.financial_warquee_time_tv);
            TextView mAmount =mView.findViewById(R.id.financial_warquee_amount_tv);
            FinancialMarqueeBean mBean =notices.get(i);
            if(mBean!=null){
//                if(!TextUtils.isEmpty(mBean.getmPhoto())){
//                    ImageLoader.getInstance().displayImage(mContext,mBean.getmPhoto(),mPhote);
//                }else{
//                    ImageLoader.getInstance().displayCircleImage(mContext,R.mipmap.ic_launcher,mPhote);
//                }
                if(!TextUtils.isEmpty(mBean.getPhone())){
                    mContent.setText("用户"+"**"+mBean.getPhone().substring(mBean.getPhone().length()-4,mBean.getPhone().length()));
                }

                if(!TextUtils.isEmpty(mBean.getAmount())){
                    if(mBean.getTradeType().equals("1")){
                        mAmount.setText("投资了¥"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount(),getResources().getString(R.string.defalut_money_separator)));
                    }else if(mBean.getTradeType().equals("2")){
                        mAmount.setText("充值了¥"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount(),getResources().getString(R.string.defalut_money_separator)));
                    }
                }

                mTime.setText(mBean.getTradeTime()+"");
            }

            addView(mView);
        }
        if (notices.size() > 0) {
            startFlipping();
        }
        return true;
    }


    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public List<FinancialMarqueeBean> getNotices() {
        return notices;
    }

    public void setNotices(List<FinancialMarqueeBean> notices) {
        this.notices = notices;
    }

    public void setOnItemClickListener(MarqueeView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, TextView textView);
    }

    @Override
    public void showNext() {
        super.showNext();

        if (mListener != null) {
            mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
        }
    }

    @Override
    public void showPrevious() {
        super.showPrevious();

        if (mListener != null) {
            mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
        }
    }

    public interface OnDisplayChagnedListener {
        void OnDisplayChildChanging(ViewFlipper view, int index);
    }

    public void setOnDisplayChagnedListener(MarqueeView.OnDisplayChagnedListener listener) {
        if (mListener != listener) {
            this.mListener = listener;
        }
    }


}
