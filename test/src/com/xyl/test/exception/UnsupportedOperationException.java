package com.xyl.test.exception;

public class UnsupportedOperationException extends Exception{
    private static final String message="MD5 算法无法解密";
    public UnsupportedOperationException(){
        super(message);
    }
}
