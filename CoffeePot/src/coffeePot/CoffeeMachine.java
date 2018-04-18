package coffeePot;

public class CoffeeMachine {
	public void main(String args[]) {
		// view class
		TheView theView = new TheView();
		
		// model classes
		Dispenser dispenser = new Dispenser();
		CoinSlot coinSlot = new CoinSlot();
		
		// controller class
		TheController theController = new TheController(theView,dispenser,coinSlot);
		
		//setting up observers
		dispenser.registerObserver(theView);
		coinSlot.registerObserver(theView);
		
		// some method to start the coffee machine
		theView.run();
	}
}
