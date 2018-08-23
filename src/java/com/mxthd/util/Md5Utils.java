package com.mxthd.util;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    public  static String md5(String input){
        if (null == input) {
            input = "";
        }
        String result = "";
        try {
            // MessageDigest类用于为应用程序提供信息摘要算法的功能，如MD5或SHA算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 获取输入
            md.update(input.getBytes());
            // 获得产出（有符号的哈希值字节数组，包含16个元素）
            byte output[] = md.digest();

            // 32位的加密字符串
            StringBuilder builder = new StringBuilder(32);
            // 下面进行十六进制的转换
            for (int offset = 0; offset < output.length; offset++) {
                // 转变成对应的ASSIC值
                int value = output[offset];
                // 将负数转为正数（最终返回结果是无符号的）
                if (value < 0) {
                    value += 256;
                }
                // 小于16，转为十六进制后只有一个字节，左边追加0来补足2个字节
                if (value < 16) {
                    builder.append("0");
                }
                // 将16位byte[]转换为32位无符号String
                builder.append(Integer.toHexString(value));
            }
            result = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        String str="appid=1435484712&body=10会员发起一笔100.0积分充值，积分充值不支持退款!&out_trade_no=ba3430a9a78e4e89be5d9bb45fda3530&show_url=http://localhost:8080&subject=10会员发起一笔100.0积分充值&total_fee=100.0&key=coHOvHSCMYSilQPv82cHxL7cLUknLjF5";
//        System.out.println(md5(str));
        String str1="appid=1256898546&body=test&out_trade_no=100000000000&show_url=http%3a%2f%2feapay.cc%2f&subject=test&total_fee=10.00&key=192006250b4c09247ec02edce69f6a2d";
        System.out.println(md5(str1).toUpperCase());
    }
}
