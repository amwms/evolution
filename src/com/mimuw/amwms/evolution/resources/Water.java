package com.mimuw.amwms.evolution.resources;

// przykładowe dodatkowe źródło jedzenia które się może znaleźć
// klasa ta jest tylko i wyłącznie by pokazać w jaki sposób możnaby było dalej rozszerzyć projekt
// wżadnym stopniu nie działa dla aktualnego stanu projektu
public class Water extends Resource{
    private int amount; //podane w litrach

    protected Water(int energy, int amount) {
        super(energy);
        this.amount = amount;
    }


    // zakładam że każdy organizm w jedej turze pije 1 litr wody
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
        // przy założeniu że zmienia się w symulacji pogoda (trzymamy ją w publicznym atrybucie "WEATHER")
        // zakłądamy że każda tura deszczu napęłnia zbiornik wodny o 1 litr
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
