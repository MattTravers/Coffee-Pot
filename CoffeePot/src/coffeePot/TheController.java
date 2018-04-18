package coffeePot;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

}
