package com.mimuw.amwms.evolution.world;

public class World {
    private final int WIDTH;
    private final int HEIGHT;

    private Space[][] worldBoard;

    public World(Space[][] worldBoard) {
        WIDTH = worldBoard.length;
        HEIGHT = worldBoard[0].length;

        this.worldBoard = worldBoard;
        updateAllSpacesSurroundings();
    }

    private void updateAllSpacesSurroundings() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                worldBoard[x][y].updateSurroundings(getTop(x, y), getRight(x, y), getBottom(x, y), getLeft(x, y));
            }
        }
    }

    private Space getTop(int myX, int myY) {
        return worldBoard[myX][(myY + HEIGHT - 1) % HEIGHT];
    }

    private Space getRight(int myX, int myY) {
        return worldBoard[(myX + 1) % WIDTH][myY];
    }

    private Space getBottom(int myX, int myY) {
        return worldBoard[myX][(myY + 1) % HEIGHT];
    }

    private Space getLeft(int myX, int myY) {
        return worldBoard[(myX + WIDTH - 1) % WIDTH][myY];
    }

    public Space getSpaceByCoordinates(int x, int y) {
        return worldBoard[x][y];
    }

    public void newDay() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                worldBoard[x][y].newDay();
            }
        }
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }


}
