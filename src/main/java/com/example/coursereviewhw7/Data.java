package com.example.coursereviewhw7;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
//When your program begins, it should check if "Reviews.sqlite3" exists.
// If the database file doesn't exist:
// Your program must create the Reviews.sqlite3 database file in the project root directory
// Run the necessary "CREATE TABLE" queries to put the three required tables into the database
// If the database file does exist, you don't need to do anything special.

//remember to cite the reused code
public class Data {
static Connection connection;

    static void programStart() throws IOException {

//          String url = "jdbc:sqlite:Reviews.sqlite3";
          try {
//              connection = DriverManager.getConnection(url);
              if (connection != null) {
                  DatabaseMetaData metaData = connection.getMetaData();
                  System.out.println("Metadata: " + metaData);
              }
//              Statement stmt = connection.createStatement();
//              stmt.execute("PRAGMA foreign_keys = ON;");

          }

          catch(SQLException e){
              e.printStackTrace();
          }

   }

    public static void connect() { //thoroughly tested
      if(connection != null){
         throw new IllegalStateException("Error: Manager is already connected.");
      }
      try{
         connection = DriverManager.getConnection("jdbc:sqlite:Reviews.sqlite3");
      }

      catch(SQLException e){
         System.out.println("You are already connected.");
      }
   }
   static Boolean dbExists() {
      String dbName = "Reviews.sqlite3";
      File dbFile = new File(dbName);
      return dbFile.exists();
   }

    private void connectionCheck() {
        if ( connection == null) {
            throw new IllegalStateException("Error: Manager has not connected yet.");
        }
    }

    public void createTables() {//tested for catching all exceptions ✔

        try {
            connectionCheck();

            Statement statement = connection.createStatement();
            String studentsTable = "CREATE TABLE IF NOT EXISTS STUDENTS"
                    + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "Username VARCHAR(255) UNIQUE NOT NULL, "
                    + "Password VARCHAR(255) NOT NULL);";
            statement.executeUpdate(studentsTable);

            String coursesTable = "CREATE TABLE IF NOT EXISTS COURSES"
                    + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "Department VARCHAR(255) NOT NULL, "
                    + "Catalog_Number INTEGER NOT NULL);";
            statement.executeUpdate(coursesTable);

            String reviewsTable = "CREATE TABLE IF NOT EXISTS REVIEWS"
                    + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "StudentID INTEGER NOT NULL, "
                    + "CourseID INTEGER NOT NULL, "
                    + "Message VARCHAR(255) NOT NULL, "
                    + "Rating INTEGER NOT NULL, "
                    + "CHECK (Rating >= 1 AND Rating <= 5), "
                    + "FOREIGN KEY (StudentID) REFERENCES STUDENTS(id) ON DELETE CASCADE, "
                    + "FOREIGN KEY (CourseID) REFERENCES COURSES(id) ON DELETE CASCADE);";
            statement.executeUpdate(reviewsTable);


            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Error: Tables already exist.");
        }
    }

   public static void main(String[] args) throws IOException, SQLException {
        Data D = new Data();
        connect();
        D.createTables();
//        programStart();
//      programStart();
//       Statement statement = connection.createStatement();
//       String tb = "CREATE TABLE TB (id INTEGER NOT NULL)";
//       String x = "CREATE DATABASE X";
//       statement.executeUpdate(tb);
//       Statement stmt = connection.createStatement();
//       stmt.execute(x);
    //createNewDatabase("Reviews.sqlite3");

   }
}

