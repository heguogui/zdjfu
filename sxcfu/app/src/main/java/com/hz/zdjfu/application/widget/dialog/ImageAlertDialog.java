package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;

/**
 * [类功能说明]
 * 取消 确认弹框
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class ImageAlertDialog extends Dialog implements View.OnClickListener {

    private String title;
    private String content;
    private ConfirmListener confirmListener;
    private CancelListener cancelListener;
    protected TextView titleTv;
    private TextView contentTv;
    private TextView confirmTv;
    private TextView cancelTv;
    private ImageView background_iv;
    private int drawable_ID =0;
    private int mGravity = -1;
    private String confirmStr;
    private String cancleStr;
    private boolean mDirection;
    private Context mContext;


    public ImageAlertDialog(Context context, String title, String content, ConfirmListener confirmListener, CancelListener cancelListener) {
        super(context, R.style.common_alert_dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
        this.cancelListener = cancelListener;
        mGravity = Gravity.LEFT;
        this.mContext =context;
    }

    public ImageAlertDialog(Context context, String title, String content, ConfirmListener confirmListener, CancelListener cancelListener, int gravity) {
        super(context, R.style.common_alert_dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
        this.cancelListener = cancelListener;
        mGravity = gravity;
        this.mContext =context;
    }



    public ImageAlertDialog(Context context, String title, String content,int bgiv,String confirmStr,String cancleStr,ConfirmListener confirmListener, CancelListener cancelListener,boolean leftcolor) {
        super(context, R.style.common_alert_dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
        this.cancelListener = cancelListener;
        this.confirmStr =confirmStr;
        this.cancleStr =cancleStr;
        this.drawable_ID =bgiv;
        this.mDirection =leftcolor;
        this.mContext =context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_image_alert_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        titleTv = (TextView) findViewById(R.id.title);
        contentTv = (TextView) findViewById(R.id.content);
        confirmTv = (TextView) findViewById(R.id.confirm_tv);
        cancelTv = (TextView) findViewById(R.id.cancel_tv);
        background_iv =findViewById(R.id.background_iv);

        if (mGravity != -1)
            titleTv.setGravity(mGravity);

        confirmTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);

        if(!TextUtils.isEmpty(title)){
            titleTv.setText(title);
        }

        if(!TextUtils.isEmpty(content)){
            contentTv.setText(content);
        }

        if(!TextUtils.isEmpty(cancleStr)){
            cancelTv.setText(cancleStr);
        }

        if(!TextUtils.isEmpty(confirmStr)){
            confirmTv.setText(confirmStr);
        }

        if(drawable_ID!=0){
            background_iv.setVisibility(View.VISIBLE);
            background_iv.setImageResource(drawable_ID);
        }else{
            background_iv.setVisibility(View.GONE);
        }

        if(mDirection){
            cancelTv.setTextColor(mContext.getResources().getColor(R.color.blue5));
            confirmTv.setTextColor(mContext.getResources().getColor(R.color.colorBack3));
        }else{
            cancelTv.setTextColor(mContext.getResources().getColor(R.color.colorBack3));
            confirmTv.setTextColor(mContext.getResources().getColor(R.color.blue5));
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_tv: {
                confirmListener.callback();
            }
            break;
            case R.id.cancel_tv: {
                cancelListener.callback();
            }
            break;
        }
        dismiss();
    }

    public interface ConfirmListener {
        void callback();
    }

    public interface CancelListener {
        void callback();
    }

}
