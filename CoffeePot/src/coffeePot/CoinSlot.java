package coffeePot;

public class CoinSlot implements Subject{
	private Observer observer;
	private int[] coins;
	
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


	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}


		@Override
	public void notifyObservers() {
		this.observer.update("Balance","$" +this.getBalance()/100 +"."+this.getBalance()%100);
	}
}
