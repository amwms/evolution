package com.mimuw.amwms.evolution.symulation;


import com.mimuw.amwms.evolution.input.DataCreator;
import com.mimuw.amwms.evolution.input.Reader;
import com.mimuw.amwms.evolution.organisms.Organism;
import com.mimuw.amwms.evolution.world.World;

import java.io.File;
import java.util.ArrayList;

public class Symulation {

    public void symulate(String fileMap, String fileParameters) {
        DataCreator data = new DataCreator();
        Reader read = new Reader();

        File parametersFile = new File(fileParameters);
        File worldFile = new File(fileMap);

        read.parseInputParameters(data, parametersFile);
        read.readWorld(data, worldFile);

        int numberOfDays = data.getNumberOfDays();
        int dailyPayment = data.getDailyPayment();
        int fullReportFrequency = data.getReportFrequency();

        World world = data.createWorld();
        ArrayList<Organism> organisms = data.createOrganisms();

        Evolution evolution = new Evolution(dailyPayment, organisms, world, fullReportFrequency);
        evolution.develop(numberOfDays);
    }

}
