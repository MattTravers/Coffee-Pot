package coffeePot;


import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TheView extends JFrame implements Observer {
	//non-view attributes
	private TheController controller;
	private ArrayList<Drink> drinkMenu;
	private JButton buttons[];
	
	private JButton coinButtons[];
	private JButton upButtons[];
	private JButton downButtons[];


	private JFrame orderWindow = new JFrame();
	private JFrame coinSlotWindow = new JFrame();

	// coinSlot window
	private Container coinSlot;

	// orderMenu window
	private Container orderMenu;
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel condimentPanel = new JPanel();
	
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	private JLabel Coffee = new JLabel("Coffee");
	private JLabel Decafe = new JLabel("Decafe");
	private JLabel Tea = new JLabel("Tea");
	private JLabel HotCocoa = new JLabel("HotCocoa");
	private JLabel ChickenBroth = new JLabel("ChickenBroth");
	
	private JTextField outputField = new JTextField("", 20);
	private JTextField balanceField = new JTextField("", 20);
	
	private String coins[] = { "penny", "nickel", "dime", "quarter" };
	private String drinks[] = {"Coffee", "Decafe","HotCocoa","Tea","ChickenBroth"};
	
	// Listener Events
	  ActionListener listener = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  
	  ActionListener coffeeClick = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  
	  ActionListener hotCocoaClick = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  
	  ActionListener decafClick = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  
	  ActionListener teaClick = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  
	  ActionListener chickenBrothClick = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  balanceField.setText("1");
		  }
	  };
	  WindowListener windowListener = new WindowAdapter(){
		
		@Override
		public void windowClosing (WindowEvent e) {
			  orderWindow.dispose();
			  coinSlotWindow.dispose();
			  System.exit(0);
		}
	  };

	public TheView() {
		drinkSelection.setLayout(new GridLayout(drinks.length, 3));
		balanceField.setEnabled(false);
		outputField.setEnabled(false);

		// instantiate buttons
		buttons = new JButton[drinks.length];
		coinButtons = new JButton[coins.length];
		upButtons = new JButton[drinks.length];
		downButtons = new JButton[drinks.length];

		outputSection.add(OUTPUT);
		outputSection.add(outputField);
		balanceSection.add(BALANCE);
		balanceSection.add(balanceField);

		// implement drink buttons
		for (int i = 0; i < drinks.length; i++) {
			buttons[i] = new JButton(drinks[i]);
			upButtons[i] = new BasicArrowButton(BasicArrowButton.NORTH);
			downButtons[i] = new BasicArrowButton(BasicArrowButton.SOUTH);
			drinkSelection.add(buttons[i]);
			condimentPanel.add(upButtons[i]);
			condimentPanel.add(downButtons[i]);
		}

		// implement coin buttons

		  for(int i = 0; i < coins.length; i++) {
			  coinButtons[i] = new JButton(coins[i]);	
			  coinSelection.add(coinButtons[i]); 
		  }
		  
		  buttons[0].addActionListener(coffeeClick);
		  buttons[1].addActionListener(decafClick);
		  buttons[2].addActionListener(hotCocoaClick);
	   	  buttons[3].addActionListener(teaClick);
		  buttons[4].addActionListener(chickenBrothClick);

		  
      Color WHITE = new Color(250,250,250);
      
      coinSlot = coinSlotWindow.getContentPane();
	  coinSlotWindow.setTitle("Coin Slot");
	  coinSlotWindow.setSize( 400, 100 );
	  coinSlotWindow.setLocation( 500, 500 );
	  coinSelection.setBackground(WHITE);
	  
	  
	  orderMenu = orderWindow.getContentPane();
	  orderWindow.setTitle("Drink Selection");
	  orderWindow.setSize( 500, 500 );
	  orderWindow.setLocation( 500, 500 );


	  orderMenu.setBackground(WHITE);
;
	  
	  orderMenu.add(outputSection,BorderLayout.SOUTH);
	  orderMenu.add(drinkSelection,BorderLayout.CENTER);
	  orderMenu.add(balanceSection,BorderLayout.NORTH);
	  
	  coinSlot.add(coinSelection,BorderLayout.CENTER);

	  
	  
	  orderWindow.addWindowListener(windowListener);
	  coinSlotWindow.addWindowListener(windowListener);
	  
	  
	  coinSlotWindow.setVisible( true );
	  orderWindow.setVisible( true );
	  
	 
	}

	@Override
	public void update(String type, String string) {
		if (type.equals("Balance")) {
			// update balance
		} else { //if output
			// update Output
		}
	}

//	public static void main(String[] args) {
//		TheView view = new TheView();
//	}

	public void setDrinkMenu(ArrayList<Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}

}