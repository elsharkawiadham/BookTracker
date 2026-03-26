package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVLoader {

    public static void loadCSV(Connection conn, String filePath) {

        String sql = "INSERT INTO ReadingHabit (habitID, book, pagesRead, submissionMoment, user) VALUES (?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // clear table first
            conn.createStatement().execute("DELETE FROM ReadingHabit");

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(",");

                int habitID = Integer.parseInt(values[0]);
                int userID = Integer.parseInt(values[1]);
                int pagesRead = Integer.parseInt(values[2]);
                String book = values[3];
                String submissionMoment = values[4];

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, habitID);
                ps.setString(2, book);
                ps.setInt(3, pagesRead);
                ps.setString(4, submissionMoment);
                ps.setInt(5, userID);

                ps.executeUpdate();
            }

            System.out.println("CSV data loaded!");

        } catch (Exception e) {
            System.out.println("Error loading CSV: " + e.getMessage());
        }
    }
}
