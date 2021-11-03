package com.toudal.awt.panels;

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
public class TAnchorPanelInfo {

  public TAnchorPanelInfo(
    boolean flag,
    boolean flag1,
    boolean flag2,
    boolean flag3
  ) {
    ivAnchorTop = false;
    ivAnchorRight = false;
    ivAnchorLeft = false;
    ivAnchorBottom = false;
    ivAnchorTop = flag;
    ivAnchorRight = flag1;
    ivAnchorLeft = flag2;
    ivAnchorBottom = flag3;
  }

  boolean ivAnchorTop;
  boolean ivAnchorRight;
  boolean ivAnchorLeft;
  boolean ivAnchorBottom;
}
