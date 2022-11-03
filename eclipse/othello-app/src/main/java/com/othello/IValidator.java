package com.othello;

import com.othello.model.Grid;

public interface IValidator {

    public boolean reverseRules(Grid grid, int i, int j, char c);
}
