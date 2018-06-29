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
 *正加减
 * @author hgg-pc
 * @version v2.0.0 ${DATA} 13:07 HAOZHUOKEJI
 * @email heguogui2013@163.com
 */

public class AddAndSubView extends LinearLayout {

    Context mContext;
    private ImageView mAddTv;
    private ImageView mSubTv;
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
    private Long mCanInvest =0L;
    private double changeNum;
    private double yearRate =0.00;//年化率
    private boolean isDelete =false;
    private int mday =0;
    private double temp=0.00;



    private RelativeLayout viewInputRl;
    public AddAndSubView(Context context) {
        super(context, null);
        mContext = context;
    }

    public AddAndSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_add_sub_layout, this, true);
        mAddTv =  view.findViewById(R.id.view_add_btn);
        mAddTv.setOnClickListener(addOnclickListener);

        mSubTv = view.findViewById(R.id.view_sub_btn);
        mSubTv.setOnClickListener(subOnclickListener);

        mNumEt = (EditText) view.findViewById(R.id.view_input_et);

        mEarnningTv =view.findViewById(R.id.view_earnning_tv);

        viewEarnningLl =view.findViewById(R.id.view_earnning_rl);

        viewCancleRl =view.findViewById(R.id.view_cancle_rl);

        viewInputRl =view.findViewById(R.id.view_input_rl);

        viewCancleRl.setVisibility(GONE);
        viewCancleRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText();
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
                }
            }
        });

    }


    private void clearEditText() {
        if(mNumEt!=null){
            mNumEt.setText("");
            SpannableString s = new SpannableString("请输入1的整数倍");
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

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
        this.temp=yearRate;
    }

    public void setAddInterest(double addInterest){
//            if(addInterest>0&&temp>0){
//                this.yearRate =temp;
//                yearRate=yearRate+addInterest;
//            }else{
//                if(mNumEt!=null){
//                    String last =mNumEt.getText().toString();
//                    if(yearRate>0&&!TextUtils.isEmpty(last)&&mday>0){
//                        //收益计算公式：本金X年化收益／360天*收益天数  (年化率/100)
//
//                        String temp =AmountUtils.mul(yearRate+"",last);
//                        String allEarning =AmountUtils.mul(AmountUtils.div(temp,"36000",4),mday+"");
//                        mEarnningTv.setText( UiUtils.formatMoneyToBigDecimal(allEarning,getResources().getString(R.string.defalut_money_separator)));
//                    }else {
//                        mEarnningTv.setText("0.00");
//                    }
//                }
//            }

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
//        if(coupon_price>0){
//            showAmount(mNumEt.getText().toString());
//        }
        showAmount(mNumEt.getText().toString());
    }

    public void setOnNumChangeListener(OnNumChangeListener listener) {
        mNumChangeListener = listener;
    }

    public void setCanInvestAmount(double canInvest){
        try {
            String mcanInvest =String.valueOf(canInvest);
            if(!TextUtils.isEmpty(mcanInvest)){
                if(mcanInvest.contains(".")) {
                    mcanInvest = mcanInvest.split("\\.")[0];
                }
                mCanInvest =Long.parseLong(mcanInvest);
            }else{
                mCanInvest =0L;
            }
            changeNum();
            changeEditTextState(false);
        }catch (Exception e){

        }
    }

    public void setAccountBalance(String balance){
        this.mAccountBalance =balance;
        changeNum();
        changeEditTextState(false);
    }

    private OnClickListener addOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String mNow =mNumEt.getText().toString();
            if(!TextUtils.isEmpty(mNow)){
                changeEditTextState(true);
                isDelete =true;
                if(Double.parseDouble(AmountUtils.sub(String.valueOf(mCanInvest),mNow))<0){//当输入金额等于可投金额时不可增加
                    ToastUtils.show(mContext,"已超出剩余可投金额");
                    mNumEt.setText(mCanInvest+"");
                }else{
                    mNumEt.setText(AmountUtils.addLong(mNow,changeNum+"")+"");
                }
            }else{
                mNumEt.setText(AmountUtils.addLong("0",changeNum+"")+"");
            }
        }
    };

    private OnClickListener subOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String mNow =mNumEt.getText().toString();
            if(!TextUtils.isEmpty(mNow)){
                changeEditTextState(true);
                if(viewEarnningLl!=null){
                    viewEarnningLl.setVisibility(View.VISIBLE);
                }
                isDelete =true;
                if(mCanInvest<100){
                    ToastUtils.show(mContext,"建议一次性投完");
                    mNumEt.setText(mCanInvest+"");
                }else{

//                    if(Double.parseDouble(mNow)<100){
//                        mNumEt.setText(100+"");
//                    }else{
//                        if(Double.parseDouble(mNow)>changeNum||Double.parseDouble(mNow)==changeNum){
//                            mNumEt.setText(AmountUtils.subLong(mNow,changeNum+"")+"");
//                        }
//                    }
                    if(Double.parseDouble(mNow)>changeNum||Double.parseDouble(mNow)==changeNum){
                        mNumEt.setText(AmountUtils.subLong(mNow,changeNum+"")+"");
                    }else{
                        clearEditText();
                    }

                }


            }
        }
    };



    public interface OnNumChangeListener {
        /**
         * 输入框中的数值改变事件
         * @param num  输入框的数值
         */
        public void onNumChange(Long num);


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
//                if(!TextUtils.isEmpty(mAccountBalance)){
//                    if(Double.parseDouble(editable.toString())>Double.parseDouble(mAccountBalance)){
//                        setBtnStatus(false);
//                    }
//                }
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


                if(mCanInvest<100&&Long.parseLong(mNow)!=mCanInvest){
                    mNumEt.setText(mCanInvest+"");
                }else{
                    if(Long.parseLong(mNow)>mCanInvest){
                        ToastUtils.show(mContext,"已超出剩余可投金额");
                        mNumEt.setText(mCanInvest+"");
                    }
//                    if(Double.parseDouble(mNow)<100){
//                        ToastUtils.show(mContext,"建议100起投");
//                        mNumEt.setText(100+"");
//                    }
                }

            }

            String last =mNumEt.getText().toString();
            if(!TextUtils.isEmpty(last)){
                mNumEt.setSelection(last.length());
                if(mNumChangeListener!=null){
                    mNumChangeListener.onNumChange(Long.parseLong(last));
                }
            }else{
                if(mNumChangeListener!=null){
                    mNumChangeListener.onNumChange(0L);
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
        if(mCanInvest>1000||mCanInvest==1000){
            changeNum =100;
            mNumEt.setText("1000");
        }else if((mCanInvest>100||mCanInvest==100)&&mCanInvest<1000){
            changeNum =100;
            mNumEt.setText("100");
        }else{
            mNumEt.setText(mCanInvest+"");
        }
       // mNumEt.setSelection(mNumEt.length());
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
