package com.mimuw.amwms.evolution.exceptions;

public class OutOfRange extends ProgramException{
    private String parameterName;

    public OutOfRange(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public void printMessage() {
        System.out.println(parameterName + " NOT FROM RANGE");
    }
}
