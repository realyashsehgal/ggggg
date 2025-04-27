package src.managers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import src.models.CarExit;

public class CarExitManager {

    private static final String addQuery = "INSERT INTO carexit ( CarNumber, DriverName, ExitTimestamp, AmountPaid) VALUES (?, ?, ?, ?)";
    private static final String showQuery = "SELECT * FROM carexit ORDER BY ExitID ASC";

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;

    public static String addcar(CarExit car) {
        if (car.getCarnum().isEmpty() || car.getDrivername().isEmpty()) {
            return "Fields can not be empty!";
        }
        try {
            Timestamp exittimestamp = Timestamp.valueOf(car.getExittime());
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(addQuery);
            st.setString(1, car.getCarnum());
            st.setString(2, car.getDrivername());
            st.setTimestamp(3, exittimestamp);
            st.setFloat(4, car.getAmount());
            st.executeUpdate();

            return "SUCCESS";
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            DatabaseManager.close(conn, st, null);
        }
    }

    public static List<String[]> getAllcars() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showQuery);
            rs = st.executeQuery();

            List<String[]> cars = new ArrayList<>();

            while (rs.next()) {
                cars.add(new String[] { rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                });
            }

            return cars;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }

}
