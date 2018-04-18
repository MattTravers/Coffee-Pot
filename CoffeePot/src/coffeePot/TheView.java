package coffeePot;

import java.util.*;

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
	
	
	private JButton drinkButtons[];
	private JButton coinButtons[];
	private JButton ingriedentPlusButtons[];
	private JButton ingriedentMinusButtons[];
	private JButton submit;

	
	private JFrame orderWindow = new JFrame();
	private JFrame coinSlotWindow = new JFrame();
	private JFrame ingredientsWindow = new JFrame();


	private Container coinSlot;
	private Container orderMenu;
	private Container ingrList;
	
	
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel ingredientSelection = new JPanel();
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JPanel buttonPanel = new JPanel();

	
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	
	
	private JTextField outputField = new JTextField("", 20);
	private JTextField balanceField = new JTextField("", 20);
	
	private String coins[] = { "penny", "nickel", "dime", "quarter" };
	

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public TheView() {
		
	}
	
	public void displayIngredientsMenu(ArrayList<Ingredient> ingredients){
		
		ingriedentPlusButtons = new JButton[ingredients.size()];
		ingriedentMinusButtons = new JButton[ingredients.size()];

		
		for(int i = 0 ; i < ingredients.size(); i++){
			ingriedentPlusButtons[i] = new JButton("+" + ingredients.get(i).getName() + "+");
			ingriedentMinusButtons[i] = new JButton("-" + ingredients.get(i).getName() + "-");
			ingredientSelection.add(ingriedentPlusButtons[i]);
			ingredientSelection.add(ingriedentMinusButtons[i]);
		}
	
		
		ingredientSelection.setLayout(new GridLayout(5, 3));
		
		ingrList = ingredientsWindow.getContentPane();
		ingredientsWindow.setTitle("Ingredient Selection");
		ingredientsWindow.setSize( 500, 500 );
		ingredientsWindow.setLocation( 500, 500 );
		  
		ingrList.add(ingredientSelection,BorderLayout.CENTER);
		
		ingredientsWindow.setVisible( true );
	}
	
	public void run() {
		drinkSelection.setLayout(new GridLayout(drinkMenu.size(), 3));
		balanceField.setEnabled(false);
		outputField.setEnabled(false);

		drinkButtons = new JButton[drinkMenu.size()];
		coinButtons = new JButton[drinkMenu.size()];



		// implement drink buttons
		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			
			drinkButtons[i].addActionListener(controller.makeActionListener(drinkButtons[i]));
			
			drinkSelection.add(drinkButtons[i]);
		}

		// implement coin buttons

		  for(int i = 0; i < coins.length; i++) {
			  coinButtons[i] = new JButton(coins[i]);	
			  coinSelection.add(coinButtons[i]); 
		  }
		  
      Color WHITE = new Color(250,250,250);
      
	  outputSection.add(OUTPUT);
	  outputSection.add(outputField);
	  balanceSection.add(BALANCE);
	  balanceSection.add(balanceField);
      
      coinSlot = coinSlotWindow.getContentPane();
	  coinSlotWindow.setTitle("Coin Slot");
	  coinSlotWindow.setSize( 400, 100 );
	  coinSlotWindow.setLocation( 200, 300 );
	 // coinSelection.setBackground(WHITE);
	  
	  
	  orderMenu = orderWindow.getContentPane();
	  orderWindow.setTitle("Drink Selection");
	  orderWindow.setSize( 500, 500 );
	  orderWindow.setLocation( 700, 200 );
	  //orderMenu.setBackground(WHITE);

	  
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
	
	public void setController(TheController controller) {
		this.controller = controller;
	}

}