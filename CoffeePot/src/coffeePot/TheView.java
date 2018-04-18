package coffeePot;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TheView extends JFrame implements Observer {
	//non-view attributes
	private TheController controller;
	private ArrayList<Drink> drinkMenu;
	
	//view attributes
	private JButton drinkButtons[];
	private JButton coinButtons[];
	private JButton returnCoin = new JButton("RETURN");
	
	private JFrame orderWindow = new JFrame();
	private JFrame coinSlotWindow = new JFrame();


	private Container coinSlot;
	private Container orderMenu;
	private Container ingrList;
	
	
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();

	
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	
	
	private JTextField outputField = new JTextField("", 20);
	private JTextField balanceField = new JTextField("", 20);
	private JTextField numIngr[] = {};
	
	private String coins[] = { "penny", "nickel", "dime", "quarter" };
	

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public TheView() {}
	
	//update balance view//
	
	
	
	///MAIN INSTANTIATING RUN///
	public void run() {
		drinkSelection.setLayout(new GridLayout(drinkMenu.size(), 3));
		balanceField.setEnabled(false);
		outputField.setEnabled(false);

		drinkButtons = new JButton[drinkMenu.size()];
		coinButtons = new JButton[drinkMenu.size()];



		// implement drink buttons
		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			drinkButtons[i].addActionListener(controller.drinkSelect(drinkButtons[i]));
			drinkSelection.add(drinkButtons[i]);
		}

		// implement coin buttons

		  for(int i = 0; i < coins.length; i++) {
			  coinButtons[i] = new JButton(coins[i]);
			  coinButtons[i].addActionListener(controller.addBalance(coinButtons[i]));
			  coinSelection.add(coinButtons[i]); 
		  }
		  
		  returnCoin.addActionListener(controller.returnBalance(returnCoin));
		  coinSelection.add(returnCoin);
      
	  outputSection.add(OUTPUT);
	  outputSection.add(outputField);
	  balanceSection.add(BALANCE);
	  balanceSection.add(balanceField);
      
      coinSlot = coinSlotWindow.getContentPane();
	  coinSlotWindow.setTitle("Coin Slot");
	  coinSlotWindow.setSize( 400, 100 );
	  coinSlotWindow.setLocation( 200, 300 );
	  
	  
	  orderMenu = orderWindow.getContentPane();
	  orderWindow.setTitle("Drink Selection");
	  orderWindow.setSize( 500, 500 );
	  orderWindow.setLocation( 700, 200 );

	  
	  orderMenu.add(outputSection,BorderLayout.SOUTH);
	  orderMenu.add(drinkSelection,BorderLayout.CENTER);
	  orderMenu.add(balanceSection,BorderLayout.NORTH);
	  
	  coinSlot.add(coinSelection,BorderLayout.CENTER);

	  
	  
	  orderWindow.addWindowListener(windowListener);
	  coinSlotWindow.addWindowListener(windowListener);
	  
	  
	  coinSlotWindow.setVisible( true );
	  orderWindow.setVisible( true );
	}
	
	//Ingredients Mode///
	public void displayIngredientsMenu(ArrayList<Ingredient> ingredients){
		
		JButton ingriedentPlusButtons[];
		JButton ingriedentMinusButtons[];
		JButton submit = new JButton("SUBMIT");
		
		submit.addActionListener(controller.submit(submit));
		
		JFrame ingredientsWindow = new JFrame();

		JPanel ingredientSelection = new JPanel();
		
		
		ingriedentPlusButtons = new JButton[ingredients.size()];
		ingriedentMinusButtons = new JButton[ingredients.size()];
		numIngr = new JTextField[ingredients.size()];

		
		for(int i = 0 ; i < ingredients.size(); i++){
			ingriedentPlusButtons[i] = new JButton(ingredients.get(i).getName());
			ingriedentMinusButtons[i] = new JButton(ingredients.get(i).getName());
			ingriedentPlusButtons[i].addActionListener(controller.incrementIngredient(ingriedentPlusButtons[i]));
			ingriedentMinusButtons[i].addActionListener(controller.decrementIngredient(ingriedentMinusButtons[i]));
			ingredientSelection.add(ingriedentPlusButtons[i]);
			ingredientSelection.add(ingriedentMinusButtons[i]);
		}
		
		ingredientSelection.add(submit);
		
		ingredientSelection.setLayout(new GridLayout(7, 1));
		
		ingrList = ingredientsWindow.getContentPane();
		ingrList.setLayout( new FlowLayout() );
		ingredientsWindow.setTitle("Ingredient Selection");
		ingredientsWindow.setSize( 180, 400 );
		ingredientsWindow.setLocation( 500, 200 );
		  
		ingrList.add(ingredientSelection);

		
		ingredientsWindow.setVisible( true );
	}
	

	public void updateOutput(String string) {
		outputField.setText(string);
	}
	

	@Override
	public void update(String type, String string) {
		if (type.equals("Balance")) {
			balanceField.setText(string);
		} else { //if output
			// update Output
		}
	}


	public void setDrinkMenu(ArrayList<Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}
	
	public void setController(TheController controller) {
		this.controller = controller;
	}

}