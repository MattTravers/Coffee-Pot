package coffeePot;

public class CoffeeMachine {
	public static void main(String args[]) {
		// view class
		TheView theView = new TheView();
		
		// model classes
		CoinSlot coinSlot = new CoinSlot();
		Dispenser dispenser = new Dispenser(coinSlot);
		
		// controller class
		TheController theController = new TheController(theView,dispenser,coinSlot);
		
		//setting up observers
		dispenser.registerObserver(theView);
		coinSlot.registerObserver(theView);
		
		// some method to start the coffee machine
		theView.run();
	}
}
