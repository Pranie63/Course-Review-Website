package com.example.coursereviewhw7;

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

public class MainMenuController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField reviewField;

    @FXML
    private TextField ratingField;
    @FXML

    private TextField CourseNameDepartment;
@FXML
    private TextField CourseNameNumber;

@FXML
    private Label enterCourseLabel;

    @FXML
    private Label enterReviewLabel;

    @FXML
    private Button AddReviewButton;

    @FXML
    private Button LogOutButton;


    @FXML
    private Button SeeReviewButton;




    @FXML
    private Label enterRatingLabel;
    private Student student;

    public void SetStudent(Student student)
    {
        System.out.println(student);
        this.student = student;
    }



//    @FXML
//    protected void handleAddReviewButtonAction() {
//        //enterCourseLabel.setVisible(true);
//        //enterRatingLabel.setVisible(true);
//        //enterReviewLabel.setVisible(true);
//        CourseNameDepartment.setVisible(true);
//        CourseNameNumber.setVisible(true);
//        ratingField.setVisible(true);
//        reviewField.setVisible(true);
//        handleAddCourseButtonAction();
//    }


    @FXML
    protected void handleAddCourseButtonAction() {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SubmitCourse.fxml"));
            Parent root = loader.load();
            SubmitCourseController controller = loader.getController();
            controller.SetStudent(student);
            Scene scene = new Scene(root);
            Stage stage = (Stage) AddReviewButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // handle add course button action here
    }
    @FXML
    protected void handleLogoutButtonAction() {
        // handle logout button action here

        try {
            // Load the login screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            HelloController controller = loader.getController();
            //controller.SetStudent(student);
            Scene scene = new Scene(root);
            Stage stage = (Stage) LogOutButton.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleViewCoursesButtonAction() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCourse.fxml"));
            Parent root = loader.load();
            ViewCourseController controller = loader.getController();
            controller.SetStudent(student);
            Scene scene = new Scene(root);
            Stage stage = (Stage) AddReviewButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // handle view courses button action here
    }



    // other methods and event handlers here
}
