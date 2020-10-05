package com.xyl.test.util;

import com.xyl.test.exception.UnsupportedOperationException;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Encryption implements Encryption{

    @Override
    public String decrypt(String text) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    @Override
    public String encrypt(String text) {
        try{
            //MD5加密算法取自网上
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            byte[] md5Bytes=messageDigest.digest(text.getBytes());
            String md5code = new BigInteger(1, md5Bytes).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            return md5code;
        }catch (Exception e){
            return "你的电脑不支持MD5（然而基本不怎么可能）";
        }

    }

}
