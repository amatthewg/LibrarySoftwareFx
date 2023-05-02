package com.aiden.librarysoftwarefx.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginScene {
    public static Scene getScene() {
        VBox vbox = new VBox();

        Label titleLabel = new Label();
        titleLabel.setText("Simple Library Management");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Label usernameLabel = new Label("username");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(500);

        Label passwordLabel = new Label("password");
        TextField passwordField = new TextField();
        passwordField.setMaxWidth(500);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String userName = usernameField.getText();
            String password = passwordField.getText();
        });
        Hyperlink createAccountLink = new Hyperlink("Create account");
        createAccountLink.setOnAction(e -> {
            // TODO implement logic
        });
        Hyperlink forgotPassLink = new Hyperlink("Forgot password");
        forgotPassLink.setOnAction(e -> {
            // TODO implement logic
        });
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameField,
                passwordLabel, passwordField, loginButton, createAccountLink,
                forgotPassLink, errorLabel);

        vbox.setPadding(new Insets(20, 20, 20, 20));

        return new Scene(vbox);
    }
}
