package com.toudal.village;

// import java
import java.awt.*;

/*
 * ===========================================================================
 * com.toudal.village
 *
 * Project: Toudal: Little Village People in Java
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
public class Map {

  // Public

  // Privates
  private Create ivHome;

  // Coords
  private int ivHouseX[] = { 3, 13, 30, 5, 34, 7, 23, 5, 45, 25, 60, 45, 5, 70, 77, };
  private int ivHouseY[] = { 5, 24, 4, 23, 40, 34, 42, 43, 4, 15, 70, 56, 63, 70, 3, };
  private int ivHouseWidth[] = { 5, 25, 6, 3, 12, 2, 2, 13, 4, 1, 12, 5, 33, 9, 4, };
  private int ivHouseHeight[] = { 7, 8, 9, 7, 3, 2, 2, 1, 1, 1, 4, 23, 4, 3, 15, };

  private int ivTrapX[] = { 4 };
  private int ivTrapY[] = { 34 };

  private int ivRestAreaX[] = { 10, 54, 30 };
  private int ivRestAreaY[] = { 10, 34, 70 };
  private int ivRestAreaR[] = { 8, 8, 8 };

  /**
   * Map constructor comment.
   */
  public Map(Create pHome) {
    super();
    ivHome = pHome;
  }

  /**
   *
   * @param pX Location x-coord for a human.
   * @param pY Location y-coord for a human.
   */
  public boolean amIDead(int pX, int pY) {
    boolean inside = false;
    for (int i = 0; i < ivTrapX.length; i++) {
      // Are we caught in a trap ??
      if ((pX == ivTrapX[i]) && (pY == ivTrapY[i])) {
        inside = true;
        break;
      }
    }

    return inside;
  }

  /**
   *
   */
  public void drawBorder() {
    ivHome.ivOffGraphics.setColor(Color.white);
    ivHome.ivOffGraphics.fillRect(0, 0, ivHome.ivOffWidth - 1, ivHome.ivOffHeight - 1);

    ivHome.ivOffGraphics.setColor(Color.black);
    ivHome.ivOffGraphics.drawRect(0, 0, ivHome.ivOffWidth - 1, ivHome.ivOffHeight - 1);
  }

  /**
   *
   */
  public void drawHouse() {
    for (int i = 0; i < ivHouseX.length; i++) {
      ivHome.ivOffGraphics.setColor(Color.black);
      ivHome.ivOffGraphics.drawRect(new Double(ivHouseX[i] * ivHome.ivXFactor).intValue(),
          new Double(ivHouseY[i] * ivHome.ivYFactor).intValue(),
          new Double(ivHouseWidth[i] * ivHome.ivXFactor).intValue(),
          new Double(ivHouseHeight[i] * ivHome.ivYFactor).intValue());

      ivHome.ivOffGraphics.setColor(Color.lightGray);
      ivHome.ivOffGraphics.fillRect(new Double(ivHouseX[i] * ivHome.ivXFactor).intValue(),
          new Double(ivHouseY[i] * ivHome.ivYFactor).intValue(),
          new Double(ivHouseWidth[i] * ivHome.ivXFactor).intValue(),
          new Double(ivHouseHeight[i] * ivHome.ivYFactor).intValue());
    }
  }

  /**
   *
   */
  public void drawRestArea() {
    for (int i = 0; i < ivRestAreaX.length; i++) {
      ivHome.ivOffGraphics.setColor(Color.red);
      ivHome.ivOffGraphics.drawOval(new Double(ivRestAreaX[i] * ivHome.ivXFactor).intValue(),
          new Double(ivRestAreaY[i] * ivHome.ivYFactor).intValue(),
          new Double(ivRestAreaR[i] * ivHome.ivXFactor).intValue(),
          new Double(ivRestAreaR[i] * ivHome.ivYFactor).intValue());
    }
  }

  /**
   *
   */
  public void drawTrap() {
    for (int i = 0; i < ivTrapX.length; i++) {
      ivHome.ivOffGraphics.setColor(Color.orange);
      ivHome.ivOffGraphics.fillRect(new Double(ivTrapX[i] * ivHome.ivXFactor).intValue(),
          new Double(ivTrapY[i] * ivHome.ivYFactor).intValue(), new Double(1 * ivHome.ivXFactor).intValue(),
          new Double(1 * ivHome.ivYFactor).intValue());
    }
  }

  /**
   * @param pX Location x-coord for a human.
   * @param pY Location y-coord for a human.
   */
  public boolean isThisPosFree(int pX, int pY) {
    boolean inside = false;
    int margin = 5;

    // Are we inside the map ??
    if ((pX < 0 - margin) || (pX + 1 > ivHome.cvMapSize + margin) || (pY < 0 - margin)
        || (pY + 1 > ivHome.cvMapSize + margin)) {
      return false;
    }

    for (int i = 0; i < ivHouseX.length; i++) {
      // Are we inside a house ??
      if (((pX >= ivHouseX[i]) && (pX < ivHouseX[i] + ivHouseWidth[i]))
          && ((pY >= ivHouseY[i]) && (pY < ivHouseY[i] + ivHouseHeight[i]))) {
        inside = true;
        break;
      }
    }

    return !inside;
  }

  /**
   * @param pX Location x-coord for a human.
   * @param pY Location y-coord for a human.
   */
  public boolean isThisPosRestArea(int pX, int pY) {
    boolean inside = false;
    int margin = 5;

    // Are we inside the map ??
    if ((pX < 0 - margin) || (pX + 1 > ivHome.cvMapSize + margin) || (pY < 0 - margin)
        || (pY + 1 > ivHome.cvMapSize + margin)) {
      return false;
    }

    for (int i = 0; i < ivRestAreaX.length; i++) {
      // Are we inside a restArea ??
      if (((pX >= ivRestAreaX[i]) && (pX < ivRestAreaX[i] + ivRestAreaR[i] + margin))
          && ((pY >= ivRestAreaY[i]) && (pY < ivRestAreaY[i] + ivRestAreaR[i] + margin))) {
        inside = true;
        break;
      }
    }

    return !inside;
  }
}
