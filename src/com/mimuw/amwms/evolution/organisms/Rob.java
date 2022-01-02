package com.mimuw.amwms.evolution.organisms;

import com.mimuw.amwms.evolution.mutations.Mutation;
import com.mimuw.amwms.evolution.tools.Direction;
import com.mimuw.amwms.evolution.tools.Program;
import com.mimuw.amwms.evolution.world.Space;

public class Rob extends SimpleLifeform {

    private final int number;

    private float probabilityOfReproducing;
    private float fractionOfParentEnergy;
    private int minLimitForReproducing;
    private Mutation mutationGene;

    public Rob(Program program, Space mySpace, Direction direction, int energy, int number,
               float probabilityOfReproducing, float fractionOfParentEnergy, int minLimitForReproducing,
               Mutation mutationGene) {
        super(energy, program, direction, mySpace);
        this.number = number;
        this.probabilityOfReproducing = probabilityOfReproducing;
        this.fractionOfParentEnergy = fractionOfParentEnergy;
        this.minLimitForReproducing = minLimitForReproducing;
        this.mutationGene = mutationGene;
    }

    // I assume that other organisms, than robs, can eat food from food spaces differently (eg. only a fraction of it).
    @Override
    public void eat(Space space) {
        if (space.hasFood())
            addEnergy(space.getEaten());
    }

    // I assume that other organisms, than robs, can die differently and for example leave something behind
    // (for example their dead bodies can become fertilizer which instantly makes the food fully grow).
    @Override
    public void die() {
        setAlive(false);
    }

    @Override
    public boolean canReproduce() {
        double random = Math.random();
        return random <= probabilityOfReproducing && minLimitForReproducing <= super.getEnergy()
                && super.isAlive() && super.getAge() > 0;
    }

    // I keep the energy of a rob as an integer and when it reproduces a fraction of that energy
    // (fractionOfParentEnergy * energy - which is casted to an integer type) goes to the kid and is taken away from the
    // parent rob
    @Override
    public Organism reproduce(String name) {
        Program newProgram = mutationGene.mutate(super.getProgram());
        int newEnergy = (int) (super.getEnergy() * fractionOfParentEnergy);
        int id = Integer.parseInt(name);

        subtractEnergy(newEnergy);

        return new Rob(newProgram, super.getMySpace(), super.getDirection().getOpposite(), newEnergy, id,
                probabilityOfReproducing, fractionOfParentEnergy, minLimitForReproducing,
                mutationGene.copy());
    }

    @Override
    public String toString() {
        return "Rob" + number + " {" +
                "energy=" + super.getEnergy() +
                ", age=" + super.getAge() +
                ", isAlive=" + super.isAlive() +
                ", " + super.getProgram() +
                ", " + super.getDirection() +
                ", coordinates=" + super.getMySpace().readCoordinates() +
                '}';
    }

}
