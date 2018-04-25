package coffeePot;

import java.util.ArrayList;

/**
 * The Dispenser is a model class for a coffee pot. It is used to keep track of
 * the user selection for a drink and deduct drink and condiment amounts from
 * the reserve.
 *
 */
public class Dispenser implements Subject {
	private CoinSlot coinSlot;
	private int reserve[];
	private Observer observer;
	private String outputString;
	private ArrayList<Drink> drinkMenu;
	private ArrayList<String> reserveLabels;

	// drink info updated with every button click the view
	private String drinkName;
	private int price;
	private ArrayList<Condiment> condiments;

	/**
	 * Dispenser constructor takes and sets a CoinSlot object.
	 * 
	 * @param coinSlot
	 */
	public Dispenser(CoinSlot coinSlot) {
		this.coinSlot = coinSlot;
	}

	// used to convert a drink/condiment name into the index for the reserve
	private int stringConverter(String name) {
		for (int i = 0; i < reserveLabels.size(); i++) {
			if (reserveLabels.get(i).equals(name))
				return i;
		}
		return -1;
	}

	/**
	 * Method used to serve drink. If there is enough money in the coinslot for the
	 * drink it updates the output in the view with the drink selection and deducts
	 * the drink/condiments from the reserve.
	 * 
	 * Outputs error message if the balance is not enough to process the drink
	 * 
	 * @return
	 */
	public boolean serveDrink() {
		if (!coinSlot.isEnough(this.price)) {
			this.outputString = "Please insert more money, " + drinkName + " costs "
					+ String.format("$%d.%02d", price / 100, price % 100);
			this.updateOutput();
			return false;
		} else {
			coinSlot.deduct(this.price);
			this.outputString = "Coffee Machine dispenses " + this.drinkName;
			for (Condiment i : this.condiments) {
				if (i.getAmount() != 0) {
					this.outputString += ", " + i.getAmount() + " " + i.getName();
				}
			}
			this.changeReserve(condiments);
			this.updateOutput();
			return true;
		}
	}

	// used to deduct drink/condiment from the reserve
	private void changeReserve(ArrayList<Condiment> condiments) {
		// deduct drink from reserve
		reserve[stringConverter(this.drinkName)]--;

		// deduct condiments from reserve
		for (int i = 0; i < this.condiments.size(); i++) {
			reserve[stringConverter(condiments.get(i).getName())] -= condiments.get(i).getAmount();
			condiments.get(i).setAmount(0);
		}
	}

	/**
	 * Increments the given condiment amount. Max is 5. Will output error if
	 * condiment would be greater than 5 or the amount in the reserve
	 * 
	 * @param condimentName
	 */
	public void increaseCondiment(String condimentName) {
		this.clearOutput();
		for (int i = 0; i < this.condiments.size(); i++) {
			Condiment tempCondiment = this.condiments.get(i);

			if (tempCondiment.getName().equals(condimentName)) {
				if (tempCondiment.getAmount() == 5) {
					this.outputString = "Max of 5";
					this.updateOutput();
				} else if (tempCondiment.getAmount() < reserve[stringConverter(condimentName)]) {
					tempCondiment.increaseAmount();
					observer.updateCondiment(tempCondiment.getAmount(), tempCondiment.getName());
				} else {
					this.outputString = "Not enough " + condimentName + ". " + tempCondiment.getAmount() + " left.";
					this.updateOutput();
				}
				break;
			}
		}

	}

	// sends outputString to the view to be displayed
	private void updateOutput() {
		this.observer.updateOutput(this.outputString);
	}

	/**
	 * Decreases the given condiment amount if amount is > 0;
	 * 
	 * @param condimentName
	 */
	public void decreaseCondiment(String condimentName) {
		this.clearOutput();
		for (Condiment i : this.condiments) {
			if (i.getName().equals(condimentName)) {
				if (i.getAmount() > 0) {
					i.decreaseAmount();
					observer.updateCondiment(i.getAmount(), i.getName());
				}
				break;
			}
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = (View) observer;
	}

	/**
	 * Sets the current drink select and updates the price and condiment list for
	 * the drink
	 * 
	 * @param drinkName
	 * @return
	 */
	public boolean setDrinkName(String drinkName) {
		this.clearOutput();
		if (this.reserve[this.stringConverter(drinkName)] <= 0) {
			this.outputString = "Out of " + drinkName;
			observer.updateOutput(this.outputString);
			return false;
		} else {
			this.drinkName = drinkName;
			for (Drink d : drinkMenu) {
				if (d.getName().equals(drinkName)) {
					this.price = d.getPrice();
					this.condiments = d.condiments();
					break;
				}
			}
			return true;
		}
	}

	// clears the output in the view
	private void clearOutput() {
		this.outputString = "";
		this.observer.updateOutput(this.outputString);
	}

	/**
	 * Takes the given drinkMenu sets up the reserve. Initial reserve values set to
	 * 25
	 * 
	 * @param drinkMenu
	 */
	public void setReserveLabels(ArrayList<Drink> drinkMenu) {
		int count = 0;
		this.drinkMenu = drinkMenu;
		this.reserveLabels = new ArrayList<String>();
		for (Drink d : drinkMenu) {
			this.reserveLabels.add(d.getName());
			count++;
			for (Condiment i : d) {
				this.reserveLabels.add(i.getName());
				count++;
			}
		}
		this.reserve = new int[count];
		for (int i = 0; i < this.reserve.length; i++) {
			this.reserve[i] = 25;
		}
	}

	/**
	 * gets currently selected list of condiments
	 * 
	 * @return
	 */
	public ArrayList<Condiment> getCondiment() {
		return this.condiments;
	}

	/**
	 * Returns the last output String
	 * 
	 * @return
	 */
	public String getOutput() {
		return this.outputString;
	}

	/**
	 * Resets the condment amounts of the currently selected drink to 0;
	 */
	public void resetCondiments() {
		for (int i = 0; i < this.condiments.size(); i++) {
			this.condiments.get(i).setAmount(0);
		}
	}
}
