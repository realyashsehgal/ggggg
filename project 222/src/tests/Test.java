// package src.tests;
// import java.sql.*;

// import src.models.Student;
// import src.models.User;

// public class Test {
    
//     static Student student;
//     public static void main(String[] args) {
//         try
//         {
//             String url = "jdbc:mysql://localhost:3306/LMS";
//             String user = "root";
//             String password = "Rohit1Rajat@";
//             Connection conn = DriverManager.getConnection(url, user, password);
//             Statement stmt = conn.createStatement();
            
//             stmt.executeUpdate("DROP TABLE Student");
//             stmt.executeUpdate("DROP TABLE Book");
//             stmt.executeUpdate("DROP TABLE Transactions");
//             stmt.executeUpdate("DROP TABLE Users");
//             // ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
//             // User user1 = new User(null, null);
//             // while (rs.next()) {
//             //     user1.setUsername(rs.getString("Username"));
//             //     user1.setPassword(rs.getString("Password"));
//             //     System.out.println(user1.toString());
//             // }
//         }
//         catch(Exception e)
//         {
//             System.out.println(e.getMessage());
//         }
//     }
// }

