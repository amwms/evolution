package com.mimuw.amwms.evolution.tools;

public enum Direction {

    N(0, "north"),
    E(1, "east"),
    S(2, "south"),
    W(3, "west");

    public final int mod;
    public final String name;

    Direction(int mod, String name) {
        this.mod = mod;
        this.name = name;
    }

    // It is impossible for a value not to be found because mod % Direction.values().length is from range 0 to 3
    // and all the values are from that range
    private Direction intToDirection(int mod) {
        for (Direction direction : Direction.values()) {
            if (direction.mod == (mod % Direction.values().length))
                return direction;
        }

        return null;
    }

    public Direction turnLeft() {
        return intToDirection(this.mod + numberOfDirections() - 1);
    }

    public Direction turnRight() {
        return intToDirection(this.mod + 1);
    }

    public int numberOfDirections() {
        return Direction.values().length;
    }

    public Direction getOpposite() {
        return intToDirection(this.mod + 2);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "name=" + name +
                '}';
    }
}
