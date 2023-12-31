package com.example.coursereviewhw7;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
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
//    static void programStart() throws IOException {
//          try {
//              if (connection != null) {
//                  DatabaseMetaData metaData = connection.getMetaData();
//                  System.out.println("Metadata: " + metaData);
//              }
//
//
//            connection = DriverManager.getConnection("jdbc:sqlite:Reviewssqlite3");
//
//        catch(SQLException e){
//            System.out.println("You are already connected.");
//        }
//          catch(SQLException e){
//              e.printStackTrace();
//          }
//   }


        public void programStart() { //thoroughly tested


//            if(connection != null){
//                throw new IllegalStateException("Error: Manager is already connected.");
//            }
            try{
                connection = DriverManager.getConnection("jdbc:sqlite:Reviews.sqlite3");
            }

            catch(SQLException e){
                System.out.println("You are already connected.");
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
            ps.close();
            rs.close();

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
            addCourse(course);
            if (courseExists(course)) {
                student.getReviewList().put(course, review);
                // Retrieve the student ID from the database
                String studentQuery = "SELECT id FROM STUDENTS WHERE Username=?";
                PreparedStatement studentPstmt = connection.prepareStatement(studentQuery);
                studentPstmt.setString(1, student.getName());
                ResultSet studentRs = studentPstmt.executeQuery();
                int studentID = studentRs.getInt("id");
//                System.out.println(studentID);
                studentRs.close();
                studentPstmt.close();

                // Retrieve the course ID from the database
                String courseQuery = "SELECT id FROM COURSES WHERE Department=? AND Catalog_Number=?";
                PreparedStatement coursePstmt = connection.prepareStatement(courseQuery);
                coursePstmt.setString(1, course.getDepartment());
                coursePstmt.setInt(2, course.getCatalogNumber());
                ResultSet courseRs = coursePstmt.executeQuery();
                int courseID = courseRs.getInt("id");
//                System.out.println(courseID);
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
            }
            else{
//                System.out.println("Course not in system");
//                if(validCourse(course)){//arnav: in the JAVAFX, you need to do 2.1.1.6.1
//                    System.out.println("Valid course");
//                    String courseInsertQuery = "INSERT INTO COURSES (Department, Catalog_Number) VALUES (?, ?)";
//                    PreparedStatement courseInsertPstmt = connection.prepareStatement(courseInsertQuery);
//                    courseInsertPstmt.setString(1, course.getDepartment());
//                    courseInsertPstmt.setInt(2, course.getCatalogNumber());
//                    courseInsertPstmt.executeUpdate();
//                }
                throw new IllegalArgumentException("Error: Invalid course entered.");
            }
        }

    }

    //2.1.1.5
    public void addCourse(Course course) throws SQLException {
        if (!courseExists(course) && validCourse(course)) {
            String courseInsertQuery = "INSERT INTO COURSES (Department, Catalog_Number) VALUES (?, ?)";
            PreparedStatement courseInsertPstmt = connection.prepareStatement(courseInsertQuery);
            courseInsertPstmt.setString(1, course.getDepartment());
            courseInsertPstmt.setInt(2, course.getCatalogNumber());
            courseInsertPstmt.executeUpdate();
            courseInsertPstmt.close();
        }
    }
//2.1.1.6.2
//    public Boolean validRating(Student student, Course course){
//       int reviewNum = student.getReviewList().get(course).getRating();
//        if(reviewNum >= 0 && reviewNum <= 5){
//            return true;
//        }
//       return false;
//    }

    public boolean validRating(Review review){
        int reviewNum = review.getRating();
        return reviewNum >= 1 && reviewNum <= 5;
//        if(reviewNum >= 0 && reviewNum <= 5){
//            return true;
//        }
//        return false;
    }
//2.2.1.5
    public Boolean courseHasReview(Course course) {
        try {
            boolean hasReview;
            String query = "SELECT * FROM REVIEWS WHERE CourseID = (SELECT id FROM COURSES WHERE Department = ? AND Catalog_Number = ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, course.getDepartment());
            pstmt.setInt(2, course.getCatalogNumber());
            ResultSet rs = pstmt.executeQuery();
            hasReview = rs.next();
            rs.close();
            pstmt.close();
            return hasReview;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> getCourseReview(Course course) {
        try {
            String query = "SELECT * FROM REVIEWS WHERE CourseID = (SELECT id FROM COURSES WHERE Department = ? AND Catalog_Number = ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, course.getDepartment());
            pstmt.setInt(2, course.getCatalogNumber());
            ResultSet rs = pstmt.executeQuery();
            List<Review> reviewList = new ArrayList<>();
            Review courseReview;
            while (rs.next()) {
                courseReview = new Review(rs.getInt("Rating"), rs.getString("Message"));
                reviewList.add(courseReview);
            }
            rs.close();
            pstmt.close();
            return reviewList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Course, Review> getStudentReview(Student student) {
        try {
            String query = "SELECT * FROM REVIEWS JOIN COURSES ON REVIEWS.CourseID = COURSES.id WHERE StudentID = (SELECT id FROM STUDENTS WHERE Username = ? AND Password = ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getPassword());
            ResultSet rs = pstmt.executeQuery();
            HashMap<Course, Review> reviewMap = new HashMap<>();
            Review courseReview;
            Course course;
//            for (int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++) {
//                System.out.println(rs.getMetaData().getColumnName(i));
//            }
            while (rs.next()) {
                courseReview = new Review(rs.getInt("Rating"), rs.getString("Message"));
                course = new Course(rs.getString("Department"), rs.getInt("Catalog_Number"));
                reviewMap.put(course, courseReview);
            }
            rs.close();
            pstmt.close();
            return reviewMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//HELPER FUNCTIONS



    private void connectionCheck() {
        if (connection == null) {
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

    public Boolean userExists(Student student) {
        try {
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
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean courseExists(Course course) throws SQLException {
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
    public Boolean validCourse(Course course){
       //REGEX
//        String reg = "^[A-Za-z]{1,4} \\d{4}$";
        String reg = "[A-Za-z]{1,4}";
        boolean isValidNum = course.getCatalogNumber() >= 1000 && course.getCatalogNumber() < 10000;
        return course.getDepartment().matches(reg) && isValidNum;
    }
    //MAIN
   public static void main(String[] args) throws IOException, SQLException {
        Data D = new Data();
        connect();
        D.createTables();
//        Student student1 = new Student("Pranav", "Pranav", new HashMap<Course, Review>());
//        Student student2 = new Student("Ahbey", "Ahbey", new HashMap<Course, Review>());
//        Student student3 = new Student("Arnav", "Arnav", new HashMap<Course, Review>());
//        Student student4 = new Student("Cornelius", "Fudge", new HashMap<Course, Review>());
//        Student student5 = new Student("Harry", "Potter", new HashMap<Course, Review>());
//        D.createUser(student1, "Pranav");
//        D.createUser(student2, "Ahbey");
//        D.createUser(student3, "Arnav");
//        D.createUser(student4, "Fudge");
//        D.createUser(student5, "Potter");
//       Course c1 = new Course("CS", 3140);
//       Review good1Pr = new Review(5, "Professor was kinda GOATed.");
//       Review good1Ah = new Review(4, "This class was too easy for me.");
//       Review good1Ar = new Review(4, "This was a fun class, and I would take it again.");
//       Review troll1Ar = new Review(1, "I am writing a second review to mess up the first.");
//       Course c2 = new Course("CS", 3130);
//       Review ok2Pr = new Review(3, "This was a useful class but not taught the best.");
//       Course c3 = new Course("CS", 3100);
//       Review ok3Ah = new Review(3, "This was a cool class, but much harder than DSA1");
//       Course c4 = new Course("CS", 4630);
//       Review good4Ha = new Review(5, "I took this course in a more magical school, but I assume it was just as good.");
//       Review bad4Co = new Review(1, "Students suck so I am giving this course a bad review");
//       Course c5 = new Course("APMA", 3100);
//       Course invalid = new Course("inval", 23423);
//       D.submitReview(student1, good1Pr, c1);
//       D.submitReview(student2, good1Ah, c1);
//       D.submitReview(student3, good1Ar, c1);
//       D.submitReview(student3, troll1Ar, c1);
//       D.submitReview(student1, ok2Pr, c2);
//       D.submitReview(student2, ok3Ah, c3);
//       D.submitReview(student5, good4Ha, c4);
//       D.submitReview(student4, bad4Co, c4);
//       D.submitReview(student4, bad4Co, invalid);
//       System.out.println(D.courseHasReview(c1));
//       List<Review> reviewList = D.getCourseReview(c1);
//       reviewList.stream().forEach((r) -> System.out.println(r.getRating()));
//       System.out.println(D.courseHasReview(c5));
//       List<Review> reviewList2 = D.getCourseReview(c5);
//       reviewList2.stream().forEach((r) -> System.out.println(r.getReviewText()));
   }
}

