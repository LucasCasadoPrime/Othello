package com.othello.model;

import java.util.ArrayList;


public class Billboard {
  
  ArrayList<String> board;
  
  public Billboard() {

    board = new ArrayList<String>();
  }

  public int getSize() {
    return board.size();
  }

  public ArrayList<String> getBoard() {
    return board;
  }

  public void setBoard(ArrayList<String> board) {
    this.board = board;
  }

}
