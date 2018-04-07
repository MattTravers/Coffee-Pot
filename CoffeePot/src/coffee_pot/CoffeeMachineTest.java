package coffee_pot;

import org.junit.Assert;

class CoffeeMachineTest {

	   @BeforeClass
	   public static void onlyOnce()
	   {
		   CoffeeMachine coffeePot = new CoffeeMachine();
	   }


	   @Before
	   public void setUp()
	   {

	   }
	  
	   //Sally puts in one quarter, presses dispense Insufficient funds.
	   @Test
	   public void testA()
	   {
		   Assert.assertEquals();
	   }

	   //Sally puts in two quarters, presses dispense  Dispenses coffee.
	   @Test
	   public void testB()
	   {
		   Assert.assertEquals();
	   }

	   //Sally puts in one quarters, presses coin return Returns 25 cents.
	   @Test
	   public void testC()
	   {
		   Assert.assertEquals();
	   }
	   
	   //Sally puts in two quarters, walks away Balance should show 50 cents.
	   @Test
	   public void testD()
	   {
		   Assert.assertEquals();
	   }
	   
	   //Sally buys two coffees, white with sugar The sugar dispenser runs out of sugar    after the first Dispenses one coffee, displays “out of sugar.”
	   @Test
	   public void testE()
	   {
		   Assert.assertEquals();
	   }
	  
	   //Call balance Balance should show 0 cents.
	   @Test
	   public void testF()
	   {
		   Assert.assertEquals(0,coffeePot.getBalance());
		   coffeePot.addCoin(25);
		   Assert.assertEquals(25,coffeePot.getBalance());
	   }
	   

	   @After
	   public void somethingThatNeedsDoingAfterEveryTestBesidesOrdinaryGarbageCollection()
	   {
	   }

	   @AfterClass
	   public static void somethingAfterAllIsDoneLikeLoggingOutOfDatabase()
	   {
	   }

}
