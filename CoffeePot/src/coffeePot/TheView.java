package coffeePot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;


public class TheView extends JFrame{
	
	private JButton buttons[];
	private JButton coinButtons[];
	private JFrame orderWindow = new JFrame();
	private JFrame coinSlotWindow = new JFrame();

	//coinSlot window
	private Container coinSlot;
	
	//condiment window
	private Container condiment;
	
	//orderMenu window
	private Container orderMenu;
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	private JTextField outputField = new JTextField("", 20);
	private JTextField balanceField = new JTextField("", 20);
	private JPanel buttonPanel = new JPanel();	

	private String coins[] = {"penny", "nickel", "dime", "quarter"};

	private String drinks[] = {"Coffee", "Decafe","HotCocoa","Tea","ChickenBroth"};
	
	public TheView(){
		drinkSelection.setLayout(new GridLayout(drinks.length,3));

		balanceField.setEnabled(false);
		outputField.setEnabled(false);
		
		buttons = new JButton[drinks.length];
		coinButtons = new JButton[coins.length];

		outputSection.add(OUTPUT);
		outputSection.add(outputField);
		balanceSection.add(BALANCE);
		balanceSection.add(balanceField);
		
		// implement drink buttons
		for(int i = 0; i < drinks.length; i++) {
		  buttons[i] = new JButton(drinks[i]);	
		  drinkSelection.add(buttons[i]);
		  
		}
		
		// implement coin buttons
		  for(int i = 0; i < coins.length; i++) {
			  coinButtons[i] = new JButton(coins[i]);	
			  
			  if(i==1) {
				  
			  }
			  
			  coinSelection.add(coinButtons[i]);
			  
		  }
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
	  
	  coinSlotWindow.setVisible( true );
	  orderWindow.setVisible( true );
	  
	 
	
	}

public static void main(String[] args){
	TheView view = new TheView();	  
	}
}