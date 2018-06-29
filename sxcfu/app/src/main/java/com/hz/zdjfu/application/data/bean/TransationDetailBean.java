package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/10 0010.
 */

public class TransationDetailBean implements Serializable{

    private int operateType;
    private int action;
    private int status;
    private String operateName;
    private String actionName;
    private String statusName;
    private String time;

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TransationDetailBean{" +
                "operateType=" + operateType +
                ", action=" + action +
                ", status=" + status +
                ", operateName='" + operateName + '\'' +
                ", actionName='" + actionName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
