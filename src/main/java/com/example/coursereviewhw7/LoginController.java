package com.example.coursereviewhw7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginController {


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
    private Button NewUserButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label welcomeText;

    private boolean isExistingUser = false;

    private boolean isNewUser = false;

    public static Student student;




    @FXML
    public void accountController() {

        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("One or more of the input fields were not filled out. Please try again.");
            alert.setTitle("Incomplete login");
            alert.showAndWait();
            return;
        }
        Student student = new Student(username, password);
        Data d = new Data();
        d.programStart();
        if (d.accountCorrect(student)) {
            loginButton.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                Parent root = loader.load();
                MainMenuController controller = loader.getController();
                controller.SetStudent(student);
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            // login failed, show error message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Your login doesnt work");
            alert.setHeaderText("Try again");
            alert.showAndWait();
            usernameTextField.setText("");
            passwordField.setText("");


        }
    }


}