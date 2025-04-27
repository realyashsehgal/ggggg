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
import src.managers.CarEntryManager;
import src.models.CarEntry;

public class CarEntryPanel extends JPanel {

    private Font font = new Font("Montserrat", Font.BOLD, 20);
    private Font headingFont = new Font("Montserrat", Font.BOLD, 80);
    private Font homeFont = new Font("Montserrat", Font.BOLD, 40);

    private static final Color BROWN = new Color(132, 72, 47);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color PURPLE = new Color(160, 10, 255);

    public CarEntryPanel() {
        this.setLayout(new BorderLayout());

        String[] buttons = { "Home", "Exit", "Transaction" };
        JPanel ribbonPanel = LibraryApp.createRibbonPanel(buttons, font);

        JPanel headPanel = new BaseHeadImagePanel("CarEntry Data", headingFont, 20, 30);
        headPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel studentPanel = new JPanel(new BorderLayout());

        BaseImagePanel buttonsPanel = new BaseImagePanel("src/images/studentPanel.jpg");
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addButton = new JButton("Add Car entry");
        addButton.setFont(homeFont);
        addButton.addActionListener(e -> {
            addFrame();
            System.out.println("ADD");
        });
        LibraryApp.addComponent(buttonsPanel, addButton, gbc, 0, 0);

        JButton showButton = new JButton("Show Car entry data");
        showButton.setFont(homeFont);
        showButton.addActionListener(e -> {
            showFrame();
            System.out.println("SHOW");
        });
        LibraryApp.addComponent(buttonsPanel, showButton, gbc, 0, 2);

        JButton[] mainButtons = { addButton, showButton };
        for (JButton button : mainButtons) {
            button.setBackground(WHITE);
            button.setForeground(PURPLE);
        }

        studentPanel.add(headPanel, BorderLayout.NORTH);
        studentPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(ribbonPanel, BorderLayout.NORTH);
        this.add(studentPanel, BorderLayout.CENTER);

    }

    private JFrame addFrame() {
        JFrame mainFrame = new BaseFrame(800, 600, "Add Student", null);
        JPanel headPanel = new BaseHeadImagePanel("Add Student", homeFont, 10, 20);

        JPanel addPanel = new BaseImagePanel("src/images/addStudent.jpg");
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel carnumber = LibraryApp.createLabel("Car number", font);
        JLabel drivername = LibraryApp.createLabel("Driver's namme", font);
        JLabel carentrytimestamp = LibraryApp.createLabel("Student Course", font);

        JLabel[] labels = { carnumber, drivername, carentrytimestamp };

        for (int i = 0; i < labels.length; i++) {

            labels[i].setForeground(WHITE);
            LibraryApp.addComponent(addPanel, labels[i], gbc, 0, i);

        }

        JTextField carnumberfield = LibraryApp.createTextField(font);
        JTextField drivernamefield = LibraryApp.createTextField(font);
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor exittimetf = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
        spinner.setEditor(exittimetf);

        JTextField[] tfs = { carnumberfield, drivernamefield };

        for (int i = 0; i < tfs.length; i++) {

            LibraryApp.addComponent(addPanel, tfs[i], gbc, 1, i);
        }

        LibraryApp.addComponent(addPanel, spinner, gbc, 1, 2);
        JButton addButton = new JButton("Add Car entry");
        addButton.addActionListener(e -> {
            String erp = carnumberfield.getText().toUpperCase();
            String name = drivernamefield.getText();
            Date date = (Date) spinner.getValue();
            Instant instant = date.toInstant();
            LocalDateTime entrytimee = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to add student with ERP: " + erp + "?", "Add Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.out.println("I treiedddddddd");
                CarEntry student = new CarEntry(erp, name, entrytimee);
                String addStudent = CarEntryManager.addStudent(student);
                if (addStudent.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(null, "Student Successfully Added!", addStudent,
                            JOptionPane.INFORMATION_MESSAGE);

                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, addStudent, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addButton.setBackground(WHITE);
        LibraryApp.addComponent(addPanel, addButton, gbc, 0, 3);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(addPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame showFrame() {

        JFrame mainFrame = new BaseFrame(800, 600, "Car's entry Data", null);
        JPanel headPanel = new BaseHeadPanel("Cars Details", BROWN, WHITE, homeFont, 20, 30);
        String[] columnNames = { "Car number", "Driver's name", "Entry time" };

        List<String[]> students = CarEntryManager.getAllStudents();
        String[][] data = students.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }

}
