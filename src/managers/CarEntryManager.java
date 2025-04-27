package src.managers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import src.models.CarEntry;

public class CarEntryManager {

    private static final String addQuery = "INSERT INTO carentry (CarNumber, DriverName, EntryTimestamp) VALUES (?, ?, ?)";
    private static final String showQuery = "SELECT * FROM Student ORDER BY ERP_ID ASC";
    private static final String getQuery = "SELECT * FROM Student WHERE ERP_ID = ?";
    private static final String removeQuery = "DELETE FROM STUDENT WHERE ERP_ID = ?";

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;

    public static String addStudent(CarEntry student) {
        // if (student.getErpId().isEmpty() || student.getName().isEmpty() ||
        // student.getCourse().isEmpty()) {
        // return "Fields can not be empty!";
        // }
        try {
            Timestamp entrytimestamp = Timestamp.valueOf(student.getEntrytime());
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(addQuery);
            st.setString(1, student.getCarnum());
            st.setString(2, student.getDrivername());
            st.setTimestamp(3, entrytimestamp);

            st.executeUpdate();

            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static String removeStudent(String erp) {
        if (erp.isEmpty()) {
            return "Fields can not be empty!";
        }
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(removeQuery);
            st.setString(1, erp);
            int result = st.executeUpdate();

            if (result == 0) {
                return "Student with this ERP_ID does not exist!";
            }
            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    // public static CarEntry getStudent(String erpId) {
    // try {
    // conn = DatabaseManager.GetConnection();
    // st = conn.prepareStatement(getQuery);
    // st.setString(1, erpId);
    // rs = st.executeQuery();

    // if (rs.next()) {
    // CarEntry student = new CarEntry(rs.getString("ERP_ID"),
    // rs.getString("Name"),
    // rs.getString("Course"));
    // return student;
    // } else {
    // System.out.println("NO STUDENT");
    // return null;
    // }

    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // return null;
    // } finally {
    // DatabaseManager.close(conn, st, rs);
    // }

    // }

    public static List<String[]> getAllStudents() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showQuery);
            rs = st.executeQuery();

            List<String[]> students = new ArrayList<>();

            while (rs.next()) {
                students.add(new String[] { rs.getString(1),
                        rs.getString(2),
                        rs.getString(3) });
            }

            return students;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }
}
