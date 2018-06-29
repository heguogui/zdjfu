package com.hz.zdjfu.application.modle.mine.rechange.rechangesuccess;

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
import com.hz.zdjfu.application.data.even.InvestEven;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 其他充值帮助
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RechangeSuccessFragment extends BaseFragment {

    private static final String TAG = RechangeSuccessFragment.class.getName();
    @BindView(R.id.rechange_success_amount)
    TextView rechangeSuccessAmount;
    @BindView(R.id.rechange_success_balance)
    TextView rechangeSuccessBalance;
    @BindView(R.id.rechange_success_sure)
    TextView rechangeSuccessSure;
    @BindView(R.id.rechange_success_goinvite)
    TextView rechangeSuccessGoinvite;


    private String mAmount;
    private Intent mIntent;
    private Bundle mBundle;
    private String mBalance;
    public static RechangeSuccessFragment newInstance() {
        return new RechangeSuccessFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rechangesuccess;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mIntent = mActivity.getIntent();
        if (mIntent != null) {
            mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                mAmount = mBundle.getString("AMOUNT");
                mBalance =mBundle.getString("BALANCE");
            }
        }

        if (!TextUtils.isEmpty(mAmount)) {
            rechangeSuccessAmount.setText("充值成功! 到账金额"+"¥"+ UiUtils.formatMoneyToBigDecimal(mAmount,getResources().getString(R.string.defalut_money_separator))+"元");
        }

        if(!TextUtils.isEmpty(mBalance)){

            String laseBalance = AmountUtils.add(mAmount,mBalance);
            rechangeSuccessBalance.setText("¥"+ UiUtils.formatMoneyToBigDecimal(laseBalance,getResources().getString(R.string.defalut_money_separator)));
        }

        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVESTFRAGMENT")){
            rechangeSuccessGoinvite.setText("继续投资");
        }


    }

    //账户余额
    private void userAccountBalance(String phone) {


        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mActivity,"获取手机IP失败");
            return;
        }
        String mSign =HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
        DialogManager.showProgressDialog(mActivity,"加载中...");
        RetrofitUtil.createService().accountBalance(phone,mIP, Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AccountDataList>() {
            @Override
            public void onSuccess(AccountDataList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getDataList().getBalance())){
                        rechangeSuccessBalance.setText("¥"+ UiUtils.formatMoneyToBigDecimal(result.getDataList().getBalance(),getResources().getString(R.string.defalut_money_separator)));
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                if(!TextUtils.isEmpty(e.getMessage())){
                    ToastUtils.show(mActivity,e.getMessage());
                }
            }
        });

    }


    @OnClick({R.id.rechange_success_sure, R.id.rechange_success_goinvite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rechange_success_sure:
                if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVESTFRAGMENT")){
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =null;
                }
                startActivity(MainActivity.makeIntent(mActivity,null));
                EventBus.getDefault().post(new MainFromTagEven("RECHANGESUCCESS"));

                mActivity.finish();
                break;
            case R.id.rechange_success_goinvite:
                if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVESTFRAGMENT")){
                    startActivity(InvestActivity.makeIntent(mActivity,null));
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =null;
                    EventBus.getDefault().post(new InvestEven("RECHANGESUCCESS"));
                }else{
                    startActivity(MainActivity.makeIntent(mActivity,null));
                    EventBus.getDefault().post(new MainFromTagEven("RECHANGESUCCESSGOINVITE"));
                }
                mActivity.finish();
                break;
        }
    }
}
