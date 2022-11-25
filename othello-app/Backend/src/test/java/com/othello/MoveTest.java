package com.othello;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.othello.model.Grid;
import com.othello.model.Pion;

@RunWith(MockitoJUnitRunner.class)
public class MoveTest {

    @InjectMocks
    private Move move;

    @Test
    public void placeTest() {
        Grid grid = new Grid(8);
        grid.initGrid();
        Pion pion = new Pion(0, 0, 'X');
        assertTrue(move.move(grid, pion).getGrid()[0][0].getC() == 'X');
        assertFalse(move.move(grid, pion).getGrid()[0][0].getC() == 'O');
    }
    
}
