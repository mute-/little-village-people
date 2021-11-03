package com.toudal.awt;

import java.awt.*;
import java.net.URL;
import java.util.Hashtable;

/**
 * ===========================================================================
 * com.toudal.awt
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
  private static final long serialVersionUID = -1281495486301959861L;

  public TCanvas() {
    ivHeight = 0;
    ivWidth = 0;
  }

  public void addImage(String pString, Image pImage) {
    ivHashtable.put(pString, pImage);
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

  public Image getImage(String pString) {
    Image image = (Image) ivHashtable.get(pString);
    if (image == null) {
      try {
        System.out.println(pString);
        MediaTracker mediatracker = new MediaTracker(this);
        URL url = new URL(pString);
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

  public void paint(Graphics pGraphics) {
    if (ivImage == null) createOffImage();
    update(pGraphics);
  }

  public void removeImage(String pString) {
    ivHashtable.remove(pString);
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
    ivHeight = pY;
    ivWidth = pX;
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
    if (ivGraphics != null) paint();
    if (ivImage != null) pGraphics.drawImage(ivImage, 0, 0, this);
  }

  private static Hashtable ivHashtable = new Hashtable();
  protected Image ivImage;
  protected Graphics ivGraphics;
  protected int ivHeight;
  protected int ivWidth;
}
