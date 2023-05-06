package com.aiden.librarysoftwarefx.scenes;

import com.aiden.librarysoftwarefx.DAL.BookDAL;
import com.aiden.librarysoftwarefx.utility.Book;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.aiden.librarysoftwarefx.DAL.UserDAL;
import com.aiden.librarysoftwarefx.utility.User;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.Optional;

import java.time.LocalDate;

public class AddBookScene { // Scene to add book to library's collection

    public static String getName() { return "add_book"; }

    public static Scene getScene() {
        // Add new book scene
        Label titleLabel = new Label("Add new book");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label bookTitleLabel = new Label("Title (required)");
        TextField bookTitleField = new TextField();
        bookTitleField.setMaxWidth(500);

        Label authorLabel = new Label("Author (required)");
        TextField authorField = new TextField();
        authorField.setMaxWidth(500);

        Label isbnLabel = new Label("ISBN");
        TextField isbnField = new TextField();
        isbnField.setMaxWidth(500);

        Label publishDateLabel = new Label("Publish date");
        DatePicker datePicker = new DatePicker(LocalDate.now());

        Label quantityLabel = new Label("Quantity");
        Spinner<Integer> quantitySpinner = new Spinner<>(0, 1000, 0, 1);
        quantitySpinner.setEditable(true);
        quantitySpinner.getValueFactory().setValue(1);
        // Create a TextFormatter to filter the user input on the spinner
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
                change -> {
                    if (change.getControlNewText().matches("\\d*")) {
                        return change;
                    } else {
                        return null;
                    }
                });

        // Set the TextFormatter on the Spinner's editor
        quantitySpinner.getEditor().setTextFormatter(textFormatter);


        Button addBookButton = new Button("Add book");
        Button goBackButton = new Button("Go back");

        Alert missingFieldAlert = new Alert(AlertType.WARNING);
        missingFieldAlert.setTitle("Missing information");

        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Book successfully added");

        Alert invalidFieldAlert = new Alert(AlertType.WARNING);
        invalidFieldAlert.setTitle("Invalid field");

        Alert bookExistsAlert = new Alert(AlertType.CONFIRMATION);
        bookExistsAlert.setTitle("Book may already exist");

        addBookButton.setOnAction(e -> {
            String title = bookTitleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            LocalDate publishDate = datePicker.getValue();
            int quantity = quantitySpinner.getValue();

            // Check for all required fields
            if(title.isEmpty()) {
                missingFieldAlert.setContentText("Title field is missing.");
                missingFieldAlert.showAndWait();
                return;
            }
            if(author.isEmpty()) {
                missingFieldAlert.setContentText("Author field is missing.");
                missingFieldAlert.showAndWait();
                return;
            }
            // Check if isbn is only numbers
            if(!isbn.matches("\\d*")) {
                invalidFieldAlert.setContentText("ISBN field must only contain numbers.");
                invalidFieldAlert.showAndWait();
                return;
            }

            // Check if a book of that title or isbn already exists
            for(Book book : BookDAL.getAllBooks()) {
                if(book.getTitle().equalsIgnoreCase(title)) {
                    bookExistsAlert.setContentText("A book with that title has already been saved. Continue?");
                    Optional<ButtonType> result = bookExistsAlert.showAndWait();
                    // User chooses cancel
                    if(result.isEmpty() || result.get() == ButtonType.CANCEL) return;
                    // Otherwise, proceed
                }
                if(book.getIsbn().matches(isbn)) {
                    bookExistsAlert.setContentText("A book with that ISBN has already been saved. Continue?");
                    Optional<ButtonType> result = bookExistsAlert.showAndWait();
                    // User chooses cancel
                    if(result.isEmpty() || result.get() == ButtonType.CANCEL) return;
                    // Otherwise, proceed
                }
            }
            // All checks passed, create the book
            Book book = new Book(title, author, publishDate);
            book.setIsbn(isbn);
            BookDAL.saveNewBook(book);

            // Book added, display confirmation
            successAlert.setContentText("Book '" + title + "' successfully added.");

            // Clear the inputs
            bookTitleField.clear();
            authorField.clear();
            isbnField.clear();
            quantitySpinner.getValueFactory().setValue(1);

            // Finished
        });

        goBackButton.setOnAction(e -> {
            // TODO scene manager switch to Main Menu
        });

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));


        addBookButton.setAlignment(Pos.CENTER_LEFT);

        vbox.getChildren().addAll(titleLabel, bookTitleLabel, bookTitleField, authorLabel,
                authorField, isbnLabel, isbnField, publishDateLabel, datePicker, quantityLabel,
                quantitySpinner, addBookButton, goBackButton);

        return new Scene(vbox);
    }


}
