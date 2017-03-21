package com.comment.temp;

import java.security.MessageDigest;

/**
 * Created by 王宁 on 2017/1/18.
 */
public class Utils {
    //阿里md5 摘要算法
    public static String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
//            logger.warn("计算MD5摘要失败", e);
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hexStr = Integer.toHexString(0xFF & byteArray[i]);
            if (hexStr.length() == 1) {
                md5StrBuff.append("0");
            }
            md5StrBuff.append(hexStr);
        }

        return md5StrBuff.toString();
    }

}
