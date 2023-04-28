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

          String url = "jdbc:sqlite:";
          try {
              connection = DriverManager.getConnection(url);
              Statement stmt = connection.createStatement();
              stmt.execute("PRAGMA foreign_keys = ON;");

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

   public static void main(String[] args) throws IOException, SQLException {
        connect();
        programStart();
//      programStart();
       Statement statement = connection.createStatement();
//       String tb = "CREATE TABLE TB";
       String x = "CREATE DATABASE X";
       statement.executeUpdate(x);
//       Statement stmt = connection.createStatement();
//       stmt.execute(x);
    //createNewDatabase("Reviews.sqlite3");

   }
}

