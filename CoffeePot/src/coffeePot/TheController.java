package coffeePot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
		this.coinSlot = coinSlot;

		// View
		this.view = view;
		view.setController(this);
		
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
	// add coin
	public void addCoin(String money) {
		System.out.println("Added " + value + " cents to the machine!");
		coinSlot.insert(money);
	}

	// coin return
	public int coinReturn() {
		System.out.println("Returned " + coinSlot.getBalance());
		return coinSlot.coinReturn();
	}

	// drink buttons
	public ActionListener makeActionListener(JButton button) {

		ActionListener drinkPressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String drinkName = button.getText();
				System.out.println(drinkName);

				view.display(drinkName);
				dispenser.setDrinkName(drinkName);
			}
		};

		return drinkPressed;
	}

	// ingredient decrease button
	
	// ingredient increase button
	
	// submit
}
