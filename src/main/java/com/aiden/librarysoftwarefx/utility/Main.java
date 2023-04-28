package com.aiden.librarysoftwarefx.utility;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {
    private static FileManager loginFileManager = new FileManager("logins.txt");
    private static FileManager bookDataFileManager = new FileManager("book_data.ser");

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        // Save file startup
        if(!loginFileManager.fileExists()) {
            loginFileManager.generateFile();
        }
        if(!bookDataFileManager.fileExists()) {
            bookDataFileManager.generateFile();
        }
        launch(args);


    }
}