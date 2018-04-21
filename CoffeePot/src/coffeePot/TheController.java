package coffeePot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

				view.makeIngredientsMenu(ingredients);
				dispenser.setDrinkName(drinkName);

			}
		};

		return drinkPressed;
	}

	// ingredient increase button
	public ActionListener incrementIngredient(JButton button) {

		ActionListener increasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = button.getText().substring(1, button.getText().length()-1);
				
				boolean inList = false;

				for (Ingredient i : ingredientChanges) {
					if (name.equals(i.getName())) {
						i.increaseAmount();
						view.updateOutput("Amount of " +i.getName() + ": "+ i.getAmount() );
						inList = true;
					}
				}

				if (!inList) {
					Ingredient i = new Ingredient(name, 1);
					ingredientChanges.add(i);
					view.updateOutput("Amount of " +i.getName() + ": "+ i.getAmount() );
				}

				dispenser.increaseIngredient(button.getText());
			}
		};

		return increasePressed;
	}

	// ingredient decrease button
	public ActionListener decrementIngredient(JButton button) {

		ActionListener decreasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = button.getText().substring(1, button.getText().length()-1);

				boolean inList = false;

				for (Ingredient i : ingredientChanges) {
					if (name.equals(i.getName())) {
						i.decreaseAmount();
						view.updateOutput("Amount of " +i.getName() + ": "+ i.getAmount() );
						inList = true;
					}
				}
				System.out.println(ingredientChanges);
				dispenser.decreaseIngredient(button.getText());

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
				view.makeDrinksMenu();
			}
		};

		return submit;
	}
	
	public ActionListener cancel(JButton button) {

		ActionListener cancel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingredientChanges.clear();
				view.updateOutput("");
				view.makeDrinksMenu();
			}
		};

		return cancel;
	}

}
