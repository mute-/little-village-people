package com.toudal.awt.panels;

import java.awt.*;
import java.net.URL;
import java.util.Hashtable;

/*
 * ===========================================================================
 * com.toudal.awt.panels
 *
 * Project: www.toudal.com in Java
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

  private static Hashtable ivHashtable = new Hashtable();
  protected Image ivImage;
  protected Graphics ivGraphics;
  protected int ivHeight;
  protected int ivWidth;

  public TCanvas() {
    ivHeight = 0;
    ivWidth = 0;
  }

  public void addImage(String pImagePath, Image pImage) {
    ivHashtable.put(pImagePath, pImage);
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

  public Image getImage(String pImagePath) {
    Image image = (Image) ivHashtable.get(pImagePath);
    if (image == null) {
      try {
        System.out.println(pImagePath);
        MediaTracker mediatracker = new MediaTracker(this);
        URL url = new URL(pImagePath);
        image = Toolkit.getDefaultToolkit().getImage(url);
        mediatracker.addImage(image, 0);
        mediatracker.waitForAll();
        mediatracker.removeImage(image);
        url.openConnection();
      } catch (Exception pException) {
        pException.printStackTrace();
      }
      if (image == null) {
        image = createImage(10, 10);
        image.getGraphics().setColor(Color.black);
        image.getGraphics().drawString("NO Image", 10, 20);
      }
      ivHashtable.put(pImagePath, image);
    }
    return image;
  }

  protected abstract void paint();

  public void paint(Graphics pGraphics) {
    if (ivImage == null)
      createOffImage();
    update(pGraphics);
  }

  public void removeImage(String pImagePath) {
    ivHashtable.remove(pImagePath);
  }

  public void setBounds(int pX, int pY, int pHeight, int lHeight) {
    super.setBounds(pX, pY, pHeight, lHeight);
    ivHeight = lHeight;
    ivWidth = pHeight;
    createOffImage();
    sizeChanged();
  }

  public void setSize(int pX, int pY) {
    super.setSize(pX, pY);
    ivWidth = pX;
    ivHeight = pY;
    createOffImage();
    sizeChanged();
  }

  public void setSize(Dimension pDimension) {
    super.setSize(pDimension);
    ivHeight = pDimension.height;
    ivWidth = pDimension.width;
    createOffImage();
    sizeChanged();
  }

  protected abstract void sizeChanged();

  public void update(Graphics pGraphics) {
    if (ivGraphics != null)
      paint();
    if (ivImage != null)
      pGraphics.drawImage(ivImage, 0, 0, this);
  }

}
