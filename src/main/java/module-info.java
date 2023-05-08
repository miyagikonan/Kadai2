module com.example.kadai2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kadai2 to javafx.fxml;
    exports com.example.kadai2;
}