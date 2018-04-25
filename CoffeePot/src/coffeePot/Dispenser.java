package coffeePot;

import java.util.ArrayList;

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

	public Dispenser(CoinSlot coinSlot) {
		this.coinSlot = coinSlot;
	}

	private int stringConverter(String name) {
		for (int i = 0; i < reserveLabels.size(); i++) {
			if (reserveLabels.get(i).equals(name))
				return i;
		}
		return -1;
	}

	// processes a drink. Removes condiments from reserve.
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

	public void changeReserve(ArrayList<Condiment> condiments) {
		// deduct drink from reserve
		reserve[stringConverter(this.drinkName)]--;

		// deduct condiments from reserve
		for (int i = 0; i < this.condiments.size(); i++) {
			reserve[stringConverter(condiments.get(i).getName())] -= condiments.get(i).getAmount();
			condiments.get(i).setAmount(0);
		}
	}

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

	private void updateOutput() {
		this.observer.updateOutput(this.outputString);
	}

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

	// sets drink name. Also updates price by referencing the drinkMenu
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

	public void clearOutput() {
		this.outputString = "";
		this.observer.updateOutput(this.outputString);
	}

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

	public ArrayList<Condiment> getCondiment() {
		return this.condiments;
	}

	public String getOutput() {
		return this.outputString;
	}

	public void resetCondiments() {
		for (int i = 0; i < this.condiments.size(); i++) {
			this.condiments.get(i).setAmount(0);
		}
	}
}
