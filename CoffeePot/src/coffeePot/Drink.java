package coffeePot;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Drink class is used to store the name, price, and list of potential add ins
 * for a drink
 *
 */
public class Drink implements Iterable<Condiment> {
	private String name;
	private int price;
	private ArrayList<Condiment> condiments;

	public Drink(String name, int price) {
		this.name = name;
		this.price = price;
		this.condiments = new ArrayList<Condiment>();
	}

	public void addCondiment(String condiment, int amount) {
		condiments.add(new Condiment(condiment, amount));
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public ArrayList<Condiment> condiments() {
		return this.condiments;
	}

	@Override
	public Iterator<Condiment> iterator() {
		return condiments.iterator();
	}

}
