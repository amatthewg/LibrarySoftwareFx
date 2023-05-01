package com.aiden.librarysoftwarefx.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserDAL { // Data access layer for User objects
    private static File saveFile;


    public static List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(Main.USERS_FILE_NAME))) {
            String line;
            while((line = br.readLine()) != null && !line.trim().isEmpty()) result.add(new User(line));
        } catch(Exception e) {
            error();
        }
        return result;
    }
    public static void saveNewUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        try(FileWriter writer = new FileWriter(Main.USERS_FILE_NAME, false)){
            for(User u : users) writer.write(u.toCsv() + "\n");
        } catch(Exception e) {
            error();
        }
    }
    private static void error() {
        Alert fileAlert = new Alert(AlertType.ERROR);
        fileAlert.setTitle("Fatal error");
        fileAlert.setContentText("A fatal error has occurred while trying to access a data file." +
                " Please restart the program.");
        fileAlert.showAndWait();
        System.exit(0);
    }
}
