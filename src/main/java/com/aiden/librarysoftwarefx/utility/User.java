package com.aiden.librarysoftwarefx.utility;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class User implements Serializable {

    private String firstName;
    private String middleName = "";
    private String lastName;
    private int userID;
    private static int userIDCount;

    private Date registrationDate;

    private double balanceDue;
    private List<LibraryCharge> listOfAllCharges;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userIDCount++;
        this.registrationDate = new Date();
        this.balanceDue = 0;
        this.listOfAllCharges = new ArrayList<>();
    }
    public void setMiddleName(String input) { this.middleName = input; }
    public String getMiddleName() { return this.middleName; }
    public int getUserID() { return this.userID; }
    public Date getRegistrationDate() { return this.registrationDate; }
    public double getBalanceDue() { return this.balanceDue; }
    public List<LibraryCharge> getListOfAllCharges() { return this.listOfAllCharges; }

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










}
