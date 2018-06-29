package com.hz.zdjfu.application.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * [类功能说明]
 *基本解析类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/18 0018
 */
public class ZTBaseResponse<T> implements Serializable {

     @SerializedName("code")
     public String code;
     @SerializedName("data")
     public T data;
     @SerializedName("msg")
     public String msg;
     @SerializedName("success")
     public boolean success;

}
