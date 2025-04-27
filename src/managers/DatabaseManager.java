package src.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/plm";
    private static String user = "";
    private static String password = "";

    public static Connection GetConnection() {
        try {

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            return null;
        }
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
        }
    }

    public static void getCreds() throws IOException {
        String creds = Files.readString(Path.of("lib/SQLConfig.txt"));
        String[] arr = creds.split("\n");
        user = arr[0].trim();
        password = arr[1].trim();
        System.out.println(user + " " + password);
        System.out.println("sfainit");

    }
}
