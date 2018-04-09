package coffeePot;

public class CoinSlot {
	
	
	// in cents
	private int balance = 0;


	public boolean isEnough(int price) {
		// TODO Auto-generated method stub
		return balance >= price;
	}
	
	
	public void insert(int amount) {
		this.balance += amount;
	}

	
	public void deduct(int price) {
		this.balance -= price;
	}
	
	public int coinReturn() 
	{
		int temp = this.balance;
		this.balance = 0;
		return temp;
	}
	
	
	// getters and setters
	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int amount) {
		this.balance = amount;
	}
}
