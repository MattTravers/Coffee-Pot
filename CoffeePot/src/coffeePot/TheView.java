package coffeePot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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

	private Container orderMenu;
	
	
	private JPanel coinSelection = new JPanel();
	private JPanel drinkSelection = new JPanel();
	private JPanel outputSection = new JPanel();
	private JPanel balanceSection = new JPanel();
	private JPanel ingredientSelection = new JPanel();


	
	private JLabel OUTPUT = new JLabel("OUTPUT");
	private JLabel BALANCE = new JLabel("BALANCE");
	private JLabel COINSLOT = new JLabel("   COIN SLOT");


	
	private JTextField outputField = new JTextField("", 30);
	private JTextField balanceField = new JTextField("", 20);
	
	private String coins[] = { "penny", "nickel", "dime", "quarter" };
	

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};

	public TheView() {}
	
	
	
	///MAIN INSTANTIATING RUN///
	public void run() {
		drinkSelection.setLayout(new GridLayout(drinkMenu.size(), 3));

		balanceField.setEnabled(false);
		outputField.setEnabled(false);

		//drinkButtons = new JButton[drinkMenu.size()];
		coinButtons = new JButton[drinkMenu.size()];


/*
		// implement drink buttons
		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			drinkButtons[i].addActionListener(controller.drinkSelect(drinkButtons[i]));
			drinkSelection.add(drinkButtons[i]);
		}*/

		// implement coin buttons
		
		coinSelection.add(COINSLOT);
		
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
	  
	  
	  orderMenu = orderWindow.getContentPane();
	  orderWindow.setTitle("Coffee Pot");
	  orderWindow.setSize( 500, 500 );
	  orderWindow.setLocation( 700, 200 );

	  coinSelection.setLayout(new GridLayout(10,10));
	  
	  
	  makeDrinksMenu();
	  orderMenu.add(outputSection,BorderLayout.SOUTH);
	 //orderMenu.add(drinkSelection,BorderLayout.CENTER);
	  orderMenu.add(balanceSection,BorderLayout.NORTH);
	  orderMenu.add(coinSelection,BorderLayout.WEST);
	  
	  
	  orderWindow.addWindowListener(windowListener);

	  orderWindow.setVisible( true );
	}
	
	///Make Drinks Menu///
	public void makeDrinksMenu(){
		
		orderWindow.invalidate();
		orderWindow.validate();
		
		drinkButtons = new JButton[drinkMenu.size()];

		for (int i = 0; i < drinkMenu.size(); i++) {
			drinkButtons[i] = new JButton(drinkMenu.get(i).getName());
			drinkButtons[i].addActionListener(controller.drinkSelect(drinkButtons[i]));
			drinkSelection.add(drinkButtons[i]);
		}
		
		changeToDrinks();

	}
	
	///Make Ingredients Menu///
	public void makeIngredientsMenu(ArrayList<Ingredient> ingredients){
				
		orderWindow.invalidate();
		orderWindow.validate();
		
		JButton ingriedentPlusButtons[];
		JButton ingriedentMinusButtons[];
		JTextField ingredientAmount[];
		JLabel ingredientName[];


		JButton submit = new JButton("SUBMIT");
		JButton cancel = new JButton("CANCEL");

		submit.addActionListener(controller.submit(submit));
		cancel.addActionListener(controller.cancel(cancel));

		
		ingriedentPlusButtons = new JButton[ingredients.size()];
		ingriedentMinusButtons = new JButton[ingredients.size()];
		ingredientAmount = new JTextField [ingredients.size()];
		ingredientName = new JLabel [ingredients.size()];
		
		for(int i = 0 ; i < ingredients.size(); i++){
			ingredientName[i] = new JLabel(ingredients.get(i).getName());
			ingredientAmount[i] = new JTextField("",18-ingredients.get(i).getName().length());
			ingredientAmount[i].setEnabled(false);
			ingriedentPlusButtons[i] = new JButton("+"+ingredients.get(i).getName()+"+");
			ingriedentMinusButtons[i] = new JButton("-"+ingredients.get(i).getName()+"-");
			ingriedentPlusButtons[i].addActionListener(controller.incrementIngredient(ingriedentPlusButtons[i]));
			ingriedentMinusButtons[i].addActionListener(controller.decrementIngredient(ingriedentMinusButtons[i]));
			ingredientSelection.add(ingredientName[i]);
			ingredientSelection.add(ingredientAmount[i]);
			ingredientSelection.add(ingriedentPlusButtons[i]);
			ingredientSelection.add(ingriedentMinusButtons[i]);

		}
		
		ingredientSelection.add(cancel);
		ingredientSelection.add(submit);
		ingredientSelection.setLayout(new FlowLayout());


		changeToIngredients();
		
	}
	
	
	public void changeToDrinks() {
		orderWindow.getContentPane().remove(ingredientSelection);
		ingredientSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.add(drinkSelection,BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize( 501, 501 );






	}
	
	
	public void changeToIngredients() {
		orderWindow.getContentPane().remove(drinkSelection);
		drinkSelection.removeAll();
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.getContentPane().remove(drinkSelection);
		orderWindow.add(ingredientSelection,BorderLayout.CENTER);
		orderWindow.invalidate();
		orderWindow.validate();
		orderWindow.setSize( 500, 500 );



	}
	

	public void updateOutput(String string) {
		outputField.setText(string);
	}
	
	
	@Override
	public void update(String type, String string) {
		if (type.equals("Balance")) {
			balanceField.setText(string);
		} else { //if output
			this.updateOutput(string);
		}
	}

	
	public void setDrinkMenu(ArrayList<Drink> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}
	
	
	public void setController(TheController controller) {
		this.controller = controller;
	}

}