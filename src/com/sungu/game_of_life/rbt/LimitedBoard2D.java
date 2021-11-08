package com.sungu.game_of_life.rbt;

/**
 * Board 2D with limited size
 */
public class LimitedBoard2D extends Board2D{

    private int width;
    private int height;

    public LimitedBoard2D(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    /**
     * Get one coordinate's survival or not in next generation with limited size.
     * @param x X coordinate
     * @param y Y coordinate
     * @return survival or not
     */
    @Override
    protected boolean nextGenerationOne(int x, int y) {
        if (x >= width || x < 0 || y < 0 || y >= height) return false;

        return super.nextGenerationOne(x, y);
    }

    /**
     * Setter of width
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter of width
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setter of height
     * @param height height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter of height
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
}
