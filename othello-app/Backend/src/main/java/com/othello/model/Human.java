package com.othello.model;

import java.util.Scanner;

import com.othello.Move;
import com.othello.Validator;

public class Human implements Player {

  private String type;
  private String name;
  private char c;
  private boolean turn;
  private Move move;
  private Validator validator;
  private Scanner sc = new Scanner(System.in);

  public Human() {}

  public Human(String type, String name, char c) {
    this.type = type;
    this.name = name;
    this.c = c;
    this.move = new Move();
    this.validator = new Validator();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public char getC() {
    return this.c;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setC(char C) {
    this.c = C;
  }

  @Override
  public int play(Grid grid) {
    String coords = getCoords();
    if (coords.equals("quit")) {
      return 3;
    }
    if (coords.equals("save and quit")) {
      return 4;
    }
    int x = Integer.parseInt(coords.split(" ")[0]);
    int y = Integer.parseInt(coords.split(" ")[1]);
    Pion pion = new Pion(x, y, this.c);
    if (validator.reverseRules(grid,x, y, c)) {
      move.move(grid, pion);
      return 0;
    }
    return 1;
  }

  private String getCoords() {
    System.out.println("Enter the coordinates of the pion you want to place (x,y): ");
    String coords = sc.nextLine();
    return coords;
  }

  @Override
  public boolean isTurn() {
    return this.turn;
  }

  @Override
  public void setTurn(boolean turn) {
    this.turn = turn;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public void setType(String type) {
    this.type = type;
  }
}
