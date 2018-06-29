package com.hz.zdjfu.application.widget.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.modle.adapter.RecycleViewAdapter;

import java.util.List;

/**
 * [类功能说明]
 *银行选择器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class WheelViewDialog extends PopupWindow implements View.OnClickListener{

    private Context mContext;
    private RecyclerView mWV;
    private RelativeLayout cancelTextView;
    private WheelViewDialog.ViewOnCleckListener mViewOnCleckListener;
    private View view;
    private LayoutInflater inflater;
    private List<String> mLists;
    private RecycleViewAdapter mAdapter;
    public WheelViewDialog(Context context, List<String> mlists,WheelViewDialog.ViewOnCleckListener viewOnCleckListener) {
        super(context);
        this.mContext = context;
        this.mViewOnCleckListener = viewOnCleckListener;
        this.mLists =mlists;
        this.mAdapter =new RecycleViewAdapter(context);
        init();
    }

    private void init() {

        this.inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.view_wheelview_dialog_layout, null);
        mWV =view.findViewById(R.id.wheelview_rv);
        cancelTextView =view.findViewById(R.id.wheelview_cancel);
        cancelTextView.setOnClickListener(this);
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
                mViewOnCleckListener.callBack(flag);
                dismiss();
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
        }
    }


    public interface ViewOnCleckListener {
        void callBack(String type);
    }


}
