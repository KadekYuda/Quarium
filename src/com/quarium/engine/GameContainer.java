package com.quarium.engine;

public class GameContainer implements  Runnable {
  private Thread thread;
  private Window window;
  private boolean running = false;
  private static final double UPDATE_CAP = 1.0 / 60.0;
  private int width = 640;
  private int height = 480;
  private float scale = 1f;
  private String title = "Quarium";

  public GameContainer() {

  }

  public void start() {
    window = new Window(this);
    thread = new Thread(this);
    thread.run();
  }

  public void stop() {

  }

  @Override
  public void run() {
    running = true;
    boolean render;
    double firstTime;
    double lastTime = System.nanoTime() / 1000000000.0;
    double passedTime;
    double unprocessedTime = 0;
    double frameTime = 0;
    int frames = 0;
    int fps;
    while (running) {
      render = false;
      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      unprocessedTime += passedTime;
      frameTime += passedTime;
      while (unprocessedTime >= UPDATE_CAP) {
        unprocessedTime -= UPDATE_CAP;
        render = true;
        //TODO: update game
        if (frameTime >= 1.0) {
          frameTime = 0;
          fps = frames;
          frames = 0;
          System.out.println("FPS: " + fps);
        }
      }
      if (render) {
        //TODO: render game
        window.update();
        frames++;
      } else {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    dispose();
  }

  public void dispose() {

  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public float getScale() {
    return scale;
  }

  public String getTitle() {
    return title;
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer();
    gc.start();
  }
}
