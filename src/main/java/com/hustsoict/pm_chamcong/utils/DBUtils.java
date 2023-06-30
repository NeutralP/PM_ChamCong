package com.hustsoict.pm_chamcong.utils;

import com.hustsoict.pm_chamcong.HelloApplication;
import com.hustsoict.pm_chamcong.controllers.LoggedinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String role) {
        Parent root = null;

        if (username != null && role != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                URL fxmlUrl = HelloApplication.class.getResource(fxmlFile);
                loader.setLocation(fxmlUrl);
                root = loader.load();
                LoggedinController loggedinController = loader.getController();
                loggedinController.setUserInformation(username, role);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (root != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
        } else {
            System.out.println("Failed to load FXML file: " + fxmlFile);
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            connection = MysqlConnection.getMysqlConnection();
            ps = connection.prepareStatement("SELECT password, role FROM users WHERE userName = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (rs.next()) {
                    String retrievedPassword = rs.getString("password");
                    String retrievedRole = rs.getString("role");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "/fxml/loggedin-view.fxml", "Welcome!", username, retrievedRole);
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
