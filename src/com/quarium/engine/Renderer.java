package com.quarium.engine;

import com.quarium.engine.gfx.Image;

import java.awt.image.DataBufferInt;


public class Renderer {
  private int pixelWidth;
  private int pixelHeight;
  private int[] pixels;

  /**
   * Render the window of the game.
   * @param gc Game container containing the game.
   */
  public Renderer(GameContainer gc) {
    pixelWidth = gc.getWidth();
    pixelHeight = gc.getHeight();
    pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
  }

  /**
   * Clearing screen with black window.
   */
  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }

  /**
   * Assign values to pixel.
   * @param x x position of pixel.
   * @param y y position of pixel.
   * @param value value of the pixel.
   */
  public void setPixel(int x, int y, int value) {
    if (x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight || value == 0xffff00ff) {
      return;
    }
    if ((value >> 24) == 0x00) {
      return;
    }

    pixels[x + y * pixelWidth] = value;
  }

  /**
   * Draws an image.
   * @param image Imaage that would be drawn.
   * @param offX X position.
   * @param offY Y position.
   */
  public void drawImage(Image image, int offX, int offY) {

    //No render condition
    if (offX < -image.getWidth()) {
      return;
    }

    if (offY < -image.getHeight()) {
      return;
    }

    if (offX >= pixelWidth) {
      return;
    }

    if (offY >= pixelHeight) {
      return;
    }

    int newX = 0;
    int newY = 0;

    //Clipping Code
    if (offX < 0) {
      newX -= offX;
    }

    if (offY < 0) {
      newY -= offY;
    }

    int newWidth = image.getWidth();
    int newHeight = image.getHeight();

    if (newWidth + offX >= pixelWidth) {
      newWidth -= newWidth + offX - pixelWidth;
    }

    if (newHeight + offY >= pixelHeight) {
      newHeight -= newHeight + offY - pixelHeight;
    }

    for (int y = newY; y < newHeight; y++) {
      for (int x = newX; x < newWidth; x++) {
        setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
      }
    }
  }
}
