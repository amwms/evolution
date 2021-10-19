package com.mimuw.amwms.evolution.exceptions;

public class WrongType extends ProgramException {
    private final String parameterName;

    public WrongType(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public void printMessage() {
        System.out.println("WRONG TYPE OF PARAMETER: " + parameterName);
    }
}
