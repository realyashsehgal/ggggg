package src.ui;

import java.awt.*;
import javax.swing.*;
import src.forms.ParkingLotApp;
import src.gui.BaseFrame;
import src.gui.BaseHeadImagePanel;
import src.gui.BaseImagePanel;
import src.managers.LoginManager;
import src.models.User;

public class LoginFrame {

    Font font = new Font("Montserrat", Font.BOLD, 20);
    Font headFont = new Font("Montserrat", Font.BOLD, 40);
    JTextField usernameField;
    JPasswordField passField;
    JPasswordField confirmPassField;
    JCheckBox showPass;
    JFrame frame;

    private static final Color WHITE = new Color(255, 255, 255);

    User tempUser = new User(null, null);
    ImageIcon logo = new ImageIcon("src/images/LMS.jpg");

    public LoginFrame() {

        frame = new BaseFrame(800, 600, "Login Window", logo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel headPanel = new BaseHeadImagePanel("Login/Sign Up", headFont, 20, 30);
        JPanel loginPanel = new BaseImagePanel("src/images/loginFrame.jpg");
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton loginButton = new JButton("Log In");
        loginButton.setFont(font);
        loginButton.setBackground(WHITE);
        loginButton.addActionListener(e -> loginFrame(frame));

        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(font);
        signupButton.setBackground(WHITE);
        signupButton.addActionListener(e -> signupFrame());

        ParkingLotApp.addComponent(loginPanel, loginButton, gbc, 0, 0);
        ParkingLotApp.addComponent(loginPanel, signupButton, gbc, 0, 1);

        frame.add(headPanel, BorderLayout.NORTH);
        frame.add(loginPanel, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    private JFrame loginFrame(JFrame frame) {
        JFrame mainFrame = new BaseFrame(800, 600, "Log in", null);
        JPanel headPanel = new BaseHeadImagePanel("Log In", headFont, 20, 30);

        JPanel mainPanel = new BaseImagePanel("src/images/loginPanel.jpg");
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = ParkingLotApp.createLabel("Username: ", font);
        userLabel.setForeground(WHITE);
        ParkingLotApp.addComponent(mainPanel, userLabel, gbc, 0, 0);

        usernameField = ParkingLotApp.createTextField(font);
        ParkingLotApp.addComponent(mainPanel, usernameField, gbc, 1, 0);

        JLabel passLabel = ParkingLotApp.createLabel("Password: ", font);
        passLabel.setForeground(WHITE);
        ParkingLotApp.addComponent(mainPanel, passLabel, gbc, 0, 1);

        passField = new JPasswordField();
        passField.setFont(font);

        ParkingLotApp.addComponent(mainPanel, passField, gbc, 1, 1);

        showPass = new JCheckBox("Show Password");
        showPass.setFont(font);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('*');
            }
        });
        ParkingLotApp.addComponent(mainPanel, showPass, gbc, 2, 1);

        JButton submitButton = new JButton("Login");
        submitButton.setFont(font);
        submitButton.setBackground(WHITE);
        submitButton.addActionListener(e -> {
            tempUser.setUsername(usernameField.getText());
            tempUser.setPassword(new String(passField.getPassword()));

            String login = LoginManager.CheckDetails(tempUser);
            if (login.equals("SUCCESS")) {
                new ParkingLotApp();
                mainFrame.dispose();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, login, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        ParkingLotApp.addComponent(mainPanel, submitButton, gbc, 0, 2);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame signupFrame() {
        JFrame mainFrame = new BaseFrame(800, 600, "Sign Up", null);
        JPanel headPanel = new BaseHeadImagePanel("Sign Up", headFont, 20, 30);

        JPanel mainPanel = new BaseImagePanel("src/images/loginPanel.jpg");
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = ParkingLotApp.createLabel("Create Username: ", font);
        userLabel.setForeground(WHITE);
        ParkingLotApp.addComponent(mainPanel, userLabel, gbc, 0, 0);

        usernameField = ParkingLotApp.createTextField(font);
        ParkingLotApp.addComponent(mainPanel, usernameField, gbc, 1, 0);

        JLabel passLabel = ParkingLotApp.createLabel("Create Password: ", font);
        passLabel.setForeground(WHITE);
        ParkingLotApp.addComponent(mainPanel, passLabel, gbc, 0, 1);

        passField = new JPasswordField();
        passField.setFont(font);

        ParkingLotApp.addComponent(mainPanel, passField, gbc, 1, 1);

        showPass = new JCheckBox("Show Password");
        showPass.setFont(font);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('*');
            }
        });
        ParkingLotApp.addComponent(mainPanel, showPass, gbc, 2, 1);

        JLabel confirmPassLabel = ParkingLotApp.createLabel("Confirm Password: ", font);
        confirmPassLabel.setForeground(WHITE);
        ParkingLotApp.addComponent(mainPanel, confirmPassLabel, gbc, 0, 2);

        confirmPassField = new JPasswordField();
        confirmPassField.setFont(font);

        ParkingLotApp.addComponent(mainPanel, confirmPassField, gbc, 1, 2);

        JButton submitButton = new JButton("Create Account");
        submitButton.setFont(font);
        submitButton.setBackground(WHITE);
        submitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this User?",
                    "Confirm Dialog", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String password = new String(passField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password should Match", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    tempUser.setUsername(usernameField.getText());
                    tempUser.setPassword(password);

                    String signup = LoginManager.CreateAccount(tempUser);
                    if (signup.equals("SUCCESS")) {
                        JOptionPane.showMessageDialog(null, "User Creation Successful!", "Creation Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                        mainFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, signup, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        ParkingLotApp.addComponent(mainPanel, submitButton, gbc, 0, 3);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    public static void exitFrame() {

    }
}
