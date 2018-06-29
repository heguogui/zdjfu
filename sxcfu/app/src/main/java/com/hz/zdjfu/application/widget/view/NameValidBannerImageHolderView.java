package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.image.ImageLoader;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/30 0030.
 */

public class NameValidBannerImageHolderView implements Holder<String> {

    private ImageView imageView;
    private Context mContext;


    /**
     * 构造器
     */
    public NameValidBannerImageHolderView (Context context){
        this.mContext =context;
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
    public void UpdateUI(Context context, int position, String data) {

        if(TextUtils.isEmpty(data)){
            ImageLoader.getInstance().displayImage(context, R.mipmap.ic_main_advertising_defulat,imageView);
        }else{
            if(data.equals("ONE")){
                ImageLoader.getInstance().displayImage(context,R.mipmap.open_one,imageView);
            }else if(data.equals("TWO")){
                ImageLoader.getInstance().displayImage(context,R.mipmap.open_two,imageView);
            }else if(data.equals("THREE")){
                ImageLoader.getInstance().displayImage(context,R.mipmap.open_three,imageView);
            }else if(data.equals("FOUR")){
                ImageLoader.getInstance().displayImage(context,R.mipmap.open_four,imageView);
            } else {
                ImageLoader.getInstance().displayImage(context, R.mipmap.ic_main_advertising_defulat,imageView);
            }
        }

    }


}
