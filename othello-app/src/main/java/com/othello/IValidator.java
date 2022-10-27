package com.othello;
public interface IValidator {

    public boolean checkIsFree(Grid grid, int i, int j, char c);
    public boolean rCheckUp(Grid grid, int i, int j, char c);
    public boolean rCheckDown(Grid grid, int i, int j, char c);
    public boolean rCheckLeft(Grid grid, int i, int j, char c);
    public boolean rCheckRight(Grid grid, int i, int j, char c);
    public boolean reverseRules(Grid grid, int i, int j, char c);
}
