package com.mxthd.util;

import java.util.regex.Pattern;

/**
 * 数据校验类
 * author : mxthd.com
 */
public class DataValidator {

    /**
     * 验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 用来验证的通用方法
     * @param expression    正则表达式
     * @param context       要校验的内容
     * @return
     */
    public static boolean check(String expression,String context){
        return Pattern.matches(expression, context);
    }
}
