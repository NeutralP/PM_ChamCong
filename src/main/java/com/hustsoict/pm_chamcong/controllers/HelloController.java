package com.hustsoict.pm_chamcong.controllers;

import com.hustsoict.pm_chamcong.utils.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.logInUser(actionEvent, username.getText(), password.getText());
            }
        });
    }

    public void setUserInformation(String username, String role) {

    }
}
