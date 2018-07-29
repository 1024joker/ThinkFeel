package com.mxthd.util;

import java.util.Random;
/**
 * 这是生成验证码的。可指定位数不能超过26位。
 */
public class CodeUtils {

    public  static String  str = "QWERRTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
    public  static StringBuffer sBuffer = new StringBuffer();

    public static String getCode(int length){
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int nextInt = random.nextInt(str.length());
            char c = str.charAt(nextInt);
            sBuffer.append(c);
        }
        return sBuffer.toString();
    }

}
