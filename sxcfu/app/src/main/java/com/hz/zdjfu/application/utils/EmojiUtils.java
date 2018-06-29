/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [类功能说明]
 * 表情处理类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class EmojiUtils {

    public static String replaceStrToEmoji(String input) {
        String temp = input;
        try {

            Pattern pattern = Pattern.compile("\\[u(.*?)\\]");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String str = matcher.group().trim();

                String codeStr = str.replace("[u", "").replace("]", "");

                //
                int code = Integer.parseInt(codeStr, 16);
                ;
//                    //在表情范围内
//                    if (sEmojiMap.containsKey(Integer.valueOf(code))) {
//
//                        char[] chars = Character.toChars(code);
//
//                        String s = new String(chars);
//                        temp = temp.replace(str, s);
//
//                    }

                if (Character.isValidCodePoint(code)) {
                    char[] chars = Character.toChars(code);
                    String s = new String(chars);
                    temp = temp.replace(str, s);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.d("model content :" + temp);

        return temp;
    }


    public static String replaceEmojiToStr(String input) {

        if (input == null) {
            return "";
        } else {
            String temp = input;
            char[] chars = input.toCharArray();
            boolean codePoint = false;
            int length = chars.length;

            for (int i = 0; i < length; ++i) {
                if (!Character.isHighSurrogate(chars[i])) {
                    int var5;
                    if (Character.isLowSurrogate(chars[i])) {
                        if (i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                            continue;
                        }

                        var5 = Character.toCodePoint(chars[i - 1], chars[i]);

                        String s = new String(Character.toChars(Integer.valueOf(var5)));
                        temp.indexOf(s);
                        temp = temp.replace(s, "[u" + Integer.toHexString(var5) + "]");

                    } else {
                        var5 = chars[i];
                    }

                    //  if (sEmojiMap.containsKey(Integer.valueOf(var5))) {

//                        String s = new String(Character.toChars(Integer.valueOf(var5)));
//                        temp.indexOf(s);
//                        temp = temp.replace(s, "[" + Integer.valueOf(var5) + "]");

                    //  }
                }
            }
            LogUtils.d("model content :" + temp);

            return temp;
        }
    }


}
