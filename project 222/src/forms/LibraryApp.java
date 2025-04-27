package src.forms;

import java.awt.*;
import javax.swing.*;
import src.gui.BaseFrame;
import src.gui.BaseHeadImagePanel;
import src.gui.BaseImagePanel;

public class LibraryApp {

    public static JPanel mainPanel;
    public static CardLayout cardLayout;

    private Font HEADING_FONT = new Font("Montserrat", Font.BOLD, 60);
    private Font HOME_FONT = new Font("Montserrat", Font.BOLD, 40);

    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color PURPLE = new Color(160, 10, 255);

    ImageIcon logo = new ImageIcon("src/images/LMS.jpg");

    public LibraryApp() {
        JFrame frame = new BaseFrame(1600, 900, "Library Management System", null);
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

        JPanel headPanel = new BaseHeadImagePanel("Library Management System", HEADING_FONT, 30, 50);

        JPanel homePanel = new BaseImagePanel("src/images/mainPanel.jpg");
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
            JLabel label = LibraryApp.createLabel(buttonNames[i], HOME_FONT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(PURPLE);
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
        JPanel mainRibbonPanel = new BaseImagePanel("src/images/ribbonPanel.png");
        mainRibbonPanel.setLayout(new BorderLayout());

        JPanel leftRibbonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 230, 30));
        JPanel rightRibbonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 230, 30));
        leftRibbonPanel.setOpaque(false);
        rightRibbonPanel.setOpaque(false);

        for (String buttonName : buttons) {

            JButton button = new JButton();
            JLabel buttonLabel = createLabel(buttonName, font);
            button.setBackground(WHITE);
            button.add(buttonLabel);
            button.addActionListener(e -> {
                cardLayout.show(mainPanel, buttonName);
            });
            if (buttonName.equals("Home")) {
                leftRibbonPanel.add(button);
            } else
                rightRibbonPanel.add(button);
        }
        mainRibbonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        mainRibbonPanel.add(leftRibbonPanel, BorderLayout.WEST);
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
