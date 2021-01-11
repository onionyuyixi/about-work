package com.example.aboutwork;

/**
 * Copyright (c) 2014 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */


import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Date;
import java.util.TreeMap;

/**
 * HmacSHA1Util
 *
 * @author long.xia
 * @date: 2016年05月30日
 */
public class HmacSHA1Util {

    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static String encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        SecretKey secretKey = new SecretKeySpec(data, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(secretKey);

        byte[] text = encryptText.getBytes(ENCODING);
        return toString(mac.doFinal(text));
    }




    private static String toString(byte[] cipherValue) {
        BigInteger bigInteger = new BigInteger(1, cipherValue);

        String cipher = bigInteger.toString(16);

        int paddingLength = (cipherValue.length * 2) - cipher.length();

        if (paddingLength > 0) {

            return String.format("%0" + paddingLength + "d", 0) + cipher;
        } else {

            return cipher;
        }
    }

    /**
     * 用于生成时间戳及签名
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        String appkey="ua20210108";
        //url 中必须包含 appid,access_token参数
        String url="appId=1569290850175";


        //添加时间戳
        long time = (new Date()).getTime();
        url += "&timestamp=" + time;
        System.err.println(url);

        String[] arr = url.split("&");
        TreeMap<String, String> tree = new TreeMap<String, String>();
        StringBuilder sb = new StringBuilder(512);
        for (String kv : arr) {
            String[] kvs = kv.split("=");
            if ("sign".equalsIgnoreCase(kvs[0])) {
                continue;
            }

            tree.put(kvs[0], kvs[1]);
        }

        for (String key : tree.keySet()) {
            sb.append(key);
            sb.append(tree.get(key));
        }

        String sign = encrypt(sb.toString(), appkey+"&");
        // sign进行base64加密
        byte[] b = null;
        String returnStr = null;
        b = sign.getBytes("utf-8");
        returnStr = new BASE64Encoder().encode(b);

        StringBuilder finalUrl = new StringBuilder(512);
        finalUrl.append("sign=");
        finalUrl.append(returnStr);
        finalUrl.append("&");
        finalUrl.append(url);
        System.out.println("加密前明文 :"+sb.toString());
        System.out.println("加密使用KEY :"+appkey+"&");
        System.out.println("加密后密文："+sign);
        System.out.println("需要传递的参数示例:"+finalUrl.toString());

    }

}

