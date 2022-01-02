package com.mimuw.amwms.evolution.tools;

import com.mimuw.amwms.evolution.exceptions.InvalidMove;

public enum Move {

    LEFT('l'),
    RIGHT('p'),
    WALK('i'),
    SMELL('w'),
    EAT('j');

    public final char alias;

    Move(char alias) {
        this.alias = alias;
    }

    public static Move stringToMove(char c) throws InvalidMove {

            for (Move move : Move.values()) {
                if (move.alias == c)
                    return move;
            }
            throw new InvalidMove();
    }

    @Override
    public String toString() {
        return "" + alias;
    }
}
