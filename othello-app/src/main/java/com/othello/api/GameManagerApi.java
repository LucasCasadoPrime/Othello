package com.othello.api;

import java.util.ArrayList;
import com.othello.model.Billboard;
import com.othello.repositories.ScoreRepository;

public interface GameManagerApi {
  
  public ArrayList<String> getModes();
  public Billboard getBillboard(ScoreRepository scoreRepository);

}
