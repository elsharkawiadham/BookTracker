# BookTracker Application

## Overview

BookTracker is a command-line Java application that allows users to
track reading habits.\
It uses SQLite as a database and JDBC for database interaction.

The application supports adding users, managing reading records, and
performing statistical queries using SQL.

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

### macOS / Linux

```bash
javac -cp "lib/*" -d bin $(find src/main/java -name "*.java")
java -cp "bin:lib/*" app.Main
```

### Windows

```powershell
javac -encoding UTF-8 -cp "lib/*" -d bin src\main\java\app\*.java src\main\java\db\*.java src\main\java\service\*.java src\main\java\util\*.java

java -cp "bin;lib/*" app.Main
```

You must have the database folder if not run this command
```bash
mkdir database
```


------------------------------------------------------------------------

## Concepts

-   JDBC connectivity
-   SQL queries
-   Aggregations (AVG, SUM)
-   GROUP BY & HAVING
-   PreparedStatement

------------------------------------------------------------------------

## Author

Adham Nabil Megahed Megahed Elsharkawi
Mirko Palone

------------------------------------------------------------------------

## Status

Fully functional
