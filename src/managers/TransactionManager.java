package src.managers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import src.models.*;

public class TransactionManager {

    private static final String showQuery = "SELECT " +
            "    ce.CarNumber, " +
            "    ce.DriverName, " +
            "    ce.EntryTimestamp, " +
            "    cx.ExitTimestamp, " +
            "    cx.AmountPaid, " +
            "    CASE " +
            "        WHEN cx.ExitTimestamp IS NULL THEN 'Inside' " +
            "        ELSE 'Exited' " +
            "    END AS Status " +
            "FROM " +
            "    carentry ce " +
            "LEFT JOIN " +
            "    carexit cx " +
            "ON " +
            "    ce.CarNumber = cx.CarNumber " +
            "ORDER BY " +
            "    ce.EntryTimestamp DESC";

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;

    private static CarExit carleft;
    private static CarEntry carenter;

    private static long pastDue = 0;

    public static List<String[]> getAllTransactions() {
        try {
            conn = DatabaseManager.GetConnection();
            st = conn.prepareStatement(showQuery);
            rs = st.executeQuery();

            List<String[]> transactions = new ArrayList<>();

            while (rs.next()) {
                transactions.add(new String[] {
                        rs.getString("CarNumber"),
                        rs.getString("DriverName"),
                        rs.getString("EntryTimestamp"),
                        rs.getString("ExitTimeStamp"),
                        rs.getString("AmountPaid")
                });
            }

            return transactions;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DatabaseManager.close(conn, st, rs);
        }
    }
}
