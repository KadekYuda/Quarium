package com.quarium.aquarium;

import java.lang.Math;
import java.util.Random;

public class Piranha extends Item implements Fish {
  private boolean isFull;
  private boolean spriteFacing;
  private int coin;
  private double timehungry;
  private double timeprevmove;

  public static double PIRANHA_SHUNGRY = 4;
  public static double PIRANHA_SNORMAL = 2;
  public static double PIRANHA_TIME_INTERVAL_HUNGRY = 8;
  public static double PIRANHA_TIME_INTERVAL_DEATH = 16;
  public static double PIRANHA_RADIUS_TO_EAT = 5;

  /**
   * Acts like a piranha. Eats Guppy.
   * @param x Axis of Piranha location
   * @param y Ordinate of Piranha location
   * @param t Time of Piranha's initialization
   */
  public Piranha(double x, double y, double t) {
    super(x,y,PIRANHA_SNORMAL / 2,PIRANHA_SNORMAL / 2);
    isFull = true;
    spriteFacing = true;
    coin = 200;
    timehungry = t;
    timeprevmove = t;
  }

  @Override
  public void setHungry() {
    setFull(false);
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
      speed = PIRANHA_SNORMAL;
    } else {
      speed = PIRANHA_SHUNGRY;
    }
    if (getXpos() < hsize && getXpos() > 0) {
      setXpos(getXpos() + speed * getXspeed());
    } else if (getXpos() <= 0) {
      setXspeed(Math.abs(getXspeed()));
      setSpriteFacing(true);
      setXpos(getXpos() + speed * getXspeed());
    } else if (getXpos() >= hsize) {
      setXspeed(-1 * Math.abs(getXspeed()));
      setSpriteFacing(false);
      setXpos(getXpos() + speed * getXspeed());
    }
    if (getYpos() < (vsize - 50) && getYpos() > 0) {
      setYpos(getYpos() + speed * getYspeed());
    } else if (getYpos() <= 0) {
      setYspeed(Math.abs(getYspeed()));
      setYpos(getYpos() + speed * getYspeed());
    } else if (getYpos() >= (vsize - 50)) {
      setYspeed(-1 * Math.abs(getYspeed()));
      setYpos(getYpos() + speed * getYspeed());
    }
  }

  public void eat(Guppy g) {
    isFull = true;
  }

  public boolean isFull() {
    return isFull;
  }

  public void setFull(boolean full) {
    isFull = full;
  }

  public boolean isSpriteFacing() {
    return spriteFacing;
  }

  public void setSpriteFacing(boolean spriteFacing) {
    this.spriteFacing = spriteFacing;
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
}
