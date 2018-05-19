package com.quarium.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Image {
  private int width;
  private int height;
  private int[] pixels;

  /**
   * Image Container for game engine.
   * @param path file path to image.
   */
  public Image(String path) {
    BufferedImage image = null;
    try {
      System.out.println(Image.class.getResourceAsStream(path));
      image = ImageIO.read(Image.class.getResourceAsStream(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

    width = image.getWidth();
    height = image.getHeight();
    pixels = image.getRGB(0, 0, width, height, null, 0, width);

    image.flush();
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public int[] getPixels() {
    return this.pixels;
  }
}
