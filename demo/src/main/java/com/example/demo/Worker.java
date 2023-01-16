package com.example.demo;
import java.lang.Math;

public class Worker {
    public int rng(int min, int max){return (int) ((Math.random() * (max - min)) + min);}
    public int salary = rng(80,120);
    protected String name = "Magda Gessler";
    public int level = 1;
    public int experience = rng(0,5);
    public int earnings = rng(70,100);
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
                salary += 80;
                break;
        }
    }

}
