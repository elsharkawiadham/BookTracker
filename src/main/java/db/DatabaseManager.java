package db;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:database/booktracker.db";

    public static Connection connect() {
    try {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        System.out.println("Connected to SQLite!");
        return conn;

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return null;
    }
}

    public static void createTables(Connection conn) {

    String userTable = """
        CREATE TABLE IF NOT EXISTS User (
            userID INTEGER PRIMARY KEY,
            age INTEGER,
            name TEXT
        );
    """;

    String readingHabitTable = """
        CREATE TABLE IF NOT EXISTS ReadingHabit (
            habitID INTEGER PRIMARY KEY,
            book TEXT,
            pagesRead INTEGER,
            submissionMoment DATETIME,
            user INTEGER,
            FOREIGN KEY (user) REFERENCES User(userID)
        );
    """;

    try {
        Statement stmt = conn.createStatement();
        stmt.execute(userTable);
        stmt.execute(readingHabitTable);
        System.out.println("Tables created successfully!");
    } catch (Exception e) {
        System.out.println("Error creating tables: " + e.getMessage());
    }
}
}
