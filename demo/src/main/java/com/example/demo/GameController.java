package com.example.demo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.time.LocalTime;
import java.util.TimerTask;
public class GameController extends HelloController{
    public int money = 0;
    public Label counter;
    public Label t;

    public Pane hiringOptionsPanel;
    public Pane workerPane;
    public Button hireButton;
    public Label workerName;
    public Label workerLevel;
    public Label workerEarnings;
    public Label workerSalary;
    public Label workerExp;
    public Label finderSalary;
    public Pane foundWorkersPane;
    public Boolean hiredWorker = false;
    public List<Worker> hiredWorkers = new ArrayList<Worker>();
    public Worker[] temporaryWorkers = {new Worker(100), new Worker(100), new Worker(100)};
    public int hiredWorkerid;
    public Slider finderSalarySlider;
    @FXML
    public void initialize(){
        finderSalary.textProperty().bind(Bindings.format("%.0f $", finderSalarySlider.valueProperty()));
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){Platform.runLater(()->{
                    t.setText("Time : "+LocalTime.now().toString());});}
        },0,1000);
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Updates every few nanosecs

                Platform.runLater(()->{
                    // Text that needs to be updated
                    counter.setText(String.valueOf(money));
                    if (money >= 10){
                        hireButton.visibleProperty().setValue(true);
                    }

                });
            }
        },0,10);

    }
    public void onPizzaClick(){
        money++;
    }
    public void enableDisableHiringPanel(){
        if(hiringOptionsPanel.isVisible()){
            hiringOptionsPanel.visibleProperty().setValue(false);
        }else{
            hiringOptionsPanel.visibleProperty().setValue(true);
        }
    }

    public void findWorkers() throws IOException, InterruptedException {
        money -= 10;
        int offeredSalary = (int) finderSalarySlider.getValue();
        enableDisableHiringPanel();
        temporaryWorkers[0] = new Worker(offeredSalary);
        temporaryWorkers[1] = new Worker(offeredSalary);
        temporaryWorkers[2] = new Worker(offeredSalary);
        foundWorkersPane.setVisible(true);
        for (int i = 0; i<3; i++){
        Pane pane = (Pane) foundWorkersPane.getChildren().get(i);
        Label name = (Label) pane.getChildren().get(0);
        Label exp = (Label) pane.getChildren().get(1);
        Label eff = (Label) pane.getChildren().get(2);
        name.setText(temporaryWorkers[i].name);
        exp.setText(String.valueOf(temporaryWorkers[i].experience));
        eff.setText(String.valueOf(temporaryWorkers[i].earnings));
        }
    }
    public void hiredWorker() {
        workerPane.setVisible(true);
        workerName.setText(hiredWorkers.get(0).name);
        workerEarnings.setText(String.valueOf(hiredWorkers.get(0).earnings));
        workerExp.setText(String.valueOf(hiredWorkers.get(0).experience));
        workerSalary.setText(String.valueOf(hiredWorkers.get(0).salary));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                money += hiredWorkers.get(0).Work();
            }
        },0,10000);
    }
    public void hiredWorker1(){
        hiredWorkers.add(temporaryWorkers[0]);
        foundWorkersPane.setVisible(false);
        hiredWorker();
    }
    public void hiredWorker2(){
        hiredWorkers.add(temporaryWorkers[1]);
        foundWorkersPane.setVisible(false);
        hiredWorker();
    }
    public void hiredWorker3() {
        hiredWorkers.add(temporaryWorkers[2]);
        foundWorkersPane.setVisible(false);
        hiredWorker();
    }

    // workers panel, 3 workers with random stats, when hired appear on the employees tab
    /* deez nuts
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
    }*/
    public void trainWorkers() {
        hiredWorkers.get(0).Train();
        workerLevel.setText(String.valueOf(hiredWorkers.get(0).level));
        workerEarnings.setText(String.valueOf(hiredWorkers.get(0).earnings));
        workerExp.setText(String.valueOf(hiredWorkers.get(0).experience));
        workerSalary.setText(String.valueOf(hiredWorkers.get(0).salary));
    }
}
//make employees random
//add trainers managers etc
// other things idk


