package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *实名认证
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/12 0012.
 */

public class NameAuthenticationFragment extends OpenSHBankBaseFragment {


    @BindView(R.id.nameandidcard_name_et)
    EditText nameandidcardNameEt;
    @BindView(R.id.nameandidcard_idcard)
    EditText nameandidcardIdcard;
    @BindView(R.id.namecertification_next_btn)
    Button namecertificationNextBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nameandidcard;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && null != mActivity.getCurrentFocus()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                return mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
            }
            return false;
        });

        nameandidcardNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)&& !TextUtils.isEmpty(nameandidcardIdcard.getText().toString())) {
                    namecertificationNextBtn.setEnabled(true);
                } else {
                    namecertificationNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        nameandidcardIdcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)&& !TextUtils.isEmpty(nameandidcardNameEt.getText().toString())) {
                    namecertificationNextBtn.setEnabled(true);
                } else {
                    namecertificationNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }


    @OnClick(R.id.namecertification_next_btn)
    public void onViewClicked() {

        if (ToastUtils.isFastDoubleClick())//防止双击
            return;

        String mName =nameandidcardNameEt.getText().toString().trim();
        if(TextUtils.isEmpty(mName)){
            return;
        }
        String idcardId =nameandidcardIdcard.getText().toString().trim();
        if(TextUtils.isEmpty(idcardId)){
            return;
        }


        String mIp = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIp)){
            ToastUtils.show(mActivity,"手机Ip为空");
            return;
        }

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
            ToastUtils.show(mActivity,"手机号码为空");
            return;
        }

        String mSign =HttpsUtils.getHttpRequestSignNoIp(mBean.getUserPhone(),idcardId,mName);
        DialogManager.showProgressDialog(mActivity, "认证中...");

        RetrofitUtil.createService().nameCertification(mBean.getUserPhone(),idcardId,mName,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                ToastUtils.show(mActivity,"认证成功");
                UserBean mBean =UserInfoManager.getInstance().getLocationUserData();
                mBean.setNameCertification(true);
                UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
                //跳转至绑卡页面
                if(((OpenSHBankAccountActivity)mActivity).openSHBankAccountFragment!=null){
                    ((OpenSHBankAccountActivity)mActivity).openSHBankAccountFragment.changeViewState(false,true,false);
                    ((OpenSHBankAccountActivity)mActivity).openSHBankAccountFragment.switchFragment(FragmentFactory.FRAGMENT_BINDBANKCARD,null);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                ToastUtils.show(mActivity,e.getMessage()+"");
            }
        });

    }
}
