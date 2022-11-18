package com.othello.model;

public interface Player {
    
    public int play(Grid grid) throws InterruptedException;
    public String getName();
    public char getC();
    public void setName(String name);
    public void setC(char C);
    public boolean isTurn();
    public void setTurn(boolean turn);
    public String getType();
    public void setType(String type);
}
