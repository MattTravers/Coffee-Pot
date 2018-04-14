package coffeePot;

import java.util.Iterator;

public class CondimentReserve implements Subject {
	private int reserve[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	private Observer observer;
	private String state;
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

		Iterator<String> it = drink.getIterator();

		while (it.hasNext()) {
			String[] name = it.next().split(" ");
			if (reserve[stringConverter(name[0])] < name[1].charAt(0) - '0') {
				System.out.println("Error: not enough " + name);
				return false;
			}
		}
		return true;
	}

	public void changeReserve(Drink drink) {
		Iterator<String> it = drink.getIterator();

		while (it.hasNext()) {
			String[] name = it.next().split(" ");
			if (stringConverter(name[0]) == -1) {

			} else {
				reserve[stringConverter(name[0])] -= name[1].charAt(0) - '0';
			}
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
		this.observer.update(this.state);
	}
}
