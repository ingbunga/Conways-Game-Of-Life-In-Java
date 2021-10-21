package com.sungu.game_of_life.rbt;

public class Cell2D implements Comparable<Cell2D> {

    private int x;
    private int y;


    public Cell2D(int x, int y) {
        this.setPos(x, y);
    }

    public Cell2D() {
        this(0, 0);
    }


    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    @Override
    public int compareTo(Cell2D o) {
        if (this.y != o.y) {
            return this.y - o.y;
        }
        if (this.x != o.x) {
            return this.x - o.x;
        }
        return 0;
    }
}
