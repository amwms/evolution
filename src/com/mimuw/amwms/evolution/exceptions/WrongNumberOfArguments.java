package com.mimuw.amwms.evolution.exceptions;

public class WrongNumberOfArguments extends ProgramException {
    private String message;

    public WrongNumberOfArguments(String message) {
        this.message = message;
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
