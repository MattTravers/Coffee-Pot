package coffeePot;

//Main for starting up the coffee machine
public class CoffeeMachine {
	public static void main(String args[]) {
		// view class
		View theView = new View();
		
		// model classes
		CoinSlot coinSlot = new CoinSlot();
		Dispenser dispenser = new Dispenser(coinSlot);
		
		// controller class
		Controller theController = new Controller(theView,dispenser,coinSlot);
		
		//setting up observers
		dispenser.registerObserver(theView);
		coinSlot.registerObserver(theView);
		
		// some method to start the coffee machine
		theView.run();
	}
}
