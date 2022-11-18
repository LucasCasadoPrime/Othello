package com.othello.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

import com.othello.model.Billboard;
import com.othello.model.Grid;
import com.othello.model.Player;
import com.othello.model.Score;
import com.othello.repositories.GridRepository;
import com.othello.repositories.ScoreRepository;

public class GameManagerApiIpml implements GameManagerApi{

  public GameManagerApiIpml() {}
  
  public void nextPlayer(ArrayList<Player> players) {
    if (players.get(0).isTurn()) {
      players.get(0).setTurn(false);
      players.get(1).setTurn(true);
    }
    else {
      players.get(0).setTurn(true);
      players.get(1).setTurn(false);
    }
  }

  public void initTurns(ArrayList<Player> players) {
    players.get(0).setTurn(true);
    players.get(1).setTurn(false);
  }

  public int whoPlay(ArrayList<Player> players, Grid grid) throws InterruptedException {
    if (players.get(0).isTurn()) {
      return players.get(0).play(grid);
    }
    else {
      return players.get(1).play(grid);
    }
  }

  public boolean isEnd(Grid grid, ArrayList<Player> players) {
    if (grid.isFull()) {
      return true;
    }
    return false;
  }


  public ArrayList<Score> saveScore(Grid grid, ArrayList<Player> players) {
    ArrayList<Score> scoreList = new ArrayList<Score>();
      for (int i = 0; i < 2; i++) {
        if (players.get(i).getC() == 'X') {
          scoreList.add(new Score(players.get(i).getName(), grid.countPions('X')));
        }
        if (players.get(i).getC() == 'O') {
          scoreList.add(new Score(players.get(i).getName(), grid.countPions('O')));
        }
      }
      return scoreList;
  }

  public void bestScores(ScoreRepository scoreRepository) {
    ArrayList<Score> scores = scoreRepository.findTop10ByOrderByScoreDesc();
    scores.sort(Comparator.comparing(Score::getScore).reversed());
    if (scores.size() == 0) {
      System.out.println("____________________");
      System.out.println("\nNo scores yet");
    } else {
      System.out.println("____________________");
      System.out.println("\nBest scores:\n");
      for (int i = 0; i < 10; i++) {
        System.out.println(scores.get(i).getName() + " : " + scores.get(i).getScore());
      }
    }
    System.out.println("____________________");
  }

  public Grid newGameOrLoad(Grid grid, GridRepository gridRepository ) {
    Optional<Grid> gridOpt = gridRepository.findTopByOrderByIdDesc();
    String choice = getInput("New game or load game? (new/load)");
    if (choice.equals("load")) {
      if (gridOpt.isPresent()) {
        grid = gridOpt.get();
      }
      else {
        System.out.println("No game to load");
        newGameOrLoad(grid, gridRepository);
      }
    }
    else if (choice.equals("new")) {
      grid = new Grid(8);
    }
    else {
      System.out.println("Invalid choice");
      newGameOrLoad(grid, gridRepository);
    }
    return grid;
  }

  public void saveGrid(Grid grid, GridRepository gridRepository) {
    gridRepository.save(grid);
    System.exit(0);
  }

  public void quitGame() {
    System.exit(0);
  }

  public void  forceQuit(int i) {
    if (i == 1000) {
      System.exit(0);
    }
  }
  
  public String getInput(String message) {
    Scanner sc = new Scanner(System.in);
    System.out.println(message);
    return sc.nextLine();
  }

  @Override
  public ArrayList<String> getModes() {
    ArrayList<String> modes = new ArrayList<String>();
    modes.add("PvP");
    modes.add("PvIA");
    modes.add("IAvIA");
    return modes;
  }

  @Override
  public Billboard getBillboard(ScoreRepository scoreRepository) {
    Billboard board = new Billboard();
    ArrayList<Score> scores = scoreRepository.findTop10ByOrderByScoreDesc();
    scores.sort(Comparator.comparing(Score::getScore).reversed());
    board.setBoard(scores);
    return board;
  }

}
