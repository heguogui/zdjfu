package com.hz.zdjfu.application.modle.party.oldparty;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.PartyBean;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;

/**
 * [类功能说明]
 *往前活动适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/17 0017.
 */

public class OldPartyAdapter extends BaseQuickAdapter<PartyBean, BaseViewHolder> {

    private Context mContext;
    public OldPartyAdapter(Context context) {
        super(R.layout.view_oldparty_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PartyBean item) {

        if(item==null)
            return;

        RelativeLayout mOldPartyCenter =helper.getView(R.id.oldparty_item_oldparty_rl);
        if(TextUtils.isEmpty(item.getMid())){
            mOldPartyCenter.setVisibility(View.VISIBLE);
        }else{
            mOldPartyCenter.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.oldparty_item_oldparty_rl);

        ImageView mImageView =helper.getView(R.id.oldparty_item_image_iv);
        if (TextUtils.isEmpty(item.getmImageuUrl())) {
            ImageLoader.getInstance().displayCircleImage(mContext, R.mipmap.ic_list_defualt_bg, mImageView);
        } else {
            String imgUrl = UiUtils.URLReplaceAll(item.getmImageuUrl()+"?Finance=true");
            ImageLoader.getInstance().displayCircleImage(mContext, imgUrl,mImageView);
        }
        helper.addOnClickListener(R.id.oldparty_item_image_iv);
    }

}
