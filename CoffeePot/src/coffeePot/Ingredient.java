package coffeePot;

public class Ingredient {
	private String name;
	private int amount;
	
	public Ingredient(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}
	
	public void increaseAmount() {
		this.amount++;
	}
	
	public void decreaseAmount() {
		this.amount--;
	}
}
