package com.mimuw.amwms.evolution.organisms;

import com.mimuw.amwms.evolution.world.Space;

public interface Organism {

    void eat(Space space);
    void die();
    void move();
    boolean isAlive();
    void newDay(int energyPaymentForDay);
    Organism reproduce(String name);
    boolean canReproduce();
    int programLength();
    int getEnergy();
    int getAge();
    int getCoordinatesX();
    int getCoordinatesY();


}
