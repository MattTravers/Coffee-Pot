package coffeePot;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class TheController {
	// Attributes
	private TheView view;
	private Dispenser dispenser;
	private CoinSlot coinSlot;

	// Attributes for creating the drinkMenu
	private Scanner menuFile;
	private ArrayList<Drink> drinkMenu;

	// Constructor
	public TheController(TheView view, Dispenser dispenser, CoinSlot coinSlot) {
		// Models
		this.dispenser = dispenser;
		this.view = view;
		view.setController(this);
		this.coinSlot = coinSlot;

		// Importing the menu.in file
		this.drinkMenu = new ArrayList<Drink>();
		try {
			menuFile = new Scanner(new File("menu.in"));
		} catch (Exception e) {
			System.out.println("Missing menu file");
			System.exit(1);
		}

		// Reading in the menu
		while (menuFile.hasNextLine()) {
			// Get name and price and make drink
			String drinkName = menuFile.nextLine();
			int drinkPrice = Integer.parseInt(menuFile.nextLine());
			Drink drink = new Drink(drinkName, drinkPrice);

			// Get ingredients and add to drink
			String ingredient = menuFile.nextLine();
			while (!ingredient.equals("endDrink")) {
				drink.addIngredient(ingredient, 0);
				ingredient = menuFile.nextLine();
			}

			// Add drink to drinkMenu
			drinkMenu.add(drink);
		}

		// sending drinkMenu to the View and the Model
		dispenser.setReserveLabels(drinkMenu);
		view.setDrinkMenu(drinkMenu);
	}

	// TODO convert all these methods to action listeners????

	// add coin method
	public void addCoin(int value) {
		System.out.println("Added " + value + " cents to the machine!");
		coinSlot.insert(value);
	}

	// get balance
	public int getBalance() {
		System.out.println("Current balance is " + coinSlot.getBalance());
		return coinSlot.getBalance();
	}

	// coin return
	public int coinReturn() {
		System.out.println("Returned " + coinSlot.getBalance());
		return coinSlot.coinReturn();
	}

	// restocks a particular condiment
	public void restockReserve(String condiment, int amount) {
		dispenser.setreserve(condiment, amount);
	}

	public boolean isEnough(Drink drink) {
		return coinSlot.isEnough(drink.getPrice());
	}

	public ActionListener makeActionListener(JButton button) {

		ActionListener drinkPressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String drinkName = button.getText();
				System.out.println(drinkName);

				// Stuff in Dispenser
				/*
				 * ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
				 * 
				 * for(Drink drink: drinkMenu) { if(drink.getName() == drinkName){ ingredients =
				 * drink.getIngredients(); } }
				 */

				view.display(drinkName);
			}
		};

		return drinkPressed;
	}

	WindowListener windowListener = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};
}
