package com.othello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

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
    
  }
  
  @Test
  public void FactoryTest() {
    Player player = FactoryPlayer.createPlayer("IA_lvl_0", 'X');
    assertTrue(player instanceof IA_lvl_0);
  }

  @Test
  public void GridTest() {
    Grid grid = new Grid(8);
    Pion pion = new Pion(0,0,'X');
    grid.initGrid();
    // Verifie si la grille est bien initialisée
    assertEquals(grid.getGrid()[3][3].getC(), 'O');
    assertEquals(grid.getGrid()[3][4].getC(), 'X');
    assertEquals(grid.getGrid()[4][3].getC(), 'X');
    assertEquals(grid.getGrid()[4][4].getC(), 'O');
    // Compte les pions
    assertEquals(grid.countPions('X'), 2);
    assertNotEquals(grid.countPions('O'), 3);
    // Verifie si la grille est bien remplie
    assertFalse(grid.isFull());
    grid.setAllGrid(pion);
    assertTrue(grid.isFull());
  }

  @Test
  public void PionTest() {
    Pion pion = new Pion(0,0,'X');
    // Verifie si le pion est bien initialisé
    assertEquals(pion.getX(), 0);
    assertEquals(pion.getY(), 0);
    assertEquals(pion.getC(), 'X');
    // Verifie si le pion est bien modifié
    pion.setX(1);
    pion.setY(1);
    pion.setC('O');
    assertEquals(pion.getX(), 1);
    assertEquals(pion.getY(), 1);
    assertEquals(pion.getC(), 'O');
  }

  @Test
  public void HumanTest() throws InterruptedException {
  Human player = new Human("Human",'X');
  int[] tab = new int[2];
  tab[0] = 2;
  tab[1] = 3;
    // Verifie si le joueur est bien initialisé
    assertEquals(player.getC(), 'X');
    assertEquals(player.getName(), "Human");
    // Verifie si le joueur est bien modifié
    player.setC('O');
    player.setName("IA");
    assertEquals(player.getC(), 'O');
    assertEquals(player.getName(), "IA");
  }

  @Test
  public void IATest() {
    Player player = new IA_lvl_0("IA",'X');
    // Verifie si le joueur est bien initialisé
    assertEquals(player.getC(), 'X');
    assertEquals(player.getName(), "IA");
    // Verifie si le joueur est bien modifié
    player.setC('O');
    player.setName("Human");
    assertEquals(player.getC(), 'O');
    assertEquals(player.getName(), "Human");
  }

  @Test
  public void MoveTest() {
    Grid grid = new Grid(8);
    Pion pion = new Pion(2,3,'X');
    Move move = new Move();
    grid.initGrid();
    // Verifie si le mouvement fonctionne
    move.move(grid, pion);
    assertEquals(grid.getGrid()[2][3].getC(), 'X');
  }

  @Test
  public void displayTest() {
    Grid grid = new Grid(8);
    grid.initGrid();
    // Verifie si l'affichage fonctionne
    grid.displayGrid();
  }
}