package com.mimuw.amwms.evolution.symulation;

import com.mimuw.amwms.evolution.input.Reporter;
import com.mimuw.amwms.evolution.organisms.Organism;
import com.mimuw.amwms.evolution.world.World;

import java.util.ArrayList;

public class Evolution {
    private int DAILY_PAYMENT;

    private ArrayList<Organism> organisms;
    private World world;
    private int time = 1;
    private int fullReportFrequency;
    private Reporter reporter = new Reporter();

    public Evolution(int DAILY_PAYMENT, ArrayList<Organism> organisms, World world, int fullReportFrequency) {
        this.DAILY_PAYMENT = DAILY_PAYMENT;
        this.organisms = organisms;
        this.world = world;
        this.fullReportFrequency = fullReportFrequency;
    }

    private void clearTheDead() {
        organisms.removeIf(organism -> !organism.isAlive());
    }

    private void newDay() {
        world.newDay();

        for (Organism organism : organisms) {
            organism.newDay(DAILY_PAYMENT);
        }
    }

    private void moveOrgamisms() {
        for (int i = 0; i < reporter.getLongestProgramLength(); i++) {
            for (Organism organism : organisms) {
                organism.move();
            }
        }
    }

    private void reproduceOrganisms() {
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).canReproduce()) {
                Organism o = organisms.get(i).reproduce(organisms.size() + "");
                organisms.add(o);
            }
        }
    }

    private boolean shouldDoFullReport() {
        return time % fullReportFrequency == 0;
    }

    // The full report consists of a report of each of the robs status which "took part" in that day
    // and of a map of the world board (the "X" is where there is currently fully grown food, the "." is where there
    // is currently no food and the numbers tell us how many organisms are currently on the specific space)
    private void fullReport() {
        for (Organism organism : organisms)  {
            System.out.println(organism);
        }

        System.out.println("------------------------MAP-------------------------");
        drawWorld();
        System.out.println("----------------------------------------------------");
    }

    private void drawWorld() {
        int [][] organismSpaces = new int[world.getWIDTH()][world.getHEIGHT()];
        for (int x = 0; x < world.getWIDTH(); x++) {
            for (int y = 0; y < world.getHEIGHT(); y++) {
                organismSpaces[x][y] = 0;
            }
        }

        for (Organism organism : organisms)  {
            organismSpaces[organism.getCoordinatesX()][organism.getCoordinatesY()]++;
        }


        for (int y = 0; y < world.getHEIGHT(); y++) {
            for (int x = 0; x < world.getWIDTH(); x++) {
                if (organismSpaces[x][y] > 0) {
                    System.out.printf("%d ", organismSpaces[x][y]);
                } else {
                    System.out.print(world.getSpaceByCoordinates(x, y));
                }
            }
            System.out.print("\n");
        }
    }

    public void develop(int numberOfRounds) {
        reporter.update(world, organisms);
        
        System.out.println("-----------REPORT BEFORE------------");
        fullReport();

        for (int i = 1; i <= numberOfRounds; i++) {
            if (shouldDoFullReport()) {
                System.out.println("-----------BEFORE------------");
                fullReport();
            }

            newDay();
            moveOrgamisms();
            reproduceOrganisms();

            if (shouldDoFullReport()) {
                System.out.println("-----------AFTER------------");
                fullReport();
            }

            clearTheDead();

            reporter.update(world, organisms);
            reporter.report();
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");

            time++;
        }
        
        System.out.println("-----------REPORT AFTER------------");
        fullReport();	
    }



}
