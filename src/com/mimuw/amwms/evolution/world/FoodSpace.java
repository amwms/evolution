package com.mimuw.amwms.evolution.world;

import com.mimuw.amwms.evolution.resources.Resource;

public class FoodSpace extends Space{
    private Resource food;

    public FoodSpace(Resource food, int x, int y) {
        super(x, y);
        this.food = food;
    }

    @Override
    public boolean hasFood() {
        return food.isFullyGrown();
    }

    @Override
    public int getEaten() {
        return food.beConsumed();
    }

    @Override
    public void newDay() {
        food.grow();
    }

    @Override
    public String toString() {
        if (hasFood())
            return "X ";
        return ". ";
    }
}
