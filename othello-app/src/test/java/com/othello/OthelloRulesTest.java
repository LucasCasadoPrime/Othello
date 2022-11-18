package com.othello;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.othello.model.Grid;

@RunWith(MockitoJUnitRunner.class)

public class OthelloRulesTest {
  
  @InjectMocks
  private Validator validator;
  
  @Test
  public void RulesTest() {
    Grid grid = new Grid(8);
    grid.initGrid();
   
    // Case vide en 0 0
    assertTrue(validator.checkIsFree(grid, 0, 0, 'X'));
    // Case vide en 3 4
    assertFalse(validator.checkIsFree(grid, 3, 4, 'X'));
    
    // Verifie si le pion peut etre posé n'importe où
    assertFalse(validator.reverseRules(grid, 0, 0, 'O'));
    // Verifie si le pion retourne un autre a 2 3 en regardant en bas
    assertTrue(validator.reverseRules(grid, 2, 3, 'X'));
    // Verifie si le pion retourne un autre a 4 2 en regardant par la droite
    assertTrue(validator.reverseRules(grid, 4, 2, 'O'));
    // Verifie si le pion retourne un autre a 2 3 en regardant par le haut
    assertTrue(validator.reverseRules(grid, 5, 3, 'X'));
    // Verifie si le pion retourne un autre a 2 3 en regardant par la gauche
    assertTrue(validator.reverseRules(grid, 4, 5, 'X'));
    // Verifie si un pion de la meme couleur peut etre posé à droite d'un autre
    assertFalse(validator.reverseRules(grid, 4, 2, 'X'));
    // Verifie si un pion de la meme couleur peut etre posé à gauche d'un autre
    assertFalse(validator.reverseRules(grid, 4, 5, 'O'));
    // Verifie si un pion de la meme couleur peut etre posé au dessus d'un autre
    assertFalse(validator.reverseRules(grid, 2, 3, 'O'));
    // Verifie si un pion de la meme couleur peut etre posé en dessous d'un autre
    assertFalse(validator.reverseRules(grid, 5, 3, 'O'));
    // Verifie si un mouvement est possible
    
  }

}
