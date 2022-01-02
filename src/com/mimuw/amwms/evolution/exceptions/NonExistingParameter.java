package com.mimuw.amwms.evolution.exceptions;

public class NonExistingParameter extends ProgramException {
    private final String parameterName;

    public NonExistingParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public void printMessage() {
        System.err.println("THIS PARAMETER DOES NOT EXIST: " + parameterName);
    }
}
