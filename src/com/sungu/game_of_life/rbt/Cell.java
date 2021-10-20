package com.sungu.game_of_life.rbt;

public class Cell implements Comparable<Cell> {

    private int x;
    private int y;
    private int z;


    public Cell(int x, int y, int z) {
        this.setPos(x, y, z);
    }

    public Cell() {
        this(0, 0, 0);
    }


    public void setPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getZPos() {
        return z;
    }

    @Override
    public int compareTo(Cell o) {
        if (this.x != o.x) {
            return this.x - o.x;
        }
        if (this.y != o.y) {
            return this.y - o.y;
        }
        if (this.z != o.z) {
            return this.z - o.z;
        }
        return 0;
    }
}
