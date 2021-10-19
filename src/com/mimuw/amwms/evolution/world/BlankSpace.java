package com.mimuw.amwms.evolution.world;

public class BlankSpace extends Space{

    public BlankSpace(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hasFood() {
        return false;
    }

    @Override
    public int getEaten() {
        return 0;
    }

    @Override
    public void newDay() {

    }

    @Override
    public String toString() {
        return ". ";
    }
}
