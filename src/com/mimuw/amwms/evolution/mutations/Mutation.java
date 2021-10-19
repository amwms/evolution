package com.mimuw.amwms.evolution.mutations;

import com.mimuw.amwms.evolution.tools.Move;
import com.mimuw.amwms.evolution.tools.Program;

import java.util.ArrayList;
import java.util.Random;

public class Mutation {
    private float probabilityOfDeletion; // pr_usunięcia_instr
    private float probabilityOfAddition; // pr_dodania_instr
    private float probabilityOfChange; // pr_zmiany_instr
    private ArrayList<Move> listOfInstructions; // spis_instr

    public Mutation(float probabilityOfDeletion, float probabilityOfAddition, float probabilityOfChange,
                    ArrayList<Move> listOfInstructions) {
        this.probabilityOfDeletion = probabilityOfDeletion;
        this.probabilityOfAddition = probabilityOfAddition;
        this.probabilityOfChange = probabilityOfChange;
        this.listOfInstructions = listOfInstructions;
    }

    public Program mutate(Program program) {
        Program newProgram = program.copy();
        int newProgramLength = newProgram.getProgramLength();

        if (Math.random() <= probabilityOfDeletion && newProgramLength > 0) {
            newProgram.getProgram().remove(newProgramLength - 1);
            newProgramLength = newProgram.getProgramLength();
        }

        if (Math.random() <= probabilityOfAddition) {
            Random rand = new Random();
            int i = rand.nextInt(listOfInstructions.size());

            newProgram.getProgram().add(listOfInstructions.get(i));
            newProgramLength = newProgram.getProgramLength();
        }

        if (Math.random() <= probabilityOfChange && newProgramLength > 0) {
            Random rand = new Random();
            int idOfMove = rand.nextInt(listOfInstructions.size());
            int idOfChange = rand.nextInt(newProgramLength);

            newProgram.getProgram().set(idOfChange, listOfInstructions.get(idOfMove));
        }

        return newProgram;
    }

    public Mutation copy() {
        ArrayList<Move> list = new ArrayList<>();
        for (Move move : listOfInstructions) {
            list.add(move);
        }

        return new Mutation(probabilityOfDeletion, probabilityOfAddition, probabilityOfChange, list);
    }
}
