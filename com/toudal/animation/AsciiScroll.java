package com.toudal.animation;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * ===========================================================================
 * com.toudal.animaition.AsciiScroll.java
 *
 * OCO Source Materials
 *
 * Project: www.toudal.com in Java
 *
 * ===========================================================================
 * History:
 *
 * Version  Date        User  Comment
 * -------  ----------  ----  -----------------------------------------------
 *   1.0.0  16-09-2001   MST  Initial version
 *   1.0.1  24-03-2003   MST  Source updated for SUN Java 1.4.1
 *   1.0.2  03-11-2021   MST  Committed to Git
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: TOUDAL.WRITEME
 * ===========================================================================
 */
public class AsciiScroll extends Applet implements Runnable {

  /**/
  private Thread ivThread;
  /**/
  private Image ivImage;
  /**/
  private Image ivOffImage;
  /**/
  private Graphics ivOffGraphics;
  /**/
  private FontMetrics ivFontMetrics;
  /**/
  private int ivX;
  /**/
  private int ivY;
  /**/
  private int ivWidth;
  /**/
  private int ivHeight;
  /**/
  private int ivPosition;
  /**/
  private boolean ivMouseOver;
  /**/
  private boolean ivIsPaused;
  /**/
  private boolean ivShouldPause;
  /**/
  private boolean ivShouldHilite;
  /**/
  private Vector ivURLVector;
  /**/
  private Vector ivTextVector;
  /**/
  private URL ivInetAddress;
  /**/
  private Color ivColorBackground = Color.gray;
  /**/
  private Color ivColorText = Color.black;
  /**/
  private Color ivColorHilite = Color.red;

  /**
   * @see java.lang.Object#Object()
   */
  public AsciiScroll() {
    ivTextVector = new Vector();
    ivURLVector = new Vector();
  }

  /**
   * @see java.applet.Applet#destroy()
   */
  public void destroy() {
    if (ivOffGraphics != null) {
      ivOffGraphics = null;
    }
    if (ivOffImage != null) {
      ivOffImage = null;
    }
  }

  /**
   * @see java.applet.Applet#init()
   */
  public void init() {
    if (null != getParameter("x")) {
      ivX = Integer.parseInt(getParameter("x"));
    }

    if (null != getParameter("y")) {
      ivY = Integer.parseInt(getParameter("y"));
    }

    if (null != getParameter("width")) {
      ivWidth = Integer.parseInt(getParameter("width"));
    }

    if (null != getParameter("height")) {
      ivHeight = Integer.parseInt(getParameter("height"));
    }

    if (null != getParameter("image")) {
      ivImage = getImage(getDocumentBase(), getParameter("image"));
      prepareImage(ivImage, this);
    }

    if (null != getParameter("pause")) {
      ivShouldPause = true;
    }

    if (null != getParameter("bgcolor")) {
      StringTokenizer tokenizer = new StringTokenizer(
        getParameter("bgcolor"),
        ","
      );
      int red = Integer.parseInt(tokenizer.nextToken());
      int green = Integer.parseInt(tokenizer.nextToken());
      int blue = Integer.parseInt(tokenizer.nextToken());
      ivColorBackground = new Color(red, green, blue);
    }

    if (null != getParameter("textcolor")) {
      StringTokenizer tokenizer = new StringTokenizer(
        getParameter("textcolor"),
        ","
      );
      int red = Integer.parseInt(tokenizer.nextToken());
      int green = Integer.parseInt(tokenizer.nextToken());
      int blue = Integer.parseInt(tokenizer.nextToken());
      ivColorText = new Color(red, green, blue);
    }

    if (null != getParameter("hilite")) {
      StringTokenizer tokenizer = new StringTokenizer(
        getParameter("hilite"),
        ","
      );
      int red = Integer.parseInt(tokenizer.nextToken());
      int green = Integer.parseInt(tokenizer.nextToken());
      int blue = Integer.parseInt(tokenizer.nextToken());
      ivColorHilite = new Color(red, green, blue);
      ivShouldHilite = true;
    }

    try {
      readfile(getParameter("file"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
      System.exit(-1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  /**
   * @see java.awt.Component#mouseDown(Event, int, int)
   * @deprecated
   */
  public boolean mouseDown(Event pEvent, int pX, int pY) {
    if (ivMouseOver) {
      getAppletContext().showDocument(ivInetAddress);
    }

    return true;
  }

  /**
   * @see java.awt.Component#mouseEnter(Event, int, int)
   * @deprecated
   */
  public boolean mouseEnter(Event pEvent, int pX, int pY) {
    //stop();
    ivMouseOver = true;

    repaint();
    return true;
  }

  /**
   * @see java.awt.Component#mouseExit(Event, int, int)
   * @deprecated
   */
  public boolean mouseExit(Event pEvent, int pX, int pY) {
    //start();
    ivMouseOver = false;

    repaint();
    return true;
  }

  /**
   * @see java.awt.Component#mouseMove(Event, int, int)
   * @deprecated
   */
  public boolean mouseMove(Event pEvent, int pX, int pY) {
    repaint();
    return true;
  }

  /**
   * @see java.awt.Component#mouseUp(Event, int, int)
   * @deprecated
   */
  public boolean mouseUp(Event pEvent, int pX, int pY) {
    return true;
  }

  /**
   * @see java.awt.Component#paint(Graphics)
   */
  public void paint(Graphics pGraphics) {
    // Create image first time around
    //
    if (null == ivOffImage) {
      ivOffImage = createImage(ivWidth, ivHeight);
      ivOffGraphics = ivOffImage.getGraphics();
      ivOffGraphics.setFont(new Font("Helvetica", 0, 11));

      ivFontMetrics = ivOffGraphics.getFontMetrics();
    }

    pGraphics.drawImage(ivOffImage, 0, 0, null);
    render();
  }

  /**
   * Method readfile.
   * @param pFileName
   * @throws MalformedURLException
   * @throws IOException
   */
  private void readfile(String pFileName)
    throws MalformedURLException, IOException {
    URL url = new URL(getCodeBase(), pFileName);
    if (null == url) {
      throw new FileNotFoundException();
    }

    DataInputStream dis = new DataInputStream(url.openStream());

    Vector vector = new Vector();
    String str;
    do {
      str = dis.readLine();

      if (null != str) {
        if (0 == str.indexOf(":")) {
          ivURLVector.addElement(new URL(str.substring(1, str.length())));

          // Make sure that url are set to first available address
          //
          if (ivInetAddress == null) {
            ivInetAddress = (URL) ivURLVector.firstElement();
          }

          ivTextVector.addElement(vector);
          vector = new Vector();
        } else {
          vector.addElement(str);
        }
      }
    } while (null != str);
  }

  /**
   * Method render.
   */
  private void render() {
    // Get offscreen buffer and zeroize
    //
    ivOffGraphics = ivOffImage.getGraphics();

    // Clear offscreen by redraw background
    //
    ivOffGraphics.setColor(ivColorBackground);
    ivOffGraphics.fillRect(0, 0, ivWidth, ivHeight);

    // Do not draw image if none was provided
    //
    if (null != ivImage) {
      ivOffGraphics.drawImage(ivImage, 0, 0, this);
    }

    // Scroll image
    //
    ivOffGraphics.translate(0, ivPosition);

    // Draw hilite
    //
    if (ivMouseOver && ivShouldHilite) {
      ivOffGraphics.setColor(ivColorHilite);
    } else {
      ivOffGraphics.setColor(ivColorText);
    }

    // Draw status bar
    //
    if (ivMouseOver && null != ivInetAddress) {
      showStatus(ivInetAddress.toString());
    } else {
      showStatus("");
    }

    Vector vector;
    int rotation = ivY;
    int half = ivFontMetrics.getHeight() / 2;
    while (rotation + ivPosition < ivHeight) {
      for (int i = 0; i < ivTextVector.size(); i++) {
        if (rotation + ivPosition - half == ivY) {
          ivIsPaused = true;
          ivInetAddress = (URL) ivURLVector.elementAt(i);
        }

        // Write all lines and seperate with font size
        //
        vector = (Vector) ivTextVector.elementAt(i);
        for (int j = 0; j < vector.size(); j++) {
          ivOffGraphics.drawString((String) vector.elementAt(j), ivX, rotation);
          rotation += ivFontMetrics.getHeight();
        }
      }
    }
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    while (true) {
      try {
        if (ivIsPaused && ivShouldPause) {
          Thread.sleep(3000L);
          ivIsPaused = false;
        } else if (ivShouldPause) {
          Thread.sleep(30L);
        } else {
          Thread.sleep(100L);
        }

        ivPosition--;
        repaint();
      } catch (InterruptedException e) {
        return;
      }
    }
  }

  /**
   * @see java.applet.Applet#start()
   */
  public void start() {
    if (null == ivThread) {
      ivThread = new Thread(this);
    }
    ivThread.start();
  }

  /**
   * @see java.applet.Applet#stop()
   */
  public void stop() {
    ivThread.stop();
  }

  /**
   * @see java.awt.Component#update(Graphics)
   */
  public void update(Graphics pGraphics) {
    paint(pGraphics);
  }
}
  public void start() {
    if (null == ivThread) {
      ivThread = new Thread(this);
    }
    ivThread.start();
  }

  /**
   * @see java.applet.Applet#stop()
   */
  public void stop() {
    ivThread.stop();
  }

  /**
   * @see java.awt.Component#update(Graphics)
   */
  public void update(Graphics pGraphics) {
    paint(pGraphics);
  }
}
