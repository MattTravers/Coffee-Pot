package coffee_pot;

import java.util.Iterator;

public class ChickenBroth implements Drink {
	private final int price = 65;
	private boolean chickenBroth;

	public ChickenBroth() {
		this.chickenBroth = true;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	public String toString() {
		return "Chicken Broth";

	}

	@Override
	public Iterator<String> getIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}