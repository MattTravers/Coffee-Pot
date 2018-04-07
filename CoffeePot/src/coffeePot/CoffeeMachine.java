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
	public Coffee serveCoffee(Coffee coffee) {
		if (condimentReserve.check(coffee) && coinSlot.isEnough(coffee.getPrice())) {
			condimentReserve.changeReserve(coffee);
			coinSlot.deduct(coffee.getPrice());
			return coffee;
		}
		return null;
	}
}
