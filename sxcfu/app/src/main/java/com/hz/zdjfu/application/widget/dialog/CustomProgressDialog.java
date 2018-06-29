package com.hz.zdjfu.application.widget.dialog;


import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;

/**
 * 只有图片和文字的dialog提示加载窗体
 */
public class CustomProgressDialog extends Dialog {
    private Context context;
    private static Button btnCancel;
    private static Button btnOK;
    private static RotateAnimation refreshingAnimation ;// 均匀旋转动画
    private static LinearInterpolator lir;

    private static CustomProgressDialog customProgressDialog;

    public CustomProgressDialog(Context context){
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }



    /**
     * 文字，图片信息加载获取提示框
     * @param context
     * @return
     */
     public static CustomProgressDialog createDialog(Context context){
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.custom_progress_dialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.rotating);// 均匀旋转动画
        lir = new LinearInterpolator();
        // 添加匀速转动动画
        refreshingAnimation.setInterpolator(lir);
        ImageView loading = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        loading.clearAnimation();
        loading.startAnimation(refreshingAnimation);
        return customProgressDialog;
    }

//    /**
//     * APP第一次进入时，提示的注册红包信息
//     * @param context
//     * @return
//     */
//    public static CustomProgressDialog appFirstWeclmoDialog(Context context){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.AppFirstProgressDialog);
//        customProgressDialog.setContentView(R.layout.app_first_frame);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        return customProgressDialog;
//    }


//    /**
//     * APP活动提示框
//     * @param context
//     * @return
//     */
//    public static CustomProgressDialog appActivityDialog(Context context){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.AppFirstProgressDialog);
//        customProgressDialog.setContentView(R.layout.app_activity_frame);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        return customProgressDialog;
//    }

//    /**
//     * 正经值说明
//     * @param context
//     * @return
//     */
//    public static CustomProgressDialog myCouponSxbInfo(Context context){
//
//            customProgressDialog = new CustomProgressDialog(context, R.style.AppFirstProgressDialog);
//        customProgressDialog.setContentView(R.layout.my_coupon_sxb_info);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        return customProgressDialog;
//    }

//    /**
//     * 社会化分享
//     * @param context
//     * @return
//     */
//    public static CustomProgressDialog share(Context context){
//
//            customProgressDialog = new CustomProgressDialog(context, R.style.AppFirstProgressDialog);
//        customProgressDialog.setContentView(R.layout.app_share);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
//
//        return customProgressDialog;
//    }

    /**
     *
     * 确认提示框
     * @param context
     * @param itemsOnClick 单击按钮回调函数
     * @return
     *
     */
    public static CustomProgressDialog createDialogContent(Context context, View.OnClickListener itemsOnClick){

        customProgressDialog = new CustomProgressDialog(context, R.style.DialogContent);
        customProgressDialog.setContentView(R.layout.dialog_confirm_content);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        btnCancel = (Button) customProgressDialog.findViewById(R.id.btn_cancel);
        btnOK = (Button) customProgressDialog.findViewById(R.id.btn_OK);

        btnCancel.setOnClickListener( itemsOnClick);
        btnOK.setOnClickListener(itemsOnClick);

        return customProgressDialog;
    }

//    /**
//     *
//     *  只有一个按钮时的提示框
//     * @param context
//     * @param itemsOnClick 单击按钮回调函数
//     * @return
//     *
//     */
//    public static CustomProgressDialog createDialogContentOnlyBtn(Context context, View.OnClickListener itemsOnClick){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.DialogContent);
//        customProgressDialog.setContentView(R.layout.dialog_confirm_only_btn);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        btnOK = (Button) customProgressDialog.findViewById(R.id.btn_OK);
//
//        btnOK.setOnClickListener(itemsOnClick);
//
//        return customProgressDialog;
//    }



//    /**
//     *
//     * APP版本更新 信息确认提示框
//     * @param context
//     * @param itemsOnClick 单击按钮回调函数
//     * @return
//     *
//     */
//    public static CustomProgressDialog createAppDownAPKDialog(Context context, View.OnClickListener itemsOnClick){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.DialogContent);
//        customProgressDialog.setContentView(R.layout.app_dialog_confirm_upversion);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        btnCancel = (Button) customProgressDialog.findViewById(R.id.btn_cancel);
//        btnOK = (Button) customProgressDialog.findViewById(R.id.btn_OK);
//
//        btnCancel.setOnClickListener( itemsOnClick);
//        btnOK.setOnClickListener(itemsOnClick);
//
//        return customProgressDialog;
//    }

//    /**
//     * 版本更新进度
//     * @param context
//     * @param itemsOnClick 单击按钮回调函数
//     * @return
//     *
//     */
//    public static CustomProgressDialog createAppDownSpeelDialog(Context context, View.OnClickListener itemsOnClick){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.DialogContent);
//        customProgressDialog.setContentView(R.layout.app_version_loadapk);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        btnOK = (Button) customProgressDialog.findViewById(R.id.btn_OK);
//        btnOK.setOnClickListener(itemsOnClick);
//
//        return customProgressDialog;
//    }

    public void onWindowFocusChanged(boolean hasFocus){

        if (customProgressDialog == null){
            return;
        }

    }

    /**
     *
     * [Summary]
     *       setTitile 标题
     * @param strTitle
     * @return
     *
     */
    public CustomProgressDialog setTitile(String strTitle){
        return customProgressDialog;
    }

    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public CustomProgressDialog setMessage(String strMessage){
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);

        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }

    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param txtContent
     * @return
     *
     */
    public CustomProgressDialog setTextContent(String title, String txtContent){

        TextView tvTitle = (TextView)customProgressDialog.findViewById(R.id.text_title);

        if (null  != title && !TextUtils.equals("",txtContent)){
            TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.text_content);
            tvMsg.setText(Html.fromHtml(txtContent));
        }
        if (null != title && !TextUtils.equals("",title)){
            tvTitle.setText(title);
        }else{ // 没有内容的时候，隐藏
            tvTitle.setVisibility(View.GONE);
        }

        return customProgressDialog;
    }


//    /**
//     * 版本更新信息设置
//     * @param version 版本号
//     * @param title 标题信息
//     * @param txtContent 更新 内容
//     * @return
//     */
//    public CustomProgressDialog setTextUpDataVersionContent(String version, String title, String txtContent){
//
//        TextView tvTitle = (TextView)customProgressDialog.findViewById(R.id.text_title);
//        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.text_content);
//        TextView tvVersion = (TextView) customProgressDialog.findViewById(R.id.text_version);
//
//        if(TextUtils.isEmpty(version)){
//            tvVersion.setVisibility(View.GONE);
//        }else{
//            tvVersion.setVisibility(View.VISIBLE);
//            tvVersion.setText(version);
//        }
//
//        if(TextUtils.isEmpty(title)){
//            title="更新内容";
//        }
//        tvTitle.setText(title);
//
//        if(TextUtils.isEmpty(txtContent)){
//            tvMsg.setVisibility(View.GONE);
//        }else{
//            tvMsg.setVisibility(View.VISIBLE);
//            tvMsg.setText(Html.fromHtml(txtContent));
//        }
//
//        return customProgressDialog;
//    }


//    /**
//     * 版本更新弹框
//     * @param context
//     * @param itemsOnClick 监听
//     * @return
//     */
//    public static CustomProgressDialog createUpdateVersionDialog(Context context, View.OnClickListener itemsOnClick){
//
//        customProgressDialog = new CustomProgressDialog(context, R.style.CustomDialog);
//        customProgressDialog.setContentView(R.layout.app_dialog_upversion);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        RelativeLayout btnCancel = (RelativeLayout) customProgressDialog.findViewById(R.id.btn_cancel);
//        ImageView btnOK = (ImageView) customProgressDialog.findViewById(R.id.btn_OK);
//
//        btnCancel.setOnClickListener( itemsOnClick);
//        btnOK.setOnClickListener(itemsOnClick);
//
//        return customProgressDialog;
//    }


}

