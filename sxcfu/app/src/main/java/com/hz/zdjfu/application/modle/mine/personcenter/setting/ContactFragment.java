package com.hz.zdjfu.application.modle.mine.personcenter.setting;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/21 0021
 */
public class ContactFragment extends BaseFragment {

    @BindView(R.id.contact_phone_one_iv)
    ImageView contactPhoneOneIv;
    @BindView(R.id.contact_phone_two_iv)
    ImageView contactPhoneTwoIv;


    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.contact_phone_one_iv, R.id.contact_phone_two_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_phone_one_iv:
                new RxPermissions(mActivity)
                        .request(Manifest.permission.READ_PHONE_STATE)
                        .compose(TransformUtils.defaultSchedulers())
                        .subscribe(granted -> {
                            if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                                String phone = Constants.PHONE_ONE_KF;
                                UiUtils.cellPhoneSevers(mActivity,phone);
                            }
                        });
                break;
            case R.id.contact_phone_two_iv:
                new RxPermissions(mActivity)
                        .request(Manifest.permission.READ_PHONE_STATE)
                        .compose(TransformUtils.defaultSchedulers())
                        .subscribe(granted -> {
                            if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                                String mphone = Constants.PHONE_TWO_KF;
                                UiUtils.cellPhoneSevers(mActivity,mphone);
                            }
                        });

                break;
        }
    }
}
