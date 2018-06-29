package com.hz.zdjfu.application.modle.ztprogect;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.FileBean;
import com.hz.zdjfu.application.data.bean.ZTLoanerFiles;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/8 0008.
 */

public class ProductDetailGridviewAdapter extends BaseAdapter{


    private Context context;
    private List<ZTLoanerFiles> mlists;

    public ProductDetailGridviewAdapter(Context context, List<ZTLoanerFiles> mlists) {
        this.context = context;
        this.mlists = mlists;
    }

    public void setData(List<ZTLoanerFiles> lists){
        if(lists!=null){
            mlists=lists;
        }
    }


    @Override
    public int getCount() {
        return mlists ==null?0:mlists.size();
    }

    @Override
    public ZTLoanerFiles getItem(int pos) {
        return mlists.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        ItemViewHolder mHolder =null;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.view_zt_product_detail_loaner_item,null);
            mHolder =new ItemViewHolder(view);
            view.setTag(mHolder);
        }else{
            mHolder = (ItemViewHolder) view.getTag();
        }

        ZTLoanerFiles bean =mlists.get(pos);
        mHolder.bindData(context,bean);
        return view;
    }


    private class  ItemViewHolder {
        private ImageView mIv;

        public ItemViewHolder(View view) {
            this.mIv = view.findViewById(R.id.lender_credentials_detail_item_iv);
        }

        private void bindData(Context mContext,ZTLoanerFiles bean){

            if(!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(bean.getFileUrl()))){
                ImageLoader.getInstance().displayImage(mContext,UiUtils.URLEncoderFileImage(bean.getFileUrl()),mIv);
            }else{
                ImageLoader.getInstance().displayImage(mContext,R.mipmap.ic_main_advertising_defulat,mIv);
            }
        }
    }
}
