package com.toudal.home;

import com.toudal.awt.panels.Slider;
import java.applet.Applet;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

	public static void displayURL(String s) {
		System.out.println("Testing platform :");
		boolean flag = isWindowsPlatform();
		String s1 = null;
		try {
			if (flag) {
				System.out
						.println("Visiting client is running a windows platform");
				s1 = "rundll32 url.dll,FileProtocolHandler " + s;
				Runtime.getRuntime().exec(s1);
			} else {
				System.out
						.println("Visiting client is running a unix platform, cool dude !");
				s1 = "netscape -remote openURL(" + s + ")";
				Process process = Runtime.getRuntime().exec(s1);
				try {
					int i = process.waitFor();
					if (i != 0) {
						s1 = "netscape " + s;
						Process process1 = Runtime.getRuntime().exec(s1);
					}
				} catch (InterruptedException interruptedexception) {
					System.err.println("Error bringing up browser, cmd='" + s1
							+ "'");
					System.err.println("Caught: " + interruptedexception);
				}
			}
		} catch (IOException ioexception) {
			System.err.println("Could not invoke browser, command=" + s1);
			System.err.println("Caught: " + ioexception);
		}
	}

	public static boolean isWindowsPlatform() {
		String s = System.getProperty("os.name");
		return s != null && s.startsWith("Windows");
	}

	private Worlds ivWorlds;

	private TextArea ivTextArea;

	private Thread ivThread;

	public Home() {
	}

	public void destroy() {
		if (ivThread != null)
			ivThread.destroy();
	}

	public void displayURL(String s, boolean flag) {
		if (flag)
			displayURL(s);
		else
			try {
				getAppletContext().showDocument(new URL(s));
			} catch (MalformedURLException _ex) {
			}
	}

	public String getAppletInfo() {
		return "Conclaves home\n\n<big>A Conclaves Production</big>\n<big><u>Future Implementation:</u></big>\n<small>\n<ul>\n<li>    </li>\n<li>    </li>\n<li>    </li>\n</ul>\n</small>\nCreation date: (12-19-1998 19:52:16)\n@author: Michael S Toudal \n\n";
	}

	public void init() {
		super.init();
		setBackground(Color.black);
	}

	public void run() {
		while (ivThread != null) {
			if (!ivWorlds.cvChanged) {
				ivWorlds.mayUpdate();
				try {
					Thread.sleep(1000L);
				} catch (Exception _ex) {
					System.err.println("I got confused in run...");
					System.err.println("maybe user exited applet...");
					stop();
				}
			}
			if (ivWorlds.cvChanged) {
				ivWorlds.cvChanged = false;
				try {
					ivTextArea.setText("");
					char ac[] = ivWorlds.getContext().toCharArray();
					for (int i = 0; i < ac.length; i++) {
						if (ivWorlds.cvChanged)
							break;
						if ("\243".equalsIgnoreCase(ac[i] + ""))
							ivTextArea.append("\n");
						else
							ivTextArea.append(ac[i] + "");
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
		ivWorlds = Worlds.getInstance();
		ivWorlds.cvHome = this;
		slider.setComponentLeft(ivWorlds);
		if (ivThread == null) {
			ivThread = new Thread(this);
			ivThread.start();
		}
	}

	public void stop() {
		ivThread = null;
	}
}
