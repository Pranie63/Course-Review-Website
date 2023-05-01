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
private static Connection connection;
private Student student;
private Course course;
private Review review;
//REQUIRED METHODS
    static void programStart() throws IOException {
          try {
              if (connection != null) {
                  DatabaseMetaData metaData = connection.getMetaData();
                  System.out.println("Metadata: " + metaData);
              }
          }
          catch(SQLException e){
              e.printStackTrace();
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
    //1.1 - Check if account exists
    public Boolean accountCorrect(Student student){ //returns True is the entered user & passcode is correct
        boolean exists = false;
        try{
            connectionCheck();

            String query = "SELECT USERNAME FROM STUDENTS WHERE USERNAME = ? AND password = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2,student.getPassword());

            ResultSet rs = ps.executeQuery();

            exists = rs.next();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Error: Account doesn't exist, or you entered incorrect credentials.");
        }
        return exists;
    }
    //1.2 - Create new User
    public void createUser(Student student, String passwordVerify){
        try{
            connectionCheck();
            if(!userExists(student)){
                if(student.getPassword().equals(passwordVerify)){
                    String query = "INSERT INTO STUDENTS (username, password) VALUES (?, ?)";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1,student.getName());
                    ps.setString(2,student.getPassword());

                    ps.executeUpdate();

                    ps.close();
                }
                else{
                    throw new IllegalArgumentException("Error: Passwords don't match!");
                }
            }
            else{
                throw new IllegalArgumentException("Error: This account already exists.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //2.1 - Submit Review for Course

    public void submitReview(Student student, Review review, Course course) throws SQLException {
        if (!student.getReviewList().containsKey(course)) {//ensure student hasn't already reviews
            if (courseExists(course)) {
                student.getReviewList().put(course, review);
                // Retrieve the student ID from the database
                String studentQuery = "SELECT ID FROM STUDENTS WHERE Name=?";
                PreparedStatement studentPstmt = connection.prepareStatement(studentQuery);
                studentPstmt.setString(1, student.getName());
                ResultSet studentRs = studentPstmt.executeQuery();
                int studentID = studentRs.getInt("ID");
                studentRs.close();
                studentPstmt.close();

                // Retrieve the course ID from the database
                String courseQuery = "SELECT ID FROM COURSES WHERE Department=? AND CatalogNumber=?";
                PreparedStatement coursePstmt = connection.prepareStatement(courseQuery);
                coursePstmt.setString(1, course.getDepartment());
                coursePstmt.setInt(2, course.getCatalogNumber());
                ResultSet courseRs = coursePstmt.executeQuery();
                int courseID = courseRs.getInt("ID");
                courseRs.close();
                coursePstmt.close();

                // Insert the review into the Review table
                String reviewQuery = "INSERT INTO REVIEWS (StudentID, CourseID, Message, Rating) VALUES (?, ?, ?, ?)";
                PreparedStatement reviewPstmt = connection.prepareStatement(reviewQuery);
                reviewPstmt.setInt(1, studentID);
                reviewPstmt.setInt(2, courseID);
                reviewPstmt.setString(3, review.getReviewText());
                reviewPstmt.setInt(4, review.getRating());
                reviewPstmt.executeUpdate();
                reviewPstmt.close();

                //connection.close();

            }
            else{
                if(validCourse(course)){//arnav: in the JAVAFX, you need to do 2.1.1.6.1

                    String courseInsertQuery = "INSERT INTO COURSES (Department, CatalogNumber) VALUES (?, ?)";
                    PreparedStatement courseInsertPstmt = connection.prepareStatement(courseInsertQuery);
                    courseInsertPstmt.setString(1, course.getDepartment());
                    courseInsertPstmt.setInt(2, course.getCatalogNumber());
                    courseInsertPstmt.executeUpdate();
                }
            }
        }

    }
//2.1.1.6.2
    public Boolean validRating(Student student, Course course){
       int reviewNum = student.getReviewList().get(course).getRating();
        if(reviewNum >= 0 && reviewNum <= 5){
            return true;
        }
       return false;
    }
//2.2.1.5
    public Boolean courseHasReview(Course course) throws SQLException {
        boolean hasReview;
        String query = "SELECT * FROM REVIEWS WHERE CourseDepartment = ? AND CourseCatalogNumber = ?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, course.getDepartment());
        pstmt.setInt(2, course.getCatalogNumber());

        ResultSet rs = pstmt.executeQuery();

        hasReview = rs.next();

        rs.close();
        pstmt.close();
        return hasReview;
    }
//HELPER FUNCTIONS



    private void connectionCheck() {
        if ( connection == null) {
            throw new IllegalStateException("Error: Manager has not connected yet.");
        }
    }
    private static void connect() { //thoroughly tested
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

    private Boolean userExists(Student student) throws SQLException {
        boolean exists;
        String query = "SELECT Username FROM STUDENTS WHERE Username = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,student.getName());

        ResultSet rs = ps.executeQuery();
        exists = rs.next();

        rs.close();
        ps.close();
        return exists;
    }

    private Boolean courseExists(Course course) throws SQLException {
        boolean exists;
        String query = "SELECT * FROM COURSES WHERE Department = ? AND Catalog_Number = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,course.getDepartment());
        ps.setInt(2,course.getCatalogNumber());

        ResultSet rs = ps.executeQuery();

        exists = rs.next();

        rs.close();
        ps.close();

        return exists;
    }
    private Boolean validCourse(Course course){
       //REGEX
        String reg = "^[A-Za-z]{1,4} \\d{4}$";
        return course.getDepartment().matches(reg);
    }
    //MAIN
   public static void main(String[] args) throws IOException, SQLException {
        Data D = new Data();
        connect();
        D.createTables();

   }
}

