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
import src.models.Transaction;

public class TransactionPanel extends JPanel {

    private Font font = new Font("Montserrat", Font.BOLD, 20);
    private Font headingFont = new Font("Montserrat", Font.BOLD, 80);
    private Font homeFont = new Font("Montserrat", Font.BOLD, 40);

    private static final Color BROWN = new Color(132, 72, 47);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color PURPLE = new Color(160, 10, 255);

    public TransactionPanel() {
        this.setLayout(new BorderLayout());

        String[] buttons = { "Home", "Entry", "Exit" };
        JPanel ribbonPanel = LibraryApp.createRibbonPanel(buttons, font);

        JPanel headPanel = new BaseHeadImagePanel("Transaction Data", headingFont, 20, 30);
        headPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel transactionPanel = new JPanel(new BorderLayout());

        BaseImagePanel buttonsPanel = new BaseImagePanel("src/images/transactionPanel.png");
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.setFont(homeFont);
        borrowButton.addActionListener(e -> {
            borrowFrame();
            System.out.println("Borrow");
        });
        LibraryApp.addComponent(buttonsPanel, borrowButton, gbc, 0, 0);

        JButton returnButton = new JButton("Return Book");
        returnButton.setFont(homeFont);
        returnButton.addActionListener(e -> {
            returnFrame();
            System.out.println("Return");
        });
        LibraryApp.addComponent(buttonsPanel, returnButton, gbc, 0, 1);

        JButton showTransactions = new JButton("Show Transactions");
        showTransactions.setFont(homeFont);
        showTransactions.addActionListener(e -> {
            showFrame();
            System.out.println("Show");
        });
        LibraryApp.addComponent(buttonsPanel, showTransactions, gbc, 0, 2);

        JButton[] mainButtons = { borrowButton, returnButton, showTransactions };
        for (JButton button : mainButtons) {
            button.setBackground(WHITE);
            button.setForeground(PURPLE);
        }

        transactionPanel.add(headPanel, BorderLayout.NORTH);
        transactionPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.add(ribbonPanel, BorderLayout.NORTH);
        this.add(transactionPanel, BorderLayout.CENTER);

    }

    private JFrame borrowFrame() {
        JFrame mainFrame = new BaseFrame(800, 600, "Borrow Book", null);

        JPanel headPanel = new BaseHeadImagePanel("Borrow Book", homeFont, 10, 20);

        JPanel addPanel = new BaseImagePanel("src/images/borrowPanel.jpg");
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentErp = LibraryApp.createLabel("Student ERP", font);
        JLabel bookID = LibraryApp.createLabel("Book ID", font);

        JLabel[] labels = { studentErp, bookID };

        for (int i = 0; i < labels.length; i++) {

            labels[i].setForeground(WHITE);
            LibraryApp.addComponent(addPanel, labels[i], gbc, 0, i);

        }

        JTextField erpField = LibraryApp.createTextField(font);
        JTextField idField = LibraryApp.createTextField(font);

        JTextField[] tfs = { erpField, idField };

        for (int i = 0; i < tfs.length; i++) {

            LibraryApp.addComponent(addPanel, tfs[i], gbc, 1, i);
        }

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(e -> {
            String erp = erpField.getText().toUpperCase();
            String id = idField.getText().toUpperCase();
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to Add Borrow Transaction with: \nStudent ERP: " + erp + "\nBook ID: " + id,
                    "Borrow Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                Transaction transaction = new Transaction(erp, id, "Borrow");
                String borrowTransaction = TransactionManager.addBorrow(transaction);
                if (borrowTransaction.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(null, "Borrow Transaction Successfully Added!", borrowTransaction,
                            JOptionPane.INFORMATION_MESSAGE);

                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, borrowTransaction, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        borrowButton.setBackground(WHITE);
        LibraryApp.addComponent(addPanel, borrowButton, gbc, 0, 3);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(addPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame returnFrame() {
        JFrame mainFrame = new BaseFrame(800, 600, "Return Book", null);

        JPanel headPanel = new BaseHeadImagePanel("Return Book", homeFont, 10, 20);

        JPanel addPanel = new BaseImagePanel("src/images/returnPanel.jpg");
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentErp = LibraryApp.createLabel("Student ERP", font);
        JLabel bookID = LibraryApp.createLabel("Book ID", font);
        JLabel[] labels = { studentErp, bookID };

        for (int i = 0; i < labels.length; i++) {

            labels[i].setForeground(WHITE);
            LibraryApp.addComponent(addPanel, labels[i], gbc, 0, i);

        }

        JTextField erpField = LibraryApp.createTextField(font);
        JTextField idField = LibraryApp.createTextField(font);

        JTextField[] tfs = { erpField, idField };

        for (int i = 0; i < tfs.length; i++) {

            LibraryApp.addComponent(addPanel, tfs[i], gbc, 1, i);
        }

        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(e -> {
            String erp = erpField.getText().toUpperCase();
            String id = idField.getText().toUpperCase();
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to Add Return Transaction with: \nStudent ERP: " + erp + "\nBook ID: " + id,
                    "Borrow Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                Transaction transaction = new Transaction(erp, id, "Borrow");
                String returnTransaction = TransactionManager.addReturn(transaction);
                if (returnTransaction.equals("SUCCESS")) {
                    JOptionPane.showMessageDialog(null, "Return Transaction Successfully Added!", returnTransaction,
                            JOptionPane.INFORMATION_MESSAGE);

                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, returnTransaction, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        returnButton.setBackground(WHITE);
        LibraryApp.addComponent(addPanel, returnButton, gbc, 0, 3);

        mainFrame.add(headPanel, BorderLayout.NORTH);
        mainFrame.add(addPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame showFrame() {

        JFrame mainFrame = new BaseFrame(800, 600, "Transactions", null);

        JPanel headPanel = new BaseHeadImagePanel("Transactions", homeFont, 20, 30);
        headPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel transactionPanel = new JPanel(new BorderLayout());

        BaseImagePanel buttonsPanel = new BaseImagePanel("src/images/showTransaction.jpg");
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton showBorrows = new JButton("Borrow Transactions");
        showBorrows.setFont(font);
        showBorrows.addActionListener(e -> {
            borrowTransactions();
            System.out.println("Borrow");
        });

        JButton showReturns = new JButton("Return Transactions");
        showReturns.setFont(font);
        showReturns.addActionListener(e -> {
            returnTransactions();
            System.out.println("Return");
        });

        JButton showTransactions = new JButton("All Transactions");
        showTransactions.setFont(font);
        showTransactions.addActionListener(e -> {
            allTransactions();
            System.out.println("Show");
        });
        JButton[] mainButtons = { showBorrows, showReturns, showTransactions };

        for (int i = 0; i < mainButtons.length; i++) {
            final int index = i;
            mainButtons[index].setBackground(WHITE);
            mainButtons[index].setForeground(PURPLE);
            LibraryApp.addComponent(buttonsPanel, mainButtons[index], gbc, 0, index);
        }

        transactionPanel.add(headPanel, BorderLayout.NORTH);
        transactionPanel.add(buttonsPanel, BorderLayout.CENTER);

        mainFrame.add(transactionPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
        return mainFrame;
    }

    private JFrame allTransactions() {
        JFrame mainFrame = new BaseFrame(800, 600, "Transaction Data", null);

        JPanel headPanel = new BaseHeadPanel("Transaction Details", BROWN, WHITE, homeFont, 20, 30);

        String[] columnNames = { "Transaction ID", "Student ERP", "Book ID", "Transaction Type", "Transaction Date",
                "Due Date" };

        List<String[]> transactions = TransactionManager.getAllTransactions();
        String[][] data = transactions.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame borrowTransactions() {
        JFrame mainFrame = new BaseFrame(800, 600, "Borrow Data", null);
        JPanel headPanel = new BaseHeadPanel("Borrow Details", BROWN, WHITE, homeFont, 20, 30);

        String[] columnNames = { "Transaction ID", "Student ERP", "Book ID", "Transaction Type", "Transaction Date",
                "Due Date" };

        List<String[]> transactions = TransactionManager.getAllBorrows();
        String[][] data = transactions.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }

    private JFrame returnTransactions() {
        JFrame mainFrame = new BaseFrame(800, 600, "Return Data", null);
        JPanel headPanel = new BaseHeadPanel("Return Details", BROWN, WHITE, homeFont, 20, 30);

        String[] columnNames = { "Transaction ID", "Student ERP", "Book ID", "Transaction Type", "Transaction Date" };

        List<String[]> transactions = TransactionManager.getAllReturns();
        String[][] data = transactions.toArray(new String[0][]);

        JScrollPane dataTable = new BaseTable(data, columnNames);

        mainFrame.add(dataTable, BorderLayout.CENTER);
        mainFrame.add(headPanel, BorderLayout.NORTH);

        mainFrame.setVisible(true);

        return mainFrame;
    }

}
