package com.sungu.game_of_life.rbt;

/**
 * Cell in 3d board.
 * for use TreeSet's element,
 * cell has theirs coordinate.
 */
public class Cell3D implements Comparable<Cell3D> {

    private int x;
    private int y;
    private int z;


    public Cell3D(int x, int y, int z) {
        this.setPos(x, y, z);
    }

    public Cell3D() {
        this(0, 0, 0);
    }


    /**
     * Set position
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public void setPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * Getter of Z coordinate
     * @return Z coordinate
     */
    public int getZPos() {
        return z;
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
    public int compareTo(Cell3D o) {
        if (this.z != o.z) {
            return this.z - o.z;
        }
        if (this.y != o.y) {
            return this.y - o.y;
        }
        if (this.x != o.x) {
            return this.x - o.x;
        }
        return 0;
    }
}
