package com.example.coursereviewhw7;
import java.sql.*;
//When your program begins, it should check if "Reviews.sqlite3" exists.
// If the database file doesn't exist:
// Your program must create the Reviews.sqlite3 database file in the project root directory
// Run the necessary "CREATE TABLE" queries to put the three required tables into the database
// If the database file does exist, you don't need to do anything special.

//remember to cite the reused code
public class Data {
   void testing() throws SQLException {
      Connection hi;
      hi = DriverManager.getConnection("jdbc:sqlite:Reviews");

   }
}
