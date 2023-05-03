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
import java.util.HashMap;

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
        HashMap<Course, Review> reviewList = new HashMap<>();


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
                        student.setReviewList(d.getStudentReview(student));
                        for (Course course2 : student.getReviewList().keySet()) {
                            System.out.println("The course review is " +student.getReviewList().get(course2).getReviewText() );
                        }
                        int sum = 0;
                       for (Course course3 : student.getReviewList().keySet()) {
                           for(int i = 0;i<student.getReviewList().size();i++)
                           {
                            sum = sum + student.getReviewList().get(course3).getRating();
                           }
                          // System.out.println(student.getReviewList().get(course3).getRating() + "dis the rating");
                       }
                       System.out.println("Course Average: " + sum/student.getReviewList().size());

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
//            } else if (d.courseExists(course)) {
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Invalid");
//                alert.setHeaderText("Sorry, your course has no reviews");
//                alert.showAndWait();
//
//            }
        }


    }
    @FXML
    protected void ConfirmReview()
    {
        int rating = Integer.parseInt(ratingField.getText());
        String field = reviewField.getText();

        review = new Review(rating, field);


        String department = CourseNameDepartment.getText();
        int CourseNumber = Integer.parseInt(CourseNameNumber.getText());

        course = new Course(department, CourseNumber);
        Data d = new Data();


    }
}

