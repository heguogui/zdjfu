package com.hz.zdjfu.application.utils.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * [类功能说明]
 *Date的Json反序列化
 * @author Administrator
 * @version v 1.0.0 2017/5/16 11:41 HaoZhuoKeJi
 * @email heguogui2013@163.com
 */

public class MyDateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
    }
}
