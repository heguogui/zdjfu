package com.hz.zdjfu.application.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.cazaea.sweetalert.SweetAlertDialog;

/**
 * [类功能说明]
 * 弹框工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class DialogManager {

    private static SweetAlertDialog mDialog;

    public static boolean isShow() {
        if (mDialog == null) {
            return false;
        }

        return mDialog.isShowing();

    }


    public static void showWarningDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmClickListener(listener);
        mDialog.show();
    }

    public static void showWarningDialog(
            Context context,
            String title,
            String content,
            SweetAlertDialog.OnSweetClickListener confirmListener,
            SweetAlertDialog.OnSweetClickListener cancleListener
    ) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmClickListener(confirmListener)
                .setCancelClickListener(cancleListener);
        mDialog.show();
    }

    public static void showLogoutWarningDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("确定")
                .setConfirmClickListener(listener);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public static void showErrorDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (mDialog != null) {
            mDialog = null;
        }

        if(context==null){
            return;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setConfirmText("确定")
                .setTitleText(title)
                .setContentText(content)
                .setConfirmClickListener(listener);
        mDialog.show();
    }

    public static void showProgressDialog(Context context, String message) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper().setBarColor(context.getResources().getColor(android.R.color.darker_gray));
        mDialog.setTitleText(message+"");
        mDialog.setCancelable(true);
        mDialog.show();
    }


    public static void showProgressDialog(Context context, String message, int progress) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper().setBarColor(context.getResources().getColor(android.R.color.darker_gray));
        mDialog.setTitleText(message);
        mDialog.setCancelable(true);
        mDialog.getProgressHelper().setProgress(progress);
        mDialog.show();
    }

    public static void showNormalDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (context == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setTitleText(title)
                .setContentText(content)
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(listener)
                .show();
    }

    /**
     * @param context
     * @param title     TitleText
     * @param content   ContentText
     * @param listener  确认按钮事件
     * @param listener2 取消按钮事件（可传空）
     * @param b         是否强制升级（是--->true）
     * @return
     */
    public static SweetAlertDialog showNormalDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener, SweetAlertDialog.OnSweetClickListener listener2, boolean b, String sure, String cancle) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        if (!b) {
            mDialog.setCancelText(cancle)
                    .setCancelClickListener(listener2);
        } else {    // 强制版本升级
            mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            mDialog.setCancelable(false);
        }
        mDialog.setTitleText(title)
                .setContentText(content)
                .setConfirmText(sure)
                .setConfirmClickListener(listener)
                .show();
        return mDialog;
    }

    public static void showSuccessDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener listener) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        mDialog.setTitleText(title)
                .setContentText(content)
                .setConfirmText("确定")
                .setConfirmClickListener(listener)
                .show();
    }

    public static void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public static void hideWithAnimation() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismissWithAnimation();
            mDialog = null;
        }
    }


    /**
     * @param context
     * @param title
     * @param content
     * @param confirmListener
     * @param cancleListener
     */
    public static void showNormalDialog(Context context, String title, String content, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancleListener) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setTitleText(title)
                .setContentText(content)
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(confirmListener)
                .setCancelClickListener(cancleListener)
                .show();
    }


    /**
     * 通用弹框
     *
     * @param context         上下文
     * @param title           标题
     * @param content         内容
     * @param leftbtn         左边按钮文字
     * @param rightbtn        右边按钮文字
     * @param confirmListener 确定监听
     * @param cancleListener  取消监听
     */
    public static void showGeneralDialog(Context context, String title, String content, String leftbtn, String rightbtn, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancleListener) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(leftbtn) || TextUtils.isEmpty(rightbtn)) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setTitleText(title)
                .setContentText(content)
                .setCancelText(leftbtn)
                .setConfirmText(rightbtn)
                .setConfirmClickListener(confirmListener)
                .setCancelClickListener(cancleListener)
                .show();
    }


}