package com.hz.zdjfu.application.modle.discount;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.DiscountCouponBean;
import com.hz.zdjfu.application.data.bean.DiscountZJZBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 卡券
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class UsedFragment extends BaseFragment implements DiscountContract.View {


    @BindView(R.id.discount_discounts_number_tv)
    TextView discountDiscountsNumberTv;
    @BindView(R.id.discount_discounts_number_ll)
    LinearLayout discountDiscountsNumberLl;
    @BindView(R.id.discount_tag_tv)
    TextView discountTagTv;
    @BindView(R.id.discount_discounts_rl)
    RelativeLayout discountDiscountsRl;
    @BindView(R.id.discount_name_tv)
    TextView discountNameTv;
    @BindView(R.id.discount_day_tv)
    TextView discountDayTv;
    @BindView(R.id.discount_time_tv)
    TextView discountTimeTv;
    @BindView(R.id.discount_state_cb)
    ImageView discountStateCb;
    @BindView(R.id.discount_use_rv)
    RecyclerView discountUseRv;
    @BindView(R.id.discount_data_ll)
    LinearLayout discountDataLl;
    @BindView(R.id.discount_no_redpacket_coupon_ll)
    LinearLayout discountNoDataLl;
    @BindView(R.id.discount_discounts_number_rl)
    RelativeLayout discountDiscountsNumberRl;
    @BindView(R.id.discount_check_coupon_type_tv)
    TextView discountCheckCouponTv;
    @BindView(R.id.discount_check_coupon_type_num_tv)
    TextView discountCheckCouponTypeNumtv;
    @BindView(R.id.discount_check_coupon_type_ll)
    LinearLayout discountCheckCouponTypeLl;
    @BindView(R.id.discount_sl)
    ScrollView discountRv;


    //  private List<DiscountCouponBean>  mList;
    private DiscountUserAdapter mAdapter;
    private DiscountContract.Presenter mPresenter;
    private UserBean mBean;
    private String income_days;
    private String amount;
    private String  mProduct_id;
    public CheckCouponType mCheckCoupon;

    private List<DiscountCouponBean> mAdapterList;

    public static UsedFragment newInstance() {
        return new UsedFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_discount_used_layout;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new DiscountPresenter(mActivity, this);
        initViewData();
        initRecycleView();
    }

    @Override
    public void showErrorTips(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }
    }

    @Override
    public void initViewData() {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            income_days = mBundle.getString("INCOMEDAYS");
            amount = mBundle.getString("AMOUNT");
            mCheckCoupon = (CheckCouponType) mBundle.getSerializable("CHECKCOUPON");
            mProduct_id =mBundle.getString("PRODUCT_ID");
            mBean = UserInfoManager.getInstance().getLocationUserData();
            if (mPresenter != null && mBean != null && !TextUtils.isEmpty(mBean.getUserPhone())&&!TextUtils.isEmpty(mProduct_id)) {
                mPresenter.allCountsHttpRequest(mBean.getUserPhone(), income_days, amount,mProduct_id);
            }
        }



    }

    @Override
    public void showDateEmptyView(boolean state) {

        if (state) {
            discountNoDataLl.setVisibility(View.GONE);
        } else {
            discountNoDataLl.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initRecycleView() {

        mAdapterList = new ArrayList<>();
        if(mCheckCoupon==null){
            mCheckCoupon = new CheckCouponType();
        }

        if(discountRv!=null){
            discountRv.post(new Runnable() {
                @Override
                public void run() {
                    if(discountRv!=null){
                        discountRv.fullScroll(ScrollView.FOCUS_UP);
                    }
                }
            });
        }

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        discountUseRv.setLayoutManager(manager);
        mAdapter = new DiscountUserAdapter(mActivity);
        discountUseRv.setAdapter(mAdapter);
        discountUseRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                checkRedpacketAndAddinterestCoupon(position);
            }
        });


        discountUseRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                checkRedpacketAndAddinterestCoupon(position);
            }
        });

    }



    private void checkRedpacketAndAddinterestCoupon(int position) {
        if (mAdapterList != null && mAdapterList.size() > 0) {
            DiscountCouponBean mBean =mAdapterList.get(position);
            DiscountZJZBean mDiscountZJZBeanbean = mBean.getmDiscountZJZBean();
            if (mDiscountZJZBeanbean != null) {
                if (mDiscountZJZBeanbean.getType().equals("1")) {//红包
                    if(mBean.isCheck()){
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(), false,mDiscountZJZBeanbean, mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                    }else{
                        checkRedPacket(true);
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(), true,mDiscountZJZBeanbean, mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                    }

                } else if (mDiscountZJZBeanbean.getType().equals("2")) {//加息券

                    if(mBean.isCheck()){
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),false,mDiscountZJZBeanbean);
                    }else{
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),true,mDiscountZJZBeanbean);
                    }
                }
            }
        }
    }


    @Override
    public void setPresenter(DiscountContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initViewPager() {

    }

    @Override
    public void showAllDiscountCoupon(DiscountBean mbean) {

        if (mbean == null || mbean.getUse() == null) {


            return;
        }

        for (int i = 0; i < mbean.getUse().size(); i++) {
            DiscountCouponBean mDiscountCouponBean = new DiscountCouponBean();
            mDiscountCouponBean.setmDiscountZJZBean(mbean.getUse().get(i));
            mDiscountCouponBean.setCheck(false);
            mAdapterList.add(mDiscountCouponBean);
        }

        for (int i = 0; i < mAdapterList.size(); i++) {
            if (mAdapterList.get(i).getmDiscountZJZBean().getType().equals("3")) {
                mCheckCoupon.setmCheckZJZBean(mAdapterList.get(i).getmDiscountZJZBean());
                mAdapterList.remove(i);
            }
        }

        //显示title 总数
        try {
            if (mAdapterList!=null&&mAdapterList.size()>0) {
                ((DiscountActivity)mActivity).refreshLeftTitle(mAdapterList.size());
                showDateEmptyView(false);
            } else {
                ((DiscountActivity)mActivity).refreshLeftTitle(0);
                showDateEmptyView(true);
            }
        } catch (Exception e) {

        }


        showZjzData();

        if (mAdapter == null) {
            mAdapter = new DiscountUserAdapter(mActivity);
        }
        if (mAdapter != null) {
            mAdapter.setNewData(mAdapterList);
        }

        //默认推荐的优惠
        showDiscountCoupon(mbean);

    }


    private void showZjzData() {

        discountDiscountsNumberRl.setVisibility(View.VISIBLE);
        if (mCheckCoupon != null && mCheckCoupon.getmCheckZJZBean() != null) {
            discountDiscountsNumberTv.setText(mCheckCoupon.getmCheckZJZBean().getAmount() + "");
            discountNameTv.setText("正经值抵现");
            discountDayTv.setText("可用余额¥" + mCheckCoupon.getmCheckZJZBean().getDates() + "");
            discountTimeTv.setText("锁定0.00");
        }else{
            discountDiscountsNumberTv.setText("0");
            discountNameTv.setText("正经值抵现");
            discountDayTv.setText("可用余额¥0.00");
            discountTimeTv.setText("锁定0.00");
        }

    }


    public void checkZdjfu(boolean zjzNum){
        if(zjzNum){
            if(mCheckCoupon.ismRedPacket()){
                mCheckCoupon.setmRedPacket(false);
            }
        }
    }

    public void checkRedPacket(boolean mRedPacket){
        if(mRedPacket){
            if(mCheckCoupon.ismZjzNum()){
                mCheckCoupon.setmZjzNum(false);
            }
        }
    }


    public void checkCoupon(boolean zjzNum,DiscountZJZBean zjzNumBean,boolean redPacket,DiscountZJZBean redPacketBean,boolean addInterest,DiscountZJZBean addinterestBean) {

        if (mCheckCoupon != null) {
            mCheckCoupon.setmZjzNum(zjzNum);
            mCheckCoupon.setmCheckZJZBean(zjzNumBean);

            mCheckCoupon.setmRedPacket(redPacket);
            mCheckCoupon.setmCheckRedPacket(redPacketBean);

            mCheckCoupon.setmAddInterest(addInterest);
            mCheckCoupon.setmCheckAddInterest(addinterestBean);
        }

        if(mCheckCoupon!=null){


//            if(redPacket){
//                if(mCheckCoupon.ismZjzNum()){
//                    mCheckCoupon.setmZjzNum(false);
//                }
//            }
//            if(zjzNum){
//                if(mCheckCoupon.ismRedPacket()){
//                    mCheckCoupon.setmRedPacket(false);
//                }
//            }

            //红包
            if(mCheckCoupon.getmCheckRedPacket()!=null){
                for(int i=0;i<mAdapterList.size();i++){
                    if(!TextUtils.isEmpty(mAdapterList.get(i).getmDiscountZJZBean().getNode_id())&&!TextUtils.isEmpty(mCheckCoupon.getmCheckRedPacket().getNode_id())){
                        if(mAdapterList.get(i).getmDiscountZJZBean().getType().equals("1")){
                            DiscountCouponBean mBean =mAdapterList.get(i);
                            if(mAdapterList.get(i).getmDiscountZJZBean().getNode_id().equals(mCheckCoupon.getmCheckRedPacket().getNode_id())){
                            boolean stte= mCheckCoupon.ismRedPacket();
                                mBean.setCheck(mCheckCoupon.ismRedPacket());
                            }else{//不相等 的红包 都不选
                                if(mCheckCoupon.ismRedPacket()){
                                    mBean.setCheck(false);
                                }
                            }
                            mAdapterList.set(i,mBean);
                        }
                    }
                }

            }

            //加息券
            if(mCheckCoupon.getmCheckAddInterest()!=null){
                for(int i=0;i<mAdapterList.size();i++){
                    if(!TextUtils.isEmpty(mAdapterList.get(i).getmDiscountZJZBean().getNode_id())&&!TextUtils.isEmpty(mCheckCoupon.getmCheckAddInterest().getNode_id())){
                       if(mAdapterList.get(i).getmDiscountZJZBean().getType().equals("2")){
                           DiscountCouponBean mBean =mAdapterList.get(i);
                           if(mAdapterList.get(i).getmDiscountZJZBean().getNode_id().equals(mCheckCoupon.getmCheckAddInterest().getNode_id())){
                               mBean.setCheck(mCheckCoupon.ismAddInterest());
                           }else{
                               if(mCheckCoupon.ismAddInterest()){
                                   mBean.setCheck(false);
                               }
                           }
                           mAdapterList.set(i,mBean);
                       }
                    }
                }
            }

            if(mAdapterList!=null&&mAdapter!=null){
                mAdapter.setNewData(mAdapterList);
            }


            if(mCheckCoupon.ismZjzNum()){
                discountStateCb.setImageResource(R.mipmap.opened);
            }else{
                discountStateCb.setImageResource(R.mipmap.check_normal);
            }


            StringBuffer mBuffer = new StringBuffer();
            double discount = 0.00;

            if(mCheckCoupon.ismRedPacket()&&mCheckCoupon.ismAddInterest()){
                discount = mCheckCoupon.getmCheckRedPacket().getAmount();
                mBuffer.append("你已选中1张红包抵现,及加息券1张,共抵扣");
            }else if(mCheckCoupon.ismZjzNum()&&mCheckCoupon.ismAddInterest()){
                discount = mCheckCoupon.getmCheckZJZBean().getAmount();
                mBuffer.append("你已选中正经值抵现,及加息券1张,共抵扣");
            }else if(mCheckCoupon.ismRedPacket()){
                discount = mCheckCoupon.getmCheckRedPacket().getAmount();
                mBuffer.append("您已选中1张红包券,共抵现");
            }else if (mCheckCoupon.ismZjzNum()){
                if(mCheckCoupon.getmCheckZJZBean()!=null){
                    discount = mCheckCoupon.getmCheckZJZBean().getAmount();
                }
                mBuffer.append("您已选中正经值，共抵现");
            }else if(mCheckCoupon.ismAddInterest()){
                mBuffer.append("您已选中1张加息券");
            }else{
                mBuffer.append("请选择优惠券");
            }

            if(discount>0){
                discountCheckCouponTypeNumtv.setText("¥" + UiUtils.formatMoneyToBigDecimal(discount + "", getResources().getString(R.string.defalut_money_separator)));
            }else {
                discountCheckCouponTypeNumtv.setText("");
            }
            discountCheckCouponTv.setText(mBuffer.toString() + "");

        }



    }




    @OnClick(R.id.discount_zjz_rl)
    public void onClick() {

        if(mCheckCoupon!=null){
            if(mCheckCoupon.ismZjzNum()){
                checkCoupon(false,mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                discountStateCb.setImageResource(R.mipmap.check_normal);
            }else{
                checkZdjfu(true);
                checkCoupon(true,mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                discountStateCb.setImageResource(R.mipmap.opened);
            }
        }

    }


    public void showDiscountCoupon(DiscountBean bean) {
        if(bean!=null){
            if(bean.getRecommend()!=null&&bean.getRecommend().size()>0){
                if(mCheckCoupon==null){
                    mCheckCoupon =new CheckCouponType();
                }
                for(int i=0;i<bean.getRecommend().size();i++){
                    if(bean.getRecommend().get(i).getType().equals("1")){
                        mCheckCoupon.setmRedPacket(true);
                        mCheckCoupon.setmCheckRedPacket(bean.getRecommend().get(i));
                    }else if(bean.getRecommend().get(i).getType().equals("2")){
                        mCheckCoupon.setmAddInterest(true);
                        mCheckCoupon.setmCheckAddInterest(bean.getRecommend().get(i));
                    }else if(bean.getRecommend().get(i).getType().equals("3")){
                        mCheckCoupon.setmZjzNum(true);
                        mCheckCoupon.setmCheckZJZBean(bean.getRecommend().get(i));
                    }
                }

                //如果选择优惠券
                if(mCheckCoupon!=null){

                    if(mCheckCoupon.ismRedPacket()){
                        checkRedPacket(true);
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(), true,mCheckCoupon.getmCheckRedPacket(), mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                    }

                    if(mCheckCoupon.ismZjzNum()){
                        checkZdjfu(true);
                        checkCoupon(true,mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),mCheckCoupon.ismAddInterest(),mCheckCoupon.getmCheckAddInterest());
                        discountStateCb.setImageResource(R.mipmap.opened);
                    }

                    if(mCheckCoupon.ismAddInterest()){
                        checkCoupon(mCheckCoupon.ismZjzNum(),mCheckCoupon.getmCheckZJZBean(),mCheckCoupon.ismRedPacket(),mCheckCoupon.getmCheckRedPacket(),true,mCheckCoupon.getmCheckAddInterest());
                    }

                }

            }
        }
    }


}
