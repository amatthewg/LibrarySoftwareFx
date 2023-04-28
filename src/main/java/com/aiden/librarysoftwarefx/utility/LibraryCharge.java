package com.aiden.librarysoftwarefx.utility;

import java.util.Date;

public class LibraryCharge {

    private double amount;
    private int userID;
    private Date dateIncurred;
    private String chargeReason;
    private boolean paid;

    public LibraryCharge(double amount, int userID, String reason) {
        this.amount = amount;
        this.userID = userID;
        this.chargeReason = reason;
        this.dateIncurred = new Date();
        this.paid = false;
    }
    public double getAmount() { return this.amount; }
    public int getUserID() { return this.userID; }
    public Date getDateIncurred() { return this.dateIncurred; }
    public String getChargeReason() { return this.chargeReason; }
    public boolean isPaid() { return this.paid; }
    public void setPaid(boolean input) { this.paid = input; }
}

