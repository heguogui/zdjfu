package com.hz.zdjfu.application.modle.mine.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *消息
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class MessageActivity extends ToolbarActivity{


    private static final String TAG =MessageActivity.class.getName();

    public MessageFragment mMessageFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_message;
    }

    @Override
    protected BaseFragment getFragment() {
        mMessageFragment =MessageFragment.newInstance();
        return mMessageFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();

        setTitle(getString(R.string.message_top_title));
        showRightTv(true);
       // setRightTv(getString(R.string.message_top_right));
        showBackBtn(true);
    }


    @Override
    protected void onRightTvClick(TextView view) {
        if(view!=null){
            String content =view.getText().toString();
            if(content.equals(getString(R.string.message_top_right))){
                if(mMessageFragment!=null){
                    mMessageFragment.readAllMessage();
                }
            }
        }
    }

    public void changeRightTitle(boolean state){
       if(state){
           setRightTv(getString(R.string.message_top_right_all_read));
       }
    }

    public void hiteRightTitle(boolean state){
        if(state){
            showRightTv(false);
        }
    }

    public void showChangeRightTitle(boolean state){
        if(state){
            setRightTVState(true);
            setRightTv(getString(R.string.message_top_right));
        }else{
            setRightTVState(false);
            setRightTv(getString(R.string.message_top_right_all_read));
        }
    }


}
