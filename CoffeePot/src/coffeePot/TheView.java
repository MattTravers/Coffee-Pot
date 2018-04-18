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
	private Scanner menuFile;
	private ArrayList<Drink> drinkMenu;
	
	public Drink tea = new Drink("tea", 111);

	public Drink lol = new Drink("lol", 3);
	public Drink yo = new Drink("yo", 11231);
	public Drink ma = new Drink("ma", 11231);
	public Drink ho = new Drink("ho", 11331);
	///
	private JButton drinkButtons[];
	private JButton coinButtons[];
	private JButton ingriedentButtons[];
	private JButton upButtons[];
	private JButton downButtons[];

	
	private JFrame orderWindow = new JFrame();
	private JFrame coinSlotWindow = new JFrame();


	private Container coinSlot;
	private Container orderMenu;
	
	
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel ingredientSelection = new JPanel();

	
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel condimentPanel = new JPanel();
	
	///
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	
	
	private JTextField outputField = new JTextField("", 20);
	private JTextField balanceField = new JTextField("", 20);
	
	private String coins[] = { "penny", "nickel", "dime", "quarter" };
	
	// Listener Events

	public TheView(TheController theController) {
		this.controller = theController;
		
		drinkMenu = new ArrayList<Drink>();
		tea.addIngredient("moar",10000);
		
		drinkMenu.add(tea);
		drinkMenu.add(lol);
		drinkMenu.add(yo);
		drinkMenu.add(ma);
		drinkMenu.add(ho);
		
		/*
		//getting the drink menu from the text file
		this.drinkMenu = new ArrayList<Drink>();
		try {
			menuFile = new Scanner(new File("menu.in"));
		} catch (Exception e) {
			System.out.println("Missing menu file");
			System.exit(1);
		}
		
		while (menuFile.hasNextLine()) {
			String drinkName = menuFile.nextLine();
			int drinkPrice = Integer.parseInt(menuFile.nextLine());
			Drink drink = new Drink(drinkName,drinkPrice);
			String ingredient = menuFile.nextLine();
			while(!ingredient.equals("endDrink")) {
				drink.addIngredient(ingredient,0);
				ingredient = menuFile.nextLine();
			}
		}
		*/
		
		drinkSelection.setLayout(new GridLayout(drinkMenu.size(), 3));
		balanceField.setEnabled(false);
		outputField.setEnabled(false);

		drinkButtons = new JButton[drinkMenu.size()];
		coinButtons = new JButton[drinkMenu.size()];
		upButtons = new JButton[drinkMenu.size()];
		downButtons = new JButton[drinkMenu.size()];


		// implement drink buttons
		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			upButtons[i] = new BasicArrowButton(BasicArrowButton.NORTH);
			downButtons[i] = new BasicArrowButton(BasicArrowButton.SOUTH);
			
			drinkButtons[i].addActionListener(controller.makeActionListener(drinkButtons[i]));
			
			drinkSelection.add(drinkButtons[i]);
			condimentPanel.add(upButtons[i]);
			condimentPanel.add(downButtons[i]);
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

	  
	  
	  orderWindow.addWindowListener(controller.windowListener);
	  coinSlotWindow.addWindowListener(controller.windowListener);
	  
	  
	  coinSlotWindow.setVisible( true );
	  orderWindow.setVisible( true );
	  
	 
	}
	
	public void displayIngredientsMenu(ArrayList<Ingredient> ingredients){
		
		ingriedentButtons = new JButton[ingredients.size()];
		
		
		for(int i = 0 ; i < ingredients.size(); i++){
			ingriedentButtons[i] = new JButton(ingredients.get(i).getName());
			ingredientSelection.add(ingriedentButtons[i]);
		}
		
		  orderMenu.add(ingredientSelection,BorderLayout.CENTER);
		
	}
	
	//test
	public void display(String str){
		
		JLabel name = new JLabel("You Pressed "+ str);
		
		System.out.println("it twerked");
		  //orderMenu.removeAll(); 

		  //orderMenu.add(name,BorderLayout.CENTER);
		
	}
	
	public void makeButtons() {
		
		String coins[] = { "penny", "nickel", "dime", "quarter" };

		JButton drinkButtons[];
		JButton coinButtons[];
		JButton upButtons[];
		JButton downButtons[];
		

	}
	
	

	@Override
	public void update(String type, String string) {
		if (type.equals("Balance")) {
			// update balance
		} else { //if output
			// update Output
		}
	}

	public static void main(String[] args) {
		TheView view = new TheView(new TheController());
	}

}