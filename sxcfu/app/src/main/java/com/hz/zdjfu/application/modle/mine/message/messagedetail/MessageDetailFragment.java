package com.hz.zdjfu.application.modle.mine.message.messagedetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MessageBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;

import butterknife.BindView;


/**
 * [类功能说明]
 * 消息详情
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class MessageDetailFragment extends BaseFragment {

    private static final String TAG = MessageDetailFragment.class.getName();
    @BindView(R.id.message_detail_title)
    TextView messageDetailTitle;
    @BindView(R.id.message_detail_time)
    TextView messageDetailTime;
    @BindView(R.id.message_detail_content)
    TextView messageDetailContent;



    public static MessageDetailFragment newInstance() {
        return new MessageDetailFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_messagedetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Intent mIntent = mActivity.getIntent();
        String messgeID =null;
        if(mIntent!=null){
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                messgeID =mBundle.getString("MESSAGEID");
            }
        }
        if(!TextUtils.isEmpty(messgeID)){

            String mIP = HttpsUtils.getMobileHostIP();
            if(TextUtils.isEmpty(mIP)){
                ToastUtils.show(mActivity,"获取手机IP失败");
                return;
            }
            UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
            if(mUserBean==null||!mUserBean.isLogin()){
                return;
            }
            String mSign = HttpsUtils.getHttpRequestSign(mUserBean.getUserPhone(), Constants.REQUESTSOURCE_ANDROID);
            if(TextUtils.isEmpty(mSign)){
                return;
            }
            RetrofitUtil.createService().messageDetail(mUserBean.getUserPhone(),mIP,Constants.REQUESTSOURCE_ANDROID,mSign,messgeID,"0").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<MessageBean>() {
                @Override
                public void onSuccess(MessageBean result) {
                    if(result!=null){
                        messageDetailContent.setText(result.getContent()+"");
                        messageDetailTime.setText(result.getCreate_time()+"");
                        messageDetailTitle.setText(result.getTitle());
                    }
                }
                @Override
                public void _onError(Throwable e) {
                   if(!TextUtils.isEmpty(e.getMessage())){
                       ToastUtils.show(mActivity,e.getMessage());
                   }
                }
            });


        }


    }

}
