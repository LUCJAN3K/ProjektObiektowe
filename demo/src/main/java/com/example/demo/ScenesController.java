package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;

public class ScenesController {

    @FXML
    private Label text;
    private Stage stage;
    private Scene scene;
    public Parent root;
    @FXML
    protected void exit(){
        Platform.exit(); System.exit(0);
    }
    public void SwitchSceneGetData(ActionEvent event, Parent root) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600, 450);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchSceneNewGame(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("game.fxml"));
        SwitchSceneGetData(event, root);
    }
    public void SwitchSceneContinue(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("savescreen.fxml"));
        SwitchSceneGetData(event, root);
    }

    public void SwitchSceneMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainmenu.fxml"));
        SwitchSceneGetData(event, root);
    }
    public void SwitchSceneRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registration.fxml"));
        SwitchSceneGetData(event, root);
    }
    public void SwitchSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        SwitchSceneGetData(event, root);
    }

}