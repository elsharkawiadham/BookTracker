package service;

import java.sql.Connection;
import java.sql.ResultSet;

public class ReadingHabitService {
    public static void getTotalPagesRead(Connection conn) {
        String sql = "SELECT SUM(pagesRead) AS totalPages FROM ReadingHabit";
        
        try {
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int total = rs.getInt("totalPages");
                System.out.println("📚 Total pages read: " + total);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    public static void getUsersPerBook(Connection conn, String bookName) {

        String sql = "SELECT COUNT(DISTINCT user) AS totalUsers FROM ReadingHabit WHERE TRIM(book) = TRIM(?)";        
        try {
            var ps = conn.prepareStatement(sql);
            ps.setString(1, bookName);

            var rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("totalUsers");
                System.out.println("👥 Users who read \"" + bookName + "\": " + total);
            }

        } catch (Exception e) {
        System.out.println("❌ Error: " + e.getMessage());
        }
    }
    public static void getUsersWithMultipleBooks(Connection conn) {

        String sql = """
            SELECT COUNT(*) AS totalUsers
            FROM (
                SELECT user
                FROM ReadingHabit
                GROUP BY user
                HAVING COUNT(DISTINCT book) > 1
            )
        """;

        try {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int total = rs.getInt("totalUsers");
                System.out.println("📚 Users with more than one book: " + total);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void updateBookTitle(Connection conn, String oldTitle, String newTitle) {

        String sql = "UPDATE ReadingHabit SET book = ? WHERE book = ?";

        try {
            var ps = conn.prepareStatement(sql);

            ps.setString(1, newTitle);
            ps.setString(2, oldTitle);

            int rowsUpdated = ps.executeUpdate();

            System.out.println("✏️ Updated rows: " + rowsUpdated);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void deleteReadingHabit(Connection conn, int habitID) {

        String sql = "DELETE FROM ReadingHabit WHERE habitID = ?";

        try {
            var ps = conn.prepareStatement(sql);

            ps.setInt(1, habitID);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("🗑️ Record deleted successfully!");
            } else {
                System.out.println("⚠️ No record found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    public static void getReadingHabitsByUser(Connection conn, int userId) {

        String sql = "SELECT * FROM ReadingHabit WHERE user = ?";

        try {
            var ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            var rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                int habitID = rs.getInt("habitID");
                String book = rs.getString("book");
                int pages = rs.getInt("pagesRead");
                String date = rs.getString("submissionMoment");

                System.out.println("📖 ID: " + habitID +
                        " | Book: " + book +
                        " | Pages: " + pages +
                        " | Date: " + date);
            }

            if (!found) {
                System.out.println("⚠️ No habits found for this user.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}