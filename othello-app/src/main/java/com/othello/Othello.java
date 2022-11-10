package com.othello;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.othello.model.Billboard;
import com.othello.model.Grid;
import com.othello.model.Player;
import com.othello.model.Score;
import com.othello.repositories.ScoreRepository;

@Component
public class Othello {

  @Autowired
  private ScoreRepository scoreRepository;

  private Billboard board;
  private Grid grid;
  private Display display;
  private ArrayList<Player> players;

  @PostConstruct
  void init() {
    initOthello();
    try {
      launchGame();
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void initOthello() {
    board = new Billboard();
    this.grid = new Grid(8);
    this.display = new Display();
    this.players = new ArrayList<Player>();
    grid.initGrid();
  }


  public void Bb() {
    ArrayList<Score> scores = scoreRepository.findTop10ByOrderByScoreDesc();
    scores.sort(Comparator.comparing(Score::getScore).reversed());
    System.out.println("\nTop 10 scores:\n");
    for (Score score : scores) {
      System.out.println(score.getName() + " " + score.getScore());
    }
    System.out.println("____________________");
  }


  public void launchGame() throws InterruptedException {
    Bb();
    int mode = Integer.parseInt(getInput("\n1 PvP\n2 PvIA\n3 IAvIA\n4 Exit\nChoose a game mode: "));
    switch (mode) {
      case 1:
        initPvp();
        break;
      case 2:
        initPvIA();
        break;
      case 3:
        initIAvIA();
        break;
      case 4:
        System.exit(0);
        break;
      default:
        System.out.println("Invalid input");
        launchGame();
        break;
    }
    Game();
  }

  public void initPvp() {
    players.add(FactoryPlayer.createPlayer("Human", getInput("Player 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("Human", getInput("Player 2 color: ").charAt(0)));
  }

  public ArrayList<Score> saveScore(Grid grid) {
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

  public void Game() throws InterruptedException {
    while (true) {
      for (Player player : players) {
        switch (player.play(grid)) {
          case 0:
            display.display(grid);
            if (grid.isFull()) {
              System.out.println("Game over");
              scoreRepository.saveAll(saveScore(grid));
              System.exit(0);
            }
            break;
          case 1:
            continue;
          case 2:
            System.out.println("no move possible for " + player.getName());
            scoreRepository.saveAll(saveScore(grid));
            System.exit(0);
            break;
          default:
            break;
        }
      }
    }
  }

  public void initPvIA() {
    players.add(FactoryPlayer.createPlayer("Human", getInput("Player color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA level: ")), getInput("IA color: ").charAt(0)));
  }

  public void initIAvIA() {
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA 1 level: ")), getInput("IA 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA 2 level: ")), getInput("IA 2 color: ").charAt(0)));
  }

  public String getInput(String message) {
      Scanner sc = new Scanner(System.in);
      System.out.println(message);
      return sc.nextLine();
  }

}
