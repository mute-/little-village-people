package com.toudal.awt.panels;

import java.awt.*;

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
public abstract class TPanel extends Panel {

  /**
   *
   */
  private static final long serialVersionUID = -1330824276091610957L;

  public TPanel() {
    ivHeight = 0;
    ivWidth = 0;
    setLayout(null);
  }

  public void setBounds(int pX, int pY, int pHeight, int lHeight) {
    super.setBounds(pX, pY, pHeight, lHeight);
    ivHeight = lHeight;
    ivWidth = pHeight;
    sizeChanged();
  }

  public void setSize(int pX, int pY) {
    super.setSize(pX, pY);
    ivWidth = pX;
    ivHeight = pY;
    sizeChanged();
  }

  public void setSize(Dimension pDimension) {
    super.setSize(pDimension);
    ivHeight = pDimension.height;
    ivWidth = pDimension.width;
    sizeChanged();
  }

  protected abstract void sizeChanged();

  protected int ivHeight;
  protected int ivWidth;
}
