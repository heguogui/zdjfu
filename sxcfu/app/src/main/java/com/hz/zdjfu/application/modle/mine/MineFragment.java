package com.hz.zdjfu.application.modle.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MyIndexDataBean;
import com.hz.zdjfu.application.data.bean.MyPageBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.invitefriend.InviteFriendActivity;
import com.hz.zdjfu.application.modle.mine.message.MessageActivity;
import com.hz.zdjfu.application.modle.mine.myasset.MyAssetActivity;
import com.hz.zdjfu.application.modle.mine.myinvest.MyInvestActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.PersonCenterActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit.WithDrawDepositActivity;
import com.hz.zdjfu.application.modle.mine.rechange.RechangeActivity;
import com.hz.zdjfu.application.modle.mine.redpacketcoupon.RedpacketCouponActivity;
import com.hz.zdjfu.application.modle.mine.returnedmoneycalendar.RMCalendarActivity;
import com.hz.zdjfu.application.modle.opendeposit.OpenDepositActivity;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.MyAlertSingleDialog;
import com.hz.zdjfu.application.widget.dialog.NameValidDialog;
import com.hz.zdjfu.application.widget.dialog.OpenBankAccountDialog;
import com.hz.zdjfu.application.widget.webview.SpecialWebViewActivity;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * [类功能说明]
 * 我的界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class MineFragment extends BaseFragment implements MineContract.View {

    private static final String TAG = MineFragment.class.getName();

    @BindView(R.id.mine_head_image_iv)
    ImageView mineHeadImageIv;
    @BindView(R.id.mine_phone_tv)
    TextView minePhoneTv;
    @BindView(R.id.mine_message_iv)
    ImageView mineMessageIv;
    @BindView(R.id.mine_all_asset_tv)
    TextView mineAllAssetTv;
    @BindView(R.id.mine_all_earnings_number_tv)
    TextView mineAllEarningsNumberTv;
    @BindView(R.id.mine_duein_moneys_number_tv)
    TextView mineDueinMoneysNumberTv;
    @BindView(R.id.mine_account_banlance_number_tv)
    TextView mineAccountBanlanceNumberTv;
    @BindView(R.id.mine_invest_number_tv)
    TextView mineInvestNumberTv;
    @BindView(R.id.mine_notication_tv)
    TextView mineNoticationTv;
    @BindView(R.id.mine_redpacket_number_tv)
    TextView mineRedpacketNumberTv;
    @BindView(R.id.mine_inviter_notication_tv)
    TextView mineInviterNoticationTv;
    @BindView(R.id.mine_all_asset_ll)
    LinearLayout mineAllAssetLL;
    @BindView(R.id.mine_openbankaccount_rl)
    RelativeLayout mineOpenBankAccountRl;
    @BindView(R.id.mine_riskevaluation_notication_tv)
    TextView mineRiskevaluationNoticationTv;
    @BindView(R.id.withdraw_deposit_tv)
    TextView withdrawDepositTv;
    @BindView(R.id.mine_recharge_tv)
    TextView mineRechargeTv;
    @BindView(R.id.mine_code_line)
    TextView mineCodeLine;
    @BindView(R.id.mine_invest_iv)
    ImageView mineInvestIv;
    @BindView(R.id.mine_duiba_iv)
    ImageView mineDuibaIv;
    @BindView(R.id.mine_duiba_number_tv)
    TextView mineDuibaNumberTv;



    private MineContract.Presenter mPresenter;

    private ZTUserDetailBean mUserDetailBean;
    private OpenBankAccountDialog dialog;
    private boolean isFirst = true;
    private MyAlertSingleDialog mDialog;
    private UserBean bean;
    private MyPageBean mMyPageBean;


    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    private MyIndexDataBean MyPageBean;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new MinePresenter(mActivity, this);
        initViewData();

    }


    @OnClick({R.id.mine_head_image_iv, R.id.mine_message_iv, R.id.withdraw_deposit_tv, R.id.mine_recharge_tv, R.id.mine_invest_rl, R.id.mine_calendar_rl, R.id.mine_redpacket_rl, R.id.mine_invite_rl, R.id.mine_all_asset_ll, R.id.mine_openbankaccount_rl, R.id.mine_riskevaluation_rl,R.id.mine_duiba_rl})
    public void onViewClicked(View view) {

        //判断是否登录
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if (mBean != null && !mBean.isLogin()) {
            startActivity(LoginActivity.makeIntent(mActivity, null));
            return;
        }

        if (StringUtils.isFastClick()) {
            return;
        }

        switch (view.getId()) {
            case R.id.mine_all_asset_ll://我的财富
                startActivity(MyAssetActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_head_image_iv://个人中心
                startActivity(PersonCenterActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_message_iv://消息中心
                startActivity(MessageActivity.makeIntent(mActivity, null));
                break;
            case R.id.withdraw_deposit_tv://提现
                if (mUserDetailBean != null && mUserDetailBean.getAuthStatus() == Constants.REAL_NAME_AUTH_OK && mUserDetailBean.getBindStatus() == 3) {
                    startActivity(WithDrawDepositActivity.makeIntent(mActivity, null));
                } else {
                    checkAttestaion();
                }
                break;
            case R.id.mine_recharge_tv://充值
                if (mUserDetailBean != null && mUserDetailBean.getAuthStatus() == Constants.REAL_NAME_AUTH_OK && mUserDetailBean.getBindStatus() == 3) {
                    ZDJFUApplication.getInstance().setParent("MINEFRAGMENT");
                    startActivity(RechangeActivity.makeIntent(mActivity, null));
                } else {
                    checkAttestaion();
                }

                break;
            case R.id.mine_invest_rl://我的投资
                startActivity(MyInvestActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_calendar_rl://回款日历
                startActivity(RMCalendarActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_redpacket_rl://红包卡券
                startActivity(RedpacketCouponActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_invite_rl://邀请好友
                startActivity(InviteFriendActivity.makeIntent(mActivity, null));
                break;
            case R.id.mine_openbankaccount_rl:
                checkAttestaion();
                break;
            case R.id.mine_riskevaluation_rl:
                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", "http://zdjfu.udesk.cn/im_client/?web_plugin_id=24428");
                mActivity.startActivity(mIntent);
                break;
            case R.id.mine_duiba_rl://兑吧

                if(mPresenter!=null){
                    mPresenter.duiba();
                }

                break;
            default:
                break;
        }
    }


    /**
     * 检查是否认证
     */
    private void checkAttestaion() {
        if (mUserDetailBean != null) {
            if (mUserDetailBean.getBindStatus() == 0 || mUserDetailBean.getBindStatus() == 1 || mUserDetailBean.getBindStatus() == 2) {
                showOpenBankAccountDialog();
            } else if (mUserDetailBean.getBindStatus() == 4) {
                ToastUtils.show(mActivity, "请先绑定有效银行卡");
            }
        }
    }

    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }

    }

    @Override
    public void initViewData() {

//        mineXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
//            @Override
//            public void onRefresh(boolean isPullDown) {
//                if(mPresenter!=null){
//                    if (bean !=null &&!TextUtils.isEmpty(bean.getUserPhone())) {
//                        mPresenter.userDetailHttpRequest(bean.getUserPhone());
//                        mPresenter.myDataHttpRequest(bean.getUserPhone());
//                    }
//                }
//                completeRefresh(true);
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//                completeRefresh(true);
//            }
//        });

    }


    @Override
    public void onResume() {
        super.onResume();
        userDetail();
    }

    @Override
    public void userDetail() {
        bean = UserInfoManager.getInstance().getLocationUserData();
        if (bean != null && !TextUtils.isEmpty(bean.getUserPhone())) {
            mPresenter.userDetailHttpRequest(bean.getUserPhone());
            mPresenter.myDataHttpRequest(bean.getUserPhone());
        }
    }

    @Override
    public void showMyIndextData(MyPageBean result) {
        if (result == null)
            return;

        this.mMyPageBean =result;

        //有数据 则显示数据   无数据 判断是否认证 认证三部曲完成了 则显示数据
        if (result.getTotalAmount() == 0.0) {
            if (mUserDetailBean.getBindStatus() == 3 || mUserDetailBean.getBindStatus() == 4) {//实名认证并绑卡及设置密码成功
                mineAllAssetLL.setVisibility(View.VISIBLE);
                mineOpenBankAccountRl.setVisibility(View.GONE);
                mineAllAssetTv.setText("0.00");
            } else {
                mineAllAssetLL.setVisibility(View.GONE);
                mineOpenBankAccountRl.setVisibility(View.VISIBLE);
            }
        } else {
            mineAllAssetTv.setText(UiUtils.formatMoneyToBigDecimal(result.getTotalAmount() + "", getResources().getString(R.string.defalut_money_separator)));
            mineAllAssetLL.setVisibility(View.VISIBLE);
            mineOpenBankAccountRl.setVisibility(View.GONE);
        }

        //余额
        if (result.getAccountBalance() == 0.0) {
            mineAccountBanlanceNumberTv.setText("0.00");
        } else {
            mineAccountBanlanceNumberTv.setText(UiUtils.formatMoneyToBigDecimal(result.getAccountBalance() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //总资产
        if (result.getTotalAmount() == 0.0) {
            mineAllAssetTv.setText("0.00");
        } else {
            mineAllAssetTv.setText(UiUtils.formatMoneyToBigDecimal(result.getTotalAmount() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //累计收益
        if (result.getTotalIncome() == 0.0) {
            mineAllEarningsNumberTv.setText("0.00");
        } else {
            mineAllEarningsNumberTv.setText(UiUtils.formatMoneyToBigDecimal(result.getTotalIncome() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //待收金额
        if (result.getPendReturn() == 0.0) {
            mineDueinMoneysNumberTv.setText("0.00");
        } else {
            mineDueinMoneysNumberTv.setText(UiUtils.formatMoneyToBigDecimal(result.getPendReturn() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //我的投资数目
        if (result.getInvestNum() == 0) {
            mineInvestNumberTv.setVisibility(View.GONE);
        } else {
            mineInvestNumberTv.setVisibility(View.VISIBLE);
            mineInvestNumberTv.setText(result.getInvestNum() + "");
        }

        //回款日历
        if (result.isWillReturn()) {
            mineNoticationTv.setText("今日有回款");
        } else {
            mineNoticationTv.setText("");
        }

        //红包卡券
        if (result.getValidGift() == 0) {
            mineRedpacketNumberTv.setVisibility(View.GONE);
        } else {
            mineRedpacketNumberTv.setVisibility(View.VISIBLE);
            String mContent = result.getValidGift() + "张待用";
            SpannableString sp = new SpannableString(mContent);
            sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red4)), 0, mContent.length() - 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mineRedpacketNumberTv.setText(sp);
        }


        //邀请好友
        mineInviterNoticationTv.setText("获好友投资额2‰正经值");

        //暂定
        mineMessageIv.setBackgroundResource(R.mipmap.message_icon);
//        //消息
//        if(TextUtils.isEmpty(result.getNews())||Double.parseDouble(result.getNews())==0.0){
//            mineMessageIv.setBackgroundResource(R.mipmap.message_icon);
//        }else{
//            mineMessageIv.setBackgroundResource(R.mipmap.new_message_iv);
//        }

        if(mineDuibaNumberTv!=null){
            mineDuibaNumberTv.setText(result.getCreditsBalance()+"");
        }

    }

    @Override
    public void showDuiBaUrl(String url) {

        if(mMyPageBean==null)
            return;
        Intent  mIntent = new Intent(mActivity, SpecialWebViewActivity.class);
        mIntent.putExtra("WebView_URL", url);
        mIntent.putExtra("WebView_NUM",mMyPageBean.getCreditsBalance()+"");
        mActivity.startActivity(mIntent);
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initView() {

    }

    @Override
    public void showUserData(ZTUserDetailBean mUserDetailBean) {

        if (mUserDetailBean == null) {
            return;
        }

        this.mUserDetailBean = mUserDetailBean;

        minePhoneTv.setText(StringUtils.desenPhoneNumber(mUserDetailBean.getPhone()) + "");

    }

    @Override
    public void completeRefresh(boolean state) {
//        if(mineXrefreshview!=null){
//            mineXrefreshview.stopRefresh();
//            mineXrefreshview.stopLoadMore();
//        }

    }


    @Override
    public void checkIsLogin() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if (mBean != null) {
            if (!mBean.isLogin()) {//为空则跳转登录页面
                Bundle mBundle = new Bundle();
                mBundle.putString("FROMPARENT", "MINE");
                startActivity(LoginActivity.makeIntent(mActivity, mBundle));
            } else {
                userDetail();
            }
        } else {
            Bundle mBundle = new Bundle();
            mBundle.putString("FROMPARENT", "MINE");
            startActivity(LoginActivity.makeIntent(mActivity, mBundle));
        }
    }

    @Override
    public void showSettingPayPwdH5(String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY = "MINEFRAGMENT";
            mIntent.putExtra("WebView_URL", url);
            mActivity.startActivity(mIntent);
        }
    }


    @Override
    public void showNameValideNoticationDialog() {

        NameValidDialog mdialog = new NameValidDialog(mActivity);
        mdialog.show();

    }

    @Override
    public void refreshData(boolean state) {
        if (state) {
            userDetail();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {


        if (!hidden) {
            checkIsLogin();
            //弹出认证
            if (!PreferencesUtils.getBoolean(mActivity, Constants.USER_NAME_VALID_PREFERENCE, false)) {
                showNameValideNoticationDialog();
                PreferencesUtils.putBoolean(mActivity, Constants.USER_NAME_VALID_PREFERENCE, true);
            }

        }

        super.onHiddenChanged(hidden);
    }


    /**
     * 弹出 认证框
     */
    public void showOpenBankAccountDialog() {

        if (dialog == null) {
            dialog = new OpenBankAccountDialog(mActivity, new OpenBankAccountDialog.OpenBankAccountCancleListener() {
                @Override
                public void callback() {
                    dialog.dismiss();
                    dialog = null;
                }
            }, new OpenBankAccountDialog.OpenBankAccountSureListener() {
                @Override
                public void callback() {
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY = "MINEFRAGMENT";
                    startActivity(OpenDepositActivity.makeIntent(mActivity, null));
                    dialog.dismiss();
                    dialog = null;
                }
            });
            dialog.show();
        }


    }


}
