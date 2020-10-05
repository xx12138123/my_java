package com.xyl.test.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        super();
        initWindow();
        initView();
    }

    private void initWindow(){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension screenSize=toolkit.getScreenSize();
        setBounds(screenSize.width/4,screenSize.height/4,screenSize.width/2,screenSize.height/2);
        setTitle("凯撒加密和MD5加密（网管队技术组二面第二轮考核）");
    }

    private void initView(){
        Container container=getContentPane();
        InputPanel inputPanel=new InputPanel(getWidth(),getHeight());
        container.add(inputPanel);
    }
}
