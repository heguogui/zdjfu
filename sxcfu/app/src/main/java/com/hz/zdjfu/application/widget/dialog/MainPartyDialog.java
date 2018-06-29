package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MainPartyBean;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 活动弹框
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/6/8 0008.
 */

public class MainPartyDialog extends Dialog implements View.OnClickListener{

    ImageView partyBgIv;
    ImageView partyCloseIv;
    private PartyDialogListener mPartyDialogListener;
    private MainPartyBean mBean;
    private Context mContext;

    public MainPartyDialog(Context context, MainPartyBean bean, PartyDialogListener partyDialogListener) {
        super(context, R.style.common_alert_dialog);
        this.mPartyDialogListener = partyDialogListener;
        this.mBean = bean;
        this.mContext =context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_party_view);
        this.setCanceledOnTouchOutside(true);

        partyBgIv =findViewById(R.id.party_bg_iv);
        partyCloseIv=findViewById(R.id.party_close_iv);
        partyBgIv.setOnClickListener(this);
        partyCloseIv.setOnClickListener(this);

        if(mBean!=null&&!TextUtils.isEmpty(mBean.getImageUrl())){
            ImageLoader.getInstance().displayRounedCornersImage(mContext,UiUtils.URLEncoderFileImage(mBean.getImageUrl()),partyBgIv);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.party_bg_iv:
                if(mBean!=null&&!TextUtils.isEmpty(mBean.getHrefUrl())){
                    mPartyDialogListener.callback(mBean);
                }
                dismiss();
                break;
            case R.id.party_close_iv:
                dismiss();
                break;
        }
    }

    public interface PartyDialogListener {
        void callback(MainPartyBean mBean);
    }


    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }
}
