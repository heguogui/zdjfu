package com.hz.zdjfu.application.modle.party.announcementlist;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.AnnouncementBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class AnnouncementListsAdapter extends BaseQuickAdapter<AnnouncementBean,BaseViewHolder> {

    private Context mContext;
    public AnnouncementListsAdapter(Context context) {
        super(R.layout.view_announcementlist_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AnnouncementBean item) {
        if(item==null){
            return;
        }
        if(!TextUtils.isEmpty(item.getCreate_time())){
            if(item.getCreate_time().contains(" ")){
                String[] create_time =item.getCreate_time().split(" ");
                helper.setText(R.id.announcementlists_time_tv, create_time[0]+"");
            }else{
                helper.setText(R.id.announcementlists_time_tv,item.getCreate_time()+"");
            }
        }

        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.announcementlists_title_tv,item.getTitle()+"");
        }
    }
}
