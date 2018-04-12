package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class Coffee implements Drink {
	private final int price = 35;
	private int cream;
	private int sugar;
	private boolean decaf;

	public Coffee(boolean decaf, int sugar, int cream) {
		this.cream = cream;
		this.sugar = sugar;
		this.decaf = decaf;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	public String toString() {
		return "Coffee" + (decaf ? ", decaf" : "") + (cream > 0 ? ", cream" : "") + (sugar > 0 ? ", sugar" : "");

	}

	@Override
	public Iterator<String> getIterator() {
		// TODO Auto-generated method stub
		ArrayList<String> ingredients = new ArrayList<String>();
		if (!decaf) {
			ingredients.add("Coffee 1");
		} else {
			ingredients.add("Decaf 1");
		}
		if (sugar > 0)
			ingredients.add("Sugar " + sugar);
		if (cream > 0)
			ingredients.add("Cream " + cream);

		return ingredients.iterator();
	}

}
