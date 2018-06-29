package com.hz.zdjfu.application.modle.mine.invitefriend;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.invitefriend.invitereword.InviteRewordActivity;
import com.hz.zdjfu.application.share.ShareDialog;
import com.hz.zdjfu.application.widget.dialog.InviteFriendRuleDialog;
import butterknife.BindView;
import butterknife.OnClick;



/**
 * [类功能说明]
 * 邀请好友View
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class InviteFriendFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = InviteFriendFragment.class.getName();
    @BindView(R.id.invitefriend_btn)
    Button invitefriendBtn;
    @BindView(R.id.invitefriend_rule)
    TextView invitefriendRule;
    @BindView(R.id.invitefriend_record)
    TextView invitefriendRecord;

    private InviteFriendRuleDialog customDialog;
    private TextView guize, ruleText;
    private RelativeLayout closeBtn;

    public static InviteFriendFragment newInstance() {
        return new InviteFriendFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invitefriend;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        //初始化Dialog
        customDialog = new InviteFriendRuleDialog(mActivity, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.invitefriend_dialog, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        customDialog.setCanceledOnTouchOutside(true);
        guize = (TextView) customDialog.findViewById(R.id.rule_rule);
        closeBtn = (RelativeLayout) customDialog.findViewById(R.id.rule_close);
        closeBtn.setOnClickListener(this);
        ruleText = (TextView) customDialog.findViewById(R.id.rule_text);

    }


    @OnClick({R.id.invitefriend_btn, R.id.invitefriend_rule, R.id.invitefriend_record})
    public void onViewClicked(View view) {
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!mBean.isLogin()){
            startActivity(LoginActivity.makeIntent(mActivity,null));
            return;
        }
        switch (view.getId()) {
            case R.id.invitefriend_btn://邀请
                shareContent();
                break;
            case R.id.invitefriend_rule://规则
                if(customDialog!=null){
                    customDialog.show();
                }
                break;
            case R.id.invitefriend_record://奖励记录
                startActivity(InviteRewordActivity.makeIntent(mActivity,null));
                break;
        }
    }



    @Override
    public void onClick(View view) {

        if(customDialog!=null){
            customDialog.dismiss();
        }
    }

    public void shareContent(){
        ShareDialog dialog = new ShareDialog(mActivity,mActivity);
        dialog.show();
    }


}
