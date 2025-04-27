package src.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseHeadPanel extends JPanel{
    
    public BaseHeadPanel(String title, Color bgColor, Color fgColor, Font headingFont, int hgap, int vgap)
    {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, hgap, vgap));
        
        JLabel heading = new JLabel(title);
        heading.setFont(headingFont);
        heading.setForeground(fgColor);

        this.add(heading);

        this.setBackground(bgColor);
    }
}