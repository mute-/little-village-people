package com.toudal.home;

import com.toudal.awt.*;
import com.toudal.awt.panels.*;
import java.applet.Applet;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
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
public class Home extends Applet implements Runnable {

  /**
   *
   */
  private static final long serialVersionUID = -7060464800305014416L;

  /**
   *
   */

  private static final String UNIX_FLAG = "-remote openURL";

  private static final String UNIX_PATH = "netscape";

  private static final String WIN_FLAG = "url.dll,FileProtocolHandler";

  private static final String WIN_ID = "Windows";

  private static final String WIN_PATH = "rundll32";

  public static void displayURL(String pDisplayURL) {
    System.out.println("Testing platform :");
    String lString = null;
    try {
      if (isWindowsPlatform()) {
        System.out.println("Visiting client is running a windows platform");
        lString = "rundll32 url.dll,FileProtocolHandler " + pDisplayURL;
        Runtime.getRuntime().exec(lString);
      } else {
        System.out.println(
          "Visiting client is running a unix platform, cool dude!"
        );
        lString = "netscape -remote openURL(" + pDisplayURL + ")";
        Process process = Runtime.getRuntime().exec(lString);
        try {
          int lWaitFor = process.waitFor();
          if (0 != lWaitFor) {
            lString = "netscape " + pDisplayURL;
            Process lProcess = Runtime.getRuntime().exec(lString);
          }
        } catch (InterruptedException pInterruptedException) {
          System.err.println(
            "Error bringing up browser, cmd='" + lString + "'"
          );
          System.err.println("Caught: " + pInterruptedException);
        }
      }
    } catch (IOException pIOException) {
      System.err.println("Could not invoke browser, command=" + lString);
      System.err.println("Caught: " + pIOException);
    }
  }

  public static boolean isWindowsPlatform() {
    String lString = System.getProperty("os.name");
    return null != lString && lString.startsWith("Windows");
  }

  private World ivWorld;

  private TextArea ivTextArea;

  private Thread ivThread;

  public Home() {}

  public void destroy() {
    if (ivThread != null) ivThread.destroy();
  }

  public void displayURL(String pString, boolean pFlag) {
    if (pFlag) displayURL(pString); else try {
      getAppletContext().showDocument(new URL(pString));
    } catch (MalformedURLException pMalformedURLException) {
      System.err.println("Caught: " + pMalformedURLException);
    }
  }

  public String getAppletInfo() {
    return "Toudal WWW\n\n<big>A Toudal Production</big>\n<big><u>Future Implementation:</u></big>\n<small>\n<ul>\n<li>    </li>\n<li>    </li>\n<li>    </li>\n</ul>\n</small>\nCreation date: (12-19-1998 19:52:16)\n@author: Michael Sebastian Toudal \n\n";
  }

  public void init() {
    super.init();
    setBackground(Color.black);
  }

  public void run() {
    while (ivThread != null) {
      if (!ivWorld.cvChanged) {
        ivWorld.mayUpdate();
        try {
          Thread.sleep(1000L);
        } catch (Exception _ex) {
          System.err.println("I got confused in run...");
          System.err.println("maybe user exited applet...");
          stop();
        }
      }
      if (ivWorld.cvChanged) {
        ivWorld.cvChanged = false;
        try {
          ivTextArea.setText("");
          char ac[] = ivWorld.getContext().toCharArray();
          for (int i = 0; i < ac.length; i++) {
            if (ivWorld.cvChanged) break;
            if ("\243".equalsIgnoreCase(ac[i] + "")) ivTextArea.append(
              "\n"
            ); else ivTextArea.append(ac[i] + "");
            Thread.sleep(5L);
          }
        } catch (Exception _ex) {
          System.err.println("Got confused in drawtext.");
        }
      }
    }
  }

  public void start() {
    Slider slider = new Slider(true);
    slider.setBounds(0, 0, 414, 190);
    slider.setSliderSize(1);
    slider.setSliderPos(132);
    add(slider);
    ivTextArea = new TextArea("", 0, 0, 3);
    ivTextArea.setBounds(0, 0, 0, 0);
    ivTextArea.setEditable(false);
    ivTextArea.setBackground(Color.black);
    ivTextArea.setForeground(Color.red);
    slider.setComponentRight(ivTextArea);
    ivWorld = World.getInstance();
    ivWorld.cvHome = this;
    slider.setComponentLeft(ivWorld);
    if (ivThread == null) {
      ivThread = new Thread(this);
      ivThread.start();
    }
  }

  public void stop() {
    ivThread = null;
  }
}
