package com.hz.zdjfu.application.modle.mine.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.andview.refreshview.XRefreshView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.MessageBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.message.messagedetail.MessageDetailActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.XXRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class MessageFragment extends BaseFragment implements MessageContract.View {

    private static final String TAG = MessageFragment.class.getName();
    @BindView(R.id.message_recycleview)
    RecyclerView messageRecycleview;
    @BindView(R.id.message_xrefreshview)
    XXRefreshView messageXrefreshview;
    @BindView(R.id.empty_data_rl)
    RelativeLayout emptyDataRl;

    private MessageAdapter mAdapter;
    private MessageContract.Presenter mPresenter;
    private List<MessageBean> mLists;
    private UserBean mBean;
    private static final int RESULT_CODE =0X12;
    public static MessageFragment newInstance() {
        return new MessageFragment();
    }


    @Override
    public void showErrorTips(String msg) {
        completeRefreshAndLoad();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initViewData() {

        mLists = new ArrayList<>();

        messageXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                if(mPresenter!=null){
                    if (mBean !=null && mBean.isLogin()) {
                        mPresenter.messageHttpRequest(mBean.getUserPhone(),true);
                    }
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if(mPresenter!=null){
                    if (mBean !=null && mBean.isLogin()) {
                        mPresenter.messageHttpRequest(mBean.getUserPhone(),false);
                    }
                }
            }
        });



        messageRecycleview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
              List<MessageBean> mlists = adapter.getData();
                MessageBean mBean =mlists.get(position);
                   if(mBean==null){
                       return;
                   }
                //修改状态
                mBean.setIs_read("2");
                mLists.set(position,mBean);
                //跳转
                Bundle mBundle = new Bundle();
                mBundle.putString("MESSAGEID",mBean.getId()+"");
                startActivityForResult(MessageDetailActivity.makeIntent(mActivity,mBundle),RESULT_CODE);
            }
        });

    }

    @Override
    public void showMessageData(List<MessageBean> mlists,boolean isRefresh) {
        //停止刷新
        completeRefreshAndLoad();

        if(mlists==null){
            return;
        }

        if(isRefresh){
            if(mlists!=null&&mlists.size()==0){
                ((MessageActivity)mActivity).hiteRightTitle(true);
                emptyDataRl.setVisibility(View.VISIBLE);
                messageRecycleview.setVisibility(View.GONE);
                return;
            }else{
                emptyDataRl.setVisibility(View.GONE);
                messageRecycleview.setVisibility(View.VISIBLE);
            }

            if(mLists!=null&&mLists.size()>0){
                mLists.clear();
            }

        }else{

            if(mlists!=null&&mlists.size()==0){
                ToastUtils.show(mActivity,"没有了更多");
                return;
            }

        }



        mLists.addAll(mlists);
        if(mLists!=null&&mLists.size()>0){
            for(int i=0;i<mLists.size();i++){
                if(mLists.get(i).getIs_read().equals("1")){//未读
                    ((MessageActivity)mActivity).showChangeRightTitle(true);
                    break;
                }
            }
        }else{
            ((MessageActivity)mActivity).hiteRightTitle(true);
        }


        if(mAdapter==null){
            mAdapter = new MessageAdapter(mActivity);
            mAdapter.setNewData(mLists);
            LinearLayoutManager manager = new LinearLayoutManager(mActivity);
            messageRecycleview.setLayoutManager(manager);
            messageRecycleview.setAdapter(mAdapter);
        }else{
            mAdapter.setNewData(mLists);
        }

    }


    @Override
    public void skipActivity(Class<?> mClass) {

    }

    @Override
    public void completeRefreshAndLoad() {

        if(messageXrefreshview!=null){
            messageXrefreshview.stopRefresh();
            messageXrefreshview.stopLoadMore();
        }

    }

    @Override
    public void readAllMessageState(boolean state) {
        //标记已读成功后重新获取一次数据
        if(state){
            ((MessageActivity)mActivity).showChangeRightTitle(false);
            if(mPresenter!=null){
                if (mBean !=null && mBean.isLogin()) {
                    mPresenter.messageHttpRequest(mBean.getUserPhone(),true);
                }
            }
        }
    }

    @Override
    public void readAllMessage() {
        if(mPresenter!=null){
            if (mBean !=null && mBean.isLogin()) {
                mPresenter.readAllMessage(mBean.getUserPhone());
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new MessagePresenter(mActivity, this);
        initViewData();

        if (mPresenter != null) {
            this.mBean = UserInfoManager.getInstance().getLocationUserData();
            if (mBean !=null && mBean.isLogin()) {
                mPresenter.messageHttpRequest(mBean.getUserPhone(),true);
            }
        }

    }

    @Override
    public void refreshMessage(){
        if(mLists!=null&&mLists.size()>0){
            if(mAdapter!=null){
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_CANCELED){
            refreshMessage();
        }

    }
}
