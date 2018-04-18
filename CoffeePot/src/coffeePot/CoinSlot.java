package coffeePot;

public class CoinSlot implements Subject {
	private Observer observer;
	private int[] coins = {1,5,10,25,100,500};
	private String[] money = { "penny", "nickel", "dime", "quarter", "dollar", "five" };

	// in cents
	private int balance = 0;

	public CoinSlot() {
		
	}

	public boolean isEnough(int price) {
		// TODO Auto-generated method stub
		return balance >= price;
	}

	public void insert(String money) {
		for (int i = 0; i < this.money.length; i++) {
			if (this.money[i].equals(money)) {
				balance += coins[i];
				break;
			}
		}
		this.notifyObservers();
	}

	public void deduct(int price) {
		if (this.balance < price) {
			// TODO throw appropriate exception
		}
		this.balance -= price;
	}

	public void coinReturn() {
		int temp = this.balance;
		// TODO Get # for each type of coin and put in a String
		// TODO figure out how to update Output on view
		this.balance = 0;
		this.notifyObservers();
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
		String string = String.format("$%d.%02d", this.getBalance() / 100,this.getBalance() % 100);
		this.observer.update("Balance", string);
	}
}
