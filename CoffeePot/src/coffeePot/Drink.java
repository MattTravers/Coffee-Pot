package coffeePot;

import java.util.ArrayList;

public class Drink {
	private String name;
	private int price;
	private ArrayList<Ingredient> ingredients;
	
	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
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
}
