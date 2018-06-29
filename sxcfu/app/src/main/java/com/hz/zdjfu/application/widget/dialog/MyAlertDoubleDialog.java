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
public class MyAlertDoubleDialog extends Dialog implements View.OnClickListener {

    private String title;
    private String content;
    private ConfirmListener confirmListener;
    private CancelListener cancelListener;
    protected TextView titleTv;
    private TextView contentTv;
    private TextView confirmTv;
    private TextView cancelTv;


    public MyAlertDoubleDialog(Context context, String title, String content, ConfirmListener confirmListener, CancelListener cancelListener) {
        super(context, R.style.common_alert_dialog);
        this.title = title;
        this.content = content;
        this.confirmListener = confirmListener;
        this.cancelListener = cancelListener;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alert_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        titleTv = (TextView) findViewById(R.id.title);
        contentTv = (TextView) findViewById(R.id.content);
        confirmTv = (TextView) findViewById(R.id.confirm_tv);
        cancelTv = (TextView) findViewById(R.id.cancel_tv);

        confirmTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);

        if(TextUtils.isEmpty(title)){
            titleTv.setVisibility(View.GONE);
        }else {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }

        contentTv.setText(content);

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
