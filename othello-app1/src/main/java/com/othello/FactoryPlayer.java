package com.othello;

import com.othello.model.Human;
import com.othello.model.IA_lvl_0;
import com.othello.model.Player;

public class FactoryPlayer {
    
    public static Player createPlayer(String name, char C) {
        if (name.equals("Human")) {
            return new Human(name, C);
        } 
        else if (name.equals("IA_lvl_0")) {
            return new IA_lvl_0(name, C);
        }
        return null;
    }

}
