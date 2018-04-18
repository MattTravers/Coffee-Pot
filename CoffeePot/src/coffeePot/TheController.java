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

	// Attributes for ActionListeners
	private ArrayList<Ingredient> ingredientChanges = new ArrayList<Ingredient>();

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

	// drink buttons
	public ActionListener drinkSelect(JButton button) {

		ActionListener drinkPressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
				String drinkName = button.getText();

				for (Drink drink : drinkMenu) {
					if (drink.getName() == drinkName) {
						ingredients = drink.getIngredients();
					}
				}

				view.displayIngredientsMenu(ingredients);
				dispenser.setDrinkName(drinkName);

			}
		};

		return drinkPressed;
	}

	// ingredient increase button
	public ActionListener incrementIngredient(JButton button) {

		ActionListener increasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean inList = false;

				for (Ingredient i : ingredientChanges) {
					if (button.getText().substring(0) == i.getName()) {
						i.increaseAmount();
						view.updateCondimentCount(i);
						inList = true;
					}
				}

				if (!inList) {
					Ingredient i = new Ingredient(button.getText().substring(0), 1);
					ingredientChanges.add(i);
					view.updateCondimentCount(i);
				}

			}
		};

		return increasePressed;
	}

	// ingredient decrease button
	public ActionListener decrementIngredient(JButton button) {

		ActionListener decreasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean inList = false;

				for (Ingredient i : ingredientChanges) {
					if (button.getText().substring(0) == i.getName()) {
						i.decreaseAmount();
						view.updateCondimentCount(i);
						inList = true;
					}
				}

			}
		};

		return decreasePressed;
	}

	// this is the add Coin method
	public ActionListener addBalance(JButton button) {

		ActionListener addBalance = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				coinSlot.insert(button.getText());

			}
		};

		return addBalance;
	}

	// Coin return
	public ActionListener returnBalance(JButton button) {

		ActionListener returnBalance = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinSlot.coinReturn();
			}
		};

		return returnBalance;
	}

	// submit
	public ActionListener submit(JButton button) {

		ActionListener submit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispenser.serveDrink();
			}
		};

		return submit;
	}

}
