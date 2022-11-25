package com.othello.model;

public enum Modes {
  PvP("PvP"), PvIA("PvIA"), IAvIA("IAvIA");

  private String mode;

  Modes(String mode) {
    this.mode = mode;
  }

  public String getMode() {
    return mode;
  }
}
