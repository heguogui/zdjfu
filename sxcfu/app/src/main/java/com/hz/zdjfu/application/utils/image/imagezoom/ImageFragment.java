/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.utils.image.imagezoom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * [展示图片fragment]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class ImageFragment extends BaseFragment {

    private static final String IMAGE_URL = "image";
    @BindView(R.id.photo_view_image_zoom)
    PhotoView image;
    private String imageUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_zoom;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Glide.with(getContext()).load(imageUrl).error(R.mipmap.ic_launcher).into(image);
//        ImageLoader.getInstance().displayImage(getContext(), imageUrl, image);
        image.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                getActivity().finish();
            }

        });
    }

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(String param1) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
