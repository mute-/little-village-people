package com.toudal.awt.panels;

import java.awt.*;
import java.net.URL;
import java.util.Hashtable;

/**
 * ===========================================================================
 * com.toudal.awt.panels
 *
 * Project: toudal.com in Java
 *
 * ===========================================================================
 * History:
 *
 * Version  Date        User  Comment
 * -------  ----------  ----  -----------------------------------------------
 *   1.0.0  03-11-2021   MST  Initial version
 *
 * ===========================================================================
 * Module Information:
 *
 * DESCRIPTION: TOUDAL.WRITEME
 * ===========================================================================
 */
public abstract class TCanvas extends Canvas {

  /**
   *
   */
  private static final long serialVersionUID = 5911829596296514334L;

  public TCanvas() {
    ivHeight = 0;
    ivWidth = 0;
  }

  public void addImage(String s, Image image) {
    ivHashtable.put(s, image);
  }

  public void clear() {
    if (ivGraphics != null) {
      ivGraphics.setColor(getBackground());
      ivGraphics.fillRect(0, 0, ivWidth, ivHeight);
    }
  }

  private void createOffImage() {
    ivImage = createImage(ivWidth, ivHeight);
    if (ivImage != null) {
      ivGraphics = ivImage.getGraphics();
      clear();
    }
  }

  public Image getImage(String s) {
    Image image = (Image) ivHashtable.get(s);
    if (image == null) {
      try {
        System.out.println(s);
        MediaTracker mediatracker = new MediaTracker(this);
        URL url = new URL(s);
        image = Toolkit.getDefaultToolkit().getImage(url);
        mediatracker.addImage(image, 0);
        mediatracker.waitForAll();
        mediatracker.removeImage(image);
        url.openConnection();
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      if (image == null) {
        image = createImage(10, 10);
        image.getGraphics().setColor(Color.black);
        image.getGraphics().drawString("NO Image", 10, 20);
      }
      ivHashtable.put(s, image);
    }
    return image;
  }

  protected abstract void paint();

  public void paint(Graphics g) {
    if (ivImage == null) createOffImage();
    update(g);
  }

  public void removeImage(String s) {
    ivHashtable.remove(s);
  }

  public void setBounds(int i, int j, int k, int l) {
    super.setBounds(i, j, k, l);
    ivHeight = l;
    ivWidth = k;
    createOffImage();
    sizeChanged();
  }

  public void setSize(int i, int j) {
    super.setSize(i, j);
    ivHeight = j;
    ivWidth = i;
    createOffImage();
    sizeChanged();
  }

  public void setSize(Dimension dimension) {
    super.setSize(dimension);
    ivHeight = dimension.height;
    ivWidth = dimension.width;
    createOffImage();
    sizeChanged();
  }

  protected abstract void sizeChanged();

  public void update(Graphics g) {
    if (ivGraphics != null) paint();
    if (ivImage != null) g.drawImage(ivImage, 0, 0, this);
  }

  private static Hashtable ivHashtable = new Hashtable();
  protected Image ivImage;
  protected Graphics ivGraphics;
  protected int ivHeight;
  protected int ivWidth;
}
