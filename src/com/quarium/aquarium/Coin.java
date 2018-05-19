package com.quarium.aquarium;

public class Coin extends Item {
  private static final double COIN_SPEED = 1.5;

  private int value;

  /**
   * Constructor for Coin at (x,y) with value of v.
   * @param x Coin's abyss position
   * @param y Coin's ordinate position
   * @param v Coin's value
   */
  public Coin(double x, double y, int v) {
    super(x,y,0,COIN_SPEED);
    value = v;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
