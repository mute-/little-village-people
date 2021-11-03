package com.toudal.awt.panels;

import java.awt.*;
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
public class TAnchorPanel extends TPanel {

  /**
   *
   */
  private static final long serialVersionUID = 305222577524167705L;

  public TAnchorPanel(int i, int j) {
    ivHashTable = new Hashtable();
    ivWidth = i;
    ivHeight = j;
    setSize(ivWidth, ivHeight);
  }

  public TAnchorPanel(int i, int j, int k, int l) {
    ivHashTable = new Hashtable();
    ivWidth = k;
    ivHeight = l;
    setSize(k, l);
    ivX = i;
    ivY = j;
    setLocation(ivX, ivY);
  }

  public void add(
    Component component,
    boolean flag,
    boolean flag1,
    boolean flag2,
    boolean flag3
  ) {
    ivHashTable.put(component, new TAnchorPanelInfo(flag, flag1, flag2, flag3));
    add(component);
  }

  public void placeComponents(int i, int j) {
    Component acomponent[] = getComponents();
    for (int k = 0; k < acomponent.length; k++) {
      TAnchorPanelInfo lTAnchorPanelInfo = (TAnchorPanelInfo) ivHashTable.get(
        acomponent[k]
      );
      if (lTAnchorPanelInfo != null) {
        Rectangle rectangle = acomponent[k].getBounds();
        if (
          lTAnchorPanelInfo.ivAnchorRight && lTAnchorPanelInfo.ivAnchorLeft
        ) rectangle.setSize(
          rectangle.getSize().width + i,
          rectangle.getSize().height
        ); else if (
          lTAnchorPanelInfo.ivAnchorRight && !lTAnchorPanelInfo.ivAnchorLeft
        ) rectangle.setLocation(
          rectangle.getLocation().x + i,
          rectangle.getLocation().y
        );
        if (
          lTAnchorPanelInfo.ivAnchorTop && lTAnchorPanelInfo.ivAnchorBottom
        ) rectangle.setSize(
          rectangle.getSize().width,
          rectangle.getSize().height + j
        ); else if (
          lTAnchorPanelInfo.ivAnchorBottom && !lTAnchorPanelInfo.ivAnchorTop
        ) rectangle.setLocation(
          rectangle.getLocation().x,
          rectangle.getLocation().y + j
        );
        acomponent[k].setBounds(rectangle);
      }
    }

    ivWidth += i;
    ivHeight += j;
  }

  public void remove(Component component) {
    super.remove(component);
    ivHashTable.remove(component);
  }

  public void sizeChanged() {}

  public void sizeChanged(int i, int j) {
    placeComponents(i - ivWidth, j - ivHeight);
  }

  private Hashtable ivHashTable;
  protected int ivX;
  protected int ivY;
  protected int ivHeight;
  protected int ivWidth;
}
