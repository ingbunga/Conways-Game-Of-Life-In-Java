package com.sungu.game_of_life.rbt;

/**
 * Cell in 2d board.
 * for use TreeSet's element,
 * cell has theirs coordinate.
 */
public class Cell2D implements Comparable<Cell2D> {

    private int x;
    private int y;

    public Cell2D(int x, int y) {
        this.setPos(x, y);
    }

    public Cell2D() {
        this(0, 0);
    }


    /**
     * Set position
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of X coordinate
     * @return X coordinate
     */
    public int getXPos() {
        return x;
    }

    /**
     * Getter of Y coordinate
     * @return Y coordinate
     */
    public int getYPos() {
        return y;
    }

    /**
     * Compare with other Cell by coordinate.
     * this method exist for TreeSet.
     * I think this is not good for design,
     * and should change this method to function which giving to TreeSet by argument.
     * @param o other cell
     * @return for sort
     */
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
