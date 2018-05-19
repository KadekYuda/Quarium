package com.quarium.aquarium;

public class Food extends Item {
  private static double FOOD_SPEED = 0.5;

  /**
   * Constructor for Food at position(x,y).
   * @param x Abyss of Food Position
   * @param y Ordinate of Food Position
   */
  public Food(double x, double y) {
    super(x,y,0,FOOD_SPEED);

  }
}
