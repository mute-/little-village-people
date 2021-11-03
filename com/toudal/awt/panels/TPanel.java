package com.toudal.awt.panels;

import java.awt.*;

/**
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

  public void setBounds(int i, int j, int k, int l) {
    super.setBounds(i, j, k, l);
    ivWidth = k;
    ivHeight = l;
    sizeChanged();
  }

  public void setSize(int i, int j) {
    super.setSize(i, j);
    ivWidth = i;
    ivHeight = j;
    sizeChanged();
  }

  public void setSize(Dimension dimension) {
    super.setSize(dimension);
    ivHeight = dimension.height;
    ivWidth = dimension.width;
    sizeChanged();
  }

  protected abstract void sizeChanged();

  protected int ivHeight;
  protected int ivWidth;
}
