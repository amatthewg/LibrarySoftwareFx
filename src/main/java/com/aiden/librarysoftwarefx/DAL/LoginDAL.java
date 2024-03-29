package com.aiden.librarysoftwarefx.DAL;

import com.aiden.librarysoftwarefx.managers.FileManager;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAL { // Data access layer for user logins

    public static boolean usernameExists(String username) {
        try(BufferedReader br = new BufferedReader(new FileReader(FileManager.getLoginsFile().getPath()))) {
            String line;
            while((line = br.readLine()) != null && !line.trim().isEmpty()) {
                if(line.split(",")[0].equalsIgnoreCase(username)) return true;
            }
        } catch (Exception e) {
            error();
        }
        return false;
    }
    private static List<String> getAllLogins() throws URISyntaxException {
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FileManager.getLoginsFile().getPath()))) {

            String line;
            while((line = br.readLine()) != null && !line.trim().isEmpty()) {
                result.add(line);
            }
        } catch (Exception e) {

            error();
        }
        return result;
    }
    public static boolean login(String username, String password) throws URISyntaxException {
        for(String login : getAllLogins()) {
            if(login.split(",")[0].matches(username)) {
                if(login.split(",")[1].matches(password)) return true;
            }
        }
        return false;
    }
    private static void saveAllLogins(List<String> list) throws IOException {
        try(FileWriter writer = new FileWriter(FileManager.getLoginsFile().getPath(), false)) {
            for(String login : list) {
                writer.write(login + "\n");
            }
        } catch(Exception e) {
            error();
        }
    }
    public static void createNewAccount(String username, String password) throws IOException, URISyntaxException {
        List<String> logins = getAllLogins();
        logins.add(username + "," + password);
        saveAllLogins(logins);
    }
    private static void error() {
        Alert fileAlert = new Alert(Alert.AlertType.ERROR);
        fileAlert.setTitle("Fatal error");
        fileAlert.setContentText("A fatal error has occurred while trying to access a data file." +
                " Please restart the program.");
        fileAlert.showAndWait();
        System.exit(0);
    }
}
