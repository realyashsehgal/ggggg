package src.managers;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import src.models.*;

public class TransactionManager {

    private static final String addBorrow = "INSERT INTO Transactions (Student_ERP, Book_ID, Type, Transaction_Date, Due_Date) VALUES (?, ?, 'Borrow', CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY))";

    private static final String addReturn = "INSERT INTO Transactions (Student_ERP, Book_ID, Type, Transaction_Date, Due_Date) VALUES (?, ?, 'Return', CURRENT_DATE, NULL)";

    private static final String getQuery = "SELECT * FROM Transactions WHERE Student_ERP = ? AND Book_ID = ? ORDER BY Transaction_ID DESC LIMIT 1";

    private static final String showQuery = "SELECT * FROM Transactions";

    private static final String showBorrows = "SELECT * FROM Transactions WHERE Type = 'Borrow'";
    private static final String showReturns = "SELECT * FROM Transactions WHERE Type = 'Return'";

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;

    private static CarExit book;
    private static CarEntry student;

    private static final String AVAIALABLE = "Yes";
    private static final String UNAVAIALABLE = "No";

    private static long pastDue = 0;

    public static String addBorrow(Transaction transaction) {
        if (transaction.getStudentErp().isEmpty() || transaction.getBookId().isEmpty()
                || transaction.getType().isEmpty()) {
            return "Fields can not be empty!";
        }
        // if ((CarEntryManager.getStudent(transaction.getStudentErp())) == null) {
        // return "The student with this Student_ID does not exist!";
        // }
        // if ((book = CarExitManager.getBook(transaction.getBookId())) == null) {
        // return "The book with this Book_ID does not exist!";
        // }
        // if (book.getAvailability().equals("No")) {
        // return "The book is not currently available!";
        // }
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(addBorrow);
            st.setString(1, transaction.getStudentErp());
            st.setString(2, transaction.getBookId());

            st.executeUpdate();

            // CarExitManager.setAvailability(book.getId(), UNAVAIALABLE);

            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static String addReturn(Transaction transaction) {
        if (transaction.getStudentErp().isEmpty() || transaction.getBookId().isEmpty()
                || transaction.getType().isEmpty()) {
            return "Fields can not be empty!";
        }
        // if ((student = CarEntryManager.getStudent(transaction.getStudentErp())) ==
        // null) {
        // return "The student with this Student_ID does not exist!";
        // }
        // if ((book = CarExitManager.getBook(transaction.getBookId())) == null) {
        // return "The book with this Book_ID does not exist!";
        // }
        // if (book.getAvailability().equals("Yes")) {
        // return "The book was Already Returned or was not Borrowed!";
        // }
        // if (!isBorrowedByStudent(student.getErpId(), book.getId())) {
        // return "The book was not borrowed by this student";
        // }
        if (pastDue > 0) {
            JOptionPane.showMessageDialog(null, "Book is Past due for " + pastDue + " Days", "Past Due",
                    JOptionPane.WARNING_MESSAGE);
            pastDue = 0;
        }
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(addReturn);
            st.setString(1, transaction.getStudentErp());
            st.setString(2, transaction.getBookId());

            st.executeUpdate();

            // CarExitManager.setAvailability(book.getId(), AVAIALABLE);
            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static Boolean isBorrowedByStudent(String studentErp, String bookId) {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(getQuery);
            st.setString(1, studentErp);
            st.setString(2, bookId);
            rs = st.executeQuery();

            if (rs.next()) {

                String type = rs.getString("Type");

                if (type.equals("Borrow")) {
                    System.out.println(rs.getDate("Due_Date"));
                    System.out.println(rs.getDate("Transaction_Date"));
                    pastDue = checkPastDue(rs.getDate("Due_Date").toLocalDate());
                    System.out.println("Past Due: " + pastDue);
                    return true;

                } else
                    return false;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }

    }

    public static List<String[]> getAllTransactions() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showQuery);
            rs = st.executeQuery();

            List<String[]> transactions = new ArrayList<>();

            while (rs.next()) {

                Date transactionDate = rs.getDate("Transaction_Date");
                String formattedTransactionDate = transactionDate.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                Date dueDate = rs.getDate("Due_Date");
                String formattedDueDate = dueDate == null ? "N/A"
                        : dueDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                transactions.add(new String[] { rs.getString("Transaction_ID"),
                        rs.getString("Student_ERP"),
                        rs.getString("Book_ID"),
                        rs.getString("Type"),
                        formattedTransactionDate,
                        formattedDueDate });
            }

            return transactions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

    public static List<String[]> getAllBorrows() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showBorrows);
            rs = st.executeQuery();

            List<String[]> transactions = new ArrayList<>();

            while (rs.next()) {

                Date transactionDate = rs.getDate("Transaction_Date");
                String formattedTransactionDate = transactionDate.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                Date dueDate = rs.getDate("Due_Date");
                String formattedDueDate = dueDate == null ? "N/A"
                        : dueDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                transactions.add(new String[] { rs.getString("Transaction_ID"),
                        rs.getString("Student_ERP"),
                        rs.getString("Book_ID"),
                        rs.getString("Type"),
                        formattedTransactionDate,
                        formattedDueDate });
            }

            return transactions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

    public static List<String[]> getAllReturns() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showReturns);
            rs = st.executeQuery();

            List<String[]> transactions = new ArrayList<>();

            while (rs.next()) {

                Date transactionDate = rs.getDate("Transaction_Date");
                String formattedTransactionDate = transactionDate.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                Date dueDate = rs.getDate("Due_Date");
                String formattedDueDate = dueDate == null ? "N/A"
                        : dueDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"));

                transactions.add(new String[] { rs.getString("Transaction_ID"),
                        rs.getString("Student_ERP"),
                        rs.getString("Book_ID"),
                        rs.getString("Type"),
                        formattedTransactionDate,
                        formattedDueDate });
            }

            return transactions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

    public static long checkPastDue(LocalDate dueDate) {
        if (LocalDate.now().isAfter(dueDate)) {
            return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        } else {
            return 0;
        }
    }
}
