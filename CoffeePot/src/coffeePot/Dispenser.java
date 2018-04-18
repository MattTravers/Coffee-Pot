package coffeePot;

import java.util.ArrayList;

public class Dispenser implements Subject {
	private int reserve[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	private Observer observer;
	private String state;
	
	//drink info updated with every button click the view
	private String drinkName;
	private ArrayList<Ingredient> ingredients;	

	private int stringConverter(String name) {
		switch (name) {
		case "Coffee":
			return 0;
		case "Tea":
			return 1;
		case "Decaf":
			return 2;
		case "Sugar":
			return 3;
		case "Cream":
			return 4;
		case "Lemon":
			return 5;
		case "ChickenBroth":
			return 6;
		case "Hot Cocoa":
			return 7;
		case "Marshmellows":
			return 8;
		}
		return -1;
	}

	public boolean check(Drink drink) {
		for (Ingredient i : drink) {
			String name = i.getName();
			if (reserve[stringConverter(name)] < i.getAmount()) {
				//TO DO: change to update view instead of output to console
				System.out.println("Error: not enough " + name); 
				return false;
			}
		}
		return true;
	}

	public void changeReserve(Drink drink) {
		for (Ingredient i : drink) {
			reserve[stringConverter(i.getName())] -= i.getAmount();
		}
	}
	
	public int getReserve(String condiment) {
		return reserve[stringConverter(condiment)];

	}

	public void setreserve(String condiment, int value) {
		this.reserve[stringConverter(condiment)] = value;
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}

	@Override
	public void notifyObservers() {
		this.observer.update("Output", this.state);
	}
}
