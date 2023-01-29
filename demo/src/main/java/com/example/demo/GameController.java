 package com.example.demo;
        import javafx.application.Platform;
        import javafx.beans.binding.Bindings;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.util.*;
        import java.time.LocalTime;

 public class GameController extends ScenesController{
    public int money = 90, taxes = 10, months = 0, doom = 0,weeks =0;
    public AnchorPane anchorPane;
    private Scene scene;
    public Parent root;
    public Pane hiringOptionsPanel, workerPane, foundWorkersPane,pizzaPane, addPizzaPane;
    public Button hireButton;
    public Label finderSalary,finderCost, moneyCounter, noMoneyError, tooManyWorkersLabel,monthsCounter, ingredientsLabel,taxesCounter,tooManyPizzas,weeksCounter;
    public List<Worker> hiredWorkers = new ArrayList<Worker>();
    public Worker[] temporaryWorkers = {new Worker(100), new Worker(100), new Worker(100)};
    public Slider finderSalarySlider;
    public ChoiceBox<String> ingredientsChoiceBox;
    public VBox workersBox,pizzaBox;
    Timer timer1 = new Timer();
    Timer timer2 = new Timer();
    Timer worktimer = new Timer();
    Ingredient[] ingredients = {
            new Ingredient("",0,1,0),
            new Ingredient("Onion",15,1,5),
            new Ingredient("Salami",30,1,20),
            new Ingredient("Jalapeno",50,1,30),
            new Ingredient("Parmezan Cheese", 0, 1.1f,30),
            new Ingredient("Corn",20,1,15)
    };
    public ArrayList<Pizza> pizzasList = new ArrayList<Pizza>();
    public HashMap<String, Ingredient> ingredientHashMap = new HashMap<String, Ingredient>();
    @FXML
    public void initialize(){
        finderSalary.textProperty().bind(Bindings.format("%.0f $", finderSalarySlider.valueProperty()));
        finderCost.textProperty().bind(Bindings.format("%.0f $", finderSalarySlider.valueProperty().divide(2)));
        for(int i = 0; i<ingredients.length;i++){
            ingredientHashMap.put(ingredients[i].name, ingredients[i]);
        }
        worktimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            //monthly events
            public void run(){Platform.runLater(()->{
                hiredWorkers.forEach(worker -> worker.Train());
                hiredWorkers.forEach(worker -> worker.regainPatience());
                money -= taxes;
                months++;
                taxes = (months*months)*10 ;
                taxesCounter.setText(String.valueOf(taxes));
                if(money<0) {
                    doom++;
                    if (doom>=5){
                        try {
                            GameOver();
                            worktimer.cancel();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }else {
                    doom=0;
                }
                ;});}},0,20000);
        //happends 10 times in a month
        /*timer1.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){Platform.runLater(()->{
                hiredWorkers.forEach(worker -> worker.bumAround())
                ;});}
        },20,1000);*/
        Timer timer3 = new Timer();
        //happends 4 times in a month (weekly)
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {Platform.runLater(()-> {
                hiredWorkers.forEach(worker -> money += worker.Work());
                hiredWorkers.forEach(worker -> worker.bumAround());
                weeks++;
            ;});}
        },0,5000);
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{


                    // stuff that needs to be updated often

                    monthsCounter.setText(String.valueOf(months));
                    moneyCounter.setText(String.valueOf(money));
                    weeksCounter.setText(String.valueOf(weeks));
                    if (money >= 50){
                        hireButton.visibleProperty().setValue(true);
                    }
                    for (int i = 0; i< hiredWorkers.size();i++) {
                        Pane pane = (Pane) workersBox.getChildren().get(i);
                        pane.setVisible(true);
                        List<Label> labels = new ArrayList<>();
                        for(int j=0;j<pane.getChildren().size()-2;j++){
                            labels.add((Label) pane.getChildren().get(j));
                        }
                        labels.get(1).setText(hiredWorkers.get(i).name);
                        labels.get(2).setText(String.valueOf(hiredWorkers.get(i).earnings));
                        labels.get(0).setText(String.valueOf(hiredWorkers.get(i).level));
                        labels.get(3).setText(String.valueOf(hiredWorkers.get(i).experience));
                        labels.get(4).setText(String.valueOf(hiredWorkers.get(i).salary));
                        if(hiredWorkers.get(i).bummingAround){
                            pane.setStyle("-fx-background-color: #FFAAAA");
                        }else{
                            pane.setStyle("-fx-background-color: #FFFFFF");
                        }
                    }
                    for (int i=0;i<pizzasList.size();i++){
                        Pane pane = (Pane) pizzaBox.getChildren().get(i);
                        pane.setVisible(true);
                        List<Label> labels = new ArrayList<Label>();
                        for(int j=0;j<pane.getChildren().size()-4;j++){
                            labels.add((Label) pane.getChildren().get(j));
                        }
                        labels.get(0).setText(pizzasList.get(i).pizzaname);
                        labels.get(1).setText(pizzasList.get(i).IngredientsList.get(0).name+", "+
                                pizzasList.get(i).IngredientsList.get(1).name+", "+pizzasList.get(i).IngredientsList.get(2).name+", "+
                                pizzasList.get(i).IngredientsList.get(3).name+", "+pizzasList.get(i).IngredientsList.get(4).name
                        );
                        labels.get(2).setText(String.valueOf(pizzasList.get(i).bonus));
                        labels.get(3).setText(String.valueOf(pizzasList.get(i).multiplier));
                        labels.get(4).setText(String.valueOf(pizzasList.get(i).monthlyCost));
                    }

                });
            }
        },0,20);

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
    public void addPizzaPaneControl(){

        if(pizzasList.size()>6){
            tooManyPizzas.setVisible(true);
            addPizzaPane.setVisible(false);
        }else {
            List<ChoiceBox<String>> boxes = new ArrayList<>();
            for(int i=0;i<addPizzaPane.getChildren().size()-8;i++){
                boxes.add((ChoiceBox<String>) addPizzaPane.getChildren().get(i));
            }
            if(!addPizzaPane.isVisible()){
                addPizzaPane.setVisible(true);
            boxes.forEach(b ->{
                b.setDisable(false);
                for(int i = 0; i<ingredients.length;i++){
                    b.getItems().add(ingredients[i].name);
                }
                b.setValue("");
                b.setOnAction(this::updateIngredientsChoiceBox);
                b.setDisable(false);
            });

            }else{
                addPizzaPane.setVisible(false);
                boxes.forEach(b->{
                    b.setDisable(false);
                    b.setValue("");
                    b.getItems().removeAll(b.getItems());
                });
            }

        }
    }
    public void updateIngredientsChoiceBox(ActionEvent event){
        //gets box causing the event
        ChoiceBox<String> box = (ChoiceBox<String>) event.getSource();
        //gets its item
        String item = box.getValue();
        //gets its id
        String id = box.getId();
        //list of all boxes that is later filled
        List<ChoiceBox<String>> boxes = new ArrayList<>();
        for(int i =0; i<addPizzaPane.getChildren().size()-8;i++){
            boxes.add((ChoiceBox<String>) addPizzaPane.getChildren().get(i));
        }
        boxes.forEach( b -> {
            if(b.getValue() != item){
                b.getItems().remove(item);

            }

        });
        box.setDisable(true);

        int bonus =0;
        float multiplier = 1;
        int monthlyCost = 0;
        for(int i =0;i<boxes.size();i++)
        {
            if(ingredientHashMap.get(boxes.get(i).getValue()) != null)
            {
            bonus += ingredientHashMap.get(boxes.get(i).getValue()).bonus;
            multiplier += (ingredientHashMap.get(boxes.get(i).getValue()).multiplier-1);
            monthlyCost += ingredientHashMap.get(boxes.get(i).getValue()).monthlyCost;
            }
        }
        List<Label> labels = new ArrayList<Label>();
        for(int i =10; i<addPizzaPane.getChildren().size();i++){
          labels.add((Label) addPizzaPane.getChildren().get(i));
        }
        labels.get(0).setText(String.valueOf(multiplier));
        labels.get(1).setText(String.valueOf(bonus));
        labels.get(2).setText(String.valueOf(monthlyCost));
    }
    public void addPizza(ActionEvent event){
        Button button =(Button) event.getSource();
        Pane pane = (Pane) button.getParent();
        List<ChoiceBox<String>> choices = new ArrayList<>();
        List<Ingredient> chosenIngredients = new ArrayList<>();
        TextField name = (TextField) pane.getChildren().get(6);
        for(int i = 0; i<5;i++){
            choices.add((ChoiceBox<String>) pane.getChildren().get(i));
            chosenIngredients.add(ingredientHashMap.get(choices.get(i).getValue()));
        }
        pizzasList.add(new Pizza(name.getText(),chosenIngredients.get(0),chosenIngredients.get(1),chosenIngredients.get(2),chosenIngredients.get(3),chosenIngredients.get(4)));
        addPizzaPaneControl();
    }
    public void movePizza(int ogSpot, int newSpot){
        Pizza movedPizza = pizzasList.get(ogSpot);
        if(newSpot==pizzasList.size()){
            newSpot=0;
        }
        Pizza oldPizza = pizzasList.get(newSpot);
        pizzasList.set(ogSpot,oldPizza);
        pizzasList.set(newSpot,movedPizza);
    }
    public void movePizza0down(){
        movePizza(0,1);
    }
     public void movePizza1down(){
         movePizza(1,2);
     }
     public void movePizza2down(){
         movePizza(2,3);
     }
     public void movePizza3down(){
         movePizza(3,4);
     }
     public void movePizza4down(){
         movePizza(4,5);
     }
     public void movePizza5down(){
         movePizza(5,6);
     }
     public void movePizza6down(){
         movePizza(6,0);
     }
    public void removePizza(int i){
        pizzasList.remove(i);
        pizzaBox.getChildren().get(pizzasList.size()).setVisible(false);
        tooManyPizzas.setVisible(false);
    }
    public void removePizza0(){
        removePizza(0);
    }
     public void removePizza1(){
         removePizza(1);
     }
     public void removePizza2(){
         removePizza(2);
     }
     public void removePizza3(){
         removePizza(3);
     }
     public void removePizza4(){
         removePizza(4);
     }
     public void removePizza5(){
         removePizza(5);
     }
     public void removePizza6(){
         removePizza(6);
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
    public void reprimend(int i){
        if(hiredWorkers.get(i).bummingAround){
            hiredWorkers.get(i).bummingAround = false;
        }else{
            hiredWorkers.get(i).earnings += 10;
            hiredWorkers.get(i).stress += 1;
            if(hiredWorkers.get(i).rng(0,100) < hiredWorkers.get(i).stress*10 + 5){
                firedWorker(i);
            }
        }
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
    public void reprimendWorker0(){
        reprimend(0);
    }
    public void reprimendWorker1(){
        reprimend(1);
    }
    public void reprimendWorker2(){
        reprimend(2);
    }
    public void reprimendWorker3(){
        reprimend(3);
    }
    public void reprimendWorker4(){
        reprimend(4);
    }
    public void reprimendWorker5(){
        reprimend(5);
    }
    public void reprimendWorker6(){
        reprimend(6);
    }
    public void GameOver() throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gameover.fxml"));
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        scene = new Scene(root,640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
//make employees random
//add trainers managers etc
// other things idk

