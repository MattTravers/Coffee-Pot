package coffeePot;

/**
 * The condiment class is used to store the name and amount of a specific
 * condiment.
 *
 */
public class Condiment {
	private String name;
	private int amount;

	/**
	 * Constructor that sets the condiment name and amount
	 * 
	 * @param name
	 * @param amount
	 */
	public Condiment(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int n) {
		this.amount = 0;
	}

	/**
	 * increase condiment amount by 1
	 */
	public void increaseAmount() {
		this.amount++;
	}

	/**
	 * decrease condiment amount by 1
	 */
	public void decreaseAmount() {
		this.amount--;
	}
}
