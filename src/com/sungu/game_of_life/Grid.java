package com.sungu.game_of_life;

/**
 * Abstract Conway's game of life Board
 */
public class Grid {

    private int height;
    private int width;
    private Cell[][] map;
    private Cell[][] tempMap;

    /**
     * @param h Height that not consider padding
     * @param w Width that not consider padding
     */
    public Grid(int h, int w) {
        height = h;
        width  = w;
        map     = new Cell[h+2][w+2];
        tempMap = new Cell[h+2][w+2];

        // 2d maps initialize
        for (int y = 0; y < h + 2; y++) {
            for (int x = 0; x < w + 2; x++) {
                map[y][x]     = new Cell();
                tempMap[y][x] = new Cell();
            }
        }
    }


    /**
     * Get one Cell from target
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
     * Get neighbor cell count.
     * @param x X coordinate that not consider padding
     * @param y Y coordinate that not consider padding
     * @return 인접 세포 수
     */
    private int CountNeighborCell(int x, int y) {
        int sum = 0;

        for (int i = y - 1; i <= y + 1; i++)
            for (int j = x - 1; j <= x + 1; j++)
                if (!(j == x && i == y))
                    sum += getCell(map, j, i).isAlive() ? 1 : 0;

        return sum;
    }

    /**
     * Get one coordinate's survival or not.
     * @param x X coordinate that not consider padding
     * @param y Y coordinate that not consider padding
     * @return survival or not
     */
    private boolean nextGenerationOne(int x, int y) {
//        if(x == 4 && y == 4)
//            System.out.println("");
        final int neighborCount = CountNeighborCell(x, y);
        final boolean alive = getCell(map, x, y).isAlive();

        return neighborCount == 3 || (alive && neighborCount == 2);
    }


    /**
     * Change map to next generation.
     */
    public void nextGeneration() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (nextGenerationOne(x, y))
                    getCell(tempMap, x, y).setLive();
                else
                    getCell(tempMap, x, y).setDead();
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

        for (int y = 0; y < loadingH; y++) {
            for (int x = 0; x < loadingW; x++) {
                final Cell cell = getCell(map, startX + x, startY + y);

                if (loadingMap[y][x])
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

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                result[y][x] = getCell(map, x, y).isAlive();

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
