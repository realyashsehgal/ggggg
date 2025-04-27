package src.managers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseInitializer {
        private static final String url = "jdbc:mysql://localhost:3306";
        private static String user = "";
        private static String password = "";

        public static int initialize() {
                try {
                        Connection conn = DriverManager.getConnection(url, user, password);
                        Statement stmt = conn.createStatement();

                        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS plm");

                        conn = DatabaseManager.GetConnection();
                        stmt = conn.createStatement();

                        stmt.executeUpdate(
                                        "CREATE TABLE IF NOT EXISTS CarEntry (" +
                                                        "EntryID INT AUTO_INCREMENT PRIMARY KEY," +
                                                        "CarNumber VARCHAR(20) NOT NULL," +
                                                        "DriverName VARCHAR(100) NOT NULL," +
                                                        "EntryTimestamp DATETIME NOT NULL" +
                                                        ")");

                        stmt.executeUpdate(
                                        "CREATE TABLE IF NOT EXISTS CarExit (" +
                                                        "ExitID INT AUTO_INCREMENT PRIMARY KEY," +
                                                        "CarNumber VARCHAR(20) NOT NULL," +
                                                        "DriverName VARCHAR(100) NOT NULL," +
                                                        "ExitTimestamp DATETIME NOT NULL," +
                                                        "AmountPaid DECIMAL(10,2) NOT NULL" +
                                                        ")");

                        stmt.executeUpdate(
                                        "CREATE TABLE IF NOT EXISTS ParkingTransaction (" +
                                                        "TransactionID INT AUTO_INCREMENT PRIMARY KEY," +
                                                        "CarNumber VARCHAR(20) NOT NULL," +
                                                        "DriverName VARCHAR(100) NOT NULL," +
                                                        "EntryTimestamp DATETIME NOT NULL," +
                                                        "ExitTimestamp DATETIME NOT NULL," +
                                                        "AmountPaid DECIMAL(10,2) NOT NULL" +
                                                        ")");
                        stmt.executeUpdate(
                                        "CREATE TABLE IF NOT EXISTS Users (" +
                                                        "Username VARCHAR(50) PRIMARY KEY, " +
                                                        "Password VARCHAR(50));");

                        return 1;

                } catch (SQLException e) {
                        System.out.println("sfa");

                        JOptionPane.showMessageDialog(null, "Error Connecting to SQL, Please Check SQLConfig.txt!",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                        return 0;
                }
        }

        public static void getCreds() throws IOException {
                String creds = Files.readString(Path.of("lib/SQLConfig.txt"));
                String[] arr = creds.split("\n");
                user = arr[0].trim();
                password = arr[1].trim();
                System.out.println(user + " naya wala " + password);
        }
}
