package com.othello.model;

import com.othello.Move;
import com.othello.Validator;

public class IA_lvl_0 implements Player{
    
    private String type;
    private String name;
    private char c;
    private boolean turn;
    private Move move;
    private Validator validator;

    public IA_lvl_0() {}

    public IA_lvl_0(String type,String name, char c) {
        this.type = type;
        this.name = name;
        this.c = c;
        this.turn = false;
        this.move = new Move();
        this.validator = new Validator();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public char getC() {
        return this.c;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setC(char C) {
        this.c = C;
    }

    @Override
    public int play(Grid grid) throws InterruptedException {   
        int[] coords = process(grid);
        Pion pion = new Pion(coords[0], coords[1], this.c);
        if (validator.reverseRules(grid,coords[0], coords[1], c)) {
            move.move(grid, pion);
            Thread.sleep(500);
            return 0;
        }
        return 1;

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

    @Override
    public boolean isTurn() {
        return this.turn;
    }

    @Override
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
