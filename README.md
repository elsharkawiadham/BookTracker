# BookTracker Application
## Overview

BookTracker is a command-line Java application that allows users to
track reading habits.\
It uses SQLite as a database and JDBC for database interaction.

The application supports adding users, managing reading records, and
performing statistical queries using SQL.

------------------------------------------------------------------------
## Link to Github:
https://github.com/elsharkawiadham/BookTracker.git
------------------------------------------------------------------------
## Technologies Used

-   Java (JDK 17+ recommended)
-   SQLite
-   JDBC (SQLite driver)
-   SLF4J (logging dependency)

------------------------------------------------------------------------
## Features

1.  Add a user\
2.  Show reading habits of a user\
3.  Update book title\
4.  Delete a reading habit\
5.  Calculate mean age of users\
6.  Count users per book\
7.  Calculate total pages read\
8.  Count users who read more than one book

------------------------------------------------------------------------

## Compile & Run

### macOS

```bash
javac -cp "lib/*" -d bin $(find src/main/java -name "*.java")
java -cp "bin:lib/*" app.Main
```

### Windows/Linux

```powershell
javac -encoding UTF-8 -cp "lib/*" -d bin src\app\Main.java src\database\DatabaseManager.java src\service\PatientService.java

java -cp "bin;lib/*" app.Main
```

You must have the database folder if not run this command
```bash
mkdir database
```
------------------------------------------------------------------------
## How to Use the Application
After running the program, a menu will appear in the terminal:

===== BOOKTRACKER MENU =====
1. Add User
2. Show User Reading Habits
3. Update Book Title
4. Delete Reading Habit
5. Mean Age
6. Users per Book
7. Total Pages Read
8. Users with >1 Book
0. Exit

Enter the number corresponding to the desired functionality and follow the instructions.
1. Add User
   - Insert a new user into the database
   - Input required:
   - User ID (integer)
   - Age (integer)
   - Name (string)

3. Show User Reading Habits
   - Displays all reading records for a specific user
   - Input required:
   - User ID

4. Update Book Title
   - Updates the title of a book in all related records
   - Input required:
   - Old book title
   - New book title

5. Delete Reading Habit
   - Deletes a specific reading record
   - Input required:
   - Habit ID

7. Mean Age
   - Displays the average age of all users
   - No input required

9. Users per Book
   - Shows how many users have read a specific book
   - Input required:
   - Book title

10. Total Pages Read
   - Displays the total number of pages read across all users
   - No input required

11. Users with More Than One Book
    - Displays the number of users who have read more than one book
    - No input required

0. Exit
   - Closes the application

------------------------------------------------------------------------
## Concepts

-   JDBC connectivity
-   SQL queries
-   Aggregations (AVG, SUM)
-   GROUP BY & HAVING
-   PreparedStatement

------------------------------------------------------------------------
## Author

- Adham Nabil Megahed Megahed Elsharkawi
- Mirko Palone

------------------------------------------------------------------------
## Status

Fully functional
