package app;

import java.util.Scanner;
import java.sql.Connection;

import db.DatabaseManager;
import util.CSVLoader;
import service.ReadingHabitService;
import service.UserService;

public class Main {

    public static void main(String[] args) {

        Connection conn = DatabaseManager.connect();

        if (conn == null) {
            System.out.println("❌ Database connection failed.");
            return;
        }

        System.out.println("✅ Database is working!");

        DatabaseManager.createTables(conn);
        CSVLoader.loadCSV(conn, "src/main/resources/data.csv");
        UserService.generateUsers(conn);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("    BOOKTRACKER MENU");
            System.out.println("==============================");
            System.out.println("[1] Add User");
            System.out.println("[2] Show User Reading Habits");
            System.out.println("[3] Update Book Title");
            System.out.println("[4] Delete Reading Habit");
            System.out.println("[5] Mean Age");
            System.out.println("[6] Users per Book");
            System.out.println("[7] Total Pages Read");
            System.out.println("[8] Users with >1 Book");
            System.out.println("[0] Exit");
            System.out.print("==>Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();

                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    UserService.addUser(conn, userId, age, name);
                    break;

                case 2:
                    System.out.print("Enter user ID: ");
                    int userIdQuery = scanner.nextInt();

                    ReadingHabitService.getReadingHabitsByUser(conn, userIdQuery);
                    break;

                case 3:
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter old book title: ");
                    String oldTitle = scanner.nextLine();

                    System.out.print("Enter new book title: ");
                    String newTitle = scanner.nextLine();

                    ReadingHabitService.updateBookTitle(conn, oldTitle, newTitle);
                    break;

                case 4:
                    System.out.print("Enter habit ID to delete: ");
                    int habitId = scanner.nextInt();

                    ReadingHabitService.deleteReadingHabit(conn, habitId);
                    break;

                case 5:
                    UserService.getMeanAge(conn);
                    break;

                case 6:
                    scanner.nextLine(); // clear buffer
                    System.out.print("Enter book name: ");
                    String book = scanner.nextLine();

                    ReadingHabitService.getUsersPerBook(conn, book);
                    break;

                case 7:
                    ReadingHabitService.getTotalPagesRead(conn);
                    break;

                case 8:
                    ReadingHabitService.getUsersWithMultipleBooks(conn);
                    break;

                case 0:
                    System.out.println("👋 Exiting BookTracker. Bye!");
                    break;

                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }

        } while (choice != 0);

        scanner.close();

        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error closing connection.");
        }
    }
}
