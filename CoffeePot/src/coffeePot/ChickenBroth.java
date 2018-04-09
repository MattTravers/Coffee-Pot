package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class ChickenBroth implements Drink {
	
	public ChickenBroth(){
		super();
	}
	private final int price = 65;

	@Override
	public int getPrice() {
		return this.price;
	}

	public String toString() {
		return "Chicken Broth";

	}

	@Override
	public Iterator<String> getIterator() {
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("ChickenBroth");
		return ingredients.iterator();
	}
}