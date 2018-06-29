package com.hz.zdjfu.application.modle.mine.invitefriend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MyInvestLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.WeiXinShareEven;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * [类功能说明]
 *邀请好友Activity
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class InviteFriendActivity extends ToolbarActivity{

    private static final String TAG =InviteFriendActivity.class.getName();

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InviteFriendActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_invitefriend;
    }

    @Override
    protected BaseFragment getFragment() {
        return InviteFriendFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.invitefriend_top_title));
        showBackBtn(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(InviteFriendActivity.this)) {
            EventBus.getDefault().register(InviteFriendActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(InviteFriendActivity.this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shareWeiXinCallBack(WeiXinShareEven even){


        if(even==null){
            return;
        }
        if(even.ismState()){


            UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
            if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
                return;
            }

            RetrofitUtil.createService().shareWeiXinState(mBean.getUserPhone(),Constants.ANDROID_SOURCE).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.i(TAG,"邀请分享通知成功!");
                }

                @Override
                public void _onError(Throwable e) {
                    Log.i(TAG,"邀请分享通知失败!");
                }
            });

        }
    }

}
