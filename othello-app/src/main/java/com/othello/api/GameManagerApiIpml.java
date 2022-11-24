package com.othello.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.othello.model.Billboard;
import com.othello.model.Grid;
import com.othello.model.Modes;
import com.othello.model.Player;
import com.othello.model.Score;
import com.othello.repositories.GridRepository;
import com.othello.repositories.ScoreRepository;

@Component
public class GameManagerApiIpml implements GameManagerApi {
  
  @Autowired
  private ScoreRepository scoreRepository;

  @Autowired
  private GridRepository gridRepository;

  @Autowired
  private DisplayApi displayApi;
  
  private Grid grid;

  public GameManagerApiIpml() {}
  
  @Override
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

  @Override
  public void initTurns(ArrayList<Player> players) {
    players.get(0).setTurn(true);
    players.get(1).setTurn(false);
  }

  @Override
  public int whoPlay(ArrayList<Player> players, Grid grid) throws InterruptedException {
    if (players.get(0).isTurn()) {
      return players.get(0).play(grid);
    }
    else {
      return players.get(1).play(grid);
    }
  }

  @Override
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

  @Override
  public void bestScores() {
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

  @Override
  public Grid newGameOrLoad() {
    Optional<Grid> gridOpt = gridRepository.findTopByOrderByIdDesc();
    String choice = getInput("New game or load game? (new/load)");
    if (choice.equals("load")) {
      if (gridOpt.isPresent()) {
        grid = gridOpt.get();
      }
      else {
        System.out.println("No game to load");
        newGameOrLoad();
      }
    }
    else if (choice.equals("new")) {
      grid = new Grid(8);
    }
    else {
      System.out.println("Invalid choice");
      newGameOrLoad();
    }
    return grid;
  }

  @Override
  public void saveGrid(Grid grid) {
    gridRepository.save(grid);
    System.exit(0);
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }

  @Override
  public void  forceQuit(int i) {
    if (i == 1000) {
      System.exit(0);
    }
  }
  
  @Override
  public String getInput(String message) {
    Scanner sc = new Scanner(System.in);
    System.out.println(message);
    return sc.nextLine();
  }

  @Override
  public Billboard getBillboard() {
    Billboard board = new Billboard();
    ArrayList<Score> scores = scoreRepository.findTop10ByOrderByScoreDesc();
    scores.sort(Comparator.comparing(Score::getScore).reversed());
    board.setBoard(scores);
    return board;
  }
  
  @Override
  public ArrayList<String> getModes() {
    ArrayList<String> m = new ArrayList<String>();
    for (Modes mode : Modes.values()) {
      m.add(mode.getMode());
    }
    return m;
  }
}
