package com.othello;

import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.othello.api.GameManagerApiIpml;
import com.othello.model.Billboard;
import com.othello.model.Grid;
import com.othello.model.Player;
import com.othello.repositories.GridRepository;
import com.othello.repositories.ScoreRepository;

@Component
public class Othello {

  @Autowired
  private ScoreRepository scoreRepository;

  @Autowired
  private GridRepository gridRepository;
  
  private Grid grid;
  @Autowired
  private GameManagerApiIpml gameManager;
  @Autowired
  private Display display;
  private ArrayList<Player> players = new ArrayList<Player>();;

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
    new Billboard();
    this.grid = this.gameManager.newGameOrLoad(grid, gridRepository);
  }

  public void launchGame() throws InterruptedException {
    gameManager.bestScores(scoreRepository);
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
    System.out.println("____________________");
    Game();
  }

  public void Game() throws InterruptedException {
    int count = 0;
    display.display(grid);
    gameManager.initTurns(players);
    while (!gameManager.isEnd(grid, players)) {
      int choice = gameManager.whoPlay(players, grid);
      switch (choice) {
        case 0:
          display.display(grid);
          gameManager.nextPlayer(players);
          count = 0;
          break;
        case 1:
          count ++;
          gameManager.forceQuit(count);
          continue;
        case 3:
          gameManager.quitGame();
          break;
        default:
          break;
      }
    }
  }

  public void initPvp() {
    players.add(FactoryPlayer.createPlayer("Human", getInput("Player 1 name: "),getInput("Player 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("Human",getInput("Player 2 name: "), getInput("Player 2 color: ").charAt(0)));
  }
  
  public void initPvIA() {
    players.add(FactoryPlayer.createPlayer("Human", getInput("Player name: "), getInput("Player color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA 1 level: ")), getInput("Player name: "), getInput("IA color: ").charAt(0)));
  }

  public void initIAvIA() {
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA 1 level: ")), getInput("Player name: "), getInput("IA 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA 2 level: ")), getInput("Player name: "), getInput("IA 2 color: ").charAt(0)));
  }

  public String getInput(String message) {
      Scanner sc = new Scanner(System.in);
      System.out.println(message);
      return sc.nextLine();
  }

}
