package src.gui;

import java.awt.BorderLayout;
import javax.swing.*;

public class BaseFrame extends JFrame {

    public BaseFrame(int width, int height, String title, ImageIcon logo) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        if (logo != null)
            this.setIconImage(logo.getImage());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
