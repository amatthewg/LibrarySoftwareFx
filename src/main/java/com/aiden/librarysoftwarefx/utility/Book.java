package com.aiden.librarysoftwarefx.utility;

public class Book {
    private String title;
    private String isbn = "NONE";
    private String author = "";
    private String publishDate = "NONE";
    private int quantity = 1;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public Book(String csvInput) {
        String[] fields = csvInput.split(",");
        this.title = fields[0];
        this.isbn = fields[1];
        this.author = fields[2];
        this.publishDate = fields[3];
        this.quantity = Integer.parseInt(fields[4]);
    }
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return this.title; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getIsbn() { return this.isbn; }
    public void setAuthor(String author) { this.author = author; }
    public String getAuthor() { return this.author; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }
    public String getPublishDate() { return this.publishDate; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return this.quantity; }

    public String toCsv() {
        return this.title + "," +
                this.isbn + "," +
                this.author + "," +
                this.publishDate + "," +
                this.quantity;
    }

}
