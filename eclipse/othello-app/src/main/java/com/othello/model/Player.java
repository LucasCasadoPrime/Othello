package com.othello.model;

public interface Player {
    
    public boolean play(Grid grid) throws InterruptedException;
    public String getName();
    public char getC();
    public void setName(String name);
    public void setC(char C);
}
