package com.mimuw.amwms.evolution.input;

import com.mimuw.amwms.evolution.mutations.Mutation;
import com.mimuw.amwms.evolution.organisms.Organism;
import com.mimuw.amwms.evolution.organisms.Rob;
import com.mimuw.amwms.evolution.tools.Direction;
import com.mimuw.amwms.evolution.tools.Move;
import com.mimuw.amwms.evolution.tools.Program;
import com.mimuw.amwms.evolution.world.World;

import java.util.ArrayList;
import java.util.Random;

public class DataCreator {
    private World world; // plansza.txt
    private int numberOfDays; // ile_tur
    private int dailyPayment; // koszt_tury
    private ArrayList<Move> listOfInstructions; // spis_instr
    private int reportFrequency; // co_ile_wypisz

    private int beginNumberOfRobs; // pocz_ile_robów
    private Program beginProgram; // pocz_progr
    private int beginEnergyOfRobs; // pocz_energia

    private int energyFromFood; // ile_daje_jedzenie
    private int foodGrowthTime; // ile_rośnie_jedzenie

    private float probabilityOfReproducing; //pr_powielenia - I assume that this is a fraction for range [0;1]
    private float fractionOfParentEnergy; // ułamek_energii_rodzica - I assume that this is a fraction for range [0;1]
    private int minLimitForReproducing; // limit_powielania
    private float probabilityOfDeletion; // pr_usunięcia_instr - I assume that this is a fraction for range [0;1]
    private float probabilityOfAddition; // pr_dodania_instr - I assume that this is a fraction for range [0;1]
    private float probabilityOfChange; // pr_zmiany_instr - I assume that this is a fraction for range [0;1]

    public void setWorld(World world) {
        this.world = world;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setDailyPayment(int dailyPayment) {
        this.dailyPayment = dailyPayment;
    }

    public void setListOfInstructions(ArrayList<Move> listOfInstructions) {
        this.listOfInstructions = listOfInstructions;
    }

    public void setReportFrequency(int reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    public void setBeginNumberOfRobs(int beginNumberOfRobs) {
        this.beginNumberOfRobs = beginNumberOfRobs;
    }

    public void setBeginProgram(Program beginProgram) {
        this.beginProgram = beginProgram;
    }

    public void setBeginEnergyOfRobs(int beginEnergyOfRobs) {
        this.beginEnergyOfRobs = beginEnergyOfRobs;
    }

    public void setEnergyFromFood(int energyFromFood) {
        this.energyFromFood = energyFromFood;
    }

    public void setFoodGrowthTime(int foodGrowthTime) {
        this.foodGrowthTime = foodGrowthTime;
    }

    public void setProbabilityOfReproducing(float probabilityOfReproducing) {
        this.probabilityOfReproducing = probabilityOfReproducing;
    }

    public void setFractionOfParentEnergy(float fractionOfParentEnergy) {
        this.fractionOfParentEnergy = fractionOfParentEnergy;
    }

    public void setMinLimitForReproducing(int minLimitForReproducing) {
        this.minLimitForReproducing = minLimitForReproducing;
    }

    public void setProbabilityOfDeletion(float probabilityOfDeletion) {
        this.probabilityOfDeletion = probabilityOfDeletion;
    }

    public void setProbabilityOfAddition(float probabilityOfAddition) {
        this.probabilityOfAddition = probabilityOfAddition;
    }

    public void setProbabilityOfChange(float probabilityOfChange) {
        this.probabilityOfChange = probabilityOfChange;
    }

    public int getEnergyFromFood() {
        return energyFromFood;
    }

    public int getFoodGrowthTime() {
        return foodGrowthTime;
    }

    public Mutation createMutation() {
        return new Mutation(probabilityOfDeletion, probabilityOfAddition, probabilityOfChange, listOfInstructions);
    }

    public World createWorld() {
        return world;
    }

    public ArrayList<Organism> createOrganisms() {
        ArrayList<Organism> organisms = new ArrayList<>();

        for (int i = 0; i < beginNumberOfRobs; i++) {
            Program program = beginProgram.copy();
            Mutation mutation = createMutation();

            Random rand = new Random();
            int x = rand.nextInt(world.getWIDTH());
            int y = rand.nextInt(world.getHEIGHT());

            Organism organism = new Rob(program, world.getSpaceByCoordinates(x, y), Direction.N, beginEnergyOfRobs, i,
                    probabilityOfReproducing, fractionOfParentEnergy, minLimitForReproducing, mutation);
            organisms.add(organism);
        }


        return organisms;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getDailyPayment() {
        return dailyPayment;
    }

    public int getReportFrequency() {
        return reportFrequency;
    }

}
