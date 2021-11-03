package com.toudal.awt.buttons;

import java.awt.event.MouseEvent;

/**
 * ===========================================================================
 * com.toudal.awt.buttons
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
public interface TButtonListener {
  public abstract void TButtonClicked(MouseEvent pMouseEvent);

  public abstract void TButtonEntered(MouseEvent pMouseEvent);

  public abstract void TButtonExited(MouseEvent pMouseEvent);

  public abstract void TButtonPressed(MouseEvent pMouseEvent);

  public abstract void TButtonReleased(MouseEvent pMouseEvent);
}
