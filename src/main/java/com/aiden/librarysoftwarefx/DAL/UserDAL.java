package com.aiden.librarysoftwarefx.DAL;

import com.aiden.librarysoftwarefx.managers.DateManager;
import com.aiden.librarysoftwarefx.managers.FileManager;
import com.aiden.librarysoftwarefx.utility.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAL { // Data access layer for User objects

    public static List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FileManager.getUsersFile().getPath()))) {
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
        try(FileWriter writer = new FileWriter(FileManager.getUsersFile().getPath(), false)){
            for(User u : users) writer.write(u.toCsv() + "\n");
        } catch(Exception e) {
            error();
        }
    }
    public static void saveAllUsers(List<User> userList) {
        try(FileWriter writer = new FileWriter(FileManager.getUsersFile().getPath(), false)){
            for(User user : userList) writer.write(user.toCsv() + "\n");
        } catch(Exception e) {
            error();
        }
    }
    public static User getUserByID(int id) {
        for(User user : getAllUsers()) {
            if(user.getUserID() == id) return user;
        }
        return null;
    }
    public static List<User> getUsersByName(String search) {
        search = search.toLowerCase();
        List<User> result = new ArrayList<>();
        for(User user : getAllUsers()) {
            if(user.getFirstName().toLowerCase().contains(search) ||
            user.getMiddleName().toLowerCase().contains(search) ||
            user.getLastName().toLowerCase().contains(search)) {
                result.add(user);
            }
        }
        return result;
    }
    public static List<User> getUsersByRegistrationDate(LocalDate date1, LocalDate date2) throws ParseException {
        List<User> result = new ArrayList<>();
        for(User user : getAllUsers()) {
            LocalDate userDate = user.getRegistrationDate();
            if(userDate.compareTo(date1) >= 0 &&
            userDate.compareTo(date2) <= 0) {
                result.add(user);
            }
        }

        return result;
    }
    public static boolean deleteUser(User user) {
        List<User> allUsers = getAllUsers();
        boolean bool = allUsers.remove(user);
        saveAllUsers(allUsers);
        return bool;
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
