package com.sungu.game_of_life.rbt;

public class LimitedBoard2D extends Board2D{

    private int width;
    private int height;

    LimitedBoard2D() {
        super();
    }

    protected boolean nextGenerationOne(int x, int y) {
        if (x >= width || x < 0 || y < 0 || y >= height) return false;

        return super.nextGenerationOne(x, y);
    }
}
