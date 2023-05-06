package com.aiden.librarysoftwarefx.scenes;

import com.aiden.librarysoftwarefx.DAL.UserDAL;
import com.aiden.librarysoftwarefx.utility.User;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

public class RegisterUserScene {
    public static String getName() { return "register_new_user"; }

    public static Scene getScene() {
        // Register new user scene
        Label titleLabel = new Label();
        titleLabel.setText("Register new user");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label firstNameLabel = new Label("First name (required)");
        TextField firstNameField = new TextField();
        firstNameField.setMaxWidth(500);

        Label middleNameLabel = new Label("Middle name");
        TextField middleNameField = new TextField();
        middleNameField.setMaxWidth(500);

        Label lastNameLabel = new Label("Last name (required)");
        TextField lastNameField = new TextField();
        lastNameField.setMaxWidth(500);

        Label emailLabel = new Label("Email address");
        TextField emailField = new TextField();
        emailField.setMaxWidth(500);

        Label phoneNumLabel = new Label("Phone number");
        TextField phoneNumField = new TextField();
        phoneNumField.setMaxWidth(500);

        Button registerButton = new Button("Register user");
        Button goBackButton = new Button("Go back");

        Alert missingFieldAlert = new Alert(AlertType.WARNING);
        missingFieldAlert.setTitle("Missing information");

        Alert nameExistsAlert = new Alert(AlertType.CONFIRMATION);
        nameExistsAlert.setTitle("Name already exists");

        Alert registeredUserAlert = new Alert(AlertType.INFORMATION);
        registeredUserAlert.setTitle("User successfully registered");

        registerButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phoneNum = phoneNumField.getText();


            // Make sure first name and last name fields are filled out
            if(firstName.isEmpty()) {
                missingFieldAlert.setContentText("First name field is required.");
                missingFieldAlert.showAndWait();
                return;
            }
            if(lastName.isEmpty()) {
                missingFieldAlert.setContentText("Last name field is required.");
                missingFieldAlert.showAndWait();
                return;
            }

            // Check to see if a user with this name already exists
            for(User user : UserDAL.getAllUsers()) {
                if(user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName)) {
                    nameExistsAlert.setContentText("A user with the name " + user.getFirstName() + " " +
                            user.getLastName() +
                    " Already exists. (User ID " + user.getUserID() + ") Proceed with registration?");

                    Optional<ButtonType> result = nameExistsAlert.showAndWait();
                    // User chooses no
                    if(result.isEmpty() || result.get() == ButtonType.CANCEL) return;
                    // Otherwise, we proceed
                }
            }
            // Register the user
            User newUser = new User(firstName, lastName);
            newUser.setMiddleName(middleName);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phoneNum);
            UserDAL.saveNewUser(newUser);

            // Display confirmation alert
            registeredUserAlert.setContentText("User '" + firstName + " " + lastName + "' " +
                    "has been registered successfully (User ID: " + newUser.getUserID() + ")");
            registeredUserAlert.showAndWait();

            // Clear the text areas
            firstNameField.clear();
            middleNameField.clear();
            lastNameField.clear();
            emailField.clear();
            phoneNumField.clear();

            // Finished

        });

        goBackButton.setOnAction(e -> {
            // TODO scene manager switch to main menu
        });

        // Create and configure VBox
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(titleLabel, firstNameLabel, firstNameField, middleNameLabel, middleNameField,
        lastNameLabel, lastNameField, emailLabel, emailField, phoneNumLabel, phoneNumField,
                registerButton, goBackButton);

        return new Scene(vbox);
    }
}
