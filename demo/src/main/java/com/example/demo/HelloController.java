package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label text;

    @FXML
    protected void onHelloButtonClick() {
        text.setText("Not Implemented Yet :(");
    }
    @FXML
    protected void exit(){
        Platform.exit();
    }
}