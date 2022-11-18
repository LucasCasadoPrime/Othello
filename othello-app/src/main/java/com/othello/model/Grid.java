package com.othello.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.othello.Validator;

@Document(collection = "grid")
public class Grid {
  public String id;
  public int size;
  public Pion[][] grid;
  public Validator validator;

  public Grid(int size) {
    this.size = size;
    this.grid = new Pion[size][size];
    initGrid();
  }

  public int getSize() {
    return this.size;
  }

  public Pion[][] getGrid() {
    return this.grid;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setGrid(Pion[][] grid) {
    this.grid = grid;
  }

  public void setAllGrid(Pion pion) {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.grid[i][j] = pion;
      }
    }
  }

  public Pion[][] initGrid() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.grid[i][j] = new Pion(i, j, ' ');
      }
      this.grid[this.size / 2][this.size / 2] = new Pion(this.size / 2, this.size / 2, 'O');
      this.grid[this.size / 2 - 1][this.size / 2 - 1] = new Pion(this.size / 2 - 1, this.size / 2 - 1, 'O');
      this.grid[this.size / 2 - 1][this.size / 2] = new Pion(this.size / 2 - 1, this.size / 2, 'X');
      this.grid[this.size / 2][this.size / 2 - 1] = new Pion(this.size / 2, this.size / 2 - 1, 'X');
    }
    return this.grid;
  }

  public Pion getPion(int i, int j) {
    return this.grid[i][j];
  }

  public void setPion(int i, int j, Pion pion) {
    this.grid[i][j] = pion;
  }

  public boolean isFull() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.grid[i][j].getC() == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public int countPions(char C) {
    int count = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.grid[i][j].getC() == C) {
          count++;
        }
      }
    }
    return count;
  }

  public boolean aMoveIsPossible(Grid grid, Player player) {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.grid[i][j].getC() == ' ') {
          if (validator.reverseRules(grid, i, j, player.getC())) {
            return true;
          }
        }
      }
    }
    return false;
  }

}