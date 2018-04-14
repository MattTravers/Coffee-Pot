package coffeePot;

public class CoffeeMachine {
	public void main(String args[]) {
		TheController theController = new TheController();
		TheView theView = new TheView(theController);
		CondimentReserve condimentReserve = new CondimentReserve();
		condimentReserve.registerObserver(theView);
		CoinSlot coinSlot = new CoinSlot();
		coinSlot.registerObserver(theView);
	}
}
