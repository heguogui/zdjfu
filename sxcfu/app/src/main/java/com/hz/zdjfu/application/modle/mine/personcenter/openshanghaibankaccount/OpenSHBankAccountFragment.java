package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.modle.mine.personcenter.PersonCenterActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.ImageAlertDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;


import butterknife.BindView;

/**
 * [类功能说明]
 *认证开通上海银行账户Fragment
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class OpenSHBankAccountFragment extends BaseFragment implements OpenSHBankAccountContract.View{

    private static String TAG = OpenSHBankAccountFragment.class.getName();
    @BindView(R.id.bindcard_iv)
    ImageView bindcardIv;
    @BindView(R.id.line_left_tv)
    TextView lineLeftTv;
    @BindView(R.id.line_right_tv)
    TextView lineRightTv;
    @BindView(R.id.namecertification_iv)
    ImageView namecertificationIv;
    @BindView(R.id.settingpwd_iv)
    ImageView settingpwdIv;
    @BindView(R.id.namecertification_tv)
    TextView namecertificationTv;
    @BindView(R.id.bindcard_tv)
    TextView bindcardTv;
    @BindView(R.id.settingpwd_tv)
    TextView settingpwdTv;
    @BindView(R.id.namecertification_fl)
    FrameLayout namecertificationFl;

    private FragmentManager mFragmentManager;
    private OpenSHBankAccountContract.Presenter mPresenter;
    private BaseFragment mContentFragment;
    private Intent mIntent;
    private Bundle mBundle;
    private String mRZState;//认证状态
    private String mOpenCardState;//开卡状态
    private ImageAlertDialog dialog;
    public static OpenSHBankAccountFragment newIntance() {
        return new OpenSHBankAccountFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_openshbankaccount;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new OpenSHBankAccountPresenter(mActivity,this);
        initViewData();

    }


    @Override
    public void showErrorTips(String msg) {

        if(!TextUtils.isEmpty(msg)){
            ToastUtils.show(mActivity,msg+"");
        }

    }

    @Override
    public void initViewData() {

        mIntent = mActivity.getIntent();
        if(mIntent!=null){
            mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mRZState = mBundle.getString("RZSTATE");
                mOpenCardState =mBundle.getString("OPENCARDSTATE");
            }
        }
        mFragmentManager = this.getFragmentManager();

        if (!TextUtils.isEmpty(mRZState) && mRZState.equals("1")) {//认证了 则直接跳转到绑卡页面
            if(!TextUtils.isEmpty(mOpenCardState)&&mOpenCardState.equals("1")){
                changeViewState(false, true, false);
                switchFragment(FragmentFactory.FRAGMENT_BINDBANKCARD, null);
            }
        } else {
            changeViewState(true, false, false);
            switchFragment(FragmentFactory.FRAGMENT_NAMEAUTHENTICATION, null);
        }


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(OpenSHBankAccountContract.Presenter presenter) {
        this.mPresenter =presenter;
    }

    @Override
    public void changeViewState(boolean mOne, boolean mTwo, boolean mThree) {

            if(mOne){
                namecertificationIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                bindcardIv.setImageDrawable(getResources().getDrawable(R.mipmap.two));
                settingpwdIv.setImageDrawable(getResources().getDrawable(R.mipmap.three));
                namecertificationTv.setTextColor(getResources().getColor(R.color.blue5));
                bindcardTv.setTextColor(getResources().getColor(R.color.colorBack3));
                settingpwdTv.setTextColor(getResources().getColor(R.color.colorBack3));
            }else if(mTwo){
                namecertificationIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                bindcardIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                settingpwdIv.setImageDrawable(getResources().getDrawable(R.mipmap.three));
                namecertificationTv.setTextColor(getResources().getColor(R.color.blue5));
                bindcardTv.setTextColor(getResources().getColor(R.color.blue5));
                settingpwdTv.setTextColor(getResources().getColor(R.color.colorBack3));
            }else if(mThree){
                namecertificationIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                bindcardIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                settingpwdIv.setImageDrawable(getResources().getDrawable(R.mipmap.opened));
                namecertificationTv.setTextColor(getResources().getColor(R.color.blue5));
                bindcardTv.setTextColor(getResources().getColor(R.color.blue5));
                settingpwdTv.setTextColor(getResources().getColor(R.color.blue5));
            }

    }

    @Override
    public void switchFragment(int flag, Bundle bundle) {
        OpenSHBankBaseFragment fragment = FragmentFactory.createFragment(flag);
        switchContent(fragment, bundle);
    }

    /**
     * 跳转fragment
     *
     * @param toFragment
     */
    private void switchContent(OpenSHBankBaseFragment toFragment, Bundle bundle) {

        FragmentTransaction tran = mFragmentManager.beginTransaction();
        if (mContentFragment == null) {
            if (bundle != null)
                toFragment.setArguments(bundle);
            tran.add(R.id.namecertification_fl,toFragment).commit();
        } else {
            if (mContentFragment != toFragment) {
                if (!toFragment.isAdded()) {
                    if (bundle != null)
                        toFragment.setArguments(bundle);
                    tran.hide(mContentFragment).add(R.id.namecertification_fl,toFragment).commit();
                } else {
                    toFragment.refreshFragment(bundle);
                    tran.hide(mContentFragment).show(toFragment).commit();
                }
            }
        }
        mContentFragment = toFragment;
    }

    @Override
    public void leftBtnOnClickListener() {
        showBackNoticationDialog();
    }


    @Override
    public void showBackNoticationDialog() {

        dialog = new ImageAlertDialog(mActivity,"提示",getString(R.string.openshbankaccount_back_notication_title),R.mipmap.notication_bg,getString(R.string.common_sure),getString(R.string.common_cancle),new ImageAlertDialog.ConfirmListener(){

            @Override
            public void callback() {
                dialog.dismiss();
                dialog =null;

                if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PERSONCENTER")){//个人中来
                    startActivity(PersonCenterActivity.makeIntent(mActivity,null));
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                }

                if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PRODUCTDETAIL")){//产品详情中来
                    startActivity(ProductDetailActivity.makeIntent(mActivity,null));
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                }

                if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWOPENACCOUNT")){//h5中来
                    Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                    mActivity.startActivity(mIntent);
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                }


                mActivity.finish();
            }
        },new ImageAlertDialog.CancelListener(){

            @Override
            public void callback() {
                dialog.dismiss();
                dialog =null;
            }
        },true);
        dialog.show();

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


}
