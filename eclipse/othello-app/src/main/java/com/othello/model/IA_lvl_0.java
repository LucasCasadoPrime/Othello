package com.othello.model;

import com.othello.Move;
import com.othello.Validator;

public class IA_lvl_0 implements Player{
    
    private String _name;
    private char _C;
    private Move move;
    private Validator validator;

    public IA_lvl_0(String name, char C) {
        this.move = new Move();
        this.validator = new Validator();
        this._name = name;
        this._C = C;
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public char getC() {
        return this._C;
    }

    @Override
    public void setName(String name) {
        this._name = name;
    }

    @Override
    public void setC(char C) {
        this._C = C;
    }

    @Override
    public boolean play(Grid grid) throws InterruptedException {   
        int[] coords = process(grid);
        Pion pion = new Pion(coords[0], coords[1], this._C);
        if (validator.reverseRules(grid, coords[0], coords[1], this._C)) {
            move.move(grid, pion);
            Thread.sleep(500);
            return true;
        }
        else if (validator.aMoveIsPossible(grid)){
            System.out.println("No move possible");
            return false;
        }
        else {
            play(grid);
            return true;
        }
    }

    public int[] process(Grid grid) {
        int[] coords = new int[2];
        coords[0] = (int) (Math.random() * 8);
        coords[1] = (int) (Math.random() * 8);
        if (grid.getGrid()[coords[0]][coords[1]].getC() == ' ') {
            return coords;
        } else {
            return process(grid);
        }
    }
}
