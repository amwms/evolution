package com.mimuw.amwms.evolution.resources;

public abstract class Resource {
    protected final int energy;

    protected Resource(int energy) {
        this.energy = energy;
    }

    public abstract int beConsumed();

    public abstract void  grow();

    public abstract boolean isFullyGrown();

}
