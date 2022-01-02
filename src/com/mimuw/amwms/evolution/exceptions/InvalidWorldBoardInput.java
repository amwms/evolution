package com.mimuw.amwms.evolution.exceptions;

public class InvalidWorldBoardInput extends ProgramException{
    private final String error;

    public InvalidWorldBoardInput(String error) {
        this.error = error;
    }

    @Override
    public void printMessage() {
        System.err.println("WRONG INPUT OF WORLD: " + error);
    }
}
