package com.othello;
public class Pion {
    public int _X;
    public int _Y;
    public char _C;

    public Pion(int X, int Y, char C) {
        this._C = C;
        this._X = X;
        this._Y = Y;
    }

    public char getC() {
        return this._C;
    }

    public int getX() {
        return this._X;
    }

    public int getY() {
        return this._Y;
    }

    public void setC(char C) {
        this._C = C;
    }

    public void setX(int X) {
        this._X = X;
    }

    public void setY(int Y) {
        this._Y = Y;
    }

}