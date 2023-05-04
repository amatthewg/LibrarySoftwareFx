package com.aiden.librarysoftwarefx.scenes;

import com.aiden.librarysoftwarefx.DAL.LoginDAL;
import com.aiden.librarysoftwarefx.managers.SceneManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URISyntaxException;

public class LoginScene {
    public static String getName() { return "login"; }
    public static Scene getScene() {
        VBox vbox = new VBox();

        Label titleLabel = new Label();
        titleLabel.setText("Simple Library Management");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

        Label usernameLabel = new Label("username");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(500);

        Label passwordLabel = new Label("password");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(500);

        Button loginButton = new Button("Login");

        Hyperlink createAccountLink = new Hyperlink("Create account");
        createAccountLink.setOnAction(e -> {
            // TODO implement logic
        });
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameField,
                passwordLabel, passwordField, loginButton, createAccountLink,
                errorLabel);

        vbox.setPadding(new Insets(20, 20, 20, 20));

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if(LoginDAL.login(username, password)) {
                    // TODO SceneManager switch to main menu
                } else {
                    try {
                        if (!LoginDAL.login(username, password)) {
                            errorLabel.setText("Invalid username or password");
                        }
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        createAccountLink.setOnAction(e -> {
            SceneManager.switchToScene(CreateAccountScene.getName());
        });
        return new Scene(vbox);
    }
}
