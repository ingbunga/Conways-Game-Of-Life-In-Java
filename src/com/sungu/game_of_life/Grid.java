package com.sungu.game_of_life;

/**
 * Abstract Conway's game of life Board
 */
public class Grid {

    private int height;
    private int width;
    private Cell[][] map;
    private Cell[][] tempMap;
    private long[][] prefixSum;

    /**
     * @param h Height that not consider padding
     * @param w Width that not consider padding
     */
    public Grid(int h, int w) {
        height = h;
        width  = w;
        map     = new Cell[h+2][w+2];
        tempMap = new Cell[h+2][w+2];
        prefixSum = new long[h+3][w+3];

        // 2d maps initialize
        for (int y = 0; y < h + 2; y++) {
            for (int x = 0; x < w + 2; x++) {
                map[y][x]     = new Cell();
                tempMap[y][x] = new Cell();
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
    public Cell getCell(Cell[][] from, int x, int y) {
        return from[y+1][x+1];
    }


    /**
     * Swap map with tempMap
     */
    private void swapMap() {
        Cell[][] tmp = this.tempMap;
        this.tempMap = this.map;
        this.map     = tmp;
    }


    /**
     * update prefix sum from map
     */
    private void updatePrefixSum() {
        for (int y = 2; y <= height + 1; y++) {
            for (int x = 2; x <= width + 1; x++) {
                prefixSum[y][x] =
                        prefixSum[y][x-1] + prefixSum[y-1][x] - prefixSum[y-1][x-1] + map[y-1][x-1].toNumber();
            }
        }
    }

    /**
     * Get neighbor cell count.
     * @param x X coordinate
     * @param y Y coordinate
     * @return neighbor cell count
     */
    private long CountNeighborCell(int x, int y) {
        return prefixSum[y + 2][x + 2]
                - prefixSum[y + 2][x - 1]
                - prefixSum[y - 1][x + 2]
                + prefixSum[y - 1][x - 1]
                - map[y][x].toNumber();
    }

    /**
     * Get one coordinate's survival or not in next generation.
     * @param x X coordinate
     * @param y Y coordinate
     * @return survival or not
     */
    private boolean nextGenerationOne(int x, int y) {
        final long neighborCount = CountNeighborCell(x, y);
        final boolean alive = map[y][x].isAlive();

        return neighborCount == 3 || (alive && neighborCount == 2);
    }


    /**
     * Change map to next generation.
     */
    public void nextGeneration() {
        updatePrefixSum();

        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                if (nextGenerationOne(x, y))
                    tempMap[y][x].setLive();
                else
                    tempMap[y][x].setDead();
            }
        }

        swapMap();
    }


    /**
     * Import map as 2d boolean
     * @param loadingMap map
     * @param startX start x coordinate  (default : 0)
     * @param startY start y coordinate  (default : 0)
     */
    public void loadMap(boolean[][] loadingMap, int startX, int startY) {
        final int loadingH = loadingMap.length;
        final int loadingW = loadingMap[0].length;

        for (int y = 1; y <= loadingH; y++) {
            for (int x = 1; x <= loadingW; x++) {
                final Cell cell = map[startY + y][startX + x];

                if (loadingMap[y - 1][x - 1])
                    cell.setLive();
                else
                    cell.setDead();
            }
        }
    }

    public void loadMap(boolean[][] loadingMap) {
        loadMap(loadingMap, 0, 0);
    }


    /**
     * Export map as 2d boolean
     * @return 2d boolean map
     */
    public boolean[][] saveMap() {
        var result = new boolean[height][width];

        for (int y = 1; y <= height; y++)
            for (int x = 1; x <= width; x++)
                result[y][x] = map[y][x].isAlive();

        return result;
    }


    /**
     * Getter of map
     * @return map
     */
    public Cell[][] getMap() {
        return map;
    }

    /**
     * Getter of height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of width
     * @return width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Deep copy self.
     * ** HIGH COST **
     * @return deep copied Grid
     */
    public Grid clone() {
        Grid result = new Grid(height, width);
        result.loadMap(saveMap());

        return result;
    }
}
