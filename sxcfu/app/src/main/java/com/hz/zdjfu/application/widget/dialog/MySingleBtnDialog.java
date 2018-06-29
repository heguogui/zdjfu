package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hz.zdjfu.application.R;

/**
 * [类功能说明]
 * 取消 确认弹框
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class MySingleBtnDialog extends Dialog implements View.OnClickListener {

    private String title;
    private String content;
    private ConfirmListener confirmListener;
    protected TextView titleTv;
    private TextView contentTv;
    private TextView sureTv;
    private String mSureStr;

    public MySingleBtnDialog(Context context, String title, String content,String sureStr,ConfirmListener confirmListener) {
        super(context, R.style.common_alert_dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
        this.mSureStr =sureStr;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_alert_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        titleTv = (TextView) findViewById(R.id.title);
        contentTv = (TextView) findViewById(R.id.content);
        sureTv = (TextView) findViewById(R.id.confirm_tv);
        sureTv.setOnClickListener(this);
        titleTv.setText(title);
        contentTv.setText(content);
        if(!TextUtils.isEmpty(mSureStr)){
            sureTv.setText(mSureStr);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_tv: {
                confirmListener.callback(true);
            }
        }
    }

    public interface ConfirmListener {
        void callback(boolean state);
    }


}
