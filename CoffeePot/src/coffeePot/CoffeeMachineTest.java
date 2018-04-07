package coffeePot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoffeeMachineTest {
	private CoffeeMachine coffeePot;

	@BeforeClass
	public static void onlyOnce() {

	}

	@Before
	public void setUp() {
		coffeePot = new CoffeeMachine();
	}

	// Sally puts in one quarter, presses dispense Insufficient funds.
	@Test
	public void testA() {
		coffeePot.addCoin(25);
		Assert.assertFalse(coffeePot.isEnough(new Coffee(true,true,true)));		
	}

	// Sally puts in two quarters, presses dispense Dispenses coffee.
	@Test
	public void testB() {
		Coffee coffee = new Coffee(false, false, false);
		coffeePot.addCoin(25);
		coffeePot.addCoin(25);
		Assert.assertEquals(50, coffeePot.getBalance());
		Assert.assertEquals(coffee, coffeePot.serveCoffee(coffee));
		Assert.assertEquals(15, coffeePot.getBalance());
	}

	// Sally puts in one quarters, presses coin return Returns 25 cents.
	@Test
	public void testC() {
		// Assert.assertEquals();
	}

	// Sally puts in two quarters, walks away Balance should show 50 cents.
	@Test
	public void testD() {
		// Assert.assertEquals();
	}

	// Sally buys two coffees, white with sugar The sugar dispenser runs out of
	// sugar after the first Dispenses one coffee, displays �out of sugar.�
	@Test
	public void testE() {
		// Assert.assertEquals();
	}

	// Call balance Balance should show 0 cents.
	@Test
	public void testF() {
		Assert.assertEquals(0, coffeePot.getBalance());
		coffeePot.addCoin(25);
		Assert.assertEquals(25, coffeePot.getBalance());
	}

	@After
	public void somethingThatNeedsDoingAfterEveryTestBesidesOrdinaryGarbageCollection() {
	}

	@AfterClass
	public static void somethingAfterAllIsDoneLikeLoggingOutOfDatabase() {
	}

}
