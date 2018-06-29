package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.unbindbankcard.UnbindBankCardActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.CustomProgressDialog;
import com.hz.zdjfu.application.widget.dialog.MySingleBtnDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 银行卡管理Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class BankCardFragment extends BaseFragment implements BankCardContract.View {

    private static final String TAG = BankCardFragment.class.getName();
    @BindView(R.id.bankcardmanager_recyclerview)
    RecyclerView bankcardRecyclerView;
    @BindView(R.id.bankcardmanager_unbind_btn)
    Button bankcardmanagerUnbindBtn;
    @BindView(R.id.bankcardmanager_rl)
    RelativeLayout bankcardmanagerRl;
    @BindView(R.id.bankcardmanager_add_btn)
    Button bankcardmanagerAddBtn;
    @BindView(R.id.bankcardmanager_empaty_rl)
    RelativeLayout bankcardmanagerEmpatyRl;

    private BankCardAdapter mAdapter;


    private BankCardContract.Presenter mPresenter;
    private CustomProgressDialog mDialog;
    private  MySingleBtnDialog mSingleDialog;
    private BankCardBean mList;
    public static BankCardFragment newInstance() {
        return new BankCardFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {
        if(bankcardmanagerEmpatyRl!=null&&bankcardmanagerRl!=null){
            if(isShow){
                bankcardmanagerEmpatyRl.setVisibility(View.VISIBLE);
                bankcardmanagerRl.setVisibility(View.GONE);
            }else{
                bankcardmanagerEmpatyRl.setVisibility(View.GONE);
                bankcardmanagerRl.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void setPresenter(BankCardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bankcard;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new BankCardPresenter(mActivity, this);
        initViewData();

    }


    @Override
    public void initViewData() {

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        bankcardRecyclerView.setLayoutManager(manager);
        mAdapter = new BankCardAdapter(mActivity);
        bankcardRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public void showBankCardNotication(BankCardBean mlist) {

        if (mlist == null) {
            return;
        }
        this.mList =mlist;
        List<BankCardBean> list = new ArrayList<>();
        list.add(mlist);
        if (mAdapter == null) {
            mAdapter = new BankCardAdapter(mActivity);
            bankcardRecyclerView.setAdapter(mAdapter);
        }

        mAdapter.setNewData(list);
    }

    @Override
    public void isShowBanlanceNoEmptyDialog(boolean isShow) {

        if(isShow){//大于0

            mSingleDialog =new MySingleBtnDialog(mActivity, getString(R.string.bankcard_dialog_title), getString(R.string.bankcard_dialog_content), getResources().getString(R.string.common_sure), new MySingleBtnDialog.ConfirmListener() {
                @Override
                public void callback(boolean state) {
                    if(state){
                        if(mSingleDialog!=null&&mSingleDialog.isShowing()){
                            mSingleDialog.dismiss();
                            mSingleDialog =null;
                        }
                    }
                }
            });
            mSingleDialog.show();

        }else{

            if(mList!=null){
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("BANKDETAIL",mList);
                startActivity(UnbindBankCardActivity.makeIntent(mActivity,mBundle));
            }


        }

    }

    @Override
    public void refreshData() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            if(mPresenter!=null){
                mPresenter.allBankCardHttpRequest(mBean.getUserPhone());
            }
        }

    }



    @Override
    public void authNameResult(String url) {

        ZDJFUApplication.getInstance().setParent("BANKCARDFRAGMENT");
        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_PARENT","PRODUCTDETAIL");
        mIntent.putExtra("WebView_URL",url);
        mActivity.startActivity(mIntent);

    }


    /**
     * 拨打客服电话
     */
    private View.OnClickListener confirmOnClick = new View.OnClickListener() {

        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_cancel:
                    mDialog.dismiss();
                    break;
                case R.id.btn_OK:
                    mDialog.dismiss();
                    String phone = Constants.PHONE_ONE_KF;
                    UiUtils.cellPhoneSevers(mActivity,phone);
                    break;
                default:
                    break;
            }
        }
    };


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @OnClick({R.id.bankcardmanager_unbind_btn, R.id.bankcardmanager_add_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bankcardmanager_unbind_btn://解绑银行卡

                if(StringUtils.isFastClick()){
                    return;
                }
                //检查用户账户是否有余额
                UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
                    mPresenter.isCheckAccountBanlance(mBean.getUserPhone());
                }

                break;
            case R.id.bankcardmanager_add_btn://添加银行卡
                if(StringUtils.isFastClick()){
                    return;
                }

                if(mPresenter!=null){
                    mPresenter.addBankCard();
                }

              //  startActivity(AddBankCardActivity.makeIntent(mActivity,null));
                break;
        }
    }
}
