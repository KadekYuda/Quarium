package com.quarium.aquarium;

public class Snail extends Item {

  public static final double SNAIL_SPEED = 3;
  private int accValue;
  private boolean spriteFacing;

  public int getAccValue() {
    return accValue;
  }

  public void setAccValue(int accValue) {
    this.accValue = accValue;
  }

  public boolean getSpriteFacing() {
    return spriteFacing;
  }

  public void setSpriteFacing(boolean spriteFacing) {
    this.spriteFacing = spriteFacing;
  }

  /**
   * Constructor for snail in position (x,y).
   * @param x abyss of Snail position
   * @param y abyss of Snail position
   */
  public Snail(double x, double y) {
    super(x,y,0,0);
    accValue = 0;
    spriteFacing = true;
  }

  /**
   * Accept a coin.
   * @param coin Coin to be accepted.
   */
  public void acceptCoin(Coin coin) {
    accValue += coin.getValue();
  }


}
