package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hz.zdjfu.application.R;

/**
 * [类功能说明]
 *开通银行账户弹框
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class OpenBankAccountDialog extends Dialog implements View.OnClickListener{

    private OpenBankAccountCancleListener mUpdateDialogCancleListener;
    private OpenBankAccountSureListener mUpdateDialigSureListener;
    private ImageView mTvCancle;
    private Button mTvSure;


    public OpenBankAccountDialog(Context context, OpenBankAccountCancleListener updateDialogCancleListener, OpenBankAccountSureListener updateDialigSureListener) {
        super(context, R.style.common_alert_dialog);
        this.mUpdateDialogCancleListener = updateDialogCancleListener;
        this.mUpdateDialigSureListener = updateDialigSureListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_openbankaccount_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        mTvCancle = (ImageView) findViewById(R.id.update_dialog_cancle);
        mTvSure = (Button) findViewById(R.id.update_dialog_sure);
        mTvCancle.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.update_dialog_cancle) {
            mUpdateDialogCancleListener.callback();
        } else if (v.getId() == R.id.update_dialog_sure) {
            mUpdateDialigSureListener.callback();
        }

    }

    public interface OpenBankAccountCancleListener {
        void callback();
    }

    public interface OpenBankAccountSureListener {
        void callback();
    }


}
