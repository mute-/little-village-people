package com.toudal.awt.panels;

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
public class TAnchorPanelInfo {

  public TAnchorPanelInfo(
    boolean pFlag1,
    boolean pFlag2,
    boolean pFlag3,
    boolean pFlag4
  ) {
    ivAnchorTop = false;
    ivAnchorRight = false;
    ivAnchorLeft = false;
    ivAnchorBottom = false;
    ivAnchorTop = pFlag1;
    ivAnchorRight = pFlag2;
    ivAnchorLeft = pFlag3;
    ivAnchorBottom = pFlag4;
  }

  boolean ivAnchorTop;
  boolean ivAnchorRight;
  boolean ivAnchorLeft;
  boolean ivAnchorBottom;
}
