package coffee_pot;

public class CoinSlot {
	
	
	// in cents
	private static int balance = 0;
	
	/*
	 * 
	 * 
	 * list of coins for later implementation
	 * 
	 * 
	 */

	public boolean isEnough(int price) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public insert(int amount) {
	this.balance =+ amount;
	}

	
	public void deduct(int price) {
		// TODO Auto-generated method stub
	}
	
	public int coinReturn() {
		return this.balance;
	}
	
	
	// getters and setter
	public int getBalance() {
		return this.balance;
	}
	
	public int setBalance(int amount) {
		this.balance = amount;
	}
}
