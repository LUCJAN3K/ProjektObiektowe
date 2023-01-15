package com.example.demo;
import java.util.Timer;
import java.util.TimerTask;

public class Worker {
    public int salary = 100;
    protected String name = "Magda Gessler";
    public int level = 1;
    public int experience = 0;
    public int earnings = 80;
    public int Work(){
       // Timer timer = new Timer();
       // timer.scheduleAtFixedRate(new TimerTask() {
        //    @Override
         //   public void run() {
                return (earnings-salary);
          //  }
      //  },0,1000);

    }
    public void Train(){
        experience++;
        switch(experience){
            case 10:
                level = 2;
                earnings *= 2;
                salary += 50;
                break;
            case 30:
                level = 3;
                earnings *= 3;
                salary += 100;
                break;
        }
    }

}
