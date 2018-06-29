package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/29 0029.
 */

public class LenderImageBean implements Serializable{


    /**
     * id : 269
     * from_id : 3
     * from_table : zd_lender
     * file_type : 0
     * file_name : 身份证正面
     * file_desc : null
     * file_url : https://www.zdjfu.com/upload/lender/201704100670491762/身份证正面.jpg
     * create_time : 1507622960000
     * file_num : 1
     * file_width : 0
     * file_height : 0
     */

    private int id;
    private int from_id;
    private String from_table;
    private int file_type;
    private String file_name;
    private String file_desc;
    private String file_url;
    private long create_time;
    private int file_num;
    private int file_width;
    private int file_height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public String getFrom_table() {
        return from_table;
    }

    public void setFrom_table(String from_table) {
        this.from_table = from_table;
    }

    public int getFile_type() {
        return file_type;
    }

    public void setFile_type(int file_type) {
        this.file_type = file_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_desc() {
        return file_desc;
    }

    public void setFile_desc(String file_desc) {
        this.file_desc = file_desc;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getFile_num() {
        return file_num;
    }

    public void setFile_num(int file_num) {
        this.file_num = file_num;
    }

    public int getFile_width() {
        return file_width;
    }

    public void setFile_width(int file_width) {
        this.file_width = file_width;
    }

    public int getFile_height() {
        return file_height;
    }

    public void setFile_height(int file_height) {
        this.file_height = file_height;
    }

    @Override
    public String toString() {
        return "LenderImageBean{" +
                "id=" + id +
                ", from_id=" + from_id +
                ", from_table='" + from_table + '\'' +
                ", file_type=" + file_type +
                ", file_name='" + file_name + '\'' +
                ", file_desc='" + file_desc + '\'' +
                ", file_url='" + file_url + '\'' +
                ", create_time=" + create_time +
                ", file_num=" + file_num +
                ", file_width=" + file_width +
                ", file_height=" + file_height +
                '}';
    }
}
