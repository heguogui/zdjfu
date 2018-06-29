package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class BankTypeBean {


    /**
     * id : 1
     * create_time : 1507688141000
     * remark : 后台添加银行名称
     * num : ABC
     * name : 农业银行
     */

    private int id;
    private long create_time;
    private String remark;
    private String num;
    private String name;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BankTypeBean{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", remark='" + remark + '\'' +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
