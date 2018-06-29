package com.hz.zdjfu.application.modle.mine.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.getturepwd.CreateGesturePwdActivity;
import com.hz.zdjfu.application.modle.getturepwd.LockPatternUtils;
import com.hz.zdjfu.application.modle.getturepwd.UnlockGesturePwdActivity;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.BankCardActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.paypwdmanager.PayPwdManagerActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.updateloginpsd.UpdateLoginPsdActivity;
import com.hz.zdjfu.application.modle.opendeposit.OpenDepositActivity;
import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.dialog.CustomProgressDialog;
import com.hz.zdjfu.application.widget.dialog.OpenBankAccountDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 个人中心Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class PersonCenterFragment extends BaseFragment implements PersonCenterContract.View {

    private static final String TAG = PersonCenterFragment.class.getName();
    @BindView(R.id.personcenter_head_picture_iv)
    ImageView personcenterHeadPictureIv;
    @BindView(R.id.personcenter_nick_tv)
    TextView personcenterNickTv;
    @BindView(R.id.personcenter_phone_tv)
    TextView personcenterPhoneTv;
    @BindView(R.id.personcenter_namecertification_tv)
    TextView personcenterNamecertificationTv;
    @BindView(R.id.personcenter_state_tv)
    TextView personcenterStateTv;
    @BindView(R.id.personcenter_gesturepassword_tb)
    ToggleButton personcenterGesturepasswordTb;
    @BindView(R.id.personcenter_exitapp_btn)
    Button personcenterExitappBtn;
    @BindView(R.id.personcenter_namecertification_name_tv)
    TextView personcenterNamecertificationNameTv;
    @BindView(R.id.personcenter_certification_rl)
    RelativeLayout personcenterCertificationRl;
    @BindView(R.id.personcenter_bankcard_rl)
    RelativeLayout personcenterBankcardRl;
    @BindView(R.id.personcenter_dealpassword_rl)
    RelativeLayout personcenterPayPwdManagerRl;
    @BindView(R.id.personcenter_namecertification_iv)
    ImageView personcenterNameCertification_iv;

    private PersonCenterContract.Presenter mPresenter;
    private CustomProgressDialog   mDialog;
    private ZTUserDetailBean mUserDetail;
    private OpenBankAccountDialog dialog;
    private  UserBean mBean;
    public static PersonCenterFragment newInstance() {
        return new PersonCenterFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
        changeViewState(false);


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

        if(isShow){
            if(personcenterStateTv!=null&&mActivity!=null){
                personcenterStateTv.setText(mActivity.getResources().getString(R.string.personcenter_addbankcard_tv));
                personcenterStateTv.setTextColor(mActivity.getResources().getColor(R.color.blue5));
            }
        }
    }

    @Override
    public void setPresenter(PersonCenterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personcenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new PersonCenterPresenter(mActivity, this);
        initViewData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initViewData() {

        mBean = UserInfoManager.getInstance().getLocationUserData();

        if(mBean==null)
            return;
        //手机号
        if (!TextUtils.isEmpty(mBean.getUserPhone())) {
            personcenterPhoneTv.setText(StringUtils.desenPhoneNumber(mBean.getUserPhone())+"");
        }
        //昵称
        if (!TextUtils.isEmpty(mBean.getUserNick())) {
            personcenterNickTv.setText(mBean.getUserNick()+"");
            personcenterNickTv.setVisibility(View.VISIBLE);
        }else{
            personcenterNickTv.setText(getString(R.string.personcenter_nick_empty_tv)+"");
            personcenterNickTv.setVisibility(View.GONE);
        }

        //头像
        if(!TextUtils.isEmpty(mBean.getUserHead())){
            ImageLoader.getInstance().displayImage(mActivity,mBean.getUserHead(),personcenterHeadPictureIv);
        }else{
            ImageLoader.getInstance().displayCircleImage(mActivity,R.mipmap.headicon,personcenterHeadPictureIv);
        }

        //设置手势密码
        if(LockPatternUtils.getInstance().savedPatternExists()){//设置了手势密码
            personcenterGesturepasswordTb.setChecked(true);
        }else{//未设置手势密码
            personcenterGesturepasswordTb.setChecked(false);
        }

        personcenterGesturepasswordTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean mState) {
                Bundle mBundle = new Bundle();
                mBundle.putString("MPARENT","TYPE_SETTING");
                if(mState){//选择ture 去设置手势密码
                    startActivity(CreateGesturePwdActivity.makeIntent(mActivity,mBundle));
                }else{//选择False 去解锁手势密码
                    startActivity(UnlockGesturePwdActivity.makeIntent(mActivity,mBundle));
                }
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        //获取用户信息
        if(mBean!=null&&mPresenter!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            mPresenter.userDetailHttpRequest(mBean.getUserPhone());
        }
    }

    @Override
    public void clearUserData() {

    }


    @Override
    public void nameCertification() {

//        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
//        mIntent.putExtra("WebView_URL",ApiMethod.METHOD_NAME_CERTIFICATION +ApiMethod.getAccessToken(mActivity));
//        mIntent.putExtra("WebView_TITLE",getResources().getString(R.string.personcenter_namecertification_title));
//        startActivity(mIntent);

    }

    @Override
    public void isCheckLogin() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null){
            if(TextUtils.isEmpty(mBean.getUserPhone())){//为空则跳转登录页面
                startActivity(LoginActivity.makeIntent(mActivity,null));
            }
        }
    }

    @Override
    public void bankCardManager() {

        startActivity(BankCardActivity.makeIntent(mActivity,null));

    }

    @Override
    public void showUserData(ZTUserDetailBean bean) {

        if(bean==null){
            return;
        }

        this.mUserDetail =bean;


        try{

            if(mUserDetail.getAuthStatus()==1){
                //真是姓名
                if(!TextUtils.isEmpty(bean.getRealName())){
                    int length =bean.getRealName().length();
                    personcenterNamecertificationNameTv.setText("**"+bean.getRealName().substring(length-1,length)+"");
                }

                //身份证号码
                if(!TextUtils.isEmpty(bean.getIdCardNo())){
                    personcenterNamecertificationTv.setText(StringUtils.identityNumber(bean.getIdCardNo())+"");
                }
            }

        }catch (Exception e){

        }

        changeViewState(true);
        //设置了交易密码 则不能再点击 实名认证
        if(mUserDetail.getAuthStatus()==1){
            personcenterCertificationRl.setEnabled(false);
            personcenterNameCertification_iv.setBackgroundResource(R.color.white0);
        }else{
            personcenterCertificationRl.setEnabled(true);
            personcenterNameCertification_iv.setVisibility(View.VISIBLE);
            personcenterNameCertification_iv.setBackgroundResource(R.mipmap.next);
        }

        //银行卡号
        try{
            if(mUserDetail.getBindStatus()==3){
                if(!TextUtils.isEmpty(mUserDetail.getBankNo())){
                    int length =mUserDetail.getBankNo().length();
                    if(personcenterStateTv!=null){
                        personcenterStateTv.setText("尾号  "+mUserDetail.getBankNo().substring(length-4,length));
                        personcenterStateTv.setTextColor(getResources().getColor(R.color.gray12));
                    }
                }
            }else if(mUserDetail.getBindStatus()==4){
                if(personcenterStateTv!=null){
                    personcenterStateTv.setText(getString(R.string.personcenter_addbankcard_tv));
                    personcenterStateTv.setTextColor(getResources().getColor(R.color.blue5));
                }
            }

        }catch (Exception e){

        }

    }

    @Override
    public void showBankCardNotication(BankCardBean mBean) {

//        if(mBean!=null){
//            //银行卡号
//            if(!TextUtils.isEmpty(mBean.getBank_no())){
//                int length =mBean.getBank_no().length();
//                if(personcenterStateTv!=null){
//                    personcenterStateTv.setText("尾号  "+mBean.getBank_no().substring(length-4,length));
//                    personcenterStateTv.setTextColor(getResources().getColor(R.color.gray12));
//                }
//            }else{
//                if(personcenterStateTv!=null){
//                    personcenterStateTv.setText(getString(R.string.personcenter_addbankcard_tv));
//                    personcenterStateTv.setTextColor(getResources().getColor(R.color.blue5));
//                }
//            }
//        }

    }

    @Override
    public void showSettingPayPwdH5(String url) {
        if(!TextUtils.isEmpty(url)){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="PERSONCENTER";
            mIntent.putExtra("WebView_URL",url);
            mActivity.startActivity(mIntent);
        }
    }

    @Override
    public void showOpenBankAccountDialog() {

        if(dialog==null){
            dialog = new OpenBankAccountDialog(mActivity, new OpenBankAccountDialog.OpenBankAccountCancleListener() {
                @Override
                public void callback() {
                    dialog.dismiss();
                    dialog =null;
                }
            }, new OpenBankAccountDialog.OpenBankAccountSureListener() {
                @Override
                public void callback() {

                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="PERSONCENTER";
                    startActivity(OpenDepositActivity.makeIntent(mActivity,null));
                    dialog.dismiss();
                    dialog =null;
                }
            });
            dialog.show();
        }


    }

    @Override
    public void checkIsOpenBankAccount() {

        if(mUserDetail!=null){
            if(mUserDetail.getBindStatus()==0||mUserDetail.getBindStatus()==1||mUserDetail.getBindStatus()==2){
                showOpenBankAccountDialog();
            }else if(mUserDetail.getBindStatus()==4){
                ToastUtils.show(mActivity,"请先绑定有效银行卡");
            }

        }

    }

    @Override
    public void changeViewState(boolean state) {
        if(personcenterCertificationRl!=null&&personcenterBankcardRl!=null&&personcenterPayPwdManagerRl!=null){
            personcenterCertificationRl.setEnabled(state);
            personcenterBankcardRl.setEnabled(state);
            personcenterPayPwdManagerRl.setEnabled(state);
        }
    }

    @Override
    public void logoutSuccess(boolean state) {

        if(state){
            UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
            mUserBean.setLogin(false);
            UserInfoManager.getInstance().saveCurrentUserInfo(mUserBean);
            ActivityManagerUtil.getActivityManager().logout();
            startActivity(MainActivity.makeIntent(mActivity,null));
            mActivity.finish();
        }

    }


    @OnClick({R.id.personcenter_nickandphone_rl, R.id.personcenter_certification_rl, R.id.personcenter_bankcard_rl, R.id.personcenter_loginpassword_rl, R.id.personcenter_dealpassword_rl, R.id.personcenter_exitapp_btn})
    public void onViewClicked(View view) {

        //检查是否登录
       // isCheckLogin();

        switch (view.getId()) {
            case R.id.personcenter_nickandphone_rl://个人资料
                //startActivity(PersonDetailActivity.makeIntent(mActivity,null));
                break;
            case R.id.personcenter_certification_rl://实名认证
                //设置了交易密码 则不能再点击 实名认证
                if(mUserDetail!=null&&mUserDetail.getAuthStatus()==1&&mUserDetail.getBindStatus()==3){
                    ToastUtils.show(mActivity,"已认证");
                }else {
                    checkIsOpenBankAccount();
                }
                break;
            case R.id.personcenter_bankcard_rl://银行卡
                if(mUserDetail!=null&&(mUserDetail.getBindStatus()==0||mUserDetail.getBindStatus()==1|mUserDetail.getBindStatus()==2)){
                    checkIsOpenBankAccount();
                }else{
                    bankCardManager();
                }
                break;
            case R.id.personcenter_loginpassword_rl://修改登录密码
                startActivity(UpdateLoginPsdActivity.makeIntent(mActivity,null));
                break;
            case R.id.personcenter_dealpassword_rl://修改交易密码
                if(mUserDetail!=null&&mUserDetail.getBindStatus()==3||mUserDetail!=null&&mUserDetail.getBindStatus()==4){
                    startActivity(PayPwdManagerActivity.makeIntent(mActivity,null));
                }else{
                    checkIsOpenBankAccount();
                }
                break;
            case R.id.personcenter_exitapp_btn://退出APP

                if(mPresenter!=null){
                    mPresenter.exitAppHttpRequest();
                }

                break;
        }
    }
}
