package com.toudal.home;

import com.toudal.awt.buttons.*;
import com.toudal.awt.panels.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.*;

/*
 * ===========================================================================
 * com.toudal.home
 *
 * Project: www.toudal.com, Little Village People in Java
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
public class World extends TPanel implements TButtonListener {

  /**
   *
   */
  private static final long serialVersionUID = -8240074872720976175L;

  private World() {
    cvOver = false;
    cvChanged = false;
    ivVector = new Vector();
    ivTimeOver = 0L;
    ivActive = 0;
    howmany = 8;
    ivInterval = 4000;
    ivImages = new Vector();
    ivPoints = new Point[howmany];
    ivUrls = new String[howmany];
    ivLabels = new String[howmany];
    ivTexts = new String[howmany];
    ivListens = new boolean[howmany];
    readcfg();
    ivTAnchorPanel = new TAnchorPanel(115, 314);
    ivTAnchorPanel.setBackground(Color.black);
    add(ivTAnchorPanel);
    int i = 0;
    ivTButtonWeb = new TButtonWeb[howmany];
    for (int j = 0; j < ivVector.size(); j++) {
      ivTButtonWeb[j] = new TButtonWeb((Image[]) ivImages.elementAt(j), ivPoints[j], "", ivColorExited);
      ivTButtonWeb[j].setBounds(0, i, 115, ivInty[j]);
      i += ivInty[j];
      if (ivListens[j])
        ivTButtonWeb[j].addTButtonListener(this);
      ivTAnchorPanel.add(ivTButtonWeb[j], false, true, true, false);
    }
  }

  public void TButtonClicked(MouseEvent pMouseEvent) {
    if (pMouseEvent.getSource() instanceof TButtonWeb) {
      TButtonWeb lTButtonWeb = (TButtonWeb) pMouseEvent.getSource();
      for (int i = 0; i < howmany; i++)
        if (lTButtonWeb.equals(ivTButtonWeb[i]) && !ivUrls[i].equalsIgnoreCase(""))
          cvHome.displayURL(ivUrls[i], false);
    }
  }

  public void TButtonEntered(MouseEvent pMouseEvent) {
    if (pMouseEvent.getSource() instanceof TButtonWeb) {
      cvChanged = true;
      cvOver = true;
      ivTimeOver = (new Date()).getTime();
      TButtonWeb lTButtonWeb = (TButtonWeb) pMouseEvent.getSource();
      for (int i = 0; i < howmany; i++)
        if (lTButtonWeb.equals(ivTButtonWeb[i])) {
          ivActive = i;
          ivTButtonWeb[i].doUpdate(1, ivLabels[i], ivColorEntered);
        }
    }
  }

  public void TButtonExited(MouseEvent pMouseEvent) {
    if (pMouseEvent.getSource() instanceof TButtonWeb) {
      cvChanged = false;
      cvOver = false;
      ivTimeOver = (new Date()).getTime();
      TButtonWeb lTButtonWeb = (TButtonWeb) pMouseEvent.getSource();
      for (int i = 0; i < howmany; i++)
        if (lTButtonWeb.equals(ivTButtonWeb[i]))
          ivTButtonWeb[i].doUpdate(0, "", ivColorExited);
    }
  }

  public void TButtonPressed(MouseEvent pMouseEvent) {
  }

  public void TButtonReleased(MouseEvent pMouseEvent) {
  }

  public String getContext() {
    return ivTexts[ivActive];
  }

  private Image getImage(String pImagePath) {
    return null;
  }

  private Image getImage(String lFolder, String lFile) {
    Image lImage = (Image) ivHashtable.get(lFolder + lFile);
    if (lImage == null) {
      try {
        MediaTracker lMediaTracker = new MediaTracker(this);
        URL lURL = new URL(lFolder + lFile);
        lImage = Toolkit.getDefaultToolkit().getImage(lURL);
        lMediaTracker.addImage(lImage, 0);
        lMediaTracker.waitForAll();
        lMediaTracker.removeImage(lImage);
        lURL.openConnection();
      } catch (Exception pException) {
        pException.printStackTrace();
      }
      if (lImage == null) {
        lImage = createImage(10, 10);
        lImage.getGraphics().setColor(Color.black);
        lImage.getGraphics().drawString("NO Image", 10, 20);
      }
      ivHashtable.put(lFolder + lFile, lImage);
    }
    return lImage;
  }

  public static World getInstance() {
    if (ivWorld == null)
      return new World();
    else
      return ivWorld;
  }

  public void mayUpdate() {
    if (!cvChanged && cvOver && ivTimeOver + (long) ivInterval < (new Date()).getTime()) {
      cvChanged = false;
      ivTimeOver = (new Date()).getTime();
      ivTButtonWeb[ivActive].doUpdate(3, ivLabels[ivActive], ivColorSpecial);
    }
  }

  private void readcfg() {
    try {
      ivInputStream = getClass().getResourceAsStream("/home.txt");
      ivBufferReader = new BufferedReader(new InputStreamReader(ivInputStream));
      do {
        String s1 = ivBufferReader.readLine();
        if (s1 == null)
          break;
        ivVector.addElement(new String(s1));
      } while (true);
    } catch (Exception pException) {
      System.out.println("Couldn't read config file sorry... : " + pException);
    } finally {
      try {
        ivBufferReader.close();
        ivInputStream.close();
      } catch (Exception pException) {
        System.out.println("Closing buffer threw an exception: " + pException);
      }
    }
    String s = "https://www.toudal.com/images/";
    for (int i = 0; i < ivVector.size(); i++) {
      for (StringTokenizer lStringTokenizer = new StringTokenizer(ivVector.elementAt(i).toString(),
          "\244"); lStringTokenizer.hasMoreTokens();)
        try {
          Image lImage[] = new Image[3];
          lImage[0] = getImage(s + lStringTokenizer.nextToken().trim(), ".gif");
          lImage[1] = getImage(s + lStringTokenizer.nextToken().trim(), ".gif");
          lImage[2] = getImage(s + lStringTokenizer.nextToken().trim(), ".gif");
          ivImages.addElement(lImage);
          ivPoints[i] = new Point(Integer.valueOf(lStringTokenizer.nextToken().trim()).intValue(),
              Integer.valueOf(lStringTokenizer.nextToken().trim()).intValue());
          ivListens[i] = (new Boolean(lStringTokenizer.nextToken().trim())).booleanValue();
          ivLabels[i] = lStringTokenizer.nextToken().trim();
          ivUrls[i] = lStringTokenizer.nextToken().trim();
          ivTexts[i] = lStringTokenizer.nextToken().trim();
        } catch (NoSuchElementException pNoSuchElementException) {
          System.out.println("Element not found: " + pNoSuchElementException);
        }
    }
  }

  public Home cvHome;
  public boolean cvOver;
  public boolean cvChanged;
  private static World ivWorld;
  private InputStream ivInputStream;
  private BufferedReader ivBufferReader;
  private Image ivImage;
  private Vector ivVector;
  private static Hashtable ivHashtable = new Hashtable();
  private long ivTimeOver;
  private int ivActive;
  private int howmany;
  private final String ivVERSION = "TOUDAL";
  private final String ivHomebase = "toudal.jar";
  private static final Color ivColorExited;
  private static final Color ivColorEntered;
  private static final Color ivColorPressed;
  private static final Color ivColorReleased;
  private static final Color ivColorSpecial;
  private TAnchorPanel ivTAnchorPanel;
  private TButtonWeb ivTButtonWeb[];
  private int ivInterval;
  private int ivInty[] = { 45, 28, 16, 16, 16, 16, 16, 28 };
  private Vector ivImages;
  private Point ivPoints[];
  private String ivUrls[];
  private String ivLabels[];
  private String ivTexts[];
  private boolean ivListens[];

  static {
    ivColorExited = Color.darkGray;
    ivColorEntered = Color.blue.darker();
    ivColorPressed = Color.lightGray;
    ivColorReleased = Color.lightGray;
    ivColorSpecial = Color.darkGray;
  }

  @Override
  protected void sizeChanged() {
    // TODO Auto-generated method stub

  }
}
