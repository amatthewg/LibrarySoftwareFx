module com.example.librarysoftwarefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aiden.librarysoftwarefx to javafx.fxml;

    exports com.aiden.librarysoftwarefx.misc;
    opens com.aiden.librarysoftwarefx.misc to javafx.fxml;
}