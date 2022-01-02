package com.mimuw.amwms.evolution.exceptions;

public class InvalidMove extends  ProgramException {
    @Override
    public void printMessage() {
        System.err.println("NO SUCH MOVE");
    }
}
