package com.mxthd.util;

import java.util.Map;
import java.util.UUID;

/**
 * 简付工具包
 */
public class eapayUtils {
    public static String signMd5(Map<String,String> map){
        //生成字符串A
        StringBuffer stringA = new StringBuffer();
        stringA.append("appid="+map.get("appid"));
        stringA.append("&body="+map.get("body"));
        stringA.append("&out_trade_no="+map.get("out_trade_no"));
        stringA.append("&show_url="+map.get("show_url"));
        stringA.append("&subject="+map.get("subject"));
        stringA.append("&total_fee="+map.get("total_fee"));
        stringA.append("&key=coHOvHSCMYSilQPv82cHxL7cLUknLjF5");
        //生成sign
        return Md5Utils.md5(stringA.toString()).toUpperCase();
    }
    public static String uuid(){
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }
}
