package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class HotCocoa implements Drink {

	private final int price = 35;
	private int marshmallows;

	public HotCocoa(int marshmallows) {
		this.marshmallows = marshmallows;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	public String toString() {
		return "Hot Cocoa" + (marshmallows>=0 ? "with Marshmallows" : "");

	}

	@Override
	public Iterator<String> getIterator() {
		// TODO Auto-generated method stub
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("HotCocoa 1");
		if (marshmallows > 0)
			ingredients.add("Marshmallows " + marshmallows);
		return ingredients.iterator();
	}

}
