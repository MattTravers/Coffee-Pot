package coffee_pot;

import java.util.Iterator;

public class Tea implements Drink {
	private final int price = 50;
	private boolean cream;
	private boolean sugar;
	private boolean lemon;
	
	public Tea (boolean sugar, boolean cream, boolean lemon) {
		this.cream = cream;
		this.sugar = sugar;
		this.lemon = lemon;
	}
	
	@Override
	public int getPrice() {
		return this.price;
	}
	
	public String toString() {
		return "Tea" + (lemon? ", lemon":"") + (cream? ", cream, ":"") + (sugar? ", sugar":"");
		
	}

	@Override
	public Iterator<String> getIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
