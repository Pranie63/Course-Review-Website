//package com.example.coursereviewhw7;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//
//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import java.io.IOException;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//
//import java.sql.SQLException;
//import java.util.HashMap;
//
//public class ViewCourseController {
//
//    @FXML
//    private TextField CourseNameDepartment;
//
//    @FXML
//    private TextField CourseNameNumber;
//
//    @FXML
//    private TextField reviewField;
//
//    @FXML
//    private TextField ratingField;
//
//    @FXML
//    private Button AddReviewButton;
//
//    @FXML
//    private Button ConfirmReviewButton;
//
//
//    @FXML
//    private Label reviewLabel;
//
//    @FXML
//    private Label averageLabel;
//
//    @FXML
//    private Label ratingLabel;
//    private Student student ;
//    private Course course;
//    private Review review;
//
//    public void SetStudent(Student student)
//    {
//        System.out.println(student);
//        this.student = student;
//    }
//    @FXML
//    protected void ViewCourse() {
//
//        String department = CourseNameDepartment.getText();
//        int CourseNumber = Integer.parseInt(CourseNameNumber.getText());
//
//        course = new Course(department, CourseNumber);
//        HashMap<Course, Review> reviewList = new HashMap<>();
//
//
//        //Student student = new Student(student.getName(), student.getPassword(), reviewList);
//
//        //student = student;
//        Data d = new Data();
//
//        d.programStart();
//
//        {
//            if (!(d.validCourse(course))) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Invalid Course");
//                alert.setHeaderText("This is an invalid course.All departments of Strings of 4 or fewer capital letters. All numbers are 4 digits. .");
//                alert.showAndWait();
//
//                try {
//                    // Load the login screen FXML file
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
//                    Parent root = loader.load();
//
//                    MainMenuController controller = loader.getController();
//                    controller.SetStudent(student);
//                    Scene scene = new Scene(root);
//                    Stage stage = (Stage) AddReviewButton.getScene().getWindow();
//
//                    stage.setScene(scene);
//                    stage.show();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//            else {
//                try {
//                    if(d.courseExists(course) && !d.courseHasReview(course))
//                    {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Invalid Course");
//                        alert.setHeaderText("This is an invalid course.All departments of Strings of 4 or fewer capital letters. All numbers are 4 digits. .");
//                        alert.showAndWait();
//
//                        try {
//                            // Load the login screen FXML file
//                            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
//                            Parent root = loader.load();
//
//                            MainMenuController controller = loader.getController();
//                            controller.SetStudent(student);
//                            Scene scene = new Scene(root);
//                            Stage stage = (Stage) AddReviewButton.getScene().getWindow();
//
//                            stage.setScene(scene);
//                            stage.show();
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                    else {
//                        try {
//                            if(d.courseExists(course) && d.courseHasReview(course))
//                            {
//                                student.setReviewList(d.getStudentReview(student));
//                                for (Course course2 : student.getReviewList().keySet()) {
//                                    //System.out.println("The course review is " +student.getReviewList().get(course2).getReviewText() );
//                                    Review review = student.getReviewList().get(course2);
//                                    if (course2.getDepartment().equals(department) && course2.getCatalogNumber() == CourseNumber) {
//                                        reviewLabel.setText(review.getReviewText());
//                                        System.out.println(review.getReviewText());
//                                        //ratingLabel.setText("Rating: " + review.getRating());
//                                    }
//                                }
//                                double sum = 0;
//                               //for (Course course3 : student.getReviewList().keySet()) {
//                                   //System.out.println(student.getReviewList().get(course3).getRating());
//                                   for(int i = 0;i<student.getReviewList().size();i++)
//                                   {
//                                       System.out.println(student.getReviewList().get(course).getRating());
//                                    sum = sum + student.getReviewList().get(course).getRating();
//                                   }
//                                  // System.out.println(student.getReviewList().get(course3).getRating() + "dis the rating");
//
//                               double average = sum/student.getReviewList().size();
//                               System.out.println("Course Average: " + average);
//                               averageLabel.setText("Course Average: " + average);
//
//                                try {
//                                    // Load the login screen FXML file
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
//                                    Parent root = loader.load();
//
//                                    MainMenuController controller = loader.getController();
//                                    controller.SetStudent(student);
//                                    Scene scene = new Scene(root);
//                                    Stage stage = (Stage) AddReviewButton.getScene().getWindow();
//
//                                    stage.setScene(scene);
//                                    stage.show();
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }
//
//
//    }
//
//}


package com.example.coursereviewhw7;

        import javafx.fxml.FXML;
        import javafx.scene.control.TextField;

        import javafx.application.Application;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import java.io.IOException;

        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;


        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class ViewCourseController {

    @FXML
    private TextField CourseNameDepartment;

    @FXML
    private TextField CourseNameNumber;

    @FXML
    private TextField reviewField;

    @FXML
    private TextField ratingField;

    @FXML
    private Button AddReviewButton;

    @FXML
    private Button ConfirmReviewButton;


    @FXML
    private Label reviewLabel;

    @FXML
    private Label averageLabel;

    @FXML
    private Label ratingLabel;
    private Student student ;
    private Course course;
    private Review review;

    public void SetStudent(Student student)
    {
        System.out.println(student);
        this.student = student;
    }
    @FXML
    protected void ViewCourse() {

        String department = CourseNameDepartment.getText();
        int CourseNumber = Integer.parseInt(CourseNameNumber.getText());

        course = new Course(department, CourseNumber);
//        HashMap<Course, Review> reviewList = new HashMap<>();
        List<Review> reviewList = new ArrayList<>();


        //Student student = new Student(student.getName(), student.getPassword(), reviewList);

        //student = student;
        Data d = new Data();

        d.programStart();
        {
            if (!(d.validCourse(course))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Course");
                alert.setHeaderText("This is an invalid course.All departments of Strings of 4 or fewer capital letters. All numbers are 4 digits. .");
                alert.showAndWait();

                try {
                    // Load the login screen FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root = loader.load();

                    MainMenuController controller = loader.getController();
                    controller.SetStudent(student);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) AddReviewButton.getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    if(d.courseHasReview(course))
                    {
//                        student.setReviewList(d.getStudentReview(student));
                        reviewList = d.getCourseReview(course);
//                        for (Course course2 : student.getReviewList().keySet()) {
//                            System.out.println("The course review is " +student.getReviewList().get(course2).getReviewText() );
//                            //System.out.println("The course review is " +student.getReviewList().get(course2).getReviewText() );
//                            Review review = student.getReviewList().get(course2);
//                            if (course2.getDepartment().equals(department) && course2.getCatalogNumber() == CourseNumber) {
//                                reviewLabel.setText(review.getReviewText());
//                                //ratingLabel.setText("Rating: " + review.getRating());
//                                break;
//                            }
//
//                            }
//                            double sum = 0;
//                            //for (Course course3 : student.getReviewList().keySet()) {
//                            //System.out.println(student.getReviewList().get(course3).getRating());
//                            for(int i = 0;i<student.getReviewList().size();i++)
//                            {
//                               // sum = sum + student.getReviewList().get(course3).getRating();
//                                System.out.println(student.getReviewList().get(course).getRating());
//                                sum = sum + student.getReviewList().get(course).getRating();
//                            }
//                            // System.out.println(student.getReviewList().get(course3).getRating() + "dis the rating");
//
//
//                        String reviews = "";
//                        double sum = 0;
                        reviewList.stream().forEach((review) -> {
                            reviewLabel.setText(reviewLabel.getText() + review.getReviewText() + "\n");
                            averageLabel.setText((Integer.parseInt((averageLabel.getText().equals("") ? "0" : averageLabel.getText())) + review.getRating()) + "");
                        });
                        double average = Double.parseDouble(averageLabel.getText())/reviewList.size();
//                        System.out.println("Course Average: " + average);
                        averageLabel.setText("Course Average: " + average + "/5");


                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }


    }


}





