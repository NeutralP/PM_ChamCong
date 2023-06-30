module com.example.pm_chamcong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.hustsoict.pm_chamcong to javafx.fxml;
    exports com.hustsoict.pm_chamcong;
    exports com.hustsoict.pm_chamcong.controllers;
    opens com.hustsoict.pm_chamcong.controllers to javafx.fxml;
}