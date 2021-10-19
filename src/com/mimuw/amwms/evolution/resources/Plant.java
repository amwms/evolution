package com.mimuw.amwms.evolution.resources;

public class Plant extends Resource{
    private int growthTime;
    private int howMuchTimeTillFullGrown = 0;

    public Plant(int energy, int growthTime) {
        super(energy);
        this.growthTime = growthTime;
    }

    public int beConsumed() {
        if (howMuchTimeTillFullGrown == 0) {
            howMuchTimeTillFullGrown = growthTime;
            return energy;
        }

        return 0;
    }

    public void grow() {
        if (howMuchTimeTillFullGrown > 0)
            howMuchTimeTillFullGrown--;
    }

    public boolean isFullyGrown() {
        return howMuchTimeTillFullGrown == 0;
    }
}
