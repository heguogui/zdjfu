package com.hz.zdjfu.application.modle.mine.personcenter.persondetail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.data.even.CameraPermissionEven;
import com.hz.zdjfu.application.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/21 0021
 */
public class PersonDetailActivity extends ToolbarActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_persondetail;
    }

    @Override
    protected BaseFragment getFragment() {
        return PersonDetailFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.persondetail_top_title));
        showBackBtn(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0x111:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EventBus.getDefault().post(new CameraPermissionEven(true));
                } else {
                    ToastUtils.show(this, "未授予相机拍照权限,拍照不可用!");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
