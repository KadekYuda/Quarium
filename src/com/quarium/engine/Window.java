package com.quarium.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window {
  private JFrame frame;
  private java.awt.image.BufferedImage image;
  private Canvas canvas;
  private java.awt.image.BufferStrategy bs;
  private Graphics graphics;

  /**
   * Create new window for game.
   * @param gc Game Container containing game to be displayed.
   */
  public Window(GameContainer gc) {
    image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
    canvas = new Canvas();
    int dw = (int)(gc.getWidth() * gc.getScale());
    int dh = (int)(gc.getHeight() * gc.getScale());
    Dimension d = new Dimension(dw, dh);
    canvas.setPreferredSize(d);
    canvas.setMaximumSize(d);
    canvas.setMinimumSize(d);
    frame = new JFrame(gc.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(canvas, BorderLayout.CENTER);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);

    canvas.createBufferStrategy(2);
    bs = canvas.getBufferStrategy();
    graphics = bs.getDrawGraphics();
  }

  public void update() {
    graphics.drawImage(image, 0,0,canvas.getWidth(), canvas.getHeight(), null);
    bs.show();
  }
}
