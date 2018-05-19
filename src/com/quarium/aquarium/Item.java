package com.quarium.aquarium;

public class Item {

  private double xpos;
  private double ypos;
  private double xspeed;
  private double yspeed;


  /**
   * Item object constructor.
   * @param xp Item abyss
   * @param yp Item ordinate
   * @param xs Item speed in terms of abyss
   * @param ys Item speed in terms of ordinate
   */
  public Item(double xp, double yp, double xs, double ys) {
    xpos = xp;
    ypos = yp;
    xspeed = xs;
    yspeed = ys;
  }

  /**
   * Copy constructor of Item.
   * @param item Item that we want to copy
   */
  public Item(Item item) {
    this(item.getXpos(), item.getYpos(), item.getXspeed(), item.getYspeed());
  }

  /**
   * Get distance between two objects.
   * @param obj operand object
   * @return distance between this object and obj
   */
  public double getDistance(Item obj) {
    return Math.sqrt(((xpos - obj.xpos) * (xpos - obj.xpos)
      + (ypos - obj.ypos) * (ypos - obj.ypos)));
  }

  /**
   * Check whether a x value is in range of this object in a radius.
   * @param deltaX x value to be compared
   * @param radius value of desired radius
   * @return true if in range, false if not.
   */
  public boolean inRangeX(double deltaX, double radius) {
    return ((deltaX <= (xpos + radius) && deltaX >= (xpos - radius)));
  }

  /**
   * Check whether a y value is in range of this object in a radius.
   * @param deltaY y value to be compared
   * @param radius value of desired radius
   * @return true if in range, false if not.
   */
  public boolean inRangeY(double deltaY, double radius) {
    return ((deltaY <= (xpos + radius) && deltaY >= (xpos - radius)));
  }

  /**
   * Stop an Item from moving.
   *
   */
  public void stopMovement() {
    this.setXspeed(0);
    this.setYspeed(0);
  }

  public double getXpos() {
    return xpos;
  }

  public void setXpos(double xpos) {
    this.xpos = xpos;
  }

  public double getYpos() {
    return ypos;
  }

  public void setYpos(double ypos) {
    this.ypos = ypos;
  }

  public double getXspeed() {
    return xspeed;
  }

  public void setXspeed(double xspeed) {
    this.xspeed = xspeed;
  }

  public double getYspeed() {
    return yspeed;
  }

  public void setYspeed(double yspeed) {
    this.yspeed = yspeed;
  }
}
