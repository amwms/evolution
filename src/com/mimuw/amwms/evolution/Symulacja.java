package com.mimuw.amwms.evolution;

import com.mimuw.amwms.evolution.symulation.Symulation;

public class Symulacja {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("WRONG NUMBER OF FILES GIVEN");
            System.exit(1);
        }

        Symulation symulation = new Symulation();
        symulation.symulate(args[0], args[1]);
    }
}
