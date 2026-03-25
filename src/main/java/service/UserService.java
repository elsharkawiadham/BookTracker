package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class UserService {

    public static void generateUsers(Connection conn) {
        
        String getUsers = "SELECT DISTINCT user FROM ReadingHabit";
        String insertUser = "INSERT INTO User (userID, age, name) VALUES (?, ?, ?)";
        

        try {
            conn.createStatement().execute("DELETE FROM User");
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getUsers);

            Random rand = new Random();

            while (rs.next()) {
                int userId = rs.getInt("user");

                int age = 18 + rand.nextInt(43); // 18–60
                String name = "User" + userId;

                PreparedStatement ps = conn.prepareStatement(insertUser);
                ps.setInt(1, userId);
                ps.setInt(2, age);
                ps.setString(3, name);

                ps.executeUpdate();
            }

            System.out.println("✅ Users generated!");

        } catch (Exception e) {
            System.out.println("❌ Error generating users: " + e.getMessage());
        }
    }
    
    public static void addUser(Connection conn, int userID, int age, String name) {

        String sql = "INSERT INTO User (userID, age, name) VALUES (?, ?, ?)";

        try {
            var ps = conn.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, age);
            ps.setString(3, name);

            ps.executeUpdate();

            System.out.println("✅ User added successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    public static void getMeanAge(Connection conn) {

    String sql = "SELECT AVG(age) AS meanAge FROM User";

    try {
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery(sql);

        if (rs.next()) {
            double meanAge = rs.getDouble("meanAge");
            System.out.println("📊 Mean age: " + meanAge);
        }

    } catch (Exception e) {
        System.out.println("❌ Error: " + e.getMessage());
    }
}
}