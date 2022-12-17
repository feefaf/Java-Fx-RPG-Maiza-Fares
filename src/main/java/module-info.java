module com.example.javafxrpgmaizafares {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxrpgmaizafares to javafx.fxml;
    exports com.example.javafxrpgmaizafares;
}