package com.quarium.engine;

public interface AbstractGame {
  void update(GameContainer gc, float dt);

  void render(GameContainer gc, Renderer r);
}
