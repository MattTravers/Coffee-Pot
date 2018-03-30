package coffee_pot;

public class CoffeeMachine {
	// Attributes
	private CondimentReserve condimentReserve;
	private CoinSlot coinSlot;

	// Constructor
	public CoffeeMachine() {
		condimentReserve = new CondimentReserve();
		coinSlot = new CoinSlot();
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
