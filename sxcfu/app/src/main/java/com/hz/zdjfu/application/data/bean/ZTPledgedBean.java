package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *抵押基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/7 0007.
 */

public class ZTPledgedBean implements Serializable{

    private String id;
    private String fileName;
    private String fileUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "ZTLoanerFiles{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
