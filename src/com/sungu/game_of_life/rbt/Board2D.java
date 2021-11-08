package com.sungu.game_of_life.rbt;

import java.util.TreeSet;

/**
 * Abstract Conway's game of life Board in 3D with Red Black Tree,
 * no padding
 */
public class Board2D {

    private TreeSet<Cell2D> map, tempMap;

    public Board2D() {
        map     = new TreeSet<>();
        tempMap = new TreeSet<>();
    }

    /**
     * Check is that coordinate has Cell
     * @param x X coordinate
     * @param y Y coordinate
     * @return has cell or not
     */
    public boolean hasCell(int x, int y) {
        return map.contains(new Cell2D(x, y));
    }


    /**
     * Swap map with tempMap
     */
    private void swapMap() {
        var tmp = this.tempMap;
        this.tempMap = this.map;
        this.map = tmp;
    }


    /**
     * Get neighbor cell count.
     * @param x X coordinate
     * @param y Y coordinate
     * @return neighbor cell count
     */
    private long CountNeighborCell(int x, int y) {
        int sum = 0;
        Cell2D cell = new Cell2D();

        for(int j = y - 1; j <= y + 1; j++) {
            for (int i = x - 1; i <= x + 1; i++) {
                if (j == y && i == x) continue;

                cell.setPos(j, i);
                if(map.contains(cell))
                    sum++;

            }
        }

        return sum;
    }


    /**
     * Get one coordinate's survival or not in next generation.
     * @param x X coordinate
     * @param y Y coordinate
     * @return survival or not
     */
    protected boolean nextGenerationOne(int x, int y) {
        final long neighborCount = CountNeighborCell(x, y);
        final Cell2D cell = new Cell2D(x, y);
        final boolean alive = map.contains(cell);

        return neighborCount == 3 || (alive && neighborCount == 2);
    }


    /**
     * Change map to next generation.
     */
    public void nextGeneration() {
        tempMap.clear();

        int eXSize, eYSize;
        for (var e : map) {
            eXSize = e.getXPos();
            eYSize = e.getYPos();

            for (int y = eYSize - 1; y <= eYSize + 1; y++)
                for (int x = eXSize - 1; x <= eXSize + 1; x++)
                    if (nextGenerationOne(x, y))
                        tempMap.add(new Cell2D(x, y));
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
                if (loadingMap[y][x]) {
                    final Cell2D cell = new Cell2D(startX + x, startY + y);

                    map.add(cell);
                }
            }
        }
    }


    /**
     * Import map as 2d boolean with start point (0, 0)
     * @param loadingMap map
     */
    public void loadMap(boolean[][] loadingMap) {
        loadMap(loadingMap, 0, 0);
    }


    /**
     * Export map as 2d boolean
     * @param height export height
     * @param width export width
     * @return 2d boolean map
     */
    public boolean[][] saveMap(int height, int width) {
        var result = new boolean[height][width];

        for (var e : map) {
            result[e.getYPos()][e.getXPos()] = true;
        }

        return result;
    }


    /**
     * Getter of map
     * @return map
     */
    public TreeSet<Cell2D> getMap() {
        return map;
    }
}
