package com.othello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.othello.api.GameManagerApi;
import com.othello.bean.CoordinateWs;
import com.othello.bean.GridWs;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/othello/1", produces = {"application/json" })
public class OthelloController {
 
  private final static String APPLICATION_JSON_VALUE = "application/json";
  @Autowired
  GameManagerApi gameManager;
  
 
  
  @PostMapping(value = "/play", consumes = APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<GridWs> play(@ApiParam(value = "Coup jouer par le joueur", required = true) @RequestBody CoordinateWs coords) {
      return new ResponseEntity<>(new GridWs(), HttpStatus.OK);
  }
  
  @GetMapping(value = "/echo")
  @ResponseBody
  public ResponseEntity<GridWs> echo() {
      return new ResponseEntity<>(new GridWs(), HttpStatus.OK);
  }
  
}
