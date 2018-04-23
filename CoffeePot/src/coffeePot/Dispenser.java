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
		return -1;
	}

	// processes a drink. Removes ingredients from reserve.
	public boolean serveDrink() {
		if (!coinSlot.isEnough(this.price)) {
			this.outputString = "Please insert more money, " + drinkName + " costs " + price;
			this.observer.updateOutput(this.outputString);
			return false;
		} else {
			coinSlot.deduct(this.price);
			this.outputString = "Coffee Machine dispenses " + this.drinkName;
			for (Ingredient i : this.ingredients) {
				if (i.getAmount() != 0) {
					this.outputString += ", " + i.getAmount() + " " + i.getName();
				}
			}
			this.changeReserve(ingredients);
			this.observer.updateOutput(this.outputString);
			return true;
		}
	}

	public void changeReserve(ArrayList<Ingredient> ingredients) {
		// deduct drink from reserve
		reserve[stringConverter(this.drinkName)]--;

		// deduct ingredients from reserve
		for (int i = 0; i < this.ingredients.size(); i++) {
			reserve[stringConverter(ingredients.get(i).getName())] -= ingredients.get(i).getAmount();
			ingredients.get(i).setAmount(0);
		}
	}

	public void increaseIngredient(String ingredientName) {
		for (int i = 0; i < this.ingredients.size(); i++) {
			Ingredient temp = this.ingredients.get(i);
			if (temp.getName().equals(ingredientName)) {
				if (temp.getAmount() < reserve[stringConverter(ingredientName)]) {
					temp.increaseAmount();
					observer.updateIngredient(temp.getAmount(), temp.getName());
				} else {
					this.outputString = "Not enough " + ingredientName + ". " + temp.getAmount() + " left.";
					this.observer.updateOutput(this.outputString);
				}
				break;
			}
		}

	}

	public void decreaseIngredient(String ingredientName) {
		for (Ingredient i : this.ingredients) {
			if (i.getName().equals(ingredientName)) {
				if (i.getAmount() > 0) {
					i.decreaseAmount();
					observer.updateIngredient(i.getAmount(), i.getName());
				}
				break;
			}
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = (TheView) observer;
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

	public ArrayList<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public String getOutput() {
		return this.outputString;
	}

	public void resetIngredients() {
		for (int i = 0; i < this.ingredients.size(); i++) {
			this.ingredients.get(i).setAmount(0);
		}
	}
}
