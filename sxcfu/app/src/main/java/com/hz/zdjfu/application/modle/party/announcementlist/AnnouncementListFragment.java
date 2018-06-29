package com.hz.zdjfu.application.modle.party.announcementlist;

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
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.data.bean.AnnouncementBean;
import com.hz.zdjfu.application.data.bean.AnnouncementList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.XXRefreshView;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

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
public class AnnouncementListFragment extends BaseFragment implements AnnouncementListsContract.View {

    private static final String TAG = AnnouncementListFragment.class.getName();
    @BindView(R.id.announcementlist_recycleview)
    RecyclerView announcementlistRecycleview;
    @BindView(R.id.announcementlist_xrefreshview)
    XXRefreshView announcementlistXrefreshview;
    @BindView(R.id.announcementlist_empty_data_rl)
    RelativeLayout emptyDataRl;

    private AnnouncementListsAdapter mAdapter;
    private AnnouncementListsContract.Presenter mPresenter;
    private List<AnnouncementBean> mLists;
    private UserBean mBean;

    public static AnnouncementListFragment newInstance() {
        return new AnnouncementListFragment();
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
    public void setPresenter(AnnouncementListsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initViewData() {

        mLists = new ArrayList<>();

        announcementlistXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                if(mPresenter!=null){
                    mPresenter.announcementListsHttpRequest(true);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if(mPresenter!=null){
                    mPresenter.announcementListsHttpRequest(false);
                }
            }
        });

        announcementlistRecycleview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                List<AnnouncementBean> mlists = adapter.getData();
                AnnouncementBean mBean =mlists.get(position);
                String url =null;
                if(!TextUtils.isEmpty(URLController.URL_ZZ)){
                    if(URLController.URL_ZZ.contains("pctest")){
                        url=URLController.URL_ZZ+"/appStatic/re_detail/"+mBean.getId()+".action";
                    }else{
                        url=URLController.URL_ZZ+"/zdjf/appStatic/re_detail/"+mBean.getId()+".action";
                    }
                }else{
                    return;
                }
                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL",url);
                mIntent.putExtra("WebView_TITLE",mBean.getTitle()+"");
                mActivity.startActivity(mIntent);

            }
        });

    }

    @Override
    public void showMessageData(AnnouncementList mlists, boolean isRefresh) {
        //停止刷新
        completeRefreshAndLoad();

        if(mlists==null){
            return;
        }

        if(isRefresh){
            if(mlists.getDataList().size()==0){
                emptyDataRl.setVisibility(View.VISIBLE);
                announcementlistRecycleview.setVisibility(View.GONE);
                return;
            }else{
                emptyDataRl.setVisibility(View.GONE);
                announcementlistRecycleview.setVisibility(View.VISIBLE);
            }

            if(mLists!=null&&mLists.size()>0){
                mLists.clear();
            }

        }


        mLists.addAll(mlists.getDataList());

        if(mAdapter==null){
            mAdapter = new AnnouncementListsAdapter(mActivity);
            mAdapter.setNewData(mLists);
            LinearLayoutManager manager = new LinearLayoutManager(mActivity);
            announcementlistRecycleview.setLayoutManager(manager);
            announcementlistRecycleview.setAdapter(mAdapter);
        }else{
            mAdapter.setNewData(mLists);
        }

    }


    @Override
    public void skipActivity(Class<?> mClass) {

    }

    @Override
    public void completeRefreshAndLoad() {

        if(announcementlistXrefreshview!=null){
            announcementlistXrefreshview.stopRefresh();
            announcementlistXrefreshview.stopLoadMore();
        }

    }

    @Override
    public void showH5Url(String url) {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_announcementlist;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new AnnouncementListPresenter(mActivity, this);

        initViewData();
        if (mPresenter != null) {
            mPresenter.announcementListsHttpRequest(true);
        }

    }

}
