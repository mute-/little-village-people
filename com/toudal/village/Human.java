package com.toudal.village;

// import java
import java.awt.*;

/**
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
public class Human {

  // Public
  public boolean cvDead = false;

  // Privates
  private Home ivHome;
  private Map ivMap;

  private char ivSex = '0';
  private Color ivColor;
  private boolean ivTired = false;

  private int ivPosX, ivPosY;
  private int ivDirection = 0;
  private int ivStep = 0;
  private int ivFresh = 0;
  private int ivMsek = 0;

  public Human(Home pHome, Map pMap) {
    super();
    ivHome = pHome;
    ivMap = pMap;
    ivDirection = new Double(Math.random() * 8).intValue();

    int r = new Double(Math.random() * 200).intValue();
    int g = new Double(Math.random() * 200).intValue();
    int b = new Double(Math.random() * 200).intValue();

    ivColor = new Color(r, g, b);
    if (r > 104) {
      ivSex = 'k';
      ivHome.incNoWomen();
    } else {
      ivSex = 'm';
      ivHome.incNoMen();
    }

    do {
      ivPosX = new Double(Math.random() * ivHome.cvMapSize).intValue();
      ivPosY = new Double(Math.random() * ivHome.cvMapSize).intValue();
    } while (!ivMap.isThisPosFree(ivPosX, ivPosY));
  }

  /**
   *
   */
  public void drawDead() {
    cvDead = true;

    if (ivTired) {
      ivHome.decNoTired();
    }

    if (String.valueOf(ivSex).equalsIgnoreCase("m")) {
      ivHome.decNoMen();
    } else {
      ivHome.decNoWomen();
    }
  }

  /**
   *
   */
  public void drawHuman() {
    ivHome.ivOffGraphics.setColor(ivColor);
    if (!cvDead) ivHome.ivOffGraphics.fillOval(
      new Double(ivPosX * ivHome.ivXFactor).intValue(),
      new Double(ivPosY * ivHome.ivYFactor).intValue(),
      new Double(1 * ivHome.ivXFactor).intValue(),
      new Double(1 * ivHome.ivYFactor).intValue()
    ); else {
      ivHome.ivOffGraphics.drawLine(
        new Double(ivPosX * ivHome.ivXFactor).intValue(),
        new Double(ivPosY * ivHome.ivYFactor).intValue(),
        new Double((ivPosX + 1) * ivHome.ivXFactor).intValue(),
        new Double((ivPosY + 1) * ivHome.ivYFactor).intValue()
      );

      ivHome.ivOffGraphics.drawLine(
        new Double((ivPosX + 1) * ivHome.ivXFactor).intValue(),
        new Double(ivPosY * ivHome.ivYFactor).intValue(),
        new Double(ivPosX * ivHome.ivXFactor).intValue(),
        new Double((ivPosY + 1) * ivHome.ivYFactor).intValue()
      );
    }
  }

  /**
   *
   */
  public void takeAStep() {
    int oldPosX = ivPosX;
    int oldPosY = ivPosY;

    int valg = new Double(Math.random() * 20).intValue();
    boolean isSoveSal = !ivMap.isThisPosRestArea(ivPosX, ivPosY);

    if (String.valueOf(ivSex).equalsIgnoreCase("k") && isSoveSal) {
      ivFresh = ivFresh + 1;

      if (valg == 1) ivDirection = ivDirection - 1; else ivDirection =
        ivDirection + 1;
    } else if (ivTired && isSoveSal) {
      ivFresh = ivFresh + 1;

      if (valg == 1) ivDirection = ivDirection - 1; else ivDirection =
        ivDirection + 1;
    } else {
      if (valg == 1) ivDirection = ivDirection - 1; else if (
        valg == 2
      ) ivDirection = ivDirection + 1;
    }

    if (ivDirection < 1) ivDirection = 8 - ivDirection;
    if (ivDirection > 8) ivDirection = ivDirection - 8;

    if (ivTired || isSoveSal) ivMsek = 1; else ivMsek = 2;

    if (ivDirection == 1) {
      //ivPosX = ivPosX;
      ivPosY = ivPosY - ivMsek;
    }

    if (ivDirection == 2) {
      ivPosX = ivPosX + ivMsek;
      ivPosY = ivPosY - ivMsek;
    }

    if (ivDirection == 3) {
      ivPosX = ivPosX + ivMsek;
      //ivPosY = ivPosY;
    }

    if (ivDirection == 4) {
      ivPosX = ivPosX + ivMsek;
      ivPosY = ivPosY + ivMsek;
    }

    if (ivDirection == 5) {
      //ivPosX = ivPosX;
      ivPosY = ivPosY + ivMsek;
    }

    if (ivDirection == 6) {
      ivPosX = ivPosX - ivMsek;
      ivPosY = ivPosY + ivMsek;
    }

    if (ivDirection == 7) {
      ivPosX = ivPosX - ivMsek;
      //ivPosY = ivPosY;
    }

    if (ivDirection == 8) {
      ivPosX = ivPosX - ivMsek;
      ivPosY = ivPosY - ivMsek;
    }

    if (!ivMap.isThisPosFree(ivPosX, ivPosY)) {
      ivPosX = oldPosX;
      ivPosY = oldPosY;
      ivDirection = ivDirection + 2;
    }

    ivStep = ivStep + 1;

    if (!ivTired) {
      if (ivStep > 500) {
        ivTired = true;
        ivFresh = 0;
        ivStep = 0;
        ivHome.incNoTired();
      }
    } else if (!ivMap.isThisPosRestArea(ivPosX, ivPosY)) {
      if (ivFresh > 15) {
        ivTired = false;
        ivFresh = 0;
        ivStep = 0;
        ivHome.decNoTired();
      }
    } else {
      if (ivStep > 200) {
        drawDead();
      }
    }

    if (!cvDead) {
      if (ivMap.amIDead(ivPosX, ivPosY)) drawDead();
    }
  }
}
