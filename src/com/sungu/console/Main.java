package com.sungu.console;

import com.sungu.game_of_life.*;

public class Main {
    public static String grid2String(Board2D grid) {
        String result = "";
        final Cell[][] map = grid.getMap();

        final int height = grid.getHeight();
        final int width = grid.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result += grid.getCell(map, x, y).isAlive() ? 'O' : '.';
            }
            result += '\n';
        }

        return result;
    }

    public static void main(String[] args) {
	    var grid = new Board2D(11, 51);

        final boolean X = false;
        final boolean O = true;


        boolean[][] map = {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, O, X, X, X},
                {X, X, O, O, O, X, X},
                {X, X, X, O, X, X, X},
                {X, X, X, X, X, X, X},
        };

        grid.loadMap(map);


        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            System.out.println(Main.grid2String(grid));
            grid.nextGeneration();
        }

        // End
        long endTime = System.currentTimeMillis();

        // Result
        long Result_Time = endTime - startTime;
        System.out.println("Result : " + Result_Time + "(ms)");

    }
}
