package com.aiden.librarysoftwarefx.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateAccountScene {

    public static Scene getScene() {
        Label titleLabel = new Label();
        titleLabel.setText("Simple Library Management");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Label usernameLabel = new Label("Choose username");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(500);

        Label passwordLabel = new Label("Choose password");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(500);

        Label confirmPasswordLabel = new Label("Confirm password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setMaxWidth(500);

        Button createAccountButton = new Button("Create account");
        Button goBackButton = new Button("Go back");

        // This display label will be used for showing errors in red as well as an account
        // creation confirmation message in green
        Label displayMessageLabel = new Label();


        createAccountButton.setOnAction(e -> {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Reject parentheses in the username / password
            if(userName.replaceAll("[^()]", "").length() > 0 || password.replaceAll("[^()]", "").length() > 0) {
                displayMessageLabel.setTextFill(Color.RED);
                displayMessageLabel.setText("No parentheses allowed in username or password");
                return;
            }
            // Check if passwords match
            if(!password.matches(confirmPassword)) {
                displayMessageLabel.setTextFill(Color.RED);
                displayMessageLabel.setText("Passwords do not match");
                return;
            }
            // Check if username / password is greater than 25 characters
            if(userName.length() > 25 || password.length() > 25) {
                displayMessageLabel.setTextFill(Color.RED);
                displayMessageLabel.setText("Username and password must be fewer than 25 characters");
                return;
            }
            // Check if username / password is fewer than 6 characters
            if(userName.length() < 6 || password.length() < 6) {
                displayMessageLabel.setTextFill(Color.RED);
                displayMessageLabel.setText("Username and password must be at least 6 characters long");
            }
            // Check if username / password field is empty
            if(userName.isEmpty() || password.isEmpty()) {
                displayMessageLabel.setTextFill(Color.RED);
                displayMessageLabel.setText("Username and password field cannot be blank");
            }
            // TODO check to see if username already exists

            // Otherwise, account can be created.

            // TODO create the account

            // Display a green confirmation message.
            displayMessageLabel.setTextFill(Color.GREEN);
            displayMessageLabel.setText("Success! Account " + userName + " successfully created.");


            // TODO call scene manager and switch to login screen

        });

        goBackButton.setOnAction(e -> {
            // TODO call scene manager and switch to login screen
        });

        // Create the VBox, add and configure everything, and return the Scene object
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(titleLabel, usernameLabel, usernameField,
                passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField,
                createAccountButton, goBackButton, displayMessageLabel);

        return new Scene(vBox);
    }
}
