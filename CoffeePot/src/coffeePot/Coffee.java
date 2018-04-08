package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class Coffee implements Drink {
	private final int price = 35;
	private boolean cream;
	private boolean sugar;
	private boolean decaf;
	
	public Coffee (boolean decaf, boolean sugar, boolean cream) {
		this.cream = cream;
		this.sugar = sugar;
		this.decaf = decaf;
	}
	
	@Override
	public int getPrice() {
		return this.price;
	}
	
	
	
	public String toString() {
		return "Coffee" + (decaf? ", decaf":"") + (cream? ", cream":"") + (sugar? ", sugar":"");
		
	}

	@Override
	public Iterator<String> getIterator() {
		// TODO Auto-generated method stub
		ArrayList<String> ingredients = new ArrayList<String>();
		if(!decaf) {
		ingredients.add("Coffee");
		}
		else {
			ingredients.add("Decaf");
		}
		if(sugar)
			ingredients.add("Sugar");
		if(cream)
			ingredients.add("Cream");
		
		return ingredients.iterator();
	}
		 
}
