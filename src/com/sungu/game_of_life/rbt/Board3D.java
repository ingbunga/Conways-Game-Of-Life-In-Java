package com.sungu.game_of_life.rbt;

import java.util.TreeSet;

/**
 * Abstract Conway's game of life Board in 3D with Red Black Tree
 */
public class Board3D {

    private int xSize, ySize, zSize;
    private TreeSet<Cell> map, tempMap;

    private boolean[] liveToLiveWhen, deadToLiveWhen;

    /**
     * @param xSize xSize that not consider padding
     * @param ySize ySize that not consider padding
     * @param zSize zSize that not consider padding
     */
    public Board3D(int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        liveToLiveWhen = new boolean[27];
        deadToLiveWhen = new boolean[27];

        map = new TreeSet<>();
        tempMap = new TreeSet<>();

    }


//    public Cell getCell(Cell[][][] from, int x, int y, int z) {
//        return from[z + 1][y + 1][x + 1];
//    }

    /**
     * return ture if any cell is located in coordinate
     * @param x X coordinate that not consider padding
     * @param y Y coordinate that not consider padding
     * @param z Z coordinate that not consider padding
     * @return is located
     */
    public boolean hasCell(int x, int y, int z) {
        return map.contains(new Cell(x + 1, y + 1, z + 1));
    }


    /**
     * Swap map with tempMap
     */
    private void swapMap() {
        TreeSet<Cell> tmp = this.tempMap;
        this.tempMap = this.map;
        this.map = tmp;
    }


    /**
     * Get neighbor cell count.
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return neighbor cell count
     */
    private int CountNeighborCell(int x, int y, int z) {
        int sum = 0;
        Cell cur = new Cell();
        for (int i = z - 1; i <= z + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                for (int k = x - 1; k <= x + 1; k++) {
                    if (i == z && j == y && k == x)
                        continue;

                    cur.setPos(k, j, i);
                    if (map.contains(cur))
                        sum++;

                }
            }
        }
        return sum;
    }


    /**
     * Get one coordinate's survival or not in next generation.
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return survival or not
     */
    private boolean nextGenerationOne(int x, int y, int z) {
        final int neighborCount = CountNeighborCell(x, y, z);
        final Cell finding = new Cell(x, y, z);
        final boolean alive = map.contains(finding);

        return (!alive && deadToLiveWhen[neighborCount]) || (alive && liveToLiveWhen[neighborCount]);
    }


    /**
     * Change map to next generation.
     */
    public void nextGeneration() {
        tempMap.clear();

        int eXSize, eYSize, eZSize;
        for (var e : map) {
            eXSize = e.getXPos();
            eYSize = e.getYPos();
            eZSize = e.getZPos();

            for (int z = eZSize - 1; z <= eZSize + 1; z++) {
                for (int y = eYSize - 1; y <= eYSize + 1; y++) {
                    for (int x = eXSize - 1; x <= eXSize + 1; x++) {
                        if (nextGenerationOne(x, y, z))
                            tempMap.add(new Cell(x, y, z));
                    }
                }
            }
        }

        swapMap();
    }


    /**
     * Import map as 3d boolean
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
                    if (loadingMap[z - 1][y - 1][x - 1]) {
                        final Cell cell = new Cell(startX + x, startY + y, startZ + z);

                        map.add(cell);
                    }
                }
            }
        }
    }

    public void loadMap(boolean[][][] loadingMap) {
        loadMap(loadingMap, 0, 0, 0);
    }


    /**
     * Export map as 3d boolean
     * @return 3d boolean map
     */
    public boolean[][][] saveMap() {
        var result = new boolean[zSize][ySize][xSize];

        for (Cell e : map) {
            int x = e.getXPos() - 1;
            int y = e.getYPos() - 1;
            int z = e.getZPos() - 1;
            result[z][y][x] = hasCell(z, y, x);
        }

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

    public TreeSet<Cell> getMap() {
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
