package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;

/**
 * [类功能说明]
 *app 更新弹框
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class UpdateDialog extends Dialog implements View.OnClickListener{

    private UpdateDialogCancleListener mUpdateDialogCancleListener;
    private UpdateDialigSureListener mUpdateDialigSureListener;
    private String mTitle;
    private String mContent;
    private ImageView mTvCancle;
    private TextView mTvSure;
    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvCodeName;
    private String mCodeName;
    private View view;
    private boolean isForce;

    public UpdateDialog(Context context, UpdateDialogCancleListener updateDialogCancleListener, UpdateDialigSureListener updateDialigSureListener, String title, String content,String codename,boolean isForce) {
        super(context, R.style.common_alert_dialog);
        this.mUpdateDialogCancleListener = updateDialogCancleListener;
        this.mUpdateDialigSureListener = updateDialigSureListener;
        this.mTitle = title;
        this.mContent = content;
        this.mCodeName =codename;
        this.isForce = isForce;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_updataversion_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        mTvCancle = (ImageView) findViewById(R.id.update_dialog_cancle);
        mTvSure = (TextView) findViewById(R.id.update_dialog_sure);
        mTvCodeName = (TextView) findViewById(R.id.update_dialog_text_version_code);
        mTvTitle =findViewById(R.id.text_title);
        mTvContent = (TextView) findViewById(R.id.update_dialog_text_version_content);
        mTvCancle.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
        mTvTitle.setText(mTitle);
        mTvContent.setText(mContent);
        mTvCodeName.setText(mCodeName+"");
        if (isForce) {
            mTvCancle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.update_dialog_cancle) {
            mUpdateDialogCancleListener.callback();
        } else if (v.getId() == R.id.update_dialog_sure) {
            mUpdateDialigSureListener.callback();
        }

    }

    public interface UpdateDialogCancleListener {
        void callback();
    }

    public interface UpdateDialigSureListener {
        void callback();
    }


}
