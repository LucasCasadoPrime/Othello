package com.othello;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
   
    assertFalse(validator.reverseRules(grid, 0, 0, 'O'));
    assertTrue(validator.reverseRules(grid, 4, 3, 'X'));
  }
}
