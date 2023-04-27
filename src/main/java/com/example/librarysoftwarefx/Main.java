package com.example.librarysoftwarefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {
    private static SaveFile loginFile = new SaveFile("logins.txt");
    private static SaveFile bookDataFile = new SaveFile("book_data.ser");

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        launch(args);
        // Save file startup
        if(!SaveFile.exists()) SaveFile.generateSaveFile();
    }
}