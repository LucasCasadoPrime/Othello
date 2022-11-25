package com.othello.model;
public class Pion {
    public int x;
    public int y;
    public char c;

    public Pion() {}
    
    public Pion(int x, int y, char c) {
        this.c = c;
        this.x = x;
        this.y = y;
    }

    public char getC() {
        return this.c;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setC(char C) {
        this.c = C;
    }

    public void setX(int X) {
        this.x = X;
    }

    public void setY(int Y) {
        this.y = Y;
    }

}