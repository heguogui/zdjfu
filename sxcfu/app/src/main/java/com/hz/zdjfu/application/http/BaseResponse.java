package com.hz.zdjfu.application.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * [类功能说明]
 * 基本解析类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/18 0018
 */
public class BaseResponse<T> implements Serializable {

//     @SerializedName("data")
//     public T data;
//     @SerializedName("respCode")
//     public String code;
//     @SerializedName("respDesc")
//     public String msg;

     @SerializedName("returnCase")
     public String returnCase;
     @SerializedName("status")
     public String code;
     @SerializedName("content")
     public T data;


}
