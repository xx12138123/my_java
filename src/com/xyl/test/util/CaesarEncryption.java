package com.xyl.test.util;

import com.xyl.test.model.Value;

public class CaesarEncryption  implements Encryption{
    private int offset= Value.DEFAULT_OFFSET;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String decrypt(String text) {
        return offsetString(text,-1*offset);
    }
    @Override
    public String encrypt(String text) {
        return offsetString(text,offset);
    }

    private String offsetString(String text,int offset){
        char[] chars=text.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]>='0'&&chars[i]<='9'){
                //对于数字部分的处理
                chars[i]=offsetChar(chars[i],'0','9',offset);
            }else if(chars[i]>='a'&&chars[i]<='z'){
                //对小写字母部分的处理
                chars[i]=offsetChar(chars[i],'a','z',offset);
            }else if(chars[i]>='A'&&chars[i]<='Z'){
                //对大写字母部分的处理
                chars[i]=offsetChar(chars[i],'A','Z',offset);
            }
        }
        return new String(chars);
    }

    private char offsetChar(char input,char charStart,char charEnd,int offset){
        char i=(char)(input+offset);
        while(true){
            if(input<charStart){
                i=(char) (charEnd+1-(charStart-i));
            }else if(i>charEnd){
                i=(char) (charStart-1+(i-charEnd));
            }else {
                break;
            }
        }
        return i;
    }

}
