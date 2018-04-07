package coffeePot;

public class CoffeeMachine {
	// Attributes
	private CondimentReserve condimentReserve;
	private CoinSlot coinSlot;

	// Constructor
	public CoffeeMachine() {
		condimentReserve = new CondimentReserve();
		coinSlot = new CoinSlot();
	}

	//add coin method
	public void addCoin(int value) {
		coinSlot.insert(value);
	}
	//get balance
	public int getBalance() {
		return coinSlot.getBalance();
	}
	//coin return
	public int coinReturn() {
		return coinSlot.coinReturn();
	}
	
	public boolean isEnough(Drink drink) {
		return coinSlot.isEnough(drink.getPrice());
	}
	
	/*
	 * takes a Drink object and checks balance and reserve, if okay, deducts from
	 * balance and reserve and prints drink.
	 */
	public void serveCoffee(Drink drink) {
		if (condimentReserve.check(drink) && coinSlot.isEnough(drink.getPrice())) {
			condimentReserve.changeReserve(drink);
			coinSlot.deduct(drink.getPrice());
			System.out.println(drink);
		}
	}
}
