package com.aiden.librarysoftwarefx.scenes;

import com.aiden.librarysoftwarefx.DAL.BookDAL;
import com.aiden.librarysoftwarefx.utility.Book;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ViewAllBooksScene {
    public static String getName() { return "view_all_books"; }

    public static Scene getScene() {
        // Create ListView object to store all books
        ListView<String> listView = new ListView<>();

        // Add all books with formatted information
        for(Book book : BookDAL.getAllBooks()) {
            // Use %-##s to left-justfiy Strings
            String formattedString = String.format("%-30s %-30s %-10s %-13s",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublishDate().toString(),
                    book.getIsbn().isEmpty() ? "-" : book.getIsbn());
            // Add to ListView
            listView.getItems().add(formattedString);

        }
        // Scroll pane object
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setPrefWidth(750);
        scrollPane.setPrefHeight(250);

        Button goBackButton = new Button("Go back");
        goBackButton.setOnAction(e -> {
            // TODO scene manager switch to main menu
        });

        // Create/configure VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(scrollPane, goBackButton);

        return new Scene(vbox);
    }
}
