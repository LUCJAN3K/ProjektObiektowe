package com.example.demo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import java.lang.Object;
import java.util.Timer;
import java.time.LocalTime;
import java.util.TimerTask;
import com.example.demo.Worker;
public class GameController extends HelloController{
    public int money = 0;
    public Label counter;
    public Label t;

    public Pane workerPane;
    public Button hireButton;
    public boolean workerHired = false;
    public Label workerName;
    public Label workerLevel;
    public Label workerEarnings;
    public Label workerSalary;
    public Label workerExp;
    public Worker employee1;
    @FXML
    public void initialize(){
        int b = 0;
        int interval = 1000;
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){Platform.runLater(()->{
                    t.setText("Time : "+LocalTime.now().toString());});}
        },b,interval);
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Updates every few nanosecs

                Platform.runLater(()->{
                    // Text that needs to be updated
                    counter.setText(String.valueOf(money));
                    if (workerHired == false && money >= 10){
                        hireButton.visibleProperty().setValue(true);
                    }
                });
            }
        },0,10);

    }
    public void onPizzaClick(){
        money++;
    }
    public void hireWorkers(){
        workerPane.visibleProperty().setValue(true);
        money -= 10;
        hireButton.visibleProperty().setValue(false);
        workerHired = true;
        employee1 = new Worker();
        workerName.setText(employee1.name);
        workerEarnings.setText(String.valueOf(employee1.earnings));
        workerExp.setText(String.valueOf(employee1.experience));
        workerSalary.setText(String.valueOf(employee1.salary));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
           @Override
            public void run() {
               money += employee1.Work();
                }
            },0,10000);
    }
    public void trainWorkers() {
        employee1.Train();
        workerLevel.setText(String.valueOf(employee1.level));
        workerEarnings.setText(String.valueOf(employee1.earnings));
        workerExp.setText(String.valueOf(employee1.experience));
        workerSalary.setText(String.valueOf(employee1.salary));
    }
}
//make employees random
//add trainers managers etc
// other things idk


