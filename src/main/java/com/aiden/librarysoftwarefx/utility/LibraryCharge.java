package com.aiden.librarysoftwarefx.utility;

import java.text.DateFormat;
import java.util.Date;

public class LibraryCharge {

    private int userID;
    private double amount;
    private String dateIncurred;
    private String chargeReason;
    private boolean paid;

    public LibraryCharge(double amount, int userID, String reason) {
        this.amount = amount;
        this.userID = userID;
        this.chargeReason = reason;
        this.dateIncurred = DateConverter.dateToString(new Date());
        this.paid = false;
    }
    public LibraryCharge(String csvInput) {
        String[] values = csvInput.split(",");
        this.userID = Integer.parseInt(values[0]);
        this.amount = Double.parseDouble(values[1]);
        this.dateIncurred = values[2];
        this.chargeReason = values[3];
        this.paid = Boolean.parseBoolean(values[4]);
    }
    public double getAmount() { return this.amount; }
    public int getUserID() { return this.userID; }
    public String getDateIncurred() { return this.dateIncurred; }
    public String getChargeReason() { return this.chargeReason; }
    public boolean isPaid() { return this.paid; }
    public void setPaid(boolean input) { this.paid = input; }

    public String toCsv() {
        return this.userID + "," +
                this.amount + "," +
                this.dateIncurred + "," +
                this.chargeReason + "," +
                this.paid;

    }
}

