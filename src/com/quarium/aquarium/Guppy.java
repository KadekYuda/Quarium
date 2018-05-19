package com.quarium.aquarium;

import java.lang.Double;
import java.util.Random;

public class Guppy extends Item implements Fish {
  private boolean isFull;
  private int growthStage;
  private boolean spriteFacing;
  private int remainingFoodToLvUp;
  private int coin;
  private double timehungry;
  private double timeprevmove;
  private double lastGenerateCoin;

  public static final double GUPPY_SHUNGRY = 3;
  public static final double GUPPY_SNORMAL = 1;
  public static final int GUPPY_FOOD_TO_LV_2 = 3;
  public static final int GUPPY_FOOD_TO_LV_3 = 5;
  public static final int GUPPY_COIN_LV_1 = 20;
  public static final int GUPPY_COIN_LV_2 = 30;
  public static final int GUPPY_COIN_LV_3 = 50;
  public static final double GUPPY_HUNGRY = 5;
  public static final double GUPPY_DEAD = 12;
  public static final double GUPPY_COIN = 6;
  public static final double GUPPY_RADIUS = 5;
  public static final int GUPPY_CHANGE_MOVE = 4;

  /**
   * Constructor of Guppy at position (x,y) with initialization of time indicators.
   * @param x Abyss position of Guppy
   * @param y Ordinate position of Guppy
   * @param t Time of Guppy's initialization
   */
  public Guppy(double x, double y, double t) {
    super(x,y,GUPPY_SNORMAL, GUPPY_SNORMAL);
    isFull = true;
    growthStage = 1;
    spriteFacing = true;// true menghadap ke kanan, false menghadap ke kiri
    remainingFoodToLvUp = GUPPY_FOOD_TO_LV_2;
    coin = GUPPY_COIN_LV_1;
    timehungry = t;
    timeprevmove = t;
    lastGenerateCoin = t;
  }

  @Override
  public void setHungry() {
    isFull = false;
  }

  @Override
  public void changeMovement() {
    double xrand;
    double yrand;
    Random rand = new Random();
    do {
      xrand = rand.nextDouble() * 600.0 + 20.0;
      yrand = rand.nextDouble() * 400.0 + 20.0;
    } while (Double.compare(xrand,getXpos()) == 0 && Double.compare(yrand,getYpos()) == 0);
    double a = Math.atan2(yrand - getYpos(), xrand - getXpos());
    setXspeed(Math.cos(a));
    setYspeed(Math.cos(a));
    if (getXspeed() > 0) {
      setSpriteFacing(true);
    } else if (getXspeed() < 0) {
      setSpriteFacing(false);
    }
  }

  @Override
  public void chaseFood(Item food) {
    double a = Math.atan2(food.getYpos() - getYpos(), food.getXpos() - getXpos());
    setXspeed(Math.cos(a)); //setting spped in x direction
    setYspeed(Math.sin(a)); //setting spped in y direction
    if (getXspeed() > 0) {
      setSpriteFacing(true);
    } else if (getXspeed() < 0) {
      setSpriteFacing(false);
    }
  }

  @Override
  public void updatePosition(int hsize, int vsize) {
    double speed;

    if (isFull()) {
      speed = GUPPY_SNORMAL;
    } else {
      speed = GUPPY_SHUNGRY;
    }
    if (getXpos() < hsize && getXpos() > 0) {
      setXpos(getXpos() + GUPPY_SNORMAL * getXspeed());
    } else if (getXpos() <= 0) {
      setXspeed(Math.abs(getXspeed()));
      setSpriteFacing(true);
      setXpos(getXpos() + GUPPY_SNORMAL * getXspeed());
    } else if (getXpos() >= hsize) {
      setXspeed(-1 * Math.abs(getXspeed()));
      setSpriteFacing(false);
      setXpos(getXpos() + GUPPY_SNORMAL * getXspeed());
    }
    if (getYpos() < (vsize - 50) && getYpos() > 0) {
      setYpos(getYpos() + GUPPY_SNORMAL * getYspeed());
    } else if (getYpos() <= 0) {
      setYspeed(Math.abs(getYspeed()));
      setYpos(getYpos() + GUPPY_SNORMAL * getYspeed());
    } else if (getYpos() >= (vsize - 50)) {
      setYspeed(-1 * Math.abs(getYspeed()));
      setYpos(getYpos() + GUPPY_SNORMAL * getYspeed());
    }

  }

  public boolean isFull() {
    return isFull;
  }

  public void setFull(boolean full) {
    isFull = full;
  }

  public int getGrowthStage() {
    return growthStage;
  }

  public void setGrowthStage(int growthStage) {
    this.growthStage = growthStage;
  }

  public boolean isSpriteFacing() {
    return spriteFacing;
  }

  public void setSpriteFacing(boolean spriteFacing) {
    this.spriteFacing = spriteFacing;
  }

  public int getRemainingFoodToLvUp() {
    return remainingFoodToLvUp;
  }

  public void setRemainingFoodToLvUp(int remainingFoodToLvUp) {
    this.remainingFoodToLvUp = remainingFoodToLvUp;
  }

  public int getCoin() {
    return coin;
  }

  public void setCoin(int coin) {
    this.coin = coin;
  }

  public double getTimehungry() {
    return timehungry;
  }

  public void setTimehungry(double timehungry) {
    this.timehungry = timehungry;
  }

  public double getTimeprevmove() {
    return timeprevmove;
  }

  public void setTimeprevmove(double timeprevmove) {
    this.timeprevmove = timeprevmove;
  }

  public double getLastGenerateCoin() {
    return lastGenerateCoin;
  }

  public void setLastGenerateCoin(double lastGenerateCoin) {
    this.lastGenerateCoin = lastGenerateCoin;
  }
}
