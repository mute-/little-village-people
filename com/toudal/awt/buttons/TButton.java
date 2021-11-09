package com.toudal.awt.buttons;

import com.toudal.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * ===========================================================================
 * com.toudal.awt.buttons
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
public abstract class TButton extends TCanvas implements MouseListener {

  /**
   *
   */
  private static final long serialVersionUID = 6532295055758842843L;

  public TButton() {
    addMouseListener(this);
  }

  public void addTButtonListener(TButtonListener pTbuttonlistener) {
    ivTButtonListener = pTbuttonlistener;
  }

  public void mouseClicked(MouseEvent pMouseEvent) {
    if (ivTButtonListener != null)
      ivTButtonListener.TButtonClicked(pMouseEvent);
    repaint();
  }

  public void mouseEntered(MouseEvent pMouseEvent) {
    if (ivTButtonListener != null)
      ivTButtonListener.TButtonEntered(pMouseEvent);
    repaint();
  }

  public void mouseExited(MouseEvent pMouseEvent) {
    if (ivTButtonListener != null)
      ivTButtonListener.TButtonExited(pMouseEvent);
    repaint();
  }

  public void mousePressed(MouseEvent pMouseEvent) {
    if (ivTButtonListener != null)
      ivTButtonListener.TButtonPressed(pMouseEvent);
    repaint();
  }

  public void mouseReleased(MouseEvent pMouseEvent) {
    if (ivTButtonListener != null)
      ivTButtonListener.TButtonReleased(pMouseEvent);
    repaint();
  }

  protected TButtonListener ivTButtonListener;
}
