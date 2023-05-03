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

public class HelloController {
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
    private Button LoginButton;

    @FXML
    private Button NewUserButton;




    @FXML
    private Label enterRatingLabel;
    private Student student;



    @FXML
    protected void LoginButtonAction() {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginAndNewUser.fxml"));
            Parent root = loader.load();
            //SubmitCourseController controller = loader.getController();
            //controller.SetStudent(student);
            Scene scene = new Scene(root);
            Stage stage = (Stage) LoginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // handle add course button action here
    }

    @FXML
    protected void NewUserButtonAction() {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginAndNewUser.fxml"));
            Parent root = loader.load();
            //SubmitCourseController controller = loader.getController();
            //controller.SetStudent(student);
            Scene scene = new Scene(root);
            Stage stage = (Stage) NewUserButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // handle add course button action here
    }


    }