package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *用户信息类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class UserBean {


    private int userId;//用户Id
    private String userName;//用户真实姓名
    private String userHead;//用户头像
    private String userPhone;//用户手机号
    private String userPassord;//用户密码
    private String userNick;//用户昵称
    private String userIdentity;//用户身份证
    private boolean isPushMsgState;//用户是否这是推送消息
    private boolean isGestureState;//用户是否设置手势密码
    private boolean isNameCertification;//用户是否实名认证
    private boolean isOpenBankCard;//银行卡快捷开通状态
    private String bankCardAlias;//银行卡类型
    private String bankCardId;//银行卡Id
    private boolean isLogin;
    private String createTime;
    private String accessToken;
    private String jwtToken;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassord() {
        return userPassord;
    }

    public void setUserPassord(String userPassord) {
        this.userPassord = userPassord;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public boolean isPushMsgState() {
        return isPushMsgState;
    }

    public void setPushMsgState(boolean pushMsgState) {
        isPushMsgState = pushMsgState;
    }

    public boolean isGestureState() {
        return isGestureState;
    }

    public void setGestureState(boolean gestureState) {
        isGestureState = gestureState;
    }

    public boolean isNameCertification() {
        return isNameCertification;
    }

    public void setNameCertification(boolean nameCertification) {
        isNameCertification = nameCertification;
    }

    public boolean isOpenBankCard() {
        return isOpenBankCard;
    }

    public void setOpenBankCard(boolean openBankCard) {
        isOpenBankCard = openBankCard;
    }

    public String getBankCardAlias() {
        return bankCardAlias;
    }

    public void setBankCardAlias(String bankCardAlias) {
        this.bankCardAlias = bankCardAlias;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassord='" + userPassord + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", isPushMsgState=" + isPushMsgState +
                ", isGestureState=" + isGestureState +
                ", isNameCertification=" + isNameCertification +
                ", isOpenBankCard=" + isOpenBankCard +
                ", bankCardAlias='" + bankCardAlias + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                ", isLogin=" + isLogin +
                ", createTime='" + createTime + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
