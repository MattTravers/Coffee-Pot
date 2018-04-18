package coffeePot;

public class CoffeeMachine {
	public void main(String args[]) {
		TheController theController = new TheController();
		TheView theView = new TheView(theController);
		Dispenser dispenser = new Dispenser();
		dispenser.registerObserver(theView);
		CoinSlot coinSlot = new CoinSlot();
		coinSlot.registerObserver(theView);
	}
}
