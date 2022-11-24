package com.othello;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.othello.api.GameManagerApi;
import com.othello.model.Player;

@Component
public class Othello {
  
  @Autowired
  private GameManagerApi gameManager;

  private ArrayList<Player> players = new ArrayList<Player>();
  
  public void choosingMode(String mode) {
    switch (mode) {
      case "PvP":
        initPvp();
        break;
      case "PvIA":
        initPvIA();
        break;
      case "IAvIA":
        initIAvIA();
        break;
      default:  
        System.out.println("Mode not found");
        break;
    }
  }
  
  public void initPvp() {
    players.add(FactoryPlayer.createPlayer("Human", gameManager.getInput("Player 1 name: "),gameManager.getInput("Player 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("Human",gameManager.getInput("Player 2 name: "), gameManager.getInput("Player 2 color: ").charAt(0)));
    gameManager.newGameOrLoad();
  }
  
  public void initPvIA() {
    players.add(FactoryPlayer.createPlayer("Human", gameManager.getInput("Player name: "), gameManager.getInput("Player color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(gameManager.getInput("IA 1 level: ")), gameManager.getInput("Player name: "), gameManager.getInput("IA color: ").charAt(0)));
    gameManager.newGameOrLoad();
  }

  public void initIAvIA() {
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(gameManager.getInput("IA 1 level: ")), gameManager.getInput("Player name: "), gameManager.getInput("IA 1 color: ").charAt(0)));
    players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(gameManager.getInput("IA 2 level: ")), gameManager.getInput("Player name: "), gameManager.getInput("IA 2 color: ").charAt(0)));
  }
}
