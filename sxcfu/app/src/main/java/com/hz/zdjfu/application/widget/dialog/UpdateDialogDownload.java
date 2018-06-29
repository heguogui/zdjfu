package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class UpdateDialogDownload extends Dialog implements View.OnClickListener{

    private UpdateDialogCancleListener mUpdateDialogCancleListener;
    private UpdateDialigSureListener mUpdateDialigSureListener;
    private String mTitle;
    private String mContent;
    private String mVersionCode;
    private boolean isForce;
    private ImageView mTvCancle;
    private TextView mTvSure;
    private TextView mTvTitle;
    private TextView mTvCodeName;
    private TextView mTvContent;
    private TextView mTvVersionCode;
    private View view;
    private  Context mContext;
    public UpdateDialogDownload(Context context, UpdateDialogCancleListener updateDialogCancleListener, UpdateDialigSureListener updateDialigSureListener, String title, String content, String versionCode, boolean isForce) {
        super(context, R.style.common_alert_dialog);
        this.mUpdateDialogCancleListener = updateDialogCancleListener;
        this.mUpdateDialigSureListener = updateDialigSureListener;
        this.mTitle = title;
        this.mContent = content;
        this.mVersionCode = versionCode;
        this.isForce = isForce;
        this.mContext =context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_update_download_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        mTvCancle = findViewById(R.id.update_dialog_cancle);
        mTvSure = (TextView) findViewById(R.id.update_dialog_sure);
        mTvCodeName = (TextView) findViewById(R.id.update_dialog_text_version_codename);
        mTvTitle = (TextView) findViewById(R.id.text_title);
        mTvContent = (TextView) findViewById(R.id.update_dialog_text_version_content);
        mTvVersionCode = (TextView) findViewById(R.id.update_dialog_text_version_code2);
        view = findViewById(R.id.dialog_update_line_button);
        mTvCancle.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
        mTvTitle.setText(mTitle+"");
        mTvContent.setText(mContent+"");
        mTvCodeName.setText(mVersionCode+"");
        mTvVersionCode.setText("版本" + mVersionCode);
        if (isForce) {
            mTvCancle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.update_dialog_cancle) {
            mUpdateDialogCancleListener.callback();
        } else if (v.getId() == R.id.update_dialog_sure) {
            if(mUpdateDialigSureListener!=null){
                mUpdateDialigSureListener.callback();
            }else{
                ToastUtils.show(mContext,"监听回调失败");
            }
        }

    }

    public interface UpdateDialogCancleListener {
        void callback();
    }

    public interface UpdateDialigSureListener {
        void callback();
    }
}
