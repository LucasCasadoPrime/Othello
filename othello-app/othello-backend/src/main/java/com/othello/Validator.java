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
    
    private boolean rCheckUpLeft(Grid grid, int i, int j, char c) {
        int k = i - 1;
        int l = j - 1;
        while (k >= 0 && l >= 0) {
            if (grid.getGrid()[k][l].getC() == c) {
                break;
            }
            else if (grid.getGrid()[k][l].getC() == ' ') {
                break;
            }
            else {
                k--;
                l--;
            }
        }
        if (k >= 0 && l >= 0) {
            if (grid.getGrid()[k][l].getC() == c) {
                for (k = i - 1, l = j - 1; k > k && l > l;) {
                    grid.getGrid()[k][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rCheckUpRight(Grid grid, int i, int j, char c) {
        int k = i - 1;
        int l = j + 1;
        while (k >= 0 && l < grid.getGrid().length) {
            if (grid.getGrid()[k][l].getC() == c) {
                break;
            }
            else if (grid.getGrid()[k][l].getC() == ' ') {
                break;
            }
            else {
                k--;
                l++;
            }
        }
        if (k >= 0 && l < grid.getGrid().length) {
            if (grid.getGrid()[k][l].getC() == c) {
                for (k = i - 1, l = j + 1; k > k && l < l;) {
                    grid.getGrid()[k][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rCheckDownLeft(Grid grid, int i, int j, char c) {
        int k = i + 1;
        int l = j - 1;
        while (k < grid.getGrid().length && l >= 0) {
            if (grid.getGrid()[k][l].getC() == c) {
                break;
            }
            else if (grid.getGrid()[k][l].getC() == ' ') {
                break;
            }
            else {
                k++;
                l--;
            }
        }
        if (k < grid.getGrid().length && l >= 0) {
            if (grid.getGrid()[k][l].getC() == c) {
                for (k = i + 1, l = j - 1; k < k && l > l;) {
                    grid.getGrid()[k][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rCheckDownRight(Grid grid, int i, int j, char c) {
        int k = i + 1;
        int l = j + 1;
        while (k < grid.getGrid().length && l < grid.getGrid().length) {
            if (grid.getGrid()[k][l].getC() == c) {
                break;
            }
            else if (grid.getGrid()[k][l].getC() == ' ') {
                break;
            }
            else {
                k++;
                l++;
            }
        }
        if (k < grid.getGrid().length && l < grid.getGrid().length) {
            if (grid.getGrid()[k][l].getC() == c) {
                for (k = i + 1, l = j + 1; k < k && l < l;) {
                    grid.getGrid()[k][l].setC(c);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkIfNoMove(Grid grid, char c) {
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid().length; j++) {
                if (grid.getGrid()[i][j].getC() == ' ') {
                    if (reverseRules(grid, i, j, c)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    @Override
    public boolean reverseRules(Grid grid, int i, int j, char c) {
        if (rCheckUp(grid, i, j, c) || rCheckDown(grid, i, j, c) || rCheckLeft(grid, i, j, c) || rCheckRight(grid, i, j, c) || rCheckUpLeft(grid, i, j, c) || rCheckUpRight(grid, i, j, c) || rCheckDownLeft(grid, i, j, c) || rCheckDownRight(grid, i, j, c)) {
            return true;
        }
        return false;
    }
}