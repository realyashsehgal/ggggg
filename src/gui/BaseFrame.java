package src.gui;

import javax.swing.*;

import java.awt.BorderLayout;

public class BaseFrame extends JFrame{

    public BaseFrame(int width, int height, String title, ImageIcon logo)
    {   
        this.setTitle(title);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLayout(new BorderLayout()); 
        if(logo != null)
            this.setIconImage(logo.getImage());
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
