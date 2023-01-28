package com.example.demo;
import java.lang.Math;


public class Worker {
    public int rng(int min, int max){return (int) ((Math.random() * (max - min)) + min);}
    public int salary;
    protected String name = namesReader.readNames();
    public int level = 1;
    public int experience;
    public int earnings;
    public int patience = 10;
    public Boolean bummingAround;
    public int Work(){
        if(!bummingAround) {
            return (earnings - salary);
        }else{
            return (-salary);
        }
    }
    public void regainPatience(){
        if(patience<10 && rng(0,10) > 6){
            patience++;
            earnings -=10;
        }
    }
    public void bumAround(){
        if(rng(0,100) > 95){
            bummingAround = true;
        }
    }

    public void Train(){
        if(rng(0,100) > 60){
        experience++;
        switch(experience) {
            case 10:
                level = 2;
                earnings += 70;
                salary += 50;
                break;
            case 30:
                level = 3;
                earnings += 120;
                salary += 80;
                break;
            case 70:
                level = 4;
                earnings += 180;
                salary += 110;
                break;
            case 150:
                level = 5;
                earnings += 300;
                salary += 150;
                break;
        }}
    } public Worker(int offeredSalary){
        salary = offeredSalary;
        experience = rng(0,salary*9/300);
        earnings= rng(salary*3/10, (int) (salary*1.5));
        bummingAround = false;
    }

}
