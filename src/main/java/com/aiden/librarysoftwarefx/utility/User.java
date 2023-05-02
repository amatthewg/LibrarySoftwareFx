package com.aiden.librarysoftwarefx.utility;
import java.util.*;
import java.util.stream.Collectors;

public class User  {

    private int userID;
    private String firstName;
    private String middleName = "";
    private String lastName;

    private static int userIDCount;

    private String registrationDate;

    private double balanceDue;

    private String email = "";
    private String phoneNumber = "";
    private List<LibraryCharge> listOfAllCharges;

    // This method is for creating user objects from the "register new user" page
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userIDCount++;
        this.registrationDate = DateManager.dateToString(new Date());
        this.balanceDue = 0;
        this.listOfAllCharges = new ArrayList<>();
    }

    public User(String csvInput) {
        String nonCharges = csvInput.substring(0, csvInput.indexOf(",("));
        String[] fields = nonCharges.split(",");
        this.userID = Integer.parseInt(fields[0]);
        this.firstName = fields[1];
        this.middleName = fields[2];
        this.lastName = fields[3];
        this.registrationDate = fields[4];
        this.balanceDue = Double.parseDouble(fields[5]);
        this.email = fields[6];
        this.phoneNumber = fields[7];
        this.listOfAllCharges = new ArrayList<>();

        String[] chargeCsvs = csvInput.substring(
                csvInput.indexOf(",(") + 1, csvInput.length() - 1)
                .split(",");
        for(String charge : chargeCsvs) this.listOfAllCharges.add(new LibraryCharge(charge));

    }
    public void setFirstName(String name) { this.firstName = name; }
    public String getFirstName() { return this.firstName; }

    public void setMiddleName(String input) { this.middleName = input; }
    public String getMiddleName() { return this.middleName; }
    public void setLastName(String name) { this.lastName = name; }
    public String getLastName() { return this.lastName; }
    public int getUserID() { return this.userID; }
    public String getRegistrationDate() { return this.registrationDate; }
    public double getBalanceDue() { return this.balanceDue; }
    public List<LibraryCharge> getListOfAllCharges() { return this.listOfAllCharges; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }
    public void setPhoneNumber(String number) { this.phoneNumber = number; }
    public static int getUserIDCount() { return userIDCount; }

    public List<LibraryCharge> getListOfPastCharges() {
        return this.listOfAllCharges.stream()
                .filter(charge -> charge.isPaid())
                .collect(Collectors.toList());

    }
    public List<LibraryCharge> getListOfCurrentCharges() {
        return this.listOfAllCharges.stream()
                .filter(charge -> !charge.isPaid())
                .collect(Collectors.toList());
    }

    // When we need to save a user object to the .csv file, we'll use this method
    public String toCsv() {
        return this.userID + "," +
                this.firstName + "," +
                this.middleName + "," +
                this.lastName + "," +
                this.registrationDate + "," +
                this.balanceDue + "," +
                this.email + "," +
                this.phoneNumber + "," +
                this.chargesToCsv();
    }
    private String chargesToCsv() {
        List<String> result = new ArrayList<>();
        for(LibraryCharge charge : this.listOfAllCharges) { result.add("(" + charge.toCsv() + ")"); }
        return String.join(",", result.toArray(new String[0]));
    }











}
