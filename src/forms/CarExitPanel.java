package src.forms;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import src.gui.BaseFrame;
import src.gui.BaseHeadImagePanel;
import src.gui.BaseHeadPanel;
import src.gui.BaseImagePanel;
import src.gui.BaseTable;
import src.managers.CarExitManager;
import src.models.CarExit;

public class CarExitPanel extends JPanel {

    private Font font = new Font("Montserrat", Font.BOLD, 20);
    private Font headingFont = new Font("Montserrat", Font.BOLD, 50);
    private Font homeFont = new Font("Montserrat", Font.BOLD, 40);

    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color BROWN = new Color(132, 72, 47);
    private static final Color BLACK = new Color(0, 0, 0);

    public CarExitPanel() {
        this.setLayout(new BorderLayout());

        String[] buttons = { "Home", "Entry", "Transaction" };
        JPanel ribbonPanel = ParkingLotApp.createRibbonPanel(buttons, font);

        JPanel headPanel = new BaseHeadImagePanel("car exit", headingFont, 20, 10);
        headPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel carPanel = new JPanel(new BorderLayout());

        BaseImagePanel buttonsPanel = new BaseImagePanel("src/images/carExitPanel.png");
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addButton = new JButton("Add Car's exit entry");
        addButton.setFont(homeFont);
        addButton.addActionListener(e -> {
            addFrame();
            System.out.println("ADD");
        });
        ParkingLotApp.addComponent(buttonsPanel, addButton, gbc, 0, 0);

        JButton showButton = new JButton("Show Car's exit ");
        showButton.setFont(homeFont);
        showButton.addActionListener(e -> {
            showFrame();
            System.out.println("SHOW");
        });
        ParkingLotApp.addComponent(buttonsPanel, showButton, gbc, 0, 2);

        JButton[] mainButtons = { addButton, showButton };
        for (JButton button : mainButtons) {
            button.setBackground(WHITE);
            button.setForeground(BLACK);
        }

        carPanel.add(headPanel, BorderLayout.NORTH);
        carPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(ribbonPanel, BorderLayout.NORTH);
        this.add(carPanel, BorderLayout.CENTER);

    }

    private JFrame addFrame() {
        JFrame mainFrame = new BaseFrame(800, 600, "Add exit entry", null);
        JPanel headPanel = new BaseHeadImagePanel("car exit", homeFont, 10, 10);

        JPanel addPanel = new BaseImagePanel("src/images/addCarExit.png");
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel carnumber = ParkingLotApp.createLabel("car Number: ", font);
        JLabel drivername = ParkingLotApp.createLabel("driver name: ", font);
        JLabel amountpaid = ParkingLotApp.createLabel("amount: ", font);
        JLabel exittime = ParkingLotApp.createLabel("exit time: ", font);

        JLabel[] labels = { carnumber, drivername, amountpaid, exittime };

        for (int i = 0; i < labels.length; i++) {

            labels[i].setForeground(WHITE);
            ParkingLotApp.addComponent(addPanel, labels[i], gbc, 0, i);

        }

        JTextField carnumbertf = ParkingLotApp.createTextField(font);
        JTextField drivernametf = ParkingLotApp.createTextField(font);
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor exittimetf = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
        spinner.setEditor(exittimetf);
        JTextField amountpaidtf = ParkingLotApp.createTextField(font);

        JTextField[] tfs = { carnumbertf, drivernametf, amountpaidtf };

        for (int i = 0; i < tfs.length; i++) {

            ParkingLotApp.addComponent(addPanel, tfs[i], gbc, 1, i);
        }
        ParkingLotApp.addComponent(addPanel, spinner, gbc, 1, 3);

        JButton addButton = new JButton("Add car exits");
        addButton.addActionListener(e -> {
            String carkenumber = carnumbertf.getText().toUpperCase();
            String driverkaname = drivernametf.getText();
            Date date = (Date) spinner.getValue();
            Instant instant = date.toInstant();
            LocalDateTime exittimeout = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            float amountkipaid = Float.parseFloat(amountpaidtf.getText());
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure the car is leaving with carnumber : " + carnumber + "?", "Add Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                CarExit car = new CarExit(carkenumber, driverkaname, exittimeout, amountkipaid);
                String addcar = CarExitManager.addcar(car);
                if (addcar.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(null, "car exit entry succesfully made", addcar,
                            JOptionPane.INFORMATION_MESSAGE);

                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, addcar, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addButton.setBackground(WHITE);
        ParkingLotApp.addComponent(addPanel, addButton, gbc, 0, 4);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(addPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame showFrame() {

        JFrame mainFrame = new BaseFrame(800, 600, "Car Data", null);

        JPanel headPanel = new BaseHeadPanel("Car Details", BROWN, WHITE, homeFont, 20, 10);

        String[] columnNames = { "Car number", "Driver's name", "Exit time ", "amount paid" };

        List<String[]> cars = CarExitManager.getAllcars();
        String[][] data = cars.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }

}