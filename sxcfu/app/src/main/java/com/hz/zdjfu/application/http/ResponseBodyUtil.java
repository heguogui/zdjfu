package com.hz.zdjfu.application.http;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/4 0004.
 */

public class ResponseBodyUtil {


    public static RequestBody  requestBody(Map<String,String> map){

        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            for (Map.Entry<String,String> entry: map.entrySet() ){
                js_request.put(entry.getKey(),entry.getValue());//添加相应键值对
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),js_request.toString());
        return body;
    }

}





