package com.othello;

import com.othello.model.Human;
import com.othello.model.IA_lvl_0;
import com.othello.model.Player;

public class FactoryPlayer {
    
    public static Player createPlayer(String type, String name, char c) {
        if (type.equals("Human")) {
            return new Human(type, name, c);
        } 
        else if (type.equals("IA_lvl_0")) {
            return new IA_lvl_0(type, name, c);
        }
        return null;
    }

}
