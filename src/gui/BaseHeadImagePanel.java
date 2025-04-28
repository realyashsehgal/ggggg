package src.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;

public class BaseHeadImagePanel extends BaseImagePanel {

    public BaseHeadImagePanel(String title, Font headingFont, int hgap, int vgap) {
        super("src/images/headPanel.png");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, hgap, vgap));

        JLabel heading = new JLabel(title);
        heading.setFont(headingFont);
        heading.setForeground(Color.white);

        this.add(heading);
    }
}
