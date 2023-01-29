package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    int bonus = 0;
    float multiplier = 1;
    public String pizzaname;
    public List<Ingredient> IngredientsList = new ArrayList<Ingredient>();
    public int assignedWorker;
    public int monthlyCost;
    public Pizza(String name, Ingredient i1, Ingredient i2, Ingredient i3, Ingredient i4, Ingredient i5){
        pizzaname = name;
        IngredientsList.add(i1);
        IngredientsList.add(i2);
        IngredientsList.add(i3);
        IngredientsList.add(i4);
        IngredientsList.add(i5);
        IngredientsList.forEach(ingredient -> bonus += ingredient.bonus);
        IngredientsList.forEach(ingredient -> multiplier += (ingredient.multiplier-1));
        IngredientsList.forEach(ingredient -> monthlyCost += ingredient.monthlyCost);
    }
}
