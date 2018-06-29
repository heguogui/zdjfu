package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class WithDrawFreeBean implements Serializable{


    /**
     * user_fee : 2.4
     * withdraw_id :
     * withdraw_prompt : 充值未投资的资金提现将收取其提现金额2‰的手续费+1元/笔
     */

    private String user_fee;
    private String withdraw_id;
    private String withdraw_prompt;

    public String getUser_fee() {
        return user_fee;
    }

    public void setUser_fee(String user_fee) {
        this.user_fee = user_fee;
    }

    public String getWithdraw_id() {
        return withdraw_id;
    }

    public void setWithdraw_id(String withdraw_id) {
        this.withdraw_id = withdraw_id;
    }

    public String getWithdraw_prompt() {
        return withdraw_prompt;
    }

    public void setWithdraw_prompt(String withdraw_prompt) {
        this.withdraw_prompt = withdraw_prompt;
    }

    @Override
    public String toString() {
        return "WithDrawFreeBean{" +
                "user_fee='" + user_fee + '\'' +
                ", withdraw_id='" + withdraw_id + '\'' +
                ", withdraw_prompt='" + withdraw_prompt + '\'' +
                '}';
    }
}
