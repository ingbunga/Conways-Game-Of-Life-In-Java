package com.sungu.game_of_life;

/**
 * Abstract Cell
 */
public class Cell {

    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public Cell() {
        this(false);
    }

    /**
     * Set live
     */
    public void setLive() {
        alive = true;
    }

    /**
     * Set dead
     */
    public void setDead() {
        alive = false;
    }

    /**
     * If cell alive return 1, else 0
     * @return 1 or 0
     */
    public int toNumber() {
        return alive ? 1 : 0;
    }

    /**
     * Getter of alive
     * @return alive
     */
    public boolean isAlive() {
        return alive;
    }
}
