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
    private Button MainMenuButton;


    @FXML
    private Label reviewLabel;

    @FXML
    private Label averageLabel;

    @FXML
    private Label ratingLabel;
    private Student student;
    private Course course;
    private Review review;

    public void SetStudent(Student student) {
        System.out.println(student);
        this.student = student;
    }

    @FXML
    protected void ViewCourse() {

        if (CourseNameDepartment.getText().equals("") || CourseNameNumber.getText().equals("")) {
            alertPopup("Course Empty", "The course name field is empty. Please enter a valid course.");
            goToMainMenu();
            return;
        }

        String department = CourseNameDepartment.getText();
        int CourseNumber = Integer.parseInt(CourseNameNumber.getText());

        course = new Course(department, CourseNumber);
        List<Review> reviewList;
        Data d = new Data();

        d.programStart();
        if (!(d.validCourse(course))) {
            alertPopup("Invalid Course", "This is an invalid course. All departments of Strings of 4 or fewer capital letters. All numbers are 4 digits.");
            goToMainMenu();
        } else if (d.courseHasReview(course)){
            reviewList = d.getCourseReview(course);
            reviewList.stream().forEach((review) -> {
                reviewLabel.setText(reviewLabel.getText() + review.getReviewText() + "\n");
                averageLabel.setText((Integer.parseInt((averageLabel.getText().equals("") ? "0" : averageLabel.getText())) + review.getRating()) + "");
            });
            double average = Double.parseDouble(averageLabel.getText()) / reviewList.size();
//                        System.out.println("Course Average: " + average);
            averageLabel.setText("Course Average: " + average + "/5");
        }
    }

    @FXML
    protected void goToMainMenu() {
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

    private void alertPopup(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }
}





