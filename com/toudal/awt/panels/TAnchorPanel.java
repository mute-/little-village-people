package com.toudal.awt.panels;

import java.awt.*;
import java.util.Hashtable;

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
public class TAnchorPanel extends TPanel {

  /**
   *
   */
  private static final long serialVersionUID = 305222577524167705L;

  public TAnchorPanel(int pX, int pY) {
    ivHashTable = new Hashtable();
    ivWidth = pX;
    ivHeight = pY;
    setSize(ivWidth, ivHeight);
  }

  public TAnchorPanel(int pX, int pY, int pHeight, int lHeight) {
    ivHashTable = new Hashtable();
    ivHeight = lHeight;
    ivWidth = pHeight;
    setSize(pX, pY);
    ivX = pX;
    ivY = pY;
    setLocation(ivX, ivY);
  }

  public void add(
    Component pComponent,
    boolean pFlag1,
    boolean pFlag2,
    boolean pFlag3,
    boolean pFlag4
  ) {
    ivHashTable.put(
      pComponent,
      new TAnchorPanelInfo(pFlag1, pFlag2, pFlag3, pFlag4)
    );
    add(pComponent);
  }

  public void placeComponents(int pX, int pY) {
    Component lComponent[] = getComponents();
    for (int k = 0; k < lComponent.length; k++) {
      TAnchorPanelInfo lTAnchorPanelInfo = (TAnchorPanelInfo) ivHashTable.get(
        lComponent[k]
      );
      if (lTAnchorPanelInfo != null) {
        Rectangle rectangle = lComponent[k].getBounds();
        if (
          lTAnchorPanelInfo.ivAnchorRight && lTAnchorPanelInfo.ivAnchorLeft
        ) rectangle.setSize(
          rectangle.getSize().width + pX,
          rectangle.getSize().height
        ); else if (
          lTAnchorPanelInfo.ivAnchorRight && !lTAnchorPanelInfo.ivAnchorLeft
        ) rectangle.setLocation(
          rectangle.getLocation().x + pX,
          rectangle.getLocation().y
        );
        if (
          lTAnchorPanelInfo.ivAnchorTop && lTAnchorPanelInfo.ivAnchorBottom
        ) rectangle.setSize(
          rectangle.getSize().width,
          rectangle.getSize().height + pY
        ); else if (
          lTAnchorPanelInfo.ivAnchorBottom && !lTAnchorPanelInfo.ivAnchorTop
        ) rectangle.setLocation(
          rectangle.getLocation().x,
          rectangle.getLocation().y + pY
        );
        lComponent[k].setBounds(rectangle);
      }
    }

    ivWidth += pX;
    ivHeight += pY;
  }

  public void remove(Component pComponent) {
    super.remove(pComponent);
    ivHashTable.remove(pComponent);
  }

  public void sizeChanged() {}

  public void sizeChanged(int pX, int pY) {
    placeComponents(pX - ivWidth, pY - ivHeight);
  }

  private Hashtable ivHashTable;
  protected int ivX;
  protected int ivY;
  protected int ivHeight;
  protected int ivWidth;
}
