package com.othello.model;

import java.util.Scanner;

import com.othello.Move;
import com.othello.Validator;

public class Human implements Player {

  private String _name;
  private char _C;
  private Move move;
  private Validator validator;
  private Scanner sc = new Scanner(System.in);

  public Human(String name, char C) {
    this.move = new Move();
    this.validator = new Validator();
    this._name = name;
    this._C = C;
  }

  @Override
  public String getName() {
    return this._name;
  }

  @Override
  public char getC() {
    return this._C;
  }

  @Override
  public void setName(String name) {
    this._name = name;
  }

  @Override
  public void setC(char C) {
    this._C = C;
  }

  @Override
  public int play(Grid grid) throws InterruptedException {
    int[] coords = getCoords();
    Pion pion = new Pion(coords[0], coords[1], this._C);
    if (validator.reverseRules(grid,coords[0], coords[1], _C)) {
        move.move(grid, pion);
        Thread.sleep(500);
        return 0;
    }
    if (validator.noMoveisPossible(grid, _C)) {
        return 2;
    }
    return 1;
  }

  private int[] getCoords() {
    int[] coords = new int[2];
    System.out.print("Where do you want to put your coin ?: ");
    coords[0] = sc.nextInt();
    coords[1] = sc.nextInt();
    return coords;
  }
}
