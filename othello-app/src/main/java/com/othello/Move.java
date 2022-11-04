package com.othello;

import com.othello.model.Grid;
import com.othello.model.Pion;

public class Move {

    public Grid move(Grid grid, Pion pion) {
        if (grid.getGrid()[pion.getX()][pion.getY()].getC() == ' ') {
            grid.getGrid()[pion.getX()][pion.getY()].setC(pion.getC());
        }
        return grid;
    }

}
