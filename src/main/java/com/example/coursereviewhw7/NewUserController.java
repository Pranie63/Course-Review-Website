package com.example.coursereviewhw7;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class NewUserController {

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
    public void newAccount()
    {
        String NewAccountUsername = usernameTextField.getText();
        String NewAccountPassword = passwordField.getText();
        String NewAccountPasswordVerify = passwordFieldVerify.getText();
        Student NewStudent = new Student(NewAccountUsername, NewAccountPassword);
        Data d = new Data();
        d.programStart();
        {
            if (!(NewAccountPassword.equals(NewAccountPasswordVerify))) {
                System.out.println(NewAccountPassword);
                System.out.println(NewAccountPasswordVerify);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Your passwords don't match");
                alert.setHeaderText("Try again");
                alert.showAndWait();
                usernameTextField.setText("");
                passwordField.setText("");
                passwordFieldVerify.setText("");
                existingUserRadioButton.setSelected(false);
                newUserRadioButton.setSelected(false);
                loginButton.setVisible(false);
                registerButton.setVisible(false);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Parent root = loader.load();
                    HelloController controller = loader.getController();
                    //controller.SetStudent(student);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) registerButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            else if (d.accountCorrect(NewStudent))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hey! This account exists already. Go login");
                alert.setHeaderText("Try again");
                alert.showAndWait();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Parent root = loader.load();
                    HelloController controller = loader.getController();
                    //controller.SetStudent(student);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) registerButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        else
            {
                d.createUser(NewStudent, NewAccountPasswordVerify);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root = loader.load();
                    MainMenuController controller = loader.getController();
                    //controller.SetStudent(student);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) registerButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
