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
		System.out.println("Added " + value + "to the machine!");
		coinSlot.insert(value);
	}
	//get balance
	public int getBalance() {
		System.out.println("Current balance is " + coinSlot.getBalance());
		return coinSlot.getBalance();
	}
	//coin return
	public int coinReturn() {
		System.out.println("Returned " + coinSlot.getBalance());
		return coinSlot.coinReturn();
	}
	
	
	public boolean isEnough(Drink drink) {
		return coinSlot.isEnough(drink.getPrice());
	}
	
	/*
	 * takes a Drink object and checks balance and reserve, if okay, deducts from
	 * balance and reserve and prints drink.
	 */
	public String serveCoffee(Drink drink) {
		
		if (!condimentReserve.check(drink)) {
			System.out.println("Coffee Machine is out of ingredients for " + drink);
			return "Coffee Machine is out of ingredients for " + drink;
		} else if(!coinSlot.isEnough(drink.getPrice())) {
			System.out.println("Please insert more money");
			return "Please insert more money";
		}
		else {
			condimentReserve.changeReserve(drink);
			coinSlot.deduct(drink.getPrice());
			System.out.println("Coffee Machine dispenses " + drink);
			return "Coffee Machine dispenses " + drink;
		}

	}
}
