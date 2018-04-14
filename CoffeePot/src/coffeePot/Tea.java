package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class Tea implements Drink {
	private final int price = 50;
	private int cream;
	private int sugar;
	private int lemon;

	public Tea(int sugar, int cream, int lemon) {
		this.cream = cream;
		this.sugar = sugar;
		this.lemon = lemon;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	public String toString() {
		return "Tea" + (lemon > 0 ? ", lemon" : "") + (cream > 0 ? ", cream" : "") + (sugar > 0 ? ", sugar" : "");

	}

	@Override
	public Iterator<String> getIterator() {
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("Tea "+1);

		if (sugar > 0)
			ingredients.add("Sugar " + sugar);
		if (cream > 0)
			ingredients.add("Cream " + cream);
		if (lemon > 0)
			ingredients.add("Lemon " + lemon);

		return ingredients.iterator();
	}

}
