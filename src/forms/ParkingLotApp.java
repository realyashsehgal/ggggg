package src.forms;

import java.awt.*;
import javax.swing.*;
import src.gui.BaseFrame;
import src.gui.BaseHeadImagePanel;
import src.gui.BaseImagePanel;

public class ParkingLotApp {

    public static JPanel mainPanel;
    public static CardLayout cardLayout;

    private Font HEADING_FONT = new Font("Montserrat", Font.BOLD, 50);
    private Font HOME_FONT = new Font("Montserrat", Font.BOLD, 40);

    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color BLACK = new Color(0, 0, 0);

    ImageIcon logo = new ImageIcon("src/images/LMS.jpg");

    public ParkingLotApp() {
        JFrame frame = new BaseFrame(1000, 600, "Parking Lot Management System", null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel homePanel = createHomePanel();
        JPanel carentry = new CarEntryPanel();
        JPanel carexit = new CarExitPanel();
        JPanel transactionPanel = new TransactionPanel();

        mainPanel.add(homePanel, "Home");
        mainPanel.add(carentry, "Entry");
        mainPanel.add(carexit, "Exit");
        mainPanel.add(transactionPanel, "Transaction");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel mainHomePanel = new JPanel(new BorderLayout());

        JPanel headPanel = new BaseHeadImagePanel("Parking Lot Management System", HEADING_FONT, 10, 10);

        JPanel homePanel = new BaseImagePanel("src/images/0b921bd66c6ff7aa20f12b98b1e98be0.jpg");
        homePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton carentryButton = null;
        JButton carexitButton = null;
        JButton transactionButton = null;

        String[] buttonNames = { "Entry", "Exit", "Transaction" };
        JButton[] buttons = { carentryButton, carexitButton, transactionButton };

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i] = new JButton();
            buttons[i].setBackground(WHITE);
            JLabel label = ParkingLotApp.createLabel(buttonNames[i], HOME_FONT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(BLACK);
            buttons[i].add(label);
            buttons[i].addActionListener(e -> {
                cardLayout.show(mainPanel, buttonNames[index]);
            });
            addComponent(homePanel, buttons[i], gbc, 0, i);
        }

        homePanel.setBackground(new Color(123, 50, 250));

        homePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        mainHomePanel.add(headPanel, BorderLayout.NORTH);
        mainHomePanel.add(homePanel, BorderLayout.CENTER);

        return mainHomePanel;
    }

    public static JPanel createRibbonPanel(String[] buttons, Font font) {
        JPanel mainRibbonPanel = new BaseImagePanel("src/images/ribbonPanel.jpg");
        mainRibbonPanel.setLayout(new BorderLayout());

        JPanel rightRibbonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 30));
        rightRibbonPanel.setOpaque(false);

        for (String buttonName : buttons) {

            JButton button = new JButton();
            JLabel buttonLabel = createLabel(buttonName, font);
            button.setBackground(WHITE);
            button.add(buttonLabel);
            button.addActionListener(e -> {
                cardLayout.show(mainPanel, buttonName);
            });
            ;

            rightRibbonPanel.add(button);
        }
        mainRibbonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        mainRibbonPanel.add(rightRibbonPanel, BorderLayout.EAST);

        return mainRibbonPanel;
    }

    public static JLabel createLabel(String text, Font newFont) {
        JLabel label = new JLabel(text);
        label.setFont(newFont);
        return label;
    }

    public static JTextField createTextField(Font font) {
        JTextField tf = new JTextField(20);
        tf.setFont(font);
        return tf;
    }

    public static void addComponent(JPanel panel, Component comp, GridBagConstraints gbc, int x, int y) {
        if (gbc != null) {
            gbc.gridx = x;
            gbc.gridy = y;
            panel.add(comp, gbc);
        } else {
            panel.add(comp);
        }
    }

}
