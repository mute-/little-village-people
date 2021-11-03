package com.toudal.village;

import java.applet.*;
// import java
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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
public class Home extends Applet implements ActionListener, Runnable {

  /** */
  public static final int cvMapSize = 80;
  /** */
  public Graphics ivOffGraphics;
  /** */
  public int ivOffWidth;
  /** */
  public int ivOffHeight;
  /** */
  public double ivXFactor;
  /** */
  public double ivYFactor;
  /** */
  private Image ivOffImage;
  /** */
  private Map ivMap;
  /** */
  private Vector ivPopulation = new Vector();
  /** */
  private Label ivLabelPeople = new Label("No of people");
  /** */
  private Label ivLabelDeadPeople = new Label("No of dead people");
  /** */
  private Label ivLabelDPeople = new Label("0");
  /** */
  private Label ivLabelTiredPeople = new Label("No of tired people");
  /** */
  private Label ivLabelTPeople = new Label("0");
  /** */
  private Label ivLabelMen = new Label("No of men");
  /** */
  private Label ivLabelMPeople = new Label("0");
  /** */
  private Label ivLabelWomen = new Label("No of women");
  /** */
  private Label ivLabelWPeople = new Label("0");
  /** */
  private TextField ivTextFieldPeople = new TextField(2);
  /** */
  private Button ivButtonPeople = new Button("Ok");
  /** */
  private Thread ivThread;

  /**
   * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
   */
  public void actionPerformed(ActionEvent pEvent) {
    try {
      ivLabelTPeople.setText("0");
      ivLabelMPeople.setText("0");
      ivLabelWPeople.setText("0");

      Human human;
      ivPopulation.removeAllElements();

      int tal = new Integer(ivTextFieldPeople.getText()).intValue();
      for (int i = 0; i < tal; i++) {
        human = new Human(this, ivMap);
        ivPopulation.addElement(human);
      }

      repaint();
    } catch (Exception e) {
      System.out.println("The number is not a valid integer");
    }
  }

  /**
   *
   */
  public void decNoMen() {
    String str = String.valueOf(
      new Integer(ivLabelMPeople.getText()).intValue() - 1
    );
    ivLabelMPeople.setText(str);
  }

  /**
   *
   */
  public void decNoTired() {
    String str = String.valueOf(
      new Integer(ivLabelTPeople.getText()).intValue() - 1
    );
    ivLabelTPeople.setText(str);
  }

  /**
   *
   */
  public void decNoWomen() {
    String str = String.valueOf(
      new Integer(ivLabelWPeople.getText()).intValue() - 1
    );
    ivLabelWPeople.setText(str);
  }

  /**
   * @see java.applet.Applet#getAppletInfo()
   */
  public String getAppletInfo() {
    return (
      "Little Village People\n" +
      "\n" +
      "This applet was initially created in VisualAge and later merged in Microsoft Visual Studio Code\n" +
      ""
    );
  }

  /**
   *
   */
  public void incNoMen() {
    String str = String.valueOf(
      new Integer(ivLabelMPeople.getText()).intValue() + 1
    );
    ivLabelMPeople.setText(str);
  }

  /**
   *
   */
  public void incNoTired() {
    String str = String.valueOf(
      new Integer(ivLabelTPeople.getText()).intValue() + 1
    );
    ivLabelTPeople.setText(str);
  }

  /**
   *
   */
  public void incNoWomen() {
    String str = String.valueOf(
      new Integer(ivLabelWPeople.getText()).intValue() + 1
    );
    ivLabelWPeople.setText(str);
  }

  /**
   * @see java.applet.Applet#init()
   */
  public void init() {
    super.init();

    this.setLayout(null);
    this.setBackground(Color.white);

    // Components
    this.add(ivLabelPeople);
    this.add(ivTextFieldPeople);
    this.add(ivButtonPeople);

    this.add(ivLabelDeadPeople);
    this.add(ivLabelDPeople);

    this.add(ivLabelTiredPeople);
    this.add(ivLabelTPeople);

    this.add(ivLabelMen);
    this.add(ivLabelMPeople);

    this.add(ivLabelWomen);
    this.add(ivLabelWPeople);

    ivLabelPeople.setBounds(10, 10, 100, 20);
    ivTextFieldPeople.setBounds(110, 10, 40, 20);

    ivButtonPeople.setBounds(155, 10, 30, 20);
    ivButtonPeople.addActionListener(this);

    ivLabelDeadPeople.setBounds(10, 35, 140, 20);
    ivLabelDPeople.setBounds(155, 35, 40, 20);

    ivLabelTiredPeople.setBounds(10, 60, 140, 20);
    ivLabelTPeople.setBounds(155, 60, 40, 20);
    ivLabelMen.setBounds(10, 85, 140, 20);
    ivLabelMPeople.setBounds(155, 85, 40, 20);
    ivLabelWomen.setBounds(10, 110, 140, 20);
    ivLabelWPeople.setBounds(155, 110, 40, 20);

    // Stuff
    ivOffWidth = getSize().width - 220;
    ivOffHeight = getSize().height - 20;

    /*
		This works:
		offImage    = createImage(xx, yy);
		offGraphics = offImage.getGraphics();
		offGraphics.drawEtEllerAndet...
		
		This does not work with colors
		offImage.getGraphics().drawEtEllerAndet...
		*/

    ivOffImage = this.createImage(ivOffWidth, ivOffHeight);
    ivOffGraphics = ivOffImage.getGraphics();

    // Why force float
    ivXFactor = (0.0 + ivOffWidth) / (0.0 + cvMapSize);
    ivYFactor = (0.0 + ivOffHeight) / (0.0 + cvMapSize);

    // Objects
    ivThread = new Thread(this);
    ivThread.start();

    ivMap = new Map(this);
  }

  /**
   * @see java.awt.Component#paint(Graphics)
   */
  public void paint(Graphics pGraphics) {
    update(pGraphics);
  }

  /**
   *
   */
  public void run() {
    int deadPeople;

    try {
      while (true) {
        Thread.sleep(50);

        // Draw
        ivMap.drawBorder();
        ivMap.drawHouse();
        ivMap.drawTrap();
        ivMap.drawRestArea();

        deadPeople = 0;
        for (int i = 0; i < ivPopulation.size(); i++) {
          if (((Human) ivPopulation.elementAt(i)).cvDead) {
            deadPeople = deadPeople + 1;
          } else {
            ((Human) ivPopulation.elementAt(i)).takeAStep();
          }
          ((Human) ivPopulation.elementAt(i)).drawHuman();
        }

        ivLabelDPeople.setText("" + deadPeople);

        repaint();
      }
    } catch (Exception e) {}
  }

  /**
   *
   */
  public void update(Graphics g) {
    // Vertical line
    g.setColor(Color.black);
    g.fillRect(197, 10, 3, getSize().height - 20);

    // Draw offImage
    g.drawImage(ivOffImage, 210, 10, this);
  }
}
