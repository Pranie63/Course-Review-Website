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

public class SubmitCourseController {

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
    private void initialize() {
        reviewField.setVisible(false);
        ratingField.setVisible(false);
        reviewLabel.setVisible(false);
        ratingLabel.setVisible(false);
        ConfirmReviewButton.setVisible(false);
    }


    @FXML
    protected void AddCourse() {


//

        String department = CourseNameDepartment.getText();
        int CourseNumber = Integer.parseInt(CourseNameNumber.getText());

        course = new Course(department, CourseNumber);
        HashMap<Course, Review> reviewList = new HashMap<>();


        //Student student = new Student(student.getName(), student.getPassword(), reviewList);

        //student = student;
        Data d = new Data();

        d.programStart();
        student.setReviewList(d.getStudentReview(student));
        for (Course course2 : student.getReviewList().keySet()) {
            System.out.println(student.getReviewList().get(course2).getReviewText());
        }
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
            } else if (student.getReviewList().containsKey(course)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid");
                alert.setHeaderText("You've already tried to review this course, you can't do it again");
                alert.showAndWait();

            }

            else
            {
                reviewField.setVisible(true);
                ratingField.setVisible(true);
                reviewLabel.setVisible(true);
                ratingLabel.setVisible(true);
                ConfirmReviewButton.setVisible(true);
            }

        }


    }
@FXML
        protected void ConfirmReview()
{
                int rating = Integer.parseInt(ratingField.getText());
                String field = reviewField.getText();

                review = new Review(rating, field);
            Data d = new Data();





                try {
//            reviewList.put(course, review);

                    // d.addCourse(course);
//            d.getCourseReview(course);

                    if(!d.validRating(review))
                    {
                        ratingField.setText("");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Invalid");
                        alert.setHeaderText("This is an invalid rating. Please type a number between 1 and 5.");
                        alert.showAndWait();
                    }
                    else {
                        d.submitReview(student, review, course);
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

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
