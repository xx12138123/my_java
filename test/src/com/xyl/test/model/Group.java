package com.xyl.test.model;

public class Group {
    private int type;
    private String inputText;
    private String outputText;
    private boolean isEncrypt;
    private int offset=Value.DEFAULT_OFFSET;

    public Group(){

    }

    public Group(int type,String inputText,String outputText,boolean isEncrypt){
        this.type=type;
        this.inputText=inputText;
        this.outputText=outputText;
        this.isEncrypt=isEncrypt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public boolean isEncrypt() {
        return isEncrypt;
    }

    public void setEncrypt(boolean encrypt) {
        isEncrypt = encrypt;
    }

}
