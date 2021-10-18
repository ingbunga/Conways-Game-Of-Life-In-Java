package com.sungu.console;

import com.sungu.game_of_life.*;

public class Main {
    public static String grid2String(Grid grid) {
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
	    var grid = new Grid(11, 11);

        final boolean X = false;
        final boolean O = true;


        boolean[][] map = {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, O, X},
                {X, X, X, X, O, O, O},
                {X, X, X, X, X, O, X},
        };

        grid.loadMap(map);

        for (int i = 0; i < 10; i++) {
            System.out.println(Main.grid2String(grid));
            grid.nextGeneration();
        }
    }
}
