package com.example.demo;
import javafx.application.Application;
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

public class GameController extends HelloController{
    public int cookies = 0;
    public Label counter;

    public void onPizzaClick(){
        cookies++;
        counter.setText(Integer.toString(cookies));
    }
}
