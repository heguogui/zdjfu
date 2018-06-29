package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.PasswordInputView;

/**
 * [类功能说明]
 *密码付款认证弹框
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class SureSMSDialog extends Dialog implements View.OnClickListener{

    private CancleListener mCancleListener;
    private SureListener mSureListener;
    private ResetSMSListener mResetSMSListener;
    private ImageView mTvCancle;
    private Button mTvSure;
    private PasswordInputView mPasswordView;
    private Context mContext;
    private String mPhone;
    private TextView mContent;
    private boolean mState;
    public SureSMSDialog(Context context,CancleListener mCancleListener,SureListener mSureListener,ResetSMSListener mResetSMSListener,String phone,boolean state) {
        super(context, R.style.common_alert_dialog);
        this.mCancleListener = mCancleListener;
        this.mSureListener = mSureListener;
        this.mResetSMSListener =mResetSMSListener;
        this.mContext =context;
        this.mPhone =phone;
        this.mState =state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_suresms_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        mTvCancle = (ImageView) findViewById(R.id.suresms_dialog_cancle);
        mTvSure = (Button) findViewById(R.id.suresms_dialog_getsms);
        mPasswordView =(PasswordInputView)findViewById(R.id.suresms_dialog_password);
        mPasswordView.addTextChangedListener(new smsEditTextOnListen());
        mContent =(TextView)findViewById(R.id.suresms_dialog_phone);

        if(!TextUtils.isEmpty(mPhone)){
            String mHandlePhone = "付款确认: 本次交易需要短信验证,短信验证码已发送至"+StringUtils.desenPhoneNumber(mPhone);
            SpannableString sp = new SpannableString(mHandlePhone);
            sp.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.blue5)),26,mHandlePhone.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mContent.setText(sp);
        }else{
          ToastUtils.show(mContext,"预留手机号为空");
        }
        mTvCancle.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
        if(mState){
            mTvSure.setBackground(mContext.getResources().getDrawable(R.drawable.gray_radius5));
            triggerCountDown();
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.suresms_dialog_cancle) {
            mCancleListener.callback();
        } else if (v.getId() == R.id.suresms_dialog_getsms) {
           //重新获取短信验证
            if(StringUtils.isFastClick()){
                return;
            }
            mResetSMSListener.callback(mTvSure,true);
        }

    }

    public interface CancleListener {
        void callback();
    }

    public interface SureListener {
        void callback(String sms);
    }


    public interface ResetSMSListener{
        void callback(Button view,boolean state);
    }

    /**
     * 开始密码输入监听
     */
    private class smsEditTextOnListen implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            isCheckFullNumber();
        }
    }

    /**
     * 判断是否密码填满
     */
    private void isCheckFullNumber() {

        String mPassword = mPasswordView.getText().toString().trim();
        if ((!TextUtils.isEmpty(mPassword) && mPassword.length() == 6)) {
            mSureListener.callback(mPassword);
        }
    }


    public void triggerCountDown() {
        if(mTvSure!=null){
            mTvSure.setClickable(false);
            new Thread(new Runnable() {
                int sum = 60;

                @Override
                public void run() {
                    while (sum >= 0) {
                        if (mContext == null) {
                            return;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sum--;
                        mHandler.sendEmptyMessage(sum);
                    }
                }
            }).start();

        }

    }



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            if (mContext == null) {
                return;
            }
            if (msg.what > 0) {
                mTvSure.setText(msg.what + "s");
            } else {
                mTvSure.setText("重新获取");
                mTvSure.setBackground(mContext.getResources().getDrawable(R.drawable.stroke_blue_solid_blue_radius5));
                mTvSure.setClickable(true);
            }
        }
    };



}
