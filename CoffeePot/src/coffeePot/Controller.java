package coffeePot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;

/**
 * The controller class is used to handle interaction between the view and the
 * models for a coffee pot.
 * 
 */
public class Controller {
	// Attributes
	private View view;
	private Dispenser dispenser;
	private CoinSlot coinSlot;

	// Attributes for creating the drinkMenu
	private Scanner menuFile;
	private ArrayList<Drink> drinkMenu;

	/**
	 * The constructor for the controller sets a view, dispenser, and coinSlot for
	 * the Controller. It also sets itself as the controller for the view and reads
	 * in a text file to create a drink menu.
	 * 
	 * @param view
	 * @param dispenser
	 * @param coinSlot
	 */
	public Controller(View view, Dispenser dispenser, CoinSlot coinSlot) {
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

			// Get condiments and add to drink
			String condiment = menuFile.nextLine();
			while (!condiment.equals("endDrink")) {
				drink.addCondiment(condiment, 0);
				condiment = menuFile.nextLine();
			}

			// Add drink to drinkMenu
			drinkMenu.add(drink);
		}

		// sending drinkMenu to the View and the Model
		dispenser.setReserveLabels(drinkMenu);
		view.setDrinkMenu(drinkMenu);
	}

	/**
	 * Creates and returns an action listener for drink buttons. The action listener
	 * updates the current drink in the dispenser. It then checks if a drink has
	 * associated condiments. If it does, it updates the view to the condiment menu,
	 * if it does not then it tells the dispenser to server the drink.
	 * 
	 * @param button
	 * @return
	 */
	public ActionListener drinkSelect(JButton button) {

		ActionListener drinkPressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String drinkName = button.getText();

				boolean hasEnoughOfDrink = dispenser.setDrinkName(drinkName);
				if (hasEnoughOfDrink) {
					if (dispenser.getCondiment().size() == 0) {
						dispenser.serveDrink();
					} else {
						view.makeCondimentMenu(dispenser.getCondiment());
					}
				}
			}
		};

		return drinkPressed;
	}

	/**
	 * Creates and returns an action listener for the increase condiment buttons.
	 * The action listener calls the increaseCondiment() method in the dispenser
	 * with the condiment name
	 */
	public ActionListener incrementCondiment(JButton button, String name) {

		ActionListener increasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispenser.increaseCondiment(name);

			}
		};

		return increasePressed;
	}

	/**
	 * Creates and returns an action listener for the decrease condiment buttons.
	 * The action listener calls the decreaseCondiment() method in the dispenser
	 * with the condiment name
	 */

	public ActionListener decrementCondiment(JButton button, String name) {

		ActionListener decreasePressed = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispenser.decreaseCondiment(name);

			}
		};

		return decreasePressed;
	}

	/**
	 * Creates and returns an action listener for the coin buttons. The action
	 * listener calls the insert() method of the coinSlot with the coin name
	 */
	public ActionListener addBalance(JButton button) {

		ActionListener addBalance = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				coinSlot.insert(button.getText());

			}
		};

		return addBalance;
	}

	/**
	 * Creates and returns an action listener for the returnBalance button. The
	 * action listener calls the coinReturn() method in the coinSlot
	 */
	public ActionListener returnBalance(JButton button) {

		ActionListener returnBalance = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinSlot.coinReturn();
			}
		};

		return returnBalance;
	}

	/**
	 * Creates and returns an action listener for the submit button. The action
	 * listener calls the serveDrink() method of the dispenser and if successful
	 * returns the view to the drink menu
	 */
	public ActionListener submit(JButton button) {

		ActionListener submit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean served = dispenser.serveDrink();
				if (served) {
					view.makeDrinksMenu();
				}
			}
		};

		return submit;
	}

	/**
	 * Creates and returns an action listener for the cancel button. The action
	 * listener resets the condiment amounts in the dispenser, clears the output and
	 * sets the view to the drinks menu
	 */
	public ActionListener cancel(JButton button) {

		ActionListener cancel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispenser.resetCondiments();
				view.updateOutput("");
				view.makeDrinksMenu();
			}
		};

		return cancel;
	}

}
