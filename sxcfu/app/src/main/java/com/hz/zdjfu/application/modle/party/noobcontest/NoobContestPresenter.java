package com.hz.zdjfu.application.modle.party.noobcontest;

import android.content.Context;

import com.hz.zdjfu.application.data.bean.NoobContestBean;

import java.util.ArrayList;
import java.util.List;


/**
 * [类功能说明]
 *新手赢奖请求接口
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class NoobContestPresenter implements NoobContestContract.Presenter{


    private NoobContestContract.View mView;
    private Context mContext;

    public NoobContestPresenter(NoobContestContract.View view, Context context){
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
    public void noobContestHttpRequest() {

        List<NoobContestBean> list = new ArrayList<>();
        NoobContestBean mBean = new NoobContestBean();
        mBean.setmContent("成功注册奖励10正经值");
        mBean.setmName("新手注册");
        mBean.setmState(true);
        list.add(mBean);

        NoobContestBean mBean1 = new NoobContestBean();
        mBean1.setmContent("成功实名认证奖励10正经值");
        mBean1.setmName("实名认证");
        mBean1.setmState(true);
        list.add(mBean1);

        NoobContestBean mBean2 = new NoobContestBean();
        mBean2.setmContent("成功绑定储蓄卡奖励10正经值");
        mBean2.setmName("绑定储蓄卡");
        mBean2.setmState(false);
        list.add(mBean2);

        NoobContestBean mBean3 = new NoobContestBean();
        mBean3.setmContent("成功下载APP并投资奖励25正经值");
        mBean3.setmName("下载APP并投资");
        mBean3.setmState(true);
        list.add(mBean3);

        NoobContestBean mBean4 = new NoobContestBean();
        mBean4.setmContent("成功首次投资>=1000元奖励20正经值");
        mBean4.setmName("首次投资");
        mBean4.setmState(true);
        list.add(mBean4);

        NoobContestBean mBean5 = new NoobContestBean();
        mBean5.setmContent("成功首次复投>=1000元奖励20正经值");
        mBean5.setmName("首次复投");
        mBean5.setmState(true);
        list.add(mBean5);
        mView.showNoobContestData(list);
    }

    @Override
    public void completeHttpRequest(String mid, String phone) {

    }
}
