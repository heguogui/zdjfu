package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.KeyboardUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

/**
 * [类功能说明]
 * 投资资金输入框
 * @author hgg-pc
 * @version v2.0.0 ${DATA} 13:07 HAOZHUOKEJI
 * @email heguogui2013@163.com
 */

public class InvestAmountView extends LinearLayout {

    Context mContext;
    private TextView mALLBanlanceTv;
    private EditText mNumEt;
    private LinearLayout viewEarnningLl;
    private TextView mEarnningTv;
    private RelativeLayout viewCancleRl;
    private int mMaxNum = 2147483647;
    private int mMinNum = 0;
    private int mDefaultNum = 0;
    private Button mSumbitBtn;
    private OnNumChangeListener mNumChangeListener;   //数量改变监听
    private String mAccountBalance;
    private double mCouponPrice =0.00;
    private double mCanInvest =0.00;
    private double changeNum;
    private double yearRate =0.00;//年化率
    private boolean isDelete =false;
    private int mday =0;
    private double temp=0.00;
    private TextView view_add_tv;
    private boolean isCheack =false;
    private double mStartAmuont=0;
    private RelativeLayout viewInputRl;
    public InvestAmountView(Context context) {
        super(context, null);
        mContext = context;
    }

    public InvestAmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_invest_number_layout, this, true);


        mNumEt = (EditText) view.findViewById(R.id.view_number_input_tv);

        mEarnningTv =view.findViewById(R.id.view_earnning_tv);

        viewEarnningLl =view.findViewById(R.id.view_earnning_rl);

        viewCancleRl =view.findViewById(R.id.view_cancle_rl);

        viewInputRl =view.findViewById(R.id.view_input_rl);

        view_add_tv=view.findViewById(R.id.view_add_tv);

        view_add_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheack){
                    isCheack =false;
                    view_add_tv.setTextColor(mContext.getResources().getColor(R.color.blue5));
                    view_add_tv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
                    changeEditTextState(true);
                    isDelete =true;
                    clearEditText();
                    if(mNumEt!=null){
                        KeyboardUtils.showSoftInput(mContext,mNumEt);
                    }

                }else {
                    if(!TextUtils.isEmpty(mAccountBalance)){
                        double balance =Double.parseDouble(mAccountBalance);
                        if(balance>mCanInvest){
                            mNumEt.setText(mCanInvest+"");
                        }else{
                            mNumEt.setText(AmountUtils.round(balance+"",2)+"");
                        }
                        isCheack =true;//选中 则为灰
                        view_add_tv.setTextColor(mContext.getResources().getColor(R.color.gray0));
                        view_add_tv.setBackgroundResource(R.drawable.stroke_gray_solid_white_radius5);
                        view_add_tv.setEnabled(false);
                    }

                }
            }
        });


        viewCancleRl.setVisibility(GONE);
        viewCancleRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText();
                if(view_add_tv!=null){
                    isCheack =false;
                    view_add_tv.setEnabled(true);
                    view_add_tv.setTextColor(mContext.getResources().getColor(R.color.blue5));
                    view_add_tv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
                }
            }
        });

        changeNum();
        mNumEt.addTextChangedListener(new MyEditTextListener());
        mContext =context;

        viewInputRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeEditTextState(true);
                isDelete =true;
                clearEditText();
                if(mNumEt!=null){
                    KeyboardUtils.showSoftInput(mContext,mNumEt);
                }
                if(view_add_tv!=null){
                    isCheack =false;
                    view_add_tv.setEnabled(true);
                    view_add_tv.setTextColor(mContext.getResources().getColor(R.color.blue5));
                    view_add_tv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
                }

            }
        });


        mNumEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){//获取了焦点
                    changeEditTextState(true);
                    isDelete =true;
                    clearEditText();
                    if(mNumEt!=null){
                        KeyboardUtils.showSoftInput(mContext,mNumEt);
                    }
                    if(view_add_tv!=null){
                        isCheack =false;
                        view_add_tv.setEnabled(true);
                        view_add_tv.setTextColor(mContext.getResources().getColor(R.color.blue5));
                        view_add_tv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
                    }
                }
            }
        });

    }


    private void clearEditText() {
        if(mNumEt!=null){
            mNumEt.setText("");
            SpannableString s = new SpannableString("请输入投资金额");
            AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(15,true);
            s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mNumEt.setHint(s);
        }
    }

    public void setListener(OnClickListener addListener, OnClickListener subListener, TextWatcher subWatcher) {

    }

    public void setMaxNum(int max) {
        mMaxNum = max;
    }

    public void setMinNum(int min) {
        mMinNum = min;
    }

    public String getText() {
        return mNumEt.getText().toString();
    }

    public void setButton(Button mbtn){
        this.mSumbitBtn =mbtn;
    }


    public void setEarnDay(int day){
        this.mday =day;
    }


    public void startAmount(int startamount){
        try {
            mStartAmuont =startamount;
            changeNum();
            changeEditTextState(false);
        }catch (Exception e){

        }
    }

    public void startInvestAmount(double startamount){

        mStartAmuont =startamount;
        changeNum();
        changeEditTextState(false);
    }

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
        this.temp=yearRate;
    }

    public void setAddInterest(double addInterest){

        this.yearRate =temp;
        yearRate=yearRate+addInterest;
        if(mNumEt!=null){
            String last =mNumEt.getText().toString();
            if(yearRate>0&&!TextUtils.isEmpty(last)&&mday>0){
                //收益计算公式：本金X年化收益／360天*收益天数  (年化率/100)
                String temp =AmountUtils.mul(yearRate+"",last);
                String allEarning =AmountUtils.mul(AmountUtils.div(temp,"36000",6),mday+"");
                mEarnningTv.setText( UiUtils.formatMoneyToBigDecimal(allEarning,getResources().getString(R.string.defalut_money_separator)));
            }else {
                mEarnningTv.setText("0.00");
            }
        }


    }

    public void setCoupon(double coupon_price){
        this.mCouponPrice =coupon_price;
        showAmount(mNumEt.getText().toString());
    }

    public void setOnNumChangeListener(OnNumChangeListener listener) {
        mNumChangeListener = listener;
    }

    public void setCanInvestAmount(double canInvest){

        mCanInvest =canInvest;
        changeNum();
        changeEditTextState(false);

        if(mCanInvest==0){
            view_add_tv.setTextColor(mContext.getResources().getColor(R.color.gray0));
            view_add_tv.setBackgroundResource(R.drawable.stroke_gray_solid_white_radius5);
            view_add_tv.setEnabled(false);
        }else{
            view_add_tv.setTextColor(mContext.getResources().getColor(R.color.blue5));
            view_add_tv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
            view_add_tv.setEnabled(true);
        }

    }

    public void setAccountBalance(String balance){
        this.mAccountBalance =balance;
        changeNum();
        changeEditTextState(false);
    }



    public interface OnNumChangeListener {
        /**
         * 输入框中的数值改变事件
         * @param num  输入框的数值
         */
        public void onNumChange(double num);


    }


    private class MyEditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            handleEtFormat(charSequence,mNumEt);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().startsWith(".")) {
                mNumEt.setText("0.");
                mNumEt.setSelection(mNumEt.getText().toString().length());
            }
            try {
                if (Double.parseDouble(editable.toString()) == 0||Double.parseDouble(mNumEt.getText().toString())==0.00) {
                    showAmount("0");
                    setBtnStatus(false);
                } else {
                    showAmount(mNumEt.getText().toString());
                    setBtnStatus(true);
                }

            } catch (Exception e) {
            }


            //当可投金额小于100 输入的金额小于 可投金额 默认可投金额 当大于100 时 输入的金额大于可投金额默认可投金额 当输入金额低于100 时默认100
            String mNow =mNumEt.getText().toString();

            if(!TextUtils.isEmpty(mNow)){

                double mNowAmount =Double.parseDouble(mNow);
                if(mCanInvest<100&&mNowAmount!=mCanInvest){
                    mNumEt.setText(mCanInvest+"");
                }else{
                    if(mNowAmount>mCanInvest){
                        ToastUtils.show(mContext,"已超出剩余可投金额");
                        mNumEt.setText(mCanInvest+"");
                    }else{
                        double endAmount =mCanInvest-mNowAmount;//当前项目剩余可投金额-用户的投资金额，剩下的余额 不足起投金额，提示：建议全部投完或留下xx元（根据起投金额显示）起投
                        if(endAmount>0&&endAmount<mStartAmuont){
                            ToastUtils.show(mContext,"建议全部投完或者留下"+mStartAmuont+"元起投");
                            mNumEt.setText(mCanInvest+"");
                        }
                    }
                }

            }

            String last =mNumEt.getText().toString();
            if(!TextUtils.isEmpty(last)){
                mNumEt.setSelection(last.length());
                if(mNumChangeListener!=null){
                    mNumChangeListener.onNumChange(Double.parseDouble(last));
                }
            }else{
                if(mNumChangeListener!=null){
                    mNumChangeListener.onNumChange(0.00);
                }
            }
            try{
                //预计收益
                if(yearRate>0&&!TextUtils.isEmpty(last)&&mday>0){
                    //收益计算公式：本金X年化收益／360天*收益天数  (年化率/100)
                    String temp =AmountUtils.mul(yearRate+"",last);
                    String allEarning =AmountUtils.mul(AmountUtils.div(temp,"36000",4),mday+"");
                    mEarnningTv.setText( UiUtils.formatMoneyToBigDecimal(allEarning,getResources().getString(R.string.defalut_money_separator)));
                }else {
                    mEarnningTv.setText("0.00");
                }

            }catch (Exception e){

            }

            //删除按钮
            if(!TextUtils.isEmpty(last)&&isDelete){
                mNumEt.setHint("");
                viewCancleRl.setVisibility(View.VISIBLE);
            }else {
                viewCancleRl.setVisibility(View.GONE);
            }

            if(TextUtils.isEmpty(last)){
                showEarningTextView(false);
            }else{
                showEarningTextView(true);
            }


        }


    }


    /**
     * 输入框最多只能输入两位小数
     */
    private void handleEtFormat(CharSequence s, EditText editText) {

        try {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
                //屏蔽多个小数点
                if(s.toString().endsWith(".")){
                    int sum =0;
                    for(int i=0;i<s.toString().length();i++){
                        char c = s.toString().charAt(i);
                        if(s.toString().charAt(i)== '.'){
                            sum++;
                        }
                    }
                    if(sum>1){
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                editText.setText(s);
                editText.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    editText.setText(s.subSequence(0, 1));
                    editText.setSelection(1);
                    return;
                }
            }
        }catch (Exception e){

        }

    }


    public void setBtnStatus(boolean active) {
        if (mSumbitBtn != null) {
            mSumbitBtn.setEnabled(active);
        }
    }


    public void showAmount(String lastAmount){

        if(mSumbitBtn!=null){
            if(TextUtils.isEmpty(lastAmount)){
                setBtnStatus(false);
                mSumbitBtn.setText("实际支出0.00元");
            }else {
                double mlastAmont =Double.parseDouble(lastAmount);
                if(mlastAmont==0){
                    setBtnStatus(false);
                }else{
                    setBtnStatus(true);
                }
                if(mCouponPrice==0.00){
                    if(TextUtils.isEmpty(mAccountBalance)){
                        mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(lastAmount,getResources().getString(R.string.defalut_money_separator))+"元");
                    }else{
                        if(mlastAmont>Double.parseDouble(mAccountBalance)){
                            mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(AmountUtils.sub(lastAmount,mAccountBalance),getResources().getString(R.string.defalut_money_separator))+"元");
                        }else {
                            mSumbitBtn.setText("实际支出"+UiUtils.formatMoneyToBigDecimal(lastAmount,getResources().getString(R.string.defalut_money_separator))+"元");
                        }
                    }

                }else{
                    if(mlastAmont>mCouponPrice){
                        double amount =mlastAmont-mCouponPrice;
                        if(TextUtils.isEmpty(mAccountBalance)){
                            mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(amount+"",getResources().getString(R.string.defalut_money_separator))+"元");
                        }else{
                            if(amount>Double.parseDouble(mAccountBalance)){
                                mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(AmountUtils.sub(amount+"",mAccountBalance),getResources().getString(R.string.defalut_money_separator))+"元");
                            }else {
                                mSumbitBtn.setText("实际支出"+UiUtils.formatMoneyToBigDecimal(amount+"",getResources().getString(R.string.defalut_money_separator))+"元");
                            }
                        }

                    }else{

                        if(TextUtils.isEmpty(mAccountBalance)){
                            mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(lastAmount+"",getResources().getString(R.string.defalut_money_separator))+"元");
                        }else{
                            if(Double.parseDouble(lastAmount)>Double.parseDouble(mAccountBalance)){
                                mSumbitBtn.setText("余额不足，还需充值"+UiUtils.formatMoneyToBigDecimal(AmountUtils.sub(lastAmount+"",mAccountBalance),getResources().getString(R.string.defalut_money_separator))+"元");
                            }else {
                                mSumbitBtn.setText("实际支出"+UiUtils.formatMoneyToBigDecimal(lastAmount+"",getResources().getString(R.string.defalut_money_separator))+"元");
                            }
                        }
                    }
                }
            }
        }

    }



    private void  changeNum(){

        if(mCanInvest>=mStartAmuont){
           mNumEt.setText(AmountUtils.round(mStartAmuont+"",2)+"");
//            mNumEt.setText(mStartAmuont+"");
        }

//
//        if(mCanInvest>1000||mCanInvest==1000){
//            changeNum =100;
//            mNumEt.setText("1000");
//        }else if((mCanInvest>100||mCanInvest==100)&&mCanInvest<1000){
//            changeNum =100;
//            mNumEt.setText("100");
//        }else{
//            mNumEt.setText(mCanInvest+"");
//        }
    }

    /**
     * 光标显示
     * @param state
     */
    private void changeEditTextState(boolean state){
        if(mNumEt!=null){
            mNumEt.setCursorVisible(state);
        }
    }

    private void showEarningTextView(boolean state){
//        if(viewEarnningLl!=null){
//            if(state){
//                viewEarnningLl.setVisibility(View.VISIBLE);
//            }else{
//                viewEarnningLl.setVisibility(View.GONE);
//            }
//        }
    }


}
