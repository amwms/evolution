package com.mimuw.amwms.evolution.tools;
//TODO - EXCEPTION - CHECKED
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

    // niemożliwe jest aby nie znalazła się wartość ponieważ mod % Direction.values().length jest z zakresu 0 do 3
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
