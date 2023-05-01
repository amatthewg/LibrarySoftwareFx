package com.aiden.librarysoftwarefx.utility;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDAL { // Data access layer for Book objects

    public static List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(Main.BOOK_FILE_NAME))) {
            String line;
            while((line = br.readLine()) != null && !line.trim().isEmpty()) result.add(new Book(line));
        } catch(Exception e) {
            error();
        }
        return result;
    }
    public static void saveNewBook(Book book) {
        List<Book> books = getAllBooks();
        books.add(book);
        try(FileWriter writer = new FileWriter(Main.BOOK_FILE_NAME, false)) {
            for(Book b : books) writer.write(b.toCsv() + "\n");
        } catch(Exception e) {
            error();
        }
    }
    public static void saveAllBooks(List<Book> bookList) {
        try(FileWriter writer = new FileWriter(Main.BOOK_FILE_NAME, false)) {
            for(Book b : bookList) writer.write(b.toCsv() + "\n");
        } catch(Exception e) {
            error();
        }
    }
    public static List<Book> getBooksByTitle(String search) {
        search = search.toLowerCase();
        List<Book> result = new ArrayList<>();
        for(Book book : getAllBooks()) {
            if(book.getTitle().toLowerCase().contains(search)) result.add(book);
        }
        return result;
    }
    public static List<Book> getBooksByISBN(String isbn) {
        List<Book> result = new ArrayList<>();
        for(Book b : getAllBooks()) {
            if(b.getIsbn().matches(isbn)) result.add(b);
        }
        return result;
    }
    public static List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for(Book book : getAllBooks()) {
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase())) result.add(book);
        }
        return result;
    }
    public static List<Book> getBooksByRegistrationDate(Date date1, Date date2) {
        // TODO implement DateManager for this purpose
        return null;
    }
    public static boolean deleteBook(Book book) {
        List<Book> allBooks = getAllBooks();
        boolean bool = allBooks.remove(book);
        saveAllBooks(allBooks);
        return bool;
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
