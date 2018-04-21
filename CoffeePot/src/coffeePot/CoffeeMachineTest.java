package coffeePot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoffeeMachineTest {
	TheView theView;
	CoinSlot coinSlot;
	Dispenser dispenser;
	TheController theController;

	@BeforeClass
	public static void onlyOnce() {

	}

	@Before
	public void setUp() { // setup is the same as main in CoffeeMachine except it doesn't run the view
		// view class
		theView = new TheView();

		// model classes
		coinSlot = new CoinSlot();
		dispenser = new Dispenser(coinSlot);

		// controller class
		theController = new TheController(theView, dispenser, coinSlot);

		// setting up observers
		dispenser.registerObserver(theView);
		coinSlot.registerObserver(theView);
	}

	// Sally puts in one quarter, presses dispense Insufficient funds.
	@Test
	public void testInsufficientFunds() {
		dispenser.setDrinkName("Regular Coffee");
		coinSlot.insert("quarter");
		dispenser.serveDrink();
		Assert.assertEquals("Please insert more money, Regular Coffee costs 35", dispenser.getOutput());
	}

	// Sally puts in two quarters, presses dispense Dispenses coffee.
	@Test
	public void testBasicDispense() {
		dispenser.setDrinkName("Regular Coffee");

		coinSlot.insert("quarter");
		coinSlot.insert("quarter");

		dispenser.serveDrink();
		
		Assert.assertEquals("Coffee Machine dispenses Regular Coffee", dispenser.getOutput());
		Assert.assertEquals(15, coinSlot.getBalance());
	}

	// Sally puts in one quarters, presses coin return Returns 25 cents.
	@Test
	public void testCoinReturn() {
		coinSlot.insert("quarter");
		coinSlot.coinReturn();
		Assert.assertEquals("$0.00", coinSlot.getOutput());
	}

	// Sally puts in two quarters, walks away Balance should show 50 cents.
	@Test
	public void testBalance() {
		coinSlot.insert("quarter");
		coinSlot.insert("quarter");
		Assert.assertEquals(50, coinSlot.getBalance());
	}

	// Sally buys two coffees, white with sugar. The sugar dispenser runs out of
	// sugar after the first Dispenses one coffee, displays out of sugar.
	@Test
	public void testOutOfIngredient() {
		coinSlot.insert("quarter");
		coinSlot.insert("quarter");
		coinSlot.insert("quarter");
		dispenser.setDrinkName("Regular Coffee");
		for (int i = 0; i < 25; i++) {
			dispenser.increaseIngredient("Sugar");
		}
		dispenser.serveDrink();
		Assert.assertEquals("Coffee Machine dispenses Regular Coffee, 25 Sugar", dispenser.getOutput());
		dispenser.setDrinkName("Regular Coffee");
		dispenser.increaseIngredient("Sugar");
		Assert.assertEquals("Not enough Sugar", dispenser.getOutput());

	}

	// Call balance Balance should show 0 cents.
	@Test
	public void testInitialBalance() {
		Assert.assertEquals(0, coinSlot.getBalance());

	}

	// lets get some decaf
	@Test
	public void testDecafCoffee() {
		coinSlot.insert("quarter");
		coinSlot.insert("dime");
		dispenser.setDrinkName("Decaf Coffee");
		dispenser.serveDrink();
		Assert.assertEquals("Coffee Machine dispenses Decaf Coffee", dispenser.getOutput());
		Assert.assertEquals(0, coinSlot.getBalance());

	}

	// ...what about some chicken broth?
	@Test
	public void testChickenBroth() {
		coinSlot.insert("quarter");
		coinSlot.insert("quarter");
		coinSlot.insert("nickel");
		coinSlot.insert("dime");
		dispenser.setDrinkName("Chicken Broth");
		dispenser.serveDrink();
		Assert.assertEquals("Coffee Machine dispenses Chicken Broth", dispenser.getOutput());
		Assert.assertEquals(0, coinSlot.getBalance());

	}

	// ...tea?
	@Test
	public void testTea() {
		coinSlot.insert("quarter");
		coinSlot.insert("quarter");
		dispenser.setDrinkName("Tea");
		dispenser.serveDrink();
		Assert.assertEquals("Coffee Machine dispenses Tea", dispenser.getOutput());
		Assert.assertEquals(0, coinSlot.getBalance());
	}

	@After
	public void somethingThatNeedsDoingAfterEveryTestBesidesOrdinaryGarbageCollection() {
	}

	@AfterClass
	public static void somethingAfterAllIsDoneLikeLoggingOutOfDatabase() {
	}

}
