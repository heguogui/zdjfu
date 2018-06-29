package com.hz.zdjfu.application.modle.mine.personcenter.updatenick;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.LimitEditText;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *设置昵称Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class UpdateNickFragment extends BaseFragment implements UpdateNickContract.View {

    private static final String TAG = UpdateNickFragment.class.getName();
    @BindView(R.id.updata_nick_et)
    LimitEditText updataNickEt;
    @BindView(R.id.update_nick_btn)
    Button updateNickBtn;


    private UpdateNickContract.Presenter mPresenter;

    public static UpdateNickFragment newInstance() {
        return new UpdateNickFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_updatenick;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new UpdateNickPresenter(mActivity, this);
        initViewData();
    }


    @Override
    public void initViewData() {
        showCurrentNick();
    }


    @Override
    public void showCurrentNick() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if (mBean == null)
            return;

        //昵称
        if (!TextUtils.isEmpty(mBean.getUserNick())) {
            updataNickEt.setText(mBean.getUserNick() + "");
        } else {
            updataNickEt.setHint(R.string.update_nick_hint);
        }

        String mContent =updataNickEt.getText().toString().trim();
        if(!TextUtils.isEmpty(mContent)){
            updataNickEt.setSelection(mContent.length());
        }

    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(UpdateNickContract.Presenter presenter) {
        this.mPresenter = presenter;
    }



    @OnClick(R.id.update_nick_btn)
    public void onViewClicked() {

        String mContent =updataNickEt.getText().toString().trim();
        if(TextUtils.isEmpty(mContent)){
            ToastUtils.show(mActivity,getString(R.string.update_nick_input_empty));
            return;
        }

        if(mContent.length()<4||mContent.length()>25){
            ToastUtils.show(mActivity,getString(R.string.update_nick_input_out));
            return;
        }

        mPresenter.updateNickpHttpRequest(mContent);

    }
}
