package com.othello;

import com.othello.model.Grid;

public class Display {
    public Grid display(Grid grid) {
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.getGrid()[i][j].getC() == ' ') {
                    System.out.print('.');
                } else {
                    System.out.print(grid.getGrid()[i][j].getC());
                }
            }
            System.out.println();
        }
        return grid;
    }
}
