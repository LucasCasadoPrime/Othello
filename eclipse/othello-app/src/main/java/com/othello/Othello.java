package com.othello;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.othello.model.Grid;
import com.othello.model.Player;

@Component
public class Othello {

    private Grid grid;
    private Display display;
    private ArrayList<Player> players;

    public Othello() {
        initOthello();
        try {
            launchGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initOthello() {
        this.grid = new Grid(8);
        this.display = new Display();
        this.players = new ArrayList<Player>();
        grid.initGrid();
    }

    public void launchGame() throws InterruptedException {
        int mode = Integer.parseInt(getInput("1 PvP\n2 PvIA\n3 IAvIA\n4 Exit\nChoose a game mode: "));
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

    public void Game() throws InterruptedException {
        while (!grid.isFull()) {
            for (Player player : players) {
                display.display(grid);
                if (player.play(grid)) {
                    System.out.println("No move possible");
                }

            }
        }
    }

    public void initPvIA() {
        players.add(FactoryPlayer.createPlayer("Human", getInput("Player color: ").charAt(0)));
        players.add(FactoryPlayer.createPlayer("IA_lvl_" + Integer.parseInt(getInput("IA level: ")) , getInput("IA color: ").charAt(0)));
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
