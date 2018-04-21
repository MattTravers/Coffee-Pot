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
	private ArrayList<Ingredient> ingredients;

	public Dispenser(CoinSlot coinSlot) {
		this.coinSlot = coinSlot;
	}

	private int stringConverter(String name) {
		for (int i = 0; i < reserveLabels.size(); i++) {
			if (reserveLabels.get(i).equals(name))
				return i;
		}
		return -1; // TODO should this throw an specific exception instead of creating and
					// indexOutOfBounds error?
	}

	// processes a drink. Removes ingredients from reserve.
	public void serveDrink() {
		if (!coinSlot.isEnough(this.price)) {
			this.outputString = "Please insert more money, " + drinkName + " costs " + price;
			this.notifyObservers();
		} else {
			this.changeReserve(ingredients);
			coinSlot.deduct(this.price);
			this.outputString = "Coffee Machine dispenses " + this.drinkName;
			for (Ingredient i : this.ingredients) {
				if (i.getAmount() != 0) {
					this.outputString += ", " + i.getAmount() + " " + i.getName();
				}
			}
			this.notifyObservers();
		}
	}

	public void changeReserve(ArrayList<Ingredient> ingredients) {
		// deduct drink from reserve
		reserve[stringConverter(this.drinkName)]--;

		// deduct ingredients from reserve
		for (Ingredient i : ingredients) {
			reserve[stringConverter(i.getName())] -= i.getAmount();
		}
	}

	public void increaseIngredient(String ingredientName) {
		for (Ingredient i : this.ingredients) {
			if (i.getName().equals(ingredientName)) {
				if (i.getAmount() < reserve[stringConverter(ingredientName)]) {
					i.increaseAmount();
				} else {
					this.outputString = "Not enough " + ingredientName;
					this.notifyObservers();
				}
				break;
			}
		}
	}

	public void decreaseIngredient(String ingredientName) {
		for (Ingredient i : this.ingredients) {
			if (i.getName().equals(ingredientName)) {
				if (i.getAmount() == 0) {
					;
				} else {
					i.decreaseAmount();
				}
				break;
			}
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}

	@Override
	public void notifyObservers() {
		this.observer.update("Output", this.outputString);
	}

	// sets drink name. Also updates price by referencing the drinkMenu
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
		for (Drink d : drinkMenu) {
			if (d.getName().equals(drinkName)) {
				this.price = d.getPrice();
				this.ingredients = d.getIngredients();
				break;
			}
		}
	}

	public void setReserveLabels(ArrayList<Drink> drinkMenu) {
		int count = 0;
		this.drinkMenu = drinkMenu;
		this.reserveLabels = new ArrayList<String>();
		for (Drink d : drinkMenu) {
			this.reserveLabels.add(d.getName());
			count++;
			for (Ingredient i : d) {
				this.reserveLabels.add(i.getName());
				count++;
			}
		}
		this.reserve = new int[count];
		for (int i = 0; i < this.reserve.length; i++) {
			this.reserve[i] = 25;
		}
	}

	public String getOutput() {
		return this.outputString;
	}
}
