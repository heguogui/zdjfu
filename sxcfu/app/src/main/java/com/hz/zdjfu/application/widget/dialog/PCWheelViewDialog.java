package com.hz.zdjfu.application.widget.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.modle.adapter.RecycleViewDialogAdapter;


import java.util.List;

/**
 * [类功能说明]
 *省市选择器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class PCWheelViewDialog extends PopupWindow implements View.OnClickListener{

    private Context mContext;
    private RecyclerView mWV;
    private RelativeLayout cancelTextView;
    private PCWheelViewDialog.ViewOnCleckListener mViewOnCleckListener;
    private PCWheelViewDialog.ChangeOnCleckListener mChangeOnCleckListener;
    private View view;
    private LayoutInflater inflater;
    private List<String> mLists;
    private RecycleViewDialogAdapter mAdapter;
    private TextView proverceTv,cityTv,lineTv;


    public PCWheelViewDialog(Context context, List<String> mlists, PCWheelViewDialog.ViewOnCleckListener viewOnCleckListener,PCWheelViewDialog.ChangeOnCleckListener changeOnCleckListener) {
        super(context);
        this.mContext = context;
        this.mViewOnCleckListener = viewOnCleckListener;
        this.mChangeOnCleckListener =changeOnCleckListener;
        this.mLists =mlists;
        this.mAdapter =new RecycleViewDialogAdapter(context);
        init();
    }

    private void init() {

        this.inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.view_pc_wheelview_dialog_layout, null);
        mWV =view.findViewById(R.id.wheelview_rv);
        cancelTextView =view.findViewById(R.id.wheelview_cancel);
        cancelTextView.setOnClickListener(this);

        proverceTv =view.findViewById(R.id.wheelview_proverce);
        cityTv =view.findViewById(R.id.wheelview_city);
        lineTv =view.findViewById(R.id.wheelview_line);

        if(cityTv!=null){
            cityTv.setVisibility(View.GONE);
        }

        if(lineTv!=null){
            lineTv.setVisibility(View.GONE);
        }


        proverceTv.setOnClickListener(this);

        mAdapter.setNewData(mLists);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mWV.setLayoutManager(manager);
        mWV.setAdapter(mAdapter);
        mWV.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<String> mLists =adapter.getData();
                String flag =mLists.get(position);
                if(!TextUtils.isEmpty(flag)){
                    //获取城市
                    mViewOnCleckListener.callBack(flag);
                }

            }
        });


        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setAnimationStyle(R.style.menu_popuwindow);
        setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(dw);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wheelview_cancel://取消
                dismiss();
                break;
            case R.id.wheelview_proverce:
                mChangeOnCleckListener.callBack();
                break;
        }
    }


    public interface ViewOnCleckListener {
        void callBack(String type);
    }


    public interface ChangeOnCleckListener {
        void callBack();
    }

    public void notifyData(List<String> lists){

        if(lists==null||lists.size()==0){
            return;
        }

        if(mLists!=null&&mLists.size()>0){
            mLists.clear();
        }

        mLists.addAll(lists);

        if(mAdapter!=null){
            mAdapter.setNewData(mLists);
        }
    }


    public void setCityTextView(String flag){
        if(cityTv!=null&&proverceTv!=null&&!TextUtils.isEmpty(flag)&&lineTv!=null){
            cityTv.setText("选择城市"+"");
            proverceTv.setText(flag+"");
            proverceTv.setTextColor(mContext.getResources().getColor(R.color.blue5));
            cityTv.setVisibility(View.VISIBLE);
            lineTv.setVisibility(View.VISIBLE);
        }
    }


}
