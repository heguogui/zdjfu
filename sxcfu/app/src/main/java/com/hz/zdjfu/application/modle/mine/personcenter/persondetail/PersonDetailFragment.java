package com.hz.zdjfu.application.modle.mine.personcenter.persondetail;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hz.zdjfu.application.BuildConfig;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.CameraPermissionEven;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.personcenter.updatenick.UpdateNickActivity;
import com.hz.zdjfu.application.utils.ImageUtils;
import com.hz.zdjfu.application.utils.SDCardUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.hz.zdjfu.application.widget.dialog.PickPicturePoPuWindow;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 个人详情Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class PersonDetailFragment extends BaseFragment implements PersonDetailContract.View {

    private static final String TAG = PersonDetailFragment.class.getName();
    @BindView(R.id.persondetail_headpicture_iv)
    ImageView persondetailHeadpictureIv;
    @BindView(R.id.persondetail_headpicture_rl)
    RelativeLayout persondetailHeadpictureRl;
    @BindView(R.id.persondetail_phone_tv)
    TextView persondetailPhoneTv;
    @BindView(R.id.persondetail_nick_tv)
    TextView persondetailNickTv;
    @BindView(R.id.persondetail_nick_rl)
    RelativeLayout persondetailNickRl;



    private PickPicturePoPuWindow mPoPuWindow;
    ;
    private static final int PHOTO_REQUEST_CAMERA = 10;// 拍照
    private static final int PHOTO_REQUEST_ABLUM = 11;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 12;// 结果
    private static final int HEAD_PICTURE_TYPE_CAMERA = 1;//拍照
    private static final int HEAD_PICTURE_TYPE_ABULM = 2;//相册上传
    private static final int CAMEAR_PERMISSION = 0x112;//授予拍照权限回调
    private static final String PHOTO_FILE_NAME = "my.png";
    private String imagePath = null;
    private final String FILE_ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/zdjfu/images/save/photo/";
    private Uri uritempFile;
    private File file;

    private PersonDetailContract.Presenter mPresenter;

    public static PersonDetailFragment newInstance() {
        return new PersonDetailFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(PersonDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_persondetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new PersonDetailPresenter(mActivity, this);
        initViewData();

    }

    @Override
    public void initView() {

    }



    @Override
    public void getPictureFormCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // 下面这句指定调用相机拍照后的照片存储的路径
            imagePath = FILE_ROOT_PATH;
            file = new File(imagePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (file != null && file.exists()) {
                 /*获取当前系统的android版本号*/
                int currentapiVersion = Build.VERSION.SDK_INT;
                if (currentapiVersion < Build.VERSION_CODES.M) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(file, PHOTO_FILE_NAME)));
                    startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                } else {
                    //判断是否授予权限
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {//没有授予则弹出框
                        ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA}, CAMEAR_PERMISSION);
                    } else {
                        openCamra();
                    }
                }


            } else {
                ToastUtils.show(mActivity, "拍照失败");
            }
        } else {
            ToastUtils.show(mActivity, "拍照失败");
        }
    }

    @Override
    public void getPictureFromAbulm() {

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_REQUEST_ABLUM);
    }

    @Override
    public void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        // 图片格式
        // intent.putExtra("outputFormat", "png");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别

        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion < Build.VERSION_CODES.M) {
            /**
             * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
             * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
             */
            //intent.putExtra("return-data", true);
            //uritempFile为Uri类变量，实例化uritempFile
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "xiangling.png");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            startActivityForResult(intent, PHOTO_REQUEST_CUT);

        } else {

            mActivity.grantUriPermission("com.android.camera", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            uritempFile = uri;
            startActivityForResult(intent, PHOTO_REQUEST_CUT);
        }
    }

    @Override
    public void showNewHeadPicture(Bitmap bitmap) {

    }

    @Override
    public void handAndSubmitPicture(Bitmap bitmap) {

    }

    @Override
    public void updateHeadPictureSucccess(String url) {
        DialogManager.hideProgressDialog();
    }

    @Override
    public void updateHeadPictureFail(String url) {

    }

    @Override
    public void openCamra() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File newfile = new File(file, PHOTO_FILE_NAME);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".FileProvider", newfile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    @Override
    public void cropImage(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //intent.putExtra("return-data", true);
        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "xiangling.png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        startActivityForResult(intent, PHOTO_REQUEST_CUT);


    }

    @Override
    public void initViewData() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();

        if (mBean == null)
            return;
        //手机号
        if (!TextUtils.isEmpty(mBean.getUserPhone())) {
            persondetailPhoneTv.setText(StringUtils.desenPhoneNumber(mBean.getUserPhone()) + "");
        }
        //昵称
        if (!TextUtils.isEmpty(mBean.getUserNick())) {
            persondetailNickTv.setText(mBean.getUserNick() + "");
        } else {
            persondetailNickTv.setText(getString(R.string.personcenter_nick_empty_tv) + "");
        }

        //头像
        if (!TextUtils.isEmpty(mBean.getUserHead())) {
            ImageLoader.getInstance().displayImage(mActivity, mBean.getUserHead(), persondetailHeadpictureIv);
        } else {
            ImageLoader.getInstance().displayCircleImage(mActivity, R.mipmap.ic_launcher, persondetailHeadpictureIv);
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PHOTO_REQUEST_ABLUM:

                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        if (uri == null) {
                            ToastUtils.show(mActivity, "修改失败");
                            return;
                        }
                        cropImage(uri);
                    }
                }
                break;
            case PHOTO_REQUEST_CAMERA:
                if (resultCode == Activity.RESULT_OK) {

                    int currentapiVersion = Build.VERSION.SDK_INT;
                    if (currentapiVersion < Build.VERSION_CODES.M) {
                        File tempFile = new File(FILE_ROOT_PATH, PHOTO_FILE_NAME);
                        if (tempFile == null) {
                            return;
                        }
                        crop(Uri.fromFile(tempFile));

                    } else {
                        imagePath = FILE_ROOT_PATH;
                        File mfile = new File(imagePath + PHOTO_FILE_NAME);
                        if (mfile == null) {
                            return;
                        }
                        Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + ".FileProvider", mfile);
                        crop(contentUri);
                    }
                }
                break;

            case PHOTO_REQUEST_CUT:
                if (resultCode == Activity.RESULT_OK) {
                    if (uritempFile == null) {
                        return;
                    }
                    DialogManager.showProgressDialog(mActivity, "提交中...");
                    Bitmap mBitmap = null;
                    try {
                        mBitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(uritempFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (mBitmap != null) {

                        String filePath = ImageUtils.saveBitmapToSD(mBitmap, "zdjfu");
                        if (TextUtils.isEmpty(filePath)) {
                            ToastUtils.show(mActivity, "图片保存路径有问题");
                            DialogManager.hideProgressDialog();
                            return;
                        }

                        //处理图片
                        int degree = ImageUtils.readPictureDegree(filePath);
                        Bitmap bitmap = ImageUtils.handPicture(filePath, degree);

                        if (bitmap != null) {
                            //提交图片
                            mPresenter.pullHeadPicture(ImageUtils.Bitmap2StrByBase64(bitmap));

                            persondetailHeadpictureIv.setImageBitmap(bitmap);

//                            //回收图片资源
//                            if (mBitmap != null && !mBitmap.isRecycled()) {
//                                mBitmap.recycle();
//                                mBitmap = null;
//                            }
                        }

                    }
                } else {
                    DialogManager.hideProgressDialog();
                    ToastUtils.show(mActivity, "修改失败");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    /**
     * 拍照授予权限后回调 继续打开相机
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cameraPermissionCallBack(CameraPermissionEven event) {
        if (event == null) {
            return;
        }
        if (event.isSuccess) {
            openCamra();
        }
    }



    @OnClick({R.id.persondetail_headpicture_rl, R.id.persondetail_nick_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_headpicture_rl:
                updateHeadPicture();
                break;
            case R.id.persondetail_nick_rl:
                startActivity(UpdateNickActivity.makeIntent(mActivity,null));
                break;
        }
    }


    @Override
    public void updateHeadPicture() {
        if (SDCardUtils.hasSDCard() && SDCardUtils.isAvailable()) {

            mPoPuWindow = new PickPicturePoPuWindow(mActivity, new PickPicturePoPuWindow.ViewOnCleckListener() {
                @Override
                public void callBack(int type) {

                    if (type == HEAD_PICTURE_TYPE_CAMERA) {//拍照
                        //  EventBus.getDefault().post(new UpdataPictureEven(true));
                        getPictureFormCamera();

                    } else if (type == HEAD_PICTURE_TYPE_ABULM) {//相册上传
                        //   EventBus.getDefault().post(new UpdataPictureEven(true));
                        getPictureFromAbulm();
                    }
                }

            });
            mPoPuWindow.showAtLocation(mActivity.findViewById(R.id.persondetail_mine_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        } else {
            ToastUtils.show(mActivity, getString(R.string.common_sd_error));
        }

    }
}
