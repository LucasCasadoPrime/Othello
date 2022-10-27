package com.othello;
public class Move {

    public Grid move(Grid grid, Pion pion) {
        if (grid.getGrid()[pion.getX()][pion.getY()].getC() == ' ') {
            grid.getGrid()[pion.getX()][pion.getY()].setC(pion.getC());
        }
        return grid;
    }

}
