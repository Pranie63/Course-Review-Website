module com.example.coursereviewhw7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coursereviewhw7 to javafx.fxml;
    exports com.example.coursereviewhw7;
}