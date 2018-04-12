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
		System.out.println("-----------------------------------------New Test Case-------------------------------------");
	}

	
	// Sally puts in one quarter, presses dispense Insufficient funds.
	@Test
	public void testA() {
		coffeePot.addCoin(25);
		Assert.assertFalse(coffeePot.isEnough(new Coffee(true,1,1)));		
	}

	
	// Sally puts in two quarters, presses dispense Dispenses coffee.
	@Test
	public void testB() {
		Coffee coffee = new Coffee(false, 1, 1);
		coffeePot.addCoin(25);
		coffeePot.addCoin(25);
		Assert.assertEquals("Coffee Machine dispenses " + coffee, coffeePot.serveCoffee(coffee));
		Assert.assertEquals(15, coffeePot.getBalance());
	}

	
	// Sally puts in one quarters, presses coin return Returns 25 cents.
	@Test
	public void testC() {
		coffeePot.addCoin(25);
		Assert.assertEquals(25, coffeePot.getBalance());
	}

	
	// Sally puts in two quarters, walks away Balance should show 50 cents.
	@Test
	public void testD() {
		coffeePot.addCoin(25);
		coffeePot.addCoin(25);
		Assert.assertEquals(50, coffeePot.getBalance());
	}

	
	// Sally buys two coffees, white with sugar. The sugar dispenser runs out of
	// sugar after the first Dispenses one coffee, displays �out of sugar.�
	@Test
	public void testE() {
		coffeePot.restockReserve("Sugar", 1);
		Coffee coffee = new Coffee(false, 1, 1);
		coffeePot.addCoin(80);
		Assert.assertEquals("Coffee Machine dispenses " + coffee, coffeePot.serveCoffee(coffee));
		Assert.assertEquals("Coffee Machine is out of ingredients for " + coffee, coffeePot.serveCoffee(coffee));

	}

	
	// Call balance Balance should show 0 cents.
	@Test
	public void testF() {
		Assert.assertEquals(0, coffeePot.getBalance());

	}
	
	
	//lets get some decaf
	@Test
	public void testG() {
		coffeePot.addCoin(35);
		Coffee coffee = new Coffee(true, 0, 0);
		Assert.assertEquals("Coffee Machine dispenses " + coffee, coffeePot.serveCoffee(coffee));

	}
	
	//...what about some chicken broth?
	@Test
	public void testH() {
		coffeePot.addCoin(65);
		ChickenBroth chickenBroth = new ChickenBroth();
		Assert.assertEquals("Coffee Machine dispenses " + chickenBroth, coffeePot.serveCoffee(chickenBroth));

	}
	
	
	//...tea?
	@Test
	public void testI() {
		coffeePot.addCoin(50);
		Tea tea = new Tea(0, 0, 1);
		Assert.assertEquals("Coffee Machine dispenses " + tea, coffeePot.serveCoffee(tea));

	}
	
	@After
	public void somethingThatNeedsDoingAfterEveryTestBesidesOrdinaryGarbageCollection() {
	}
	

	@AfterClass
	public static void somethingAfterAllIsDoneLikeLoggingOutOfDatabase() {
	}

}
