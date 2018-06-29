package com.hz.zdjfu.application.modle.mine.personcenter.persondetail;

import android.graphics.Bitmap;
import android.net.Uri;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

/**
 * [类功能说明]
 *我的页面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface PersonDetailContract {



    //view 接口
    interface View extends BaseView<PersonDetailContract.Presenter> {
        //初始View
        void initView();

        //修改头像
        void updateHeadPicture();

        //相机拍照
        void getPictureFormCamera();

        //相册获取
        void getPictureFromAbulm();

        //剪切图片
        void crop(Uri uri);

        //显示新图片
        void showNewHeadPicture(Bitmap bitmap);

        //处理图片及上传（防止拍照角度旋转以及质量过大）
        void handAndSubmitPicture(Bitmap bitmap);

        //上传头像成功
        void updateHeadPictureSucccess(String url);

        //上传头像失败
        void updateHeadPictureFail(String url);

        //拍照
        void openCamra();

        //剪切图片
        void cropImage(Uri uri);


    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 上传头像
         * @param url
         */
       void pullHeadPicture(String url);

    }

}
