package com.aiden.librarysoftwarefx.utility;

import com.aiden.librarysoftwarefx.scenes.LoginScene;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    // Save file names for public location
    public final static String
            BOOK_FILE_NAME = "bookData.csv",
            USERS_FILE_NAME = "userData.csv",
            LOGINS_FILE_NAME = "logins.csv";
    @Override
    public void start(Stage stage) throws URISyntaxException, IOException{
        // Create alert object for use if files fail to load
        Alert fileAlert = new Alert(AlertType.ERROR);
        fileAlert.setTitle("Fatal error");
        fileAlert.setHeaderText("A fatal error has occurred.");
        fileAlert.setContentText("One or more data files has failed to load properly. " +
                "Please restart the program.");



        // We need to ensure that the save files will be generated in the same
        // directory as the .jar file, so we get the path of the .jar
        String jarPath = Main.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        // Get the path of the directory that the .jar is in
        String jarDirectory = new File(jarPath).getParent();

        // Create the file objects to see if they exist
        File bookFile = new File(jarDirectory + File.separator + BOOK_FILE_NAME);
        File usersFile = new File(jarDirectory + File.separator + USERS_FILE_NAME);
        File loginsFile = new File(jarDirectory + File.separator + LOGINS_FILE_NAME);

        if(!bookFile.exists()) {
            boolean x = bookFile.createNewFile();
            if(!x) {
                fileAlert.showAndWait();
                System.exit(0);
            }
        }
        if(!usersFile.exists()) {
            boolean x = usersFile.createNewFile();
            if(!x) {
                fileAlert.showAndWait();
                System.exit(0);
            }
        }
        if(!loginsFile.exists()) {
            boolean x = loginsFile.createNewFile();
            if(!x) {
                fileAlert.showAndWait();
                System.exit(0);
            }
        }
        // Ready to rock and roll. Get all our scene objects, and
        // add them to our SceneManager
        SceneManager.addScene("login_page", LoginScene.getScene());
    }

    public static void main(String[] args)  {
        launch(args);
    }
}