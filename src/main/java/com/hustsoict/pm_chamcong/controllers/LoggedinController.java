package com.hustsoict.pm_chamcong.controllers;


import com.hustsoict.pm_chamcong.HelloApplication;
import com.hustsoict.pm_chamcong.utils.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedinController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "/fxml/hello-view.fxml", "Log in",null, null);
            }
        });
    }

    public void setUserInformation(String DBusername, String DBrole) {
        username.setText(DBusername);
        label_welcome.setText("Welcome " + DBrole + "!");
    }
}
