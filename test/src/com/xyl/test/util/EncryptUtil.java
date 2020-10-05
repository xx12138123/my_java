package com.xyl.test.util;

import com.xyl.test.model.Group;
import com.xyl.test.model.Value;


public class EncryptUtil {
    private CaesarEncryption caesarEncryption;
    private Md5Encryption md5Encryption;
    private AddGroupListener groupListener;

    private static EncryptUtil encryptUtil;
    public static EncryptUtil getInstance(){
        if(encryptUtil==null){
            encryptUtil=new EncryptUtil();
        }
        return encryptUtil;
    }
    private EncryptUtil(){
        caesarEncryption=new CaesarEncryption();
        md5Encryption=new Md5Encryption();
    }
    public String getAndAddEncryption(Group group){
        int type=group.getType();
        boolean isEncrypt=group.isEncrypt();
        String show="";
        String input=group.getInputText();
        if(type == Value.TYPE_MD5){
            try {
                show=isEncrypt? md5Encryption.encrypt(input): md5Encryption.decrypt(input);
            }catch (Exception e){
                return e.getMessage();
            }

        }else if(type==Value.TYPE_CAESAR){
            caesarEncryption.setOffset(group.getOffset());
            show=isEncrypt? caesarEncryption.encrypt(input): caesarEncryption.decrypt(input);
        }
        group.setOutputText(show);

        if(groupListener!=null){
            groupListener.onAddGroup(group);
        }

        return show;
    }
    public void setAddGroupListener(AddGroupListener groupListener){
        this.groupListener=groupListener;
    }
    public interface AddGroupListener{
        void onAddGroup(Group group);
    }
}
