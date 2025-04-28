package src.forms;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import src.gui.BaseFrame;
import src.gui.BaseHeadImagePanel;
import src.gui.BaseHeadPanel;
import src.gui.BaseImagePanel;
import src.gui.BaseTable;
import src.managers.TransactionManager;

public class TransactionPanel extends JPanel {

    private Font font = new Font("Montserrat", Font.BOLD, 20);
    private Font headingFont = new Font("Montserrat", Font.BOLD, 50);
    private Font homeFont = new Font("Montserrat", Font.BOLD, 40);

    private static final Color BROWN = new Color(132, 72, 47);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color BLACK = new Color(0, 0, 0);

    public TransactionPanel() {
        this.setLayout(new BorderLayout());

        String[] buttons = { "Home", "Entry", "Exit" };
        JPanel ribbonPanel = ParkingLotApp.createRibbonPanel(buttons, font);

        JPanel headPanel = new BaseHeadImagePanel("Transaction Data", headingFont, 10, 10);
        headPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel transactionPanel = new JPanel(new BorderLayout());

        BaseImagePanel buttonsPanel = new BaseImagePanel("src/images/transactionPanel.png");
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton showTransactions = new JButton("Show car history");
        showTransactions.setFont(homeFont);
        showTransactions.addActionListener(e -> {
            allTransactions();
            System.out.println("Show");
        });
        ParkingLotApp.addComponent(buttonsPanel, showTransactions, gbc, 0, 2);

        JButton[] mainButtons = { showTransactions };
        for (JButton button : mainButtons) {
            button.setBackground(WHITE);
            button.setForeground(BLACK);
        }

        transactionPanel.add(headPanel, BorderLayout.NORTH);
        transactionPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(ribbonPanel, BorderLayout.NORTH);
        this.add(transactionPanel, BorderLayout.CENTER);

    }

    private JFrame allTransactions() {
        JFrame mainFrame = new BaseFrame(800, 600, "Transaction Data", null);

        JPanel headPanel = new BaseHeadPanel("Transaction Details", BROWN, WHITE, homeFont, 20, 10);

        String[] columnNames = { "Car number", "Driver name", "Entry time", "Exit time",
                "Amount paid" };

        List<String[]> transactions = TransactionManager.getAllTransactions();
        String[][] data = transactions.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }
}