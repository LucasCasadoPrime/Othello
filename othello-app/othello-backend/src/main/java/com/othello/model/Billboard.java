package com.othello.model;

import java.util.ArrayList;


public class Billboard {
  
  ArrayList<Score> board;
  
  public Billboard() {

    board = new ArrayList<Score>();
  }

  public int getSize() {
    return board.size();
  }

  public ArrayList<Score> getBoard() {
    return board;
  }

  public void setBoard(ArrayList<Score> board) {
    this.board = board;
  }

}
