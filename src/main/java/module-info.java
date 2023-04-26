module com.example.librarysoftwarefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.librarysoftwarefx to javafx.fxml;
    exports com.example.librarysoftwarefx;
}