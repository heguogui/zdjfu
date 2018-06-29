package com.hz.zdjfu.application.modle.opendeposit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.personcenter.PersonCenterActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.ImageAlertDialog;
import com.hz.zdjfu.application.widget.dialog.PCWheelViewDialog;
import com.hz.zdjfu.application.widget.dialog.WheelViewDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * [类功能说明]
 * 2.0开通银行存管
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/17 0017.
 */

public class OpenDepositFragment extends BaseFragment implements OpenDepositContract.View {

    private static final String TAG = OpenDepositFragment.class.getName();
    @BindView(R.id.nameandidcard_name_et)
    EditText nameandidcardNameEt;
    @BindView(R.id.nameandidcard_idcard)
    EditText nameandidcardIdcard;
    @BindView(R.id.namecertification_next_btn)
    Button namecertificationNextBtn;


    private OpenDepositContract.Presenter mPresenter;
    private ImageAlertDialog dialog;
    private String mName;
    private String mCardid;


    public static OpenDepositFragment newInstance() {
        return new OpenDepositFragment();
    }

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

        new OpenDepositPresenter(mActivity, this);


        initViewData();

    }

    @Override
    public void initViewData() {

        //判断是否认证了
        if(mPresenter!=null){
            mPresenter.isCheckUserAuthNameCardIdState();
        }

        nameandidcardNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeBtnState();
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
                changeBtnState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void setPresenter(OpenDepositContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void leftBtnOnClickListener() {
        showBackNoticationDialog();
    }

    @Override
    public void showBackNoticationDialog() {
        dialog = new ImageAlertDialog(mActivity, "提示", getString(R.string.openshbankaccount_back_notication_title), R.mipmap.notication_bg, getString(R.string.common_sure), getString(R.string.common_cancle), new ImageAlertDialog.ConfirmListener() {

            @Override
            public void callback() {
                dialog.dismiss();
                dialog = null;

                if (ZDJFUApplication.getInstance().MCURRENT_ACTIVITY != null && ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PERSONCENTER")) {//个人中来
                    startActivity(PersonCenterActivity.makeIntent(mActivity, null));
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY = null;
                }

                if (ZDJFUApplication.getInstance().MCURRENT_ACTIVITY != null && ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PRODUCTDETAIL")) {//产品详情中来
                    startActivity(ProductDetailActivity.makeIntent(mActivity, null));
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY = null;
                }

                if(ZDJFUApplication.getInstance().isCheckParent("ZTPRODUCTDETAIL")){//直投产品详情
                    ZDJFUApplication.getInstance().clearParent("ZTPRODUCTDETAIL");
                    mActivity.finish();
                }


                if (ZDJFUApplication.getInstance().MCURRENT_ACTIVITY != null && ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWOPENACCOUNT")) {//h5中来
                    Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                    mActivity.startActivity(mIntent);
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY = null;
                }

                mActivity.finish();
            }
        }, new ImageAlertDialog.CancelListener() {

            @Override
            public void callback() {
                dialog.dismiss();
                dialog = null;
            }
        }, true);
        dialog.show();
    }

    @Override
    public void changeBtnState() {

        mName = nameandidcardNameEt.getText().toString();
        mCardid = nameandidcardIdcard.getText().toString();
        if (!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mCardid)) {
            namecertificationNextBtn.setEnabled(true);
        } else {
            namecertificationNextBtn.setEnabled(false);
        }

    }

    @Override
    public void authNameResult(String url) {

        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL",url);
        mActivity.startActivity(mIntent);
        mActivity.finish();

    }

    @Override
    public void showAuthDetail(ZTUserDetailBean mZTUserDetailBean) {

        if(mZTUserDetailBean==null)
            return;

        if(mZTUserDetailBean.getAuthStatus()==1){
            nameandidcardNameEt.setEnabled(false);
            nameandidcardNameEt.setFocusable(false);
            this.mName =mZTUserDetailBean.getRealName();
            nameandidcardNameEt.setText(mZTUserDetailBean.getRealName()+"");
            nameandidcardIdcard.setEnabled(false);
            nameandidcardIdcard.setFocusable(false);
            this.mCardid =mZTUserDetailBean.getIdCardNo();
            nameandidcardIdcard.setText(mZTUserDetailBean.getIdCardNo());
        }


    }


    @Override
    public void showErrorTips(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }
    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showBackNoticationDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @OnClick(R.id.namecertification_next_btn)
    public void onViewClicked() {

        if(!TextUtils.isEmpty(mName)&&!TextUtils.isEmpty(mCardid)&&mPresenter!=null){
            mPresenter.getUserAuthNameCard(mName,mCardid);
        }

    }
}
