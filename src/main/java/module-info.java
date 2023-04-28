module com.example.librarysoftwarefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aiden.librarysoftwarefx to javafx.fxml;

    exports com.aiden.librarysoftwarefx.utility;
    opens com.aiden.librarysoftwarefx.utility to javafx.fxml;
}