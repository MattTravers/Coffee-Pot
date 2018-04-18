package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

public class Drink implements Iterable<Ingredient> {
	private String name;
	private int price;
	private ArrayList<Ingredient> ingredients;
	
	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
		this.ingredients = new ArrayList<Ingredient>();
		this.ingredients.add(new Ingredient(name,1));
	}

	public void addIngredient(String ingredient, int amount) {
		ingredients.add(new Ingredient(ingredient,amount));
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	@Override
	public Iterator<Ingredient> iterator() {
		return ingredients.iterator();
	}

		
	
}
