package coffeePot;

public class CoinSlot {
	
	
	// in cents
	private int balance = 0;


	public boolean isEnough(int price) {
		// TODO Auto-generated method stub
		return balance>price;
	}
	
	
	public void insert(int amount) {
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
	
	public void setBalance(int amount) {
		this.balance = amount;
	}
}
