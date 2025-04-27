package src.managers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.models.User;

public class LoginManager {

    private static final String createQuery = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
    private static final String checkQuery = "SELECT * FROM Users";

    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    private static String tempUsername;
    private static String tempPassword;

    public static String CreateAccount(User user)
    {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty())
        {
            return "Fields Can not be Empty";
        }
        if(exists(user))
        {
            return "This user already exists";
        }
        try{
            conn = DatabaseManager.GetConnection();
            if(conn == null)
            {
                System.out.println("LOL");
                return "Invalid Mysql Credentials, Please Check SQLConfig.txt";
            }
            stmt = conn.prepareStatement(createQuery);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

            return "SUCCESS";
        }catch(SQLException e)
        {
            return e.getMessage();
        }
        finally
        {
            DatabaseManager.close(conn, stmt, rs);
        }
    }

    public static String CheckDetails(User user)
    {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty())
        {
            return "Fields Can not be Empty";
        }
        try{
            conn = DatabaseManager.GetConnection();
            if(conn == null)
                return "Invalid Mysql Credentials, Please Check SQLConfig.txt";
            stmt = conn.prepareStatement(checkQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {
                tempUsername = rs.getString("Username");
                tempPassword = rs.getString("Password");
                if(user.getUsername().equals(tempUsername) && user.getPassword().equals(tempPassword))
                    return "SUCCESS";
            }

            return "Invalid Credentials";
        }catch(SQLException e)
        {
            return e.getMessage();
        }
        finally
        {
            DatabaseManager.close(conn, stmt, rs);
        }
    }

    private static boolean exists(User user)
    {
        try
        {
            conn = DatabaseManager.GetConnection();
            stmt = conn.prepareStatement(checkQuery);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if(rs.getString("Username").equals(user.getUsername()))
                return true;
            }
            return false;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        finally
        {
            DatabaseManager.close(conn, stmt, rs);
        }
    }

}
