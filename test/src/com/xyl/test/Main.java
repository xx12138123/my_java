package com.xyl.test;

import com.xyl.test.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame=new MainFrame();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }
}
