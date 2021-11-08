package com.sungu.game_of_life.rbt;

/**
 * Board 3D with limited size
 */
public class LimitedBoard3D extends Board3D {

    private int xSize;
    private int ySize;
    private int zSize;

    public LimitedBoard3D(int xSize, int ySize, int zSize) {
        super();
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
    }

    /**
     * Get one coordinate's survival or not in next generation with limited size.
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return survival or not
     */
    @Override
    protected boolean nextGenerationOne(int x, int y, int z) {
        if (x < 0 || x >= xSize || y < 0 || y >= ySize || z < 0 || z >= zSize) return false;

        return super.nextGenerationOne(x, y, z);
    }

    /**
     * Getter of xSize
     * @return xSize
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * Setter of xSize
     * @param xSize xSize
     */
    public void setXSize(int xSize) {
        this.xSize = xSize;
    }

    /**
     * Getter of ySize
     * @return ySize
     */
    public int getYSize() {
        return ySize;
    }

    /**
     * Setter of ySize
     * @param ySize ySize
     */
    public void setYSize(int ySize) {
        this.ySize = ySize;
    }

    /**
     * Getter of zSize
     * @return zSize
     */
    public int getZSize() {
        return zSize;
    }

    /**
     * Setter of zSize
     * @param zSize zSize
     */
    public void setZSize(int zSize) {
        this.zSize = zSize;
    }
}
