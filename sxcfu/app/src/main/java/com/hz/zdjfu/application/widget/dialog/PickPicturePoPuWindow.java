package com.hz.zdjfu.application.widget.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.StringUtils;

/**
 * [类功能说明]
 *剪切图片上传
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/22 0022
 */
public class PickPicturePoPuWindow extends PopupWindow implements View.OnClickListener{


    private Context mContext;
    private TextView photoAlbumText;
    private TextView photoGraphText;
    private TextView cancelTextView;
    private ViewOnCleckListener mViewOnCleckListener;
    private View view;
    private LayoutInflater inflater;
    private RelativeLayout mParent;

    /**
     * <p>Title </p>
     * <p>Description 选择照片弹出框</p>
     *
     * @param context
     */
    public PickPicturePoPuWindow(Context context, ViewOnCleckListener viewOnCleckListener) {
        super(context);
        this.mContext = context;
        this.mViewOnCleckListener = viewOnCleckListener;
        init();
    }

    private void init() {

        this.inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.view = inflater.inflate(R.layout.view_pick_picture_window, null);
        photoAlbumText = (TextView) view.findViewById(R.id.tv_album);
        photoGraphText = (TextView) view.findViewById(R.id.tv_graph);
        cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        mParent = (RelativeLayout) view.findViewById(R.id.head_picture_ll);
        photoAlbumText.setOnClickListener(this);
        photoGraphText.setOnClickListener(this);
        cancelTextView.setOnClickListener(this);
        mParent.setOnClickListener(this);

        setContentView(view);
        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.WRAP_CONTENT);
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
            case R.id.tv_album://拍照

                if (!StringUtils.isFastClick()) {
                    mViewOnCleckListener.callBack(1);
                    dismiss();
                }

                break;
            case R.id.tv_graph://相册
                if (!StringUtils.isFastClick()) {
                    mViewOnCleckListener.callBack(2);
                    dismiss();
                }
                break;
            case R.id.tv_cancel://取消
            case R.id.head_picture_ll:
                dismiss();
                break;
        }
    }


    public interface ViewOnCleckListener {
        void callBack(int type);
    }


}
