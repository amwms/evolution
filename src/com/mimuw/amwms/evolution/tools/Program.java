package com.mimuw.amwms.evolution.tools;

import com.mimuw.amwms.evolution.exceptions.InvalidMove;
import java.util.ArrayList;

public class Program {
    private ArrayList<Move> program;
    private Integer id = 0;


    public Program(ArrayList<Move> program) {
        this.program = program;
    }

    public Program(String s) throws InvalidMove {
        ArrayList<Move> program = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            program.add(Move.stringToMove(s.charAt(i)));
        }

        this.program = program;
    }

    public Program copy() {
        ArrayList<Move> prog = new ArrayList<>();
        for (Move move : program) {
            prog.add(move);
        }

        return new Program(prog);
    }

    public Move makeNextMove() {
        return program.get(id++);
    }

    public void resetProgram() {
        id = 0;
    }

    public boolean hasProgramEnded() {
        return id.equals(program.size());
    }

    public int getProgramLength() {
        return program.size();
    }

    @Override
    public String toString() {
        StringBuilder prog = new StringBuilder();

        for (Move move : program) {
            prog.append(move.toString());
        }

        return "Program{" +
                "program=" + prog.toString() +
                '}';
    }

    public ArrayList<Move> getProgram() {
        return program;
    }
}
