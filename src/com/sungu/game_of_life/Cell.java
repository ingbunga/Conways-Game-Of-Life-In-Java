package com.sungu.game_of_life;

/**
 * Abstract cell
 */
public class Cell {

    private boolean alive;


    public Cell(boolean alive) {
        this.alive = alive;
    }

    public Cell() {
        this(false);
    }


    public void setLive() {
        alive = true;
    }

    public void setDead() {
        alive = false;
    }

    public int toNumber() {
        return alive ? 1 : 0;
    }

    public boolean isAlive() {
        return alive;
    }
}
