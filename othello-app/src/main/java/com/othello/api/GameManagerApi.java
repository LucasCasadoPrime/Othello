package com.othello.api;

import java.util.ArrayList;

import com.othello.model.Billboard;
import com.othello.model.Grid;
import com.othello.model.Player;

public interface GameManagerApi {
  
  public ArrayList<String> getModes();
  public Billboard getBillboard();
  public Grid newGameOrLoad();
  public void bestScores();
  public void initTurns(ArrayList<Player> players);
  public boolean isEnd(Grid grid, ArrayList<Player> players);
  public void nextPlayer(ArrayList<Player> players);
  public void forceQuit(int count);
  public void quitGame();
  public int whoPlay(ArrayList<Player> players, Grid grid) throws InterruptedException;
  public String getInput(String message);
  public void saveGrid(Grid grid);
}
