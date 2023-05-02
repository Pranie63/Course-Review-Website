package com.example.coursereviewhw7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
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
    private PasswordField passwordFieldVerify;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label welcomeText;

    private boolean isExistingUser = false;

    private boolean isNewUser = false;


    @FXML
    void initialize() {

        // add event listeners to the radio buttons to show/hide the buttons
        existingUserRadioButton.setOnAction(event -> {
            if (existingUserRadioButton.isSelected()) {
                loginButton.setVisible(true);
                registerButton.setVisible(false);
            }
        });

        newUserRadioButton.setOnAction(event -> {
            if (newUserRadioButton.isSelected()) {
                loginButton.setVisible(false);
                registerButton.setVisible(true);
            }
        });
    }

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
            System.out.println("Your login's incorrect");
        }
    }

    @FXML
     public void accountController() {

        String username = usernameTextField.getText();
        String password = passwordField.getText();
        Student student = new Student(username, password);
        Data d = new Data();
        d.programStart();

        if (d.accountCorrect(student)) {
            System.out.println("This is working");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Success");
            alert.setHeaderText("You have logged in successfully.");
            alert.showAndWait();
        } else{
            System.out.println("this isnt working");
            // login failed, show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
        }
    }

        @FXML
        public void newAccount()
        {
            String NewAccountUsername = usernameTextField.getText();
            String NewAccountPassword = passwordField.getText();
            String NewAccountPasswordVerify = passwordFieldVerify.getText();
            Student NewStudent = new Student(NewAccountUsername, NewAccountPassword);
            Data d = new Data();
            d.programStart();
            if(!(NewAccountPassword.equals(NewAccountPasswordVerify))) {
                System.out.println(NewAccountPassword);
                System.out.println(NewAccountPasswordVerify);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Your passwords don't match");
                alert.setHeaderText("Try again");
                alert.showAndWait();
            }
            else
            {
            d.createUser(NewStudent, NewAccountPasswordVerify);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Success");
                alert.setHeaderText("You have logged in successfully.");
                alert.showAndWait();
            }
            }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}