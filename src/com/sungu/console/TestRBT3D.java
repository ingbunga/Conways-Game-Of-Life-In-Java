package com.sungu.console;

import com.sungu.game_of_life.rbt.Board3D;

public class TestRBT3D {
    public static String board3dToString(Board3D grid) {
        String result = "vec![ ";

        final int xSize = grid.getXSize();
        final int ySize = grid.getYSize();
        final int zSize = grid.getZSize();


        for (int z = 0; z < zSize; z++) {
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    result += grid.hasCell(x, y, z)
                            ? "(" + x + ", " + y + ", " + z + "), "
                            : "";
                }
            }
        }

        result += "],";


        return result;
    }

    public static void main(String[] args) {
        var board = new Board3D(51, 51, 51);

        final boolean X = false;
        final boolean O = true;


        boolean[][][] map = {
            {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X}
            }, {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
            }, {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, O, X},
                {X, X, X, X, X, X, X},
            }, {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, O, X},
                {X, X, X, X, O, O, O},
                {X, X, X, X, X, O, X},
            }, {
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, X, X},
                {X, X, X, X, X, O, X},
                {X, X, X, X, X, X, X},
            },
        };


        int[] ltl = {5};
        int[] dtl = {4, 5};

        board.enableLiveToLiveWhen(ltl);
        board.enableDeadToLiveWhen(dtl);

        board.loadMap(map);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            System.out.println(TestRBT3D.board3dToString(board));
            board.nextGeneration();
        }

        // End
        long endTime = System.currentTimeMillis();

        // Result
        long Result_Time = endTime - startTime;
        System.out.println("Result : " + Result_Time + "(ms)");

    }
}
