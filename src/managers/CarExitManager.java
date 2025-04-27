package src.managers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import src.models.CarExit;

public class CarExitManager {

    private static final String addQuery = "INSERT INTO carexit ( CarNumber, DriverName, ExitTimestamp, AmountPaid) VALUES (?, ?, ?, ?)";
    private static final String showQuery = "SELECT * FROM carexit ORDER BY ExitID ASC";
    private static final String removeQuery = "DELETE FROM Book WHERE Book_ID = ?";
    private static final String getQuery = "SELECT * FROM Book WHERE Book_ID = ?";
    private static final String setAvailability = "UPDATE Book SET Availability = ? WHERE Book_Id = ?";

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;

    public static String addBook(CarExit book) {
        // if (book.getId().isEmpty() || book.getTitle().isEmpty() ||
        // book.getAuthor().isEmpty()) {
        // return "Fields can not be empty!";
        // }
        try {
            Timestamp exittimestamp = Timestamp.valueOf(book.getExittime());
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(addQuery);
            st.setString(1, book.getCarnum());
            st.setString(2, book.getDrivername());
            st.setTimestamp(3, exittimestamp);
            st.setFloat(4, book.getAmount());
            st.executeUpdate();

            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static String removeBook(String id) {
        if (id.isEmpty()) {
            return "Fields can not be empty!";
        }
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(removeQuery);
            st.setString(1, id);
            int result = st.executeUpdate();

            if (result == 0) {
                return "Book with this ID does not exist!";
            }
            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static String setAvailability(String bookID, String availability) {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(setAvailability);
            st.setString(1, availability);
            st.setString(2, bookID);

            st.executeUpdate();

            return "SUCCESS";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

    // public static CarExit getBook(String bookId) {
    // try {
    // conn = DatabaseManager.GetConnection();
    // st = conn.prepareStatement(getQuery);
    // st.setString(1, bookId);
    // rs = st.executeQuery();

    // if (rs.next()) {
    // CarExit book = new CarExit(rs.getString("Book_ID"),
    // rs.getString("Title"),
    // rs.getString("Author"));
    // book.setAvailability(rs.getString("Availability"));
    // System.out.println(book.toString());
    // return book;
    // } else {
    // System.out.println("NO BOOK");
    // return null;
    // }

    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // return null;
    // } finally {
    // DatabaseManager.close(conn, st, rs);
    // }
    // }

    public static List<String[]> getAllBooks() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showQuery);
            rs = st.executeQuery();

            List<String[]> books = new ArrayList<>();

            while (rs.next()) {
                books.add(new String[] { rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                });
            }

            return books;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

}
