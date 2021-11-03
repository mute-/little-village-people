package com.toudal.awt.buttons;

import java.awt.*;
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
public class TButtonWeb extends TButton {

  public static final int IMAGE_ENTERED = 1;

  public static final int IMAGE_EXITED = 0;

  public static final int IMAGE_PRESSED = 2;

  public static final int IMAGE_SPECIEL = 3;

  /**
   *
   */
  private static final long serialVersionUID = -6490993378276417356L;

  private Color ivColor;

  private int ivCurrentImage;

  private boolean ivDown;

  private Font ivFont;

  private int ivHeight;

  private Image ivImage[];

  private Point ivImagePoint;

  private Point ivLabelPoint;

  private boolean ivSticky;

  private String ivText;

  private int ivWidth;

  /**
   *
   */
  public TButtonWeb() {
    this(null);
  }

  /**
   *
   */
  public TButtonWeb(Image aimage[]) {
    ivCurrentImage = 0;
    ivImage = null;
    ivLabelPoint = new Point(10, 12);
    ivImagePoint = new Point(0, 0);
    ivColor = Color.darkGray;
    ivFont = new Font("Lucinda", 1, 12);
    ivText = "";
    ivWidth = 0;
    ivHeight = 0;
    ivDown = false;
    ivSticky = false;
    ivImage = aimage;
    ivWidth = aimage[0].getWidth(this);
    ivHeight = aimage[0].getHeight(this);
    ivCurrentImage = 0;
  }

  /**
   *
   */
  public TButtonWeb(Image aimage[], Point point) {
    this(aimage);
    ivLabelPoint = point;
  }

  /**
   *
   */
  public TButtonWeb(Image aimage[], Point point, String s) {
    this(aimage, point);
    ivText = s;
  }

  /**
   *
   */
  public TButtonWeb(Image aimage[], Point point, String s, Color color) {
    this(aimage, point, s);
    ivColor = color;
  }

  /**
   *
   */
  public TButtonWeb(
    Image aimage[],
    Point point,
    String s,
    Color color,
    Font font
  ) {
    this(aimage, point, s, color);
    ivFont = font;
  }

  /**
   *
   */
  public void doUpdate(int i, String s, Color color) {
    ivCurrentImage = i;
    ivText = s;
    ivColor = color;
    if (!ivText.equalsIgnoreCase("")) repaint();
  }

  /**
   *
   */
  public boolean getSticky() {
    return ivSticky;
  }

  /**
   *
   */
  public boolean isDown() {
    return ivDown;
  }

  /**
   *
   */
  @Override
  public void mouseEntered(MouseEvent mouseevent) {
    if (ivLabelPoint.x != 0 || ivLabelPoint.y != 0) {
      ivCurrentImage = 1;
      super.mouseEntered(mouseevent);
    }
  }

  /**
   *
   */
  @Override
  public void mouseExited(MouseEvent mouseevent) {
    ivCurrentImage = 0;
    super.mouseExited(mouseevent);
  }

  /**
   *
   */
  @Override
  public void mousePressed(MouseEvent mouseevent) {
    ivCurrentImage = 2;
    super.mousePressed(mouseevent);
  }

  /**
   *
   */
  @Override
  public void mouseReleased(MouseEvent mouseevent) {
    ivCurrentImage = 3;
    super.mouseReleased(mouseevent);
  }

  /**
   *
   */
  @Override
  protected void paint() {
    super.ivGraphics.setColor(ivColor);
    super.ivGraphics.setFont(ivFont);
    switch (ivCurrentImage) {
      case 0: // '\0'
        super.ivGraphics.drawImage(ivImage[0], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 1: // '\001'
        super.ivGraphics.drawImage(
          ivImage[1],
          ivImagePoint.x,
          ivImagePoint.y,
          this
        );
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 2: // '\002'
        super.ivGraphics.drawImage(ivImage[1], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 3: // '\003'
        super.ivGraphics.drawImage(ivImage[2], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
    }
  }

  /**
   *
   */
  public void setDown(boolean flag) {
    ivDown = flag;
    if (!flag) ivCurrentImage = 0; else ivCurrentImage = 2;
    repaint();
  }

  /**
   *
   */
  public void setSticky(boolean flag) {
    ivSticky = flag;
  }

  /**
   *
   */
  @Override
  protected void sizeChanged() {
    repaint();
  }
}
    Image aimage[],
    Point point,
    String s,
    Color color,
    Font font
  ) {
    this(aimage, point, s, color);
    ivFont = font;
  }

  /**
   *
   */
  public void doUpdate(int i, String s, Color color) {
    ivCurrentImage = i;
    ivText = s;
    ivColor = color;
    if (!ivText.equalsIgnoreCase("")) repaint();
  }

  /**
   *
   */
  public boolean getSticky() {
    return ivSticky;
  }

  /**
   *
   */
  public boolean isDown() {
    return ivDown;
  }

  /**
   *
   */
  @Override
  public void mouseEntered(MouseEvent mouseevent) {
    if (ivLabelPoint.x != 0 || ivLabelPoint.y != 0) {
      ivCurrentImage = 1;
      super.mouseEntered(mouseevent);
    }
  }

  /**
   *
   */
  @Override
  public void mouseExited(MouseEvent mouseevent) {
    ivCurrentImage = 0;
    super.mouseExited(mouseevent);
  }

  /**
   *
   */
  @Override
  public void mousePressed(MouseEvent mouseevent) {
    ivCurrentImage = 2;
    super.mousePressed(mouseevent);
  }

  /**
   *
   */
  @Override
  public void mouseReleased(MouseEvent mouseevent) {
    ivCurrentImage = 3;
    super.mouseReleased(mouseevent);
  }

  /**
   *
   */
  @Override
  protected void paint() {
    super.ivGraphics.setColor(ivColor);
    super.ivGraphics.setFont(ivFont);
    switch (ivCurrentImage) {
      case 0: // '\0'
        super.ivGraphics.drawImage(ivImage[0], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 1: // '\001'
        super.ivGraphics.drawImage(
          ivImage[1],
          ivImagePoint.x,
          ivImagePoint.y,
          this
        );
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 2: // '\002'
        super.ivGraphics.drawImage(ivImage[1], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
      case 3: // '\003'
        super.ivGraphics.drawImage(ivImage[2], 0, 0, this);
        super.ivGraphics.drawString(ivText, ivLabelPoint.x, ivLabelPoint.y);
        return;
    }
  }

  /**
   *
   */
  public void setDown(boolean flag) {
    ivDown = flag;
    if (!flag) ivCurrentImage = 0; else ivCurrentImage = 2;
    repaint();
  }

  /**
   *
   */
  public void setSticky(boolean flag) {
    ivSticky = flag;
  }

  /**
   *
   */
  @Override
  protected void sizeChanged() {
    repaint();
  }
}
