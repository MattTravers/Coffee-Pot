package coffeePot;

public class CoinSlot implements Subject {
	private Observer observer;
	private int[] coins = new int[6];
	private String[] money = { "penny", "nickle", "dime", "quarter", "dollar", "five" };

	// in cents
	private int balance = 0;

	public CoinSlot() {
		for (int i = 0; i < coins.length; i++) {
			coins[i] = 100;
		}
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
		this.observer.update("Balance", "$" + this.getBalance() / 100 + "." + this.getBalance() % 100);
	}
}
