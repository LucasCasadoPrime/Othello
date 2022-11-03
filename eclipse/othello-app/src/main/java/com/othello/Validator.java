package com.othello;

import com.othello.model.Grid;

public class Validator implements IValidator {

    public Validator() {}

    public boolean checkIsFree(Grid grid, int i, int j, char c) {
        if (grid.getGrid()[i][j].getC() == ' ') {
            return true;
        }
        return false;
    }

    private boolean rCheckUp(Grid grid, int i, int j, char C) {
        int k = i - 1;
        while (k >= 0) {
            if (grid.getGrid()[k][j].getC() == C) {
                break;
            }
            else if (grid.getGrid()[k][j].getC() == ' ') {
                break;
            }
            else {
                k--;
            }
        }
        if (k >= 0) {
            if (grid.getGrid()[k][j].getC() == C) {
                for (int l = i - 1; l > k;) {
                    grid.getGrid()[l][j].setC(C);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rCheckDown(Grid grid, int i, int j, char c) {
        int k = i + 1;
        while (k < grid.getGrid().length) {
            if (grid.getGrid()[k][j].getC() == c) {
                break;
            }
            else if (grid.getGrid()[k][j].getC() == ' ') {
                break;
            }
            else {
                k++;
            }
        }
        if (k < grid.getGrid().length) {
            if (grid.getGrid()[k][j].getC() == c) {
                for (int l = i + 1; l < k;) {
                    grid.getGrid()[l][j].setC(c);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rCheckLeft(Grid grid, int i, int j, char c) {
        int k = j - 1;
        while (k >= 0) {
            if (grid.getGrid()[i][k].getC() == c) {
                break;
            }
            else if (grid.getGrid()[i][k].getC() == ' ') {
                break;
            }
            else {
                k--;
            }
        }
        if (k >= 0) {
            if (grid.getGrid()[i][k].getC() == c) {
                for (int l = j - 1; l > k;) {
                    grid.getGrid()[i][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean rCheckRight(Grid grid, int i, int j, char c) {
        int k = j + 1;
        while (k < grid.getGrid().length) {
            if (grid.getGrid()[i][k].getC() == c) {
                break;
            }
            else if (grid.getGrid()[i][k].getC() == ' ') {
                break;
            }
            else {
                k++;
            }
        }
        if (k < grid.getGrid().length) {
            if (grid.getGrid()[i][k].getC() == c) {
                for (int l = j + 1; l < k;) {
                    grid.getGrid()[i][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean aMoveIsPossible(Grid grid) {
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid().length; j++) {
                if (grid.getGrid()[i][j].getC() == ' ') {
                    if (rCheckUp(grid, i, j, 'X') || rCheckDown(grid, i, j, 'X') || rCheckLeft(grid, i, j, 'X') || rCheckRight(grid, i, j, 'X') || rCheckUp(grid, i, j, 'O') || rCheckDown(grid, i, j, 'O') || rCheckLeft(grid, i, j, 'O') || rCheckRight(grid, i, j, 'O')) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean reverseRules(Grid grid, int i, int j, char c) {
        if (rCheckUp(grid, i, j, c) || rCheckDown(grid, i, j, c) || rCheckLeft(grid, i, j, c) || rCheckRight(grid, i, j, c)) {
            return true;
        }
        return false;
    }
}