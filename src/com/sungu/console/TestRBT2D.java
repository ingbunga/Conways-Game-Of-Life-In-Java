package com.sungu.console;

import com.sungu.game_of_life.rbt.*;

import java.util.TreeMap;

public class TestRBT2D {
    public static String grid2String(Board2D grid) {
        String result = "";
        final boolean[][] map = grid.saveMap(11, 51);

        for (var line : map) {
            for (var e : line) {
                result += e ? '0' : '.';
            }
            result += '\n';
        }

        return result;
    }

    public static void main(String[] args) {
        var grid = new Board2D();

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


        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            System.out.println(TestRBT2D.grid2String(grid));
            grid.nextGeneration();
        }

        // End
        long endTime = System.currentTimeMillis();

        // Result
        long Result_Time = endTime - startTime;
        System.out.println("Result : " + Result_Time + "(ms)");

    }
}
