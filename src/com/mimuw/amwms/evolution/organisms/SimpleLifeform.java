package com.mimuw.amwms.evolution.organisms;

import com.mimuw.amwms.evolution.tools.Direction;
import com.mimuw.amwms.evolution.tools.Program;
import com.mimuw.amwms.evolution.world.Space;
//TODO EXCEPTION - CHECKED
public abstract class SimpleLifeform implements Organism {

    private int energy;
    private int age = 0;
    private boolean isAlive = true;
    private final Program program;
    private Direction direction;
    private Space mySpace;

    protected SimpleLifeform(int energy, Program program, Direction direction, Space mySpace) {
        this.energy = energy;
        this.program = program;
        this.direction = direction;
        this.mySpace = mySpace;
    }

    @Override
    public void newDay(int energyPaymentForDay) {
        age++;
        program.resetProgram();
        payEnergyForNewDay(energyPaymentForDay);

        updateDeath();
//        hasEnergy();
    }

    //zakładam że wszystkie proste organizmy poruszają się jak roby ale inne typy organizmów mogą inaczej rozumieć
    // instrukcje ze spisu instrukcji (np wąchają tylko pole za sobą a nie wszytskie, które są dookoła nich)

    @Override
    public void move() {
        updateDeath();
        if (hasEnergy() && !program.hasProgramEnded()) {
            useEnergy();
//            try {
                switch (program.makeNextMove()) {
                    case LEFT:
                        direction = direction.turnLeft();
                        break;
                    case RIGHT:
                        direction = direction.turnRight();
                        break;
                    case WALK:
                        mySpace = mySpace.getNeighbouring(direction);
                        if(mySpace.hasFood())
                            eat(mySpace);
                        break;
                    case EAT:
                        if (mySpace.isFoodInSurroundingEight()) {
                            mySpace = mySpace.whereIsFoodInSurroundingEight();
                            eat(mySpace);
                        }
                        break;
                    case SMELL:
                        direction = mySpace.findFoodInSurroundingFour(direction);
                        break;
                    default:
                }
//            } catch (Exception e) {
//                System.out.println("WRONG DIRECTION GIVEN");
//                e.printStackTrace();
//                System.exit(1);
//            }
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public int programLength() {
        return program.getProgramLength();
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getCoordinatesX() {
        return mySpace.getX();
    }

    @Override
    public int getCoordinatesY() {
        return mySpace.getY();
    }

    private boolean hasEnergy() {
//        updateDeath();
//        return isAlive();
        return energy > 0;
    }

    protected void setAlive(boolean alive) {
        isAlive = alive;
    }

    private void payEnergyForNewDay(int energyPayment) {
        energy -= energyPayment;
        updateDeath();
    }

    private void useEnergy() {
        energy--;
        updateDeath();
    }

    protected void addEnergy(int energy) {
        this.energy += energy;
    }

    protected void subtractEnergy(int energy) {
        this.energy -= energy;
    }

    protected Program getProgram() {
        return program;
    }

    protected Direction getDirection() {
        return direction;
    }

    protected Space getMySpace() {
        return mySpace;
    }

    //TODO SPANIE PONIZEJ ZERA
    protected void updateDeath() {
//        if (energy <= 0 && isAlive())
//            die();
        if (energy < 0 && isAlive())
            die();
    }

}
