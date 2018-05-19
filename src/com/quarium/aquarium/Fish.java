package com.quarium.aquarium;

public interface Fish {
  /**
   * Set fish to be hungry.
   */
  void setHungry();

  /**
   * Change direction of fish movement.
   */
  void changeMovement();

  /**
   * Chase food if there is any.
   * @param food Fish's food (Food for Guppy, Guppy for Piranha
   */
  void chaseFood(Item food);

  /**
   * Update fish's position according to the border
   * @param hsize Abyss border
   * @param vsize Ordinate border
   */
  void updatePosition(int hsize, int vsize);
}
