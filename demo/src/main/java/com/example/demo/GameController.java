package com.example.demo;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class GameController extends HelloController{
    public int money = 0;
    public Pane hiringOptionsPanel, workerPane, foundWorkersPane;
    public Button hireButton;
    public Label finderSalary,finderCost, t, counter, noMoneyError, tooManyWorkersLabel;
    public List<Worker> hiredWorkers = new ArrayList<Worker>();
    public Worker[] temporaryWorkers = {new Worker(100), new Worker(100), new Worker(100)};
    public Slider finderSalarySlider;
    public VBox workersBox;
    Timer worktimer = new Timer();
    TimerTask worktask = new TimerTask(){
        @Override
        public void run(){Platform.runLater(()->{
            hiredWorkers.forEach(worker -> money += worker.Work());
            hiredWorkers.forEach(worker -> worker.Train());});}
    };
    @FXML
    public void initialize(){
        finderSalary.textProperty().bind(Bindings.format("%.0f $", finderSalarySlider.valueProperty()));
        finderCost.textProperty().bind(Bindings.format("%.0f $", finderSalarySlider.valueProperty().divide(2)));
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        worktimer.scheduleAtFixedRate(worktask,0,10000);
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
                    if (money >= 50){
                        hireButton.visibleProperty().setValue(true);
                    }
                    for (int i = 0; i< hiredWorkers.size();i++) {
                        Pane pane = (Pane) workersBox.getChildren().get(i);
                        pane.setVisible(true);
                        List<Label> labels = new ArrayList<>();
                        for(int j=0;j<pane.getChildren().size()-1;j++){
                            labels.add((Label) pane.getChildren().get(j));
                        }
                        labels.get(1).setText(hiredWorkers.get(i).name);
                        labels.get(2).setText(String.valueOf(hiredWorkers.get(i).earnings));
                        labels.get(0).setText(String.valueOf(hiredWorkers.get(i).level));
                        labels.get(3).setText(String.valueOf(hiredWorkers.get(i).experience));
                        labels.get(4).setText(String.valueOf(hiredWorkers.get(i).salary));
                    }

                });
            }
        },0,10);

    }
    public void onPizzaClick(){
        money++;
    }
    public void enableDisableHiringPanel(){
        if(hiredWorkers.size()<7){
        if(hiringOptionsPanel.isVisible()){
            hiringOptionsPanel.visibleProperty().setValue(false);
        }else{
            hiringOptionsPanel.visibleProperty().setValue(true);
        }}else{
            tooManyWorkersLabel.setVisible(true);
            Timer timer = new Timer();
            TimerTask tooManyWorkersIssue = new TimerTask() {
                @Override
                public void run() {
                    tooManyWorkersLabel.setVisible(false);
                }
            };
            timer.schedule(tooManyWorkersIssue,1500);
        }
    }

    public void findWorkers() throws IOException, InterruptedException {
        if ((int) (finderSalarySlider.getValue())/2 <= money){
        money -= (int) (finderSalarySlider.getValue())/2;
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
        }}else{
            noMoneyError.setVisible(true);
            Timer timer = new Timer();
            TimerTask moneyIssues = new TimerTask() {
                @Override
                public void run() {
                    noMoneyError.setVisible(false);
                }
            };
            timer.schedule(moneyIssues,1500);
        }
    }
    public void hiredWorker1(){
        if(hiredWorkers.size()<7){
        hiredWorkers.add(temporaryWorkers[0]);
        foundWorkersPane.getChildren().get(0).setVisible(false);
        workersBox.setVisible(true);}
        else {
            foundWorkersPane.setVisible(false);
        }
    }
    public void hiredWorker2(){
        if(hiredWorkers.size()<7){
        hiredWorkers.add(temporaryWorkers[1]);
        foundWorkersPane.getChildren().get(1).setVisible(false);
        workersBox.setVisible(true);}else {
            foundWorkersPane.setVisible(false);
        }
    }
    public void hiredWorker3() {
        if(hiredWorkers.size()<7){
        hiredWorkers.add(temporaryWorkers[2]);
        foundWorkersPane.getChildren().get(2).setVisible(false);
        workersBox.setVisible(true);}else {
             foundWorkersPane.setVisible(false);
        }
    }
    public void cancelHiring(){
        foundWorkersPane.getChildren().get(0).setVisible(true);
        foundWorkersPane.getChildren().get(1).setVisible(true);
        foundWorkersPane.getChildren().get(2).setVisible(true);
        foundWorkersPane.setVisible(false);
    }
    public void firedWorker(int i){

        hiredWorkers.remove(i);
        workersBox.getChildren().get(hiredWorkers.size()).setVisible(false);
    }
    public void firedWorker0(){
        firedWorker(0);
    }
    public void firedWorker1(){
        firedWorker(1);
    }
    public void firedWorker2(){
        firedWorker(2);
    }
    public void firedWorker3(){
        firedWorker(3);
    }
    public void firedWorker4(){
        firedWorker(4);
    }
    public void firedWorker5(){
        firedWorker(5);
    }public void firedWorker6(){
        firedWorker(6);
    }

}
//make employees random
//add trainers managers etc
// other things idk


