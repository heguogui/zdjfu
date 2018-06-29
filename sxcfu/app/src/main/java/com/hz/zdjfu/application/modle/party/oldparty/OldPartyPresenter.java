package com.hz.zdjfu.application.modle.party.oldparty;

import android.content.Context;

import com.hz.zdjfu.application.data.bean.PartyBean;

import java.util.ArrayList;
import java.util.List;


/**
 * [类功能说明]
 *活动请求接口
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class OldPartyPresenter implements OldPartyContract.Presenter{


    private OldPartyContract.View mView;
    private int currPage =0;
    private Context mContext;

    public OldPartyPresenter(OldPartyContract.View view, Context context){
        this.mView =view;
        mView.setPresenter(this);
        this.mContext =context;
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void partysHttpRequest(boolean isrefresh) {

        List<PartyBean> mPartyLists = new ArrayList<>();

        if(isrefresh){
            mPartyLists.clear();
            PartyBean mBean = new PartyBean();
            mBean.setmTime("2017-10-17");
            mBean.setmTitle("正道金服3");
            mBean.setmImageuUrl("");
            mBean.setMid("2");
            mPartyLists.add(mBean);

            PartyBean mBean1 = new PartyBean();
            mBean1.setmTime("2017-10-17");
            mBean1.setmTitle("正道金服4");
            mBean1.setmImageuUrl("");
            mBean1.setMid("2");
            mPartyLists.add(mBean1);

            PartyBean mBean2 = new PartyBean();
            mBean2.setmTime("2017-10-17");
            mBean2.setmTitle("正道金服5");
            mBean2.setmImageuUrl("");
            mBean2.setMid("2");
            mPartyLists.add(mBean2);
        }else{
            PartyBean mBean = new PartyBean();
            mBean.setmTime("2017-10-17");
            mBean.setmTitle("正道金服6");
            mBean.setmImageuUrl("");
            mBean.setMid("2");
            mPartyLists.add(mBean);

            PartyBean mBean1 = new PartyBean();
            mBean1.setmTime("2017-10-17");
            mBean1.setmTitle("正道金服7");
            mBean1.setmImageuUrl("");
            mBean1.setMid("2");
            mPartyLists.add(mBean1);

            PartyBean mBean2 = new PartyBean();
            mBean2.setmTime("2017-10-17");
            mBean2.setmTitle("正道金服8");
            mBean2.setmImageuUrl("");
            mBean2.setMid("2");
            mPartyLists.add(mBean2);

            PartyBean mBean3 = new PartyBean();
            mBean3.setmTime("2017-10-17");
            mBean3.setmTitle("正道金服9");
            mBean3.setmImageuUrl("");
            mBean3.setMid("");
            mPartyLists.add(mBean3);
        }

        mView.showPartyViewData(mPartyLists,isrefresh);

    }
}
