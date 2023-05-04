package com.aiden.librarysoftwarefx.managers;

import com.aiden.librarysoftwarefx.utility.Main;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;


public class FileManager { // Class to manage our data files


    // File names
    private final static String
            BOOK_FILE_NAME = "bookData.csv",
            USERS_FILE_NAME = "userData.csv",
            LOGINS_FILE_NAME = "logins.csv";

    // We need to ensure that the save files will be generated in the same
    // directory as the .jar file, so we get the path of the .jar
    private static String jarPath;

    static {
        try {
            jarPath = Main.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (URISyntaxException e) {
            fileError();
        }
    }

    // Get the path of the directory that the .jar is in
    private static String jarDirectory = new File(jarPath).getParent();

    // File objects
    private static File bookFile = new File(jarDirectory + File.separator + BOOK_FILE_NAME);
    private static File usersFile = new File(jarDirectory + File.separator + USERS_FILE_NAME);
    private static File loginsFile = new File(jarDirectory + File.separator + LOGINS_FILE_NAME);

    public static File getBookFile() { return bookFile; }
    public static File getUsersFile() { return usersFile; }
    public static File getLoginsFile() { return loginsFile; }


    public static void startup() throws IOException { // Startup functionality for all files
        if(!bookFile.exists()) {
            boolean x = bookFile.createNewFile();
            if(!x) fileError();
        }
        if(!usersFile.exists()) {
            boolean x = usersFile.createNewFile();
            if(!x) fileError();
        }
        if(!loginsFile.exists()) {
            boolean x = loginsFile.createNewFile();
            if(!x) fileError();
        }
    }
    public static void fileError() {
        Alert fileAlert = new Alert(Alert.AlertType.ERROR);
        fileAlert.setTitle("Fatal error");
        fileAlert.setContentText("A fatal error has occurred while trying to access a data file." +
                " Please restart the program.");
        fileAlert.showAndWait();
        System.exit(0);
    }
}
