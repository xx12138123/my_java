package com.xyl.test.view;

import com.xyl.test.model.Group;
import com.xyl.test.model.Value;
import com.xyl.test.util.EncryptUtil;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputPanel extends JPanel {
    private JTextField inputTextField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JRadioButton chooseCaesarButton;
    private JRadioButton chooseMD5Button;
    private JTextField showTextField;
    private JTextField offsetTextField;
    private JLabel showOffsetText;
    private ShowHistoryTable showHistoryTable;
    private int mainFrameWidth;
    private int mainFrameHeight;
    private Group group;
    private EncryptUtil encryptUtil;


    public InputPanel(int width,int height){
        super();
        mainFrameHeight=height;
        mainFrameWidth=width;
        group=new Group(Value.TYPE_CAESAR,"","",true);
        encryptUtil=EncryptUtil.getInstance();
        initView();
        encryptUtil.setAddGroupListener(new EncryptUtil.AddGroupListener() {
            @Override
            public void onAddGroup(Group group) {
                showHistoryTable.addGroup(group);
            }
        });
    }


    private void initView(){
        inputTextField=new JTextField("Hello World!");
        encryptButton=new JButton("加密");
        decryptButton=new JButton("解密");
        chooseCaesarButton=new JRadioButton("凯撒加密",true);
        chooseMD5Button=new JRadioButton("MD5加密");
        showTextField=new JTextField("在这里显示结果");
        offsetTextField=new JTextField(String.valueOf(Value.DEFAULT_OFFSET));
        showHistoryTable=new ShowHistoryTable();
        offsetTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar=e.getKeyChar();
                if(keyChar<'0'||keyChar>'9'){
                    e.consume();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        showOffsetText=new JLabel("偏移量");

        inputTextField.setBounds(10,10,mainFrameWidth-40,30);

        encryptButton.setBounds(10,50,100,30);

        decryptButton.setBounds(120,50,100,30);

        chooseCaesarButton.setBounds(230,50,90,30);

        chooseMD5Button.setBounds(330,50,90,30);

        showOffsetText.setBounds(420,50,50,30);

        offsetTextField.setBounds(470,50,50,25);

        showTextField.setBounds(10,100,mainFrameWidth-40,30);

        showHistoryTable.setBounds(10,170,mainFrameWidth-40,150);

        showHistoryTable.getTableHeader().setBounds(10,140,mainFrameWidth-40,30);

        ButtonGroup chooseButtonGroup=new ButtonGroup();
        chooseButtonGroup.add(chooseCaesarButton);
        chooseButtonGroup.add(chooseMD5Button);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEncrypt(true);
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEncrypt(false);
            }
        });

        chooseCaesarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group.setType(Value.TYPE_CAESAR);
                showOffsetText.setVisible(true);
                offsetTextField.setVisible(true);
            }
        });

        chooseMD5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                group.setType(Value.TYPE_MD5);
                showOffsetText.setVisible(false);
                offsetTextField.setVisible(false);
            }
        });

        setLayout(null);
        add(inputTextField);
        add(encryptButton);
        add(decryptButton);
        add(chooseCaesarButton);
        add(chooseMD5Button);
        add(showTextField);
        add(offsetTextField);
        add(showOffsetText);
        JScrollPane jScrollPane=new JScrollPane(showHistoryTable);
        jScrollPane.setBounds(10,170,mainFrameWidth-40,150);
        add(jScrollPane);
        add(showHistoryTable.getTableHeader());
    }

    private void setEncrypt(boolean isEncrypt){
        group.setEncrypt(isEncrypt);
        group.setInputText(inputTextField.getText());
        if(offsetTextField.getText().equals("")){
            offsetTextField.setText(String.valueOf(Value.DEFAULT_OFFSET));
        }
        try{
            group.setOffset(Integer.parseInt(offsetTextField.getText()));
        }catch (Exception e){
            //输入过大的数的处理
            group.setOffset(Value.DEFAULT_OFFSET);
            offsetTextField.setText(String.valueOf(Value.DEFAULT_OFFSET));
        }
        String show=encryptUtil.getAndAddEncryption(group);
        showTextField.setText(show);
    }

}
