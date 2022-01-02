package com.mimuw.amwms.evolution.input;

import com.mimuw.amwms.evolution.organisms.Organism;
import com.mimuw.amwms.evolution.world.World;

import java.util.ArrayList;

public class Reporter {
    private int dayCount = -1;
    private int organismCount;
    private int spacesWithGrownFood;

    private int minimalProgramLength = Integer.MAX_VALUE;
    private float averageProgramLength;
    private int maximalProgramLength = 0;

    private int minimalOrganismEnergy = Integer.MAX_VALUE;
    private float averageOrganismEnergy;
    private int maximalOrganismEnergy = 0;

    private int minimalOrganismAge = Integer.MAX_VALUE;
    private float averageOrganismAge;
    private int maximalOrganismAge = 0;

    public void report() {
        System.out.printf("%d, ", dayCount);
        System.out.printf("rob: %d, ", organismCount);
        System.out.printf("żyw: %d, ", spacesWithGrownFood);

        System.out.printf("prg: %d/%.2f/%d, ", minimalProgramLength, averageProgramLength, maximalProgramLength);
        System.out.printf("energ: %d/%.2f/%d, ", minimalOrganismEnergy, averageOrganismEnergy, maximalOrganismEnergy);
        System.out.printf("wiek: %d/%.2f/%d\n", minimalOrganismAge, averageOrganismAge, maximalOrganismAge);
    }

    public void update(World world, ArrayList<Organism> organisms) {
        updateDayCount();
        updateOrganismCount(organisms);
        updateSpacesWithGrownFoodCount(world);
        updatePrograms(organisms);
        updateEnergies(organisms);
        updateAges(organisms);
    }

    private void updateDayCount() {
        dayCount++;
    }

    private void updateOrganismCount(ArrayList<Organism> organisms) {
        organismCount = organisms.size();
    }

    private void updateSpacesWithGrownFoodCount(World world) {
        spacesWithGrownFood = 0;

        for (int x = 0; x < world.getWIDTH(); x++) {
            for (int y = 0; y < world.getHEIGHT(); y++) {
                if (world.getSpaceByCoordinates(x, y).hasFood())
                    spacesWithGrownFood++;
            }
        }
    }

    private void updatePrograms(ArrayList<Organism> organisms) {
        int lengthSum = 0;
        resetProgramLength();

        if (organisms.size() == 0) {
            minimalProgramLength = 0;
            maximalProgramLength = 0;
        }

        for (Organism organism : organisms) {
            maximalProgramLength = maximalProgramLength < organism.programLength() ? organism.programLength() : maximalProgramLength;
            minimalProgramLength = minimalProgramLength > organism.programLength() ? organism.programLength() : minimalProgramLength;

            lengthSum += organism.programLength();
        }

        averageProgramLength = ((float) lengthSum) / ((float) organisms.size());
    }

    private void updateEnergies(ArrayList<Organism> organisms) {
        int energySum = 0;
        resetEnergy();

        if (organisms.size() == 0) {
            minimalOrganismEnergy = 0;
            maximalOrganismEnergy = 0;
        }

        for (Organism organism : organisms) {
            maximalOrganismEnergy = maximalOrganismEnergy < organism.getEnergy() ? organism.getEnergy() : maximalOrganismEnergy;
            minimalOrganismEnergy = minimalOrganismEnergy > organism.getEnergy() ? organism.getEnergy() : minimalOrganismEnergy;

            energySum += organism.getEnergy();
        }

        averageOrganismEnergy = ((float) energySum) / ((float) organisms.size());
    }

    private void updateAges(ArrayList<Organism> organisms) {
        int ageSum = 0;
        resetAge();

        if (organisms.size() == 0) {
            minimalOrganismAge = 0;
            maximalOrganismAge = 0;
        }

        for (Organism organism : organisms) {
            maximalOrganismAge = maximalOrganismAge < organism.getAge() ? organism.getAge() : maximalOrganismAge;
            minimalOrganismAge = minimalOrganismAge > organism.getAge() ? organism.getAge() : minimalOrganismAge;

            ageSum += organism.getAge();
        }

        averageOrganismAge = ((float) ageSum) / ((float) organisms.size());
    }

    public int getLongestProgramLength() {
        return maximalProgramLength;
    }

    private void resetProgramLength() {
        minimalProgramLength = Integer.MAX_VALUE;
        maximalProgramLength = 0;
    }

    private void resetEnergy() {
        minimalOrganismEnergy = Integer.MAX_VALUE;
        maximalOrganismEnergy = 0;
    }

    private void resetAge() {
        minimalOrganismAge = Integer.MAX_VALUE;
        maximalOrganismAge = 0;
    }
}
