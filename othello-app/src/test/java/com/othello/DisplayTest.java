package com.othello;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.othello.model.Grid;

@RunWith(MockitoJUnitRunner.class)
public class DisplayTest {
    
    @InjectMocks
    private Display display;
    
    @Test
    public void displayTest() {
        Grid grid = new Grid(8);
        grid.initGrid();
        assertTrue(display.display(grid).getGrid()[0][0].getC() == ' ');
    }
}
