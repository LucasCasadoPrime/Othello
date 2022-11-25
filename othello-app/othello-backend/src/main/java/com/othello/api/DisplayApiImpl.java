package com.othello.api;

import org.springframework.stereotype.Component;
import com.othello.model.Grid;

@Component
public class DisplayApiImpl implements DisplayApi {

    public Grid display(Grid grid) {
      System.out.println();
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.getGrid()[i][j].getC() == ' ') {
                    System.out.print('.');
                } else {
                    System.out.print(grid.getGrid()[i][j].getC());
                }
            }
            System.out.println();
        }
        System.out.println("____________________");
        return grid;
    }
}
