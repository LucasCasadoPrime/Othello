package com.othello;
public interface Player {
    public void play(Grid grid) throws InterruptedException;
    public String getName();
    public char getC();
    public void setName(String name);
    public void setC(char C);
}
