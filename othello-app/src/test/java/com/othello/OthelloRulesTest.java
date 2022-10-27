package com.othello;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class OthelloRulesTest {
  
  @InjectMocks
  private Validator validator;
  
  @Test
  public void totoTest() {
    Grid grid = new Grid(8);
    grid.initGrid();
   
    assertFalse( validator.rCheckDown(grid, 0, 0, 'O'));
  }
}
