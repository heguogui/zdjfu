package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.BannerRecordsBean;
import com.hz.zdjfu.application.modle.home.HomeFragment;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;

/**
 * [类功能说明]
 *自定义广告轮播图类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/29 0029
 */
public class BannerImageHolderView implements Holder<BannerRecordsBean>{


    private HomeFragment homeFragment;
    private ImageView imageView;
    private MyOnClickListener onClickListener;


    /**
     * 构造器
     * @param homeFragment
     */
    public BannerImageHolderView (HomeFragment homeFragment){
        this.homeFragment =homeFragment;
        onClickListener = new MyOnClickListener(homeFragment);
    }


    /**
     * 创建轮播子View
     * @param context
     * @return
     */
    @Override
    public View createView(Context context) {

        this.imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    /**
     * 绑定数据
     * @param context
     * @param position
     * @param data
     */
    @Override
    public void UpdateUI(Context context, int position, BannerRecordsBean data) {

        if(data==null||data.getId().equals(Constants.BANNER_ID)){
            ImageLoader.getInstance().displayImage(context,R.mipmap.ic_main_advertising_defulat,imageView);
        }else{
            onClickListener.setData(data);
            imageView.setOnClickListener(onClickListener);
            String imageUrl =null;
            if(!TextUtils.isEmpty(data.getImage_url())){

               // imageUrl = UiUtils.URLReplaceAll(data.getImage_url())+"?"+Constants.CJRANDJCR;
                imageUrl =UiUtils.URLEncoderFileImage(data.getImage_url());
                ImageLoader.getInstance().displayImageNocenterCrop(context,imageUrl,R.mipmap.ic_main_advertising_defulat,imageView);
            }else{
                ImageLoader.getInstance().displayImage(context,R.mipmap.ic_main_advertising_defulat,imageView);
            }
        }

    }


    /**
     * 对子View 监听
     */
    private static class MyOnClickListener implements View.OnClickListener {

        private BannerRecordsBean data;

        HomeFragment homeFragment;

        public MyOnClickListener(HomeFragment homeFragment) {
            this.homeFragment = homeFragment;
        }

        public BannerRecordsBean getData() {
            return data;
        }

        public void setData(BannerRecordsBean data) {
            this.data = data;
        }

        @Override
        public void onClick(View view) {
            if (data != null) {
                homeFragment.goBannerDetail(data);
            }
        }
    }
}
