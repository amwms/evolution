package com.mimuw.amwms.evolution.organisms;

import com.mimuw.amwms.evolution.tools.Direction;
import com.mimuw.amwms.evolution.tools.Program;
import com.mimuw.amwms.evolution.world.Space;

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
    }

    // I assume that all simple lifeforms move int the same way as robs, but other organisms
    // (which can be added as an extension) can understand the instructions in a different way
    // (for example they only smell behind themselves).
    // I also assume that simple organisms (in this robs) love the north so they always look for food
    // (moves SMELL and EAT) in the north first and then they check the rest of the directions clockwise.
    @Override
    public void move() {
        updateDeath();
        if (hasEnergy() && !program.hasProgramEnded()) {
            useEnergy();

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

    protected void updateDeath() {
        if (energy < 0 && isAlive())
            die();
    }

}
