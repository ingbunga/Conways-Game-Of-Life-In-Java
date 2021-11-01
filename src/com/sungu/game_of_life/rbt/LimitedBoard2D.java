package com.sungu.game_of_life.rbt;

public class LimitedBoard2D extends Board2D{

    private int width;
    private int height;

    public LimitedBoard2D(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    @Override
    protected boolean nextGenerationOne(int x, int y) {
        if (x >= width || x < 0 || y < 0 || y >= height) return false;

        return super.nextGenerationOne(x, y);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }
}
