package com.mimuw.amwms.evolution.exceptions;

public class WrongInputVerseLength extends ProgramException {
    private final String error;

    public WrongInputVerseLength(String error) {
        this.error = error;
    }

    @Override
    public void printMessage() {
        System.out.println("TOO " + error + "INPUT");
    }
}
