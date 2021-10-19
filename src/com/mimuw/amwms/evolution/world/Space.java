package com.mimuw.amwms.evolution.world;

import com.mimuw.amwms.evolution.tools.Direction;

//TODO EXCEPTION - checked but check again
public abstract class Space {
    private Space top;
    private Space right;
    private Space bottom;
    private Space left;

    private final int x;
    private final int y;

    protected Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateSurroundings(Space top, Space right, Space bottom, Space left) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public Space getLeft() {
        return left;
    }

    public Space getRight() {
        return right;
    }

    public Space getNeighbouring(Direction direction) /*throws Exception*/ {
        switch (direction) {
            case N:
                return top;
            case E:
                return right;
            case S:
                return bottom;
//            case W:
//                return left;
            default:
                return left;
//                throw new Exception("WRONG MOVE GIVEN - NOT DIRECTION CHANGE");
        }
    }

    public Space whereIsFoodInSurroundingEight() {
        if (top.hasFood())
            return top;
        if (top.getRight().hasFood())
            return top.getRight();
        if (right.hasFood())
            return right;
        if (bottom.getRight().hasFood())
            return bottom.getRight();
        if (bottom.hasFood())
            return bottom;
        if (bottom.getLeft().hasFood())
            return bottom.getLeft();
        if (left.hasFood())
            return left;
        if (top.getLeft().hasFood())
            return top.getLeft();
        return this;
    }

    public boolean isFoodInSurroundingEight() {
        return top.hasFood() || top.getLeft().hasFood() || left.hasFood() || bottom.getLeft().hasFood()
                || bottom.hasFood() || bottom.getRight().hasFood() || right.hasFood() || top.getRight().hasFood();
    }

    public Direction findFoodInSurroundingFour(Direction direction) {
        if (top.hasFood())
            return Direction.N;
        if (right.hasFood())
            return Direction.E;
        if (bottom.hasFood())
            return Direction.S;
        if (left.hasFood())
            return Direction.W;
        return direction;
    }

    public abstract boolean hasFood();
    public abstract int getEaten();
    public abstract void newDay();

    public String readCoordinates() {
        return "( " + x + ", " +  y + " )";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
