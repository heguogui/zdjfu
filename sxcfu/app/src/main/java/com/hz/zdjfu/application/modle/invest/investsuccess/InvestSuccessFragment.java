package com.hz.zdjfu.application.modle.invest.investsuccess;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * [类功能说明]
 * 提现申请
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class InvestSuccessFragment extends BaseFragment {


    private static final String TAG = InvestSuccessFragment.class.getName();

    @BindView(R.id.applysuccess_amount_tv)
    TextView applysuccessAmountTv;
    @BindView(R.id.applysuccess_balance_tv)
    TextView applysuccessBalanceTv;
    @BindView(R.id.invest_success_create_time_tv)
    TextView investSuccessCreateTimeTv;
    @BindView(R.id.invest_success_amount_tv)
    TextView investSuccessAmountTv;
    @BindView(R.id.invest_success_start_time_tv)
    TextView investSuccessStartTimeTv;
    @BindView(R.id.invest_success_end_time_tv)
    TextView investSuccessEndTimeTv;
    @BindView(R.id.invest_detail_btn)
    TextView investDetailBtn;
    Unbinder unbinder;

    private String mAmount;
    private Intent mIntent;
    private Bundle mBundle;

    public static InvestSuccessFragment newInstance() {
        return new InvestSuccessFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investsuccess;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


//        if (!TextUtils.isEmpty(mAmount)) {
//            applysuccessAmountTv.setText("¥"+ UiUtils.formatMoneyToBigDecimal(mAmount,getResources().getString(R.string.defalut_money_separator)));
//        }

        //  accountBalance();
    }


    //账户余额
    private void showAccountBalance(String balance) {

        applysuccessBalanceTv.setText("¥" + UiUtils.formatMoneyToBigDecimal(balance, getResources().getString(R.string.defalut_money_separator)));

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
        String mSign = HttpsUtils.getHttpRequestSign(mBean.getUserPhone(), Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().accountBalance(mBean.getUserPhone(), mIP, Constants.ANDROID_SOURCE, mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AccountDataList>() {
            @Override
            public void onSuccess(AccountDataList result) {

                if (result != null && !TextUtils.isEmpty(result.getDataList().getBalance())) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.invest_detail_btn)
    public void onClick() {


    }
}
