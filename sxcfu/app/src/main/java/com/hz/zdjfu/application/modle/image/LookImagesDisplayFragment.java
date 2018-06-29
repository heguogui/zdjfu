package com.hz.zdjfu.application.modle.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;

import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.modle.mine.rechange.rechangehelp.RechangeHelpFragment;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.view.HackyViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/29 0029.
 */

public class LookImagesDisplayFragment extends BaseFragment {
    private static final String TAG = RechangeHelpFragment.class.getName();
    @BindView(R.id.images_view_pager)
    HackyViewPager imagesViewPager;
    @BindView(R.id.txt_image_index)
    TextView txtImageIndex;
    @BindView(R.id.txt_image_name)
    TextView txtImageName;

    private Intent mIntent = null;
    private Bundle mBundle = null;
    private ArrayList<LookImageBean> imagesBean;
    private int imgIndex = 0;
    private String mType;

    public static LookImagesDisplayFragment newInstance() {
        return new LookImagesDisplayFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lookimagedisplay;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mIntent = mActivity.getIntent();
        if (mIntent != null) {
            mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                imagesBean = (ArrayList<LookImageBean>) mBundle.getSerializable("imgUrls");
                imgIndex = Integer.parseInt(mBundle.getString("imgIndex"));
            }
        }


        txtImageIndex.setText(imgIndex + 1 +"/"+imagesBean.size()+"");
        txtImageName.setText(imagesBean.get(imgIndex).getM_name()+"");

        imagesViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                // 显示当前的选择页数和总页数
                txtImageIndex.setText(position + 1 +"/"+imagesBean.size()+"");
                txtImageName.setText(imagesBean.get(position).getM_name()+"");
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });





        imagesViewPager.setAdapter(new SamplePagerAdapter(imagesBean));
        // 选择默认要查看的图片
        imagesViewPager.setCurrentItem(imgIndex);

    }


    class SamplePagerAdapter extends PagerAdapter {

        private ArrayList<LookImageBean> mBeanLists ;
        private SamplePagerAdapter(ArrayList<LookImageBean> item){
            mBeanLists = item;
        }

        @Override
        public int getCount() {
            return mBeanLists.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoView photoView = new PhotoView(container.getContext());

//            if(!TextUtils.isEmpty(mBeanLists.get(position).getM_url())){
//                ImageLoader.getInstance().displayImage(mActivity,mBeanLists.get(position).getM_url(),photoView);
//            }else{
//                ImageLoader.getInstance().displayImage(mActivity,R.mipmap.ic_main_advertising_defulat,photoView);
//            }

            Glide.with(mActivity).load(UiUtils.URLEncoderFileImage(mBeanLists.get(position).getM_url())).asBitmap()
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    photoView.setAdjustViewBounds(true);
                    photoView.setImageBitmap(resource);
                }
            });

            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    // 单击的时候，关闭当前图片预览页面
                    mActivity.finish();
                }

            });


            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }




    private boolean isViewPagerActive() {
        return (imagesViewPager != null && imagesViewPager instanceof HackyViewPager);
    }


}
