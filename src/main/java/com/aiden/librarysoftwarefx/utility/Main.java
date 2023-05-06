package com.aiden.librarysoftwarefx.utility;

import com.aiden.librarysoftwarefx.managers.FileManager;
import com.aiden.librarysoftwarefx.managers.SceneManager;
import com.aiden.librarysoftwarefx.scenes.AddBookScene;
import com.aiden.librarysoftwarefx.scenes.CreateAccountScene;
import com.aiden.librarysoftwarefx.scenes.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    // Save file names for public location

    @Override
    public void start(Stage stage) throws URISyntaxException, IOException{

        FileManager.startup();

        // Create all scene objects, add them to SceneManager
        SceneManager.addScene(LoginScene.getName(), LoginScene.getScene());
        SceneManager.addScene(CreateAccountScene.getName(), CreateAccountScene.getScene());
        SceneManager.addScene(AddBookScene.getName(), AddBookScene.getScene());

        // Create primary stage, configure it, and add it to SceneManager
        Stage primaryStage = new Stage();
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        SceneManager.setStage(primaryStage);

        // Switch to Login page to start
        SceneManager.switchToScene(LoginScene.getName());
    }

    public static void main(String[] args)  {
        launch(args);
    }
}