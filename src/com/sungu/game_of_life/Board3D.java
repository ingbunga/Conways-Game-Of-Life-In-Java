package com.sungu.game_of_life;

/**
 * Abstract Conway's game of life Board
 */
public class Board3D {

    private int xSize, ySize, zSize;
    private Cell[][][] map, tempMap;
    private boolean[] liveToLiveWhen, deadToLiveWhen;

    /**
     * @param h Height that not consider padding
     * @param w Width that not consider padding
     */
    public Board3D(int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        map     = new Cell[zSize + 2][ySize + 2][xSize + 2];
        tempMap = new Cell[zSize + 2][ySize + 2][xSize + 2];

        liveToLiveWhen = new boolean[27];
        deadToLiveWhen = new boolean[27];

        // 2d maps initialize
        for (int z = 0; z < zSize + 2; z++) {
            for (int y = 0; y < ySize + 2; y++) {
                for (int x = 0; x < xSize + 2; x++) {
                    map[z][y][x]        = new Cell();
                    tempMap[z][y][x]    = new Cell();
                }
            }
        }
    }


    /**
     * Get one cell from target
     * @param from Target map
     * @param x X coordinate that not consider padding
     * @param y Y coordinate that not consider padding
     * @return Cell that locate
     */
    public Cell getCell(Cell[][][] from, int x, int y, int z) {
        return from[z + 1][y + 1][x + 1];
    }


    /**
     * Swap map with tempMap
     */
    private void swapMap() {
        Cell[][][] tmp = this.tempMap;
        this.tempMap = this.map;
        this.map     = tmp;
    }


    /**
     * Get neighbor cell count.
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return neighbor cell count
     */
    private int CountNeighborCell(int x, int y, int z) {
        return    map[z - 1][y - 1][x - 1].toNumber() + map[z - 1][y - 1][x].toNumber() + map[z - 1][y - 1][x + 1].toNumber()
                + map[z - 1][y][x - 1].toNumber()     + map[z - 1][y][x].toNumber()     + map[z - 1][y][x + 1].toNumber()
                + map[z - 1][y + 1][x - 1].toNumber() + map[z - 1][y + 1][x].toNumber() + map[z - 1][y + 1][x + 1].toNumber()
                +
                  map[z][y - 1][x - 1].toNumber()     + map[z][y - 1][x].toNumber()     + map[z][y - 1][x + 1].toNumber()
                + map[z][y][x - 1].toNumber()                                           + map[z][y][x + 1].toNumber()
                + map[z][y + 1][x - 1].toNumber()     + map[z][y + 1][x].toNumber()     + map[z][y + 1][x + 1].toNumber()
                +
                  map[z + 1][y - 1][x - 1].toNumber() + map[z + 1][y - 1][x].toNumber() + map[z + 1][y - 1][x + 1].toNumber()
                + map[z + 1][y][x - 1].toNumber()     + map[z + 1][y][x].toNumber()     + map[z + 1][y][x + 1].toNumber()
                + map[z + 1][y + 1][x - 1].toNumber() + map[z + 1][y + 1][x].toNumber() + map[z + 1][y + 1][x + 1].toNumber();
    }


    /**
     * Get one coordinate's survival or not in next generation.
     * @param x X coordinate
     * @param y Y coordinate
     * @return survival or not
     */
    private boolean nextGenerationOne(int x, int y, int z) {
        final int neighborCount = CountNeighborCell(x, y, z);
        final boolean alive = map[z][y][x].isAlive();

        return (!alive && deadToLiveWhen[neighborCount]) || (alive && liveToLiveWhen[neighborCount]);
    }


    /**
     * Change map to next generation.
     */
    public void nextGeneration() {
        for (int z = 1; z <= zSize; z++) {
            for (int y = 1; y <= ySize; y++) {
                for (int x = 1; x <= xSize; x++) {
                    if (nextGenerationOne(x, y, z))
                        tempMap[z][y][x].setLive();
                    else
                        tempMap[z][y][x].setDead();
                }
            }
        }

        swapMap();
    }


    /**
     * Import map as 2d boolean
     * @param loadingMap map
     * @param startX start x coordinate  (default : 0)
     * @param startY start y coordinate  (default : 0)
     * @param startZ start z coordinate  (default : 0)
     */
    public void loadMap(boolean[][][] loadingMap, int startX, int startY, int startZ) {
        final int loadingZ = loadingMap.length;
        final int loadingY = loadingMap[0].length;
        final int loadingX = loadingMap[0][0].length;

        for (int z = 1; z <= loadingZ; z++) {
            for (int y = 1; y <= loadingY; y++) {
                for (int x = 1; x <= loadingX; x++) {
                    final Cell cell = map[startZ + z][startY + y][startX + x];

                    if (loadingMap[z - 1][y - 1][x - 1])
                        cell.setLive();
                    else
                        cell.setDead();
                }
            }
        }
    }

    public void loadMap(boolean[][][] loadingMap) {
        loadMap(loadingMap, 0, 0, 0);
    }


    /**
     * Export map as 2d boolean
     * @return 2d boolean map
     */
    public boolean[][][] saveMap() {
        var result = new boolean[zSize][ySize][xSize];

        for (int z = 1; z <= zSize; z++)
            for (int y = 1; y <= ySize; y++)
                for (int x = 1; x <= xSize; x++)
                    result[z - 1][y - 1][x - 1] = map[z][y][x].isAlive();

        return result;
    }


    /**
     * set true liveToLiveWhen with indexes.
     * @param nums indexes
     */
    public void enableLiveToLiveWhen(int[] nums) {
        for (var e : nums) {
            liveToLiveWhen[e] = true;
        }
    }


    /**
     * set false liveToLiveWhen with indexes.
     * @param nums indexes
     */
    public void disableLiveToLiveWhen(int[] nums) {
        for (var e : nums) {
            liveToLiveWhen[e] = false;
        }
    }


    /**
     * set true deadToLiveWhen with indexes.
     * @param nums indexes
     */
    public void enableDeadToLiveWhen(int[] nums) {
        for (var e : nums) {
            deadToLiveWhen[e] = true;
        }
    }


    /**
     * set false deadToLiveWhen with indexes.
     * @param nums indexes
     */
    public void disableDeadToLiveWhen(int[] nums) {
        for (var e : nums) {
            deadToLiveWhen[e] = false;
        }
    }

    public Cell[][][] getMap() {
        return map;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public int getZSize() {
        return zSize;
    }
}
