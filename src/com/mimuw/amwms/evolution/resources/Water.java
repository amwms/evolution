package com.mimuw.amwms.evolution.resources;

// This is an example of another source of food that could be added as an extension to the original project.
// This is only, and only, for demonstration purposes on how the project could be extended and in no way does it work
// in the current state of the project.
public class Water extends Resource{
    private int amount; // in liters

    public Water(int energy, int amount) {
        super(energy);
        this.amount = amount;
    }


    // I asume that all organisms drink one liter of water on walking onto a space with water.
    @Override
    public int beConsumed() {
        if (amount > 0) {
            amount--;
            return energy;
        }

        return 0;
    }

    @Override
    public void grow() {
        // Assuming that we implement weather changes in the project (another extension) and assuming that one day
        // of rain fills the reservoir with one liter of water, this is an example of how the method grow() would look
        // for this class.
        /*
        * if (Symulation.WEATHER == RAIN)
        *   amount++;
        * */
    }

    @Override
    public boolean isFullyGrown() {
        return amount > 0;
    }


}
