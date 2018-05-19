package com.quarium.game;

import com.quarium.engine.AbstractGame;
import com.quarium.engine.GameContainer;
import com.quarium.engine.Renderer;
import com.quarium.engine.gfx.Image;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {

  private Image background;

  public GameManager() {
    background = new Image("/background.jpg");
  }

  @Override
  public void update(GameContainer gc, float dt) {
    if (gc.getInput().isKeyDown(KeyEvent.VK_G)) {
      System.out.println("Buy Guppy");
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    r.drawImage(background,0,0);
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
}
