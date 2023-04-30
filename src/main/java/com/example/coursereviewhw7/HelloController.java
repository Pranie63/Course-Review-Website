package com.example.coursereviewhw7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;


public class HelloController {


    @FXML
    private AnchorPane loginPane;

    @FXML
    private RadioButton existingUserRadioButton;

    @FXML
    private RadioButton newUserRadioButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    @FXML
    private Label welcomeText;


    @FXML
    void handleLoginButtonAction() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        // check if username and password match an existing user in the Students table
        // if they do, log in the user
        // if not, show an error message
        if (username.equals("test") && password.equals("test123")) {
            // logged in successfully, do something here
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Success");
            alert.setHeaderText("You have logged in successfully.");
            alert.showAndWait();
        } else {
            // login failed, show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}