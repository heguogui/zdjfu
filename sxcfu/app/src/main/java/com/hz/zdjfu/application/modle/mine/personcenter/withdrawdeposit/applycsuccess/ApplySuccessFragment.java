package com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit.applycsuccess;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 *提现申请
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class ApplySuccessFragment extends BaseFragment {


    private static final String TAG = ApplySuccessFragment.class.getName();

    @BindView(R.id.applysuccess_amount_tv)
    TextView applysuccessAmountTv;
    @BindView(R.id.applysuccess_balance_tv)
    TextView applysuccessBalanceTv;

    private String mAmount;
    private Intent mIntent;
    private Bundle mBundle;

    public static ApplySuccessFragment newInstance() {
        return new ApplySuccessFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_applysuccess;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIntent = mActivity.getIntent();
        if (mIntent != null) {
            mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                mAmount = mBundle.getString("AMOUNT");
            }
        }



        if (!TextUtils.isEmpty(mAmount)) {
            applysuccessAmountTv.setText("¥"+ UiUtils.formatMoneyToBigDecimal(mAmount,getResources().getString(R.string.defalut_money_separator)));
        }

        if(!TextUtils.isEmpty(ZDJFUApplication.getInstance().BALANCE_NUM)){
            showAccountBalance(ZDJFUApplication.getInstance().BALANCE_NUM);
        }


      //  accountBalance();
    }



    //账户余额
    private void showAccountBalance(String balance) {

        applysuccessBalanceTv.setText("¥"+ UiUtils.formatMoneyToBigDecimal(balance,getResources().getString(R.string.defalut_money_separator)));

    }


    private void accountBalance() {

        UserBean mBean = UserInfoManager.getInstance().getUserBean();

        if (mBean == null || TextUtils.isEmpty(mBean.getUserPhone())) {
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if (TextUtils.isEmpty(mIP)) {
            ToastUtils.show(mActivity, "获取手机IP失败");
            return;
        }
        String mSign = HttpsUtils.getHttpRequestSign(mBean.getUserPhone(),Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().accountBalance(mBean.getUserPhone(), mIP, Constants.ANDROID_SOURCE, mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AccountDataList>() {
            @Override
            public void onSuccess(AccountDataList result) {

                if(result!=null&&!TextUtils.isEmpty(result.getDataList().getBalance())){
                    showAccountBalance(result.getDataList().getBalance());
                }

            }

            @Override
            public void _onError(Throwable e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    ToastUtils.show(mActivity, e.getMessage());
                }
            }
        });

    }


    @OnClick(R.id.applysuccess_sure_btn)
    public void onViewClicked() {
        EventBus.getDefault().post(new MainFromTagEven("APPLYSUCCESS"));
        startActivity(MainActivity.makeIntent(mActivity,null));
        mActivity.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!TextUtils.isEmpty(ZDJFUApplication.getInstance().BALANCE_NUM)){
            ZDJFUApplication.getInstance().BALANCE_NUM=null;
        }
    }
}
