package com.xyl.test.util;

public interface Encryption {
    String decrypt(String text) throws Exception;
    String encrypt(String text);
}
