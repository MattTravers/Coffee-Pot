package coffeePot;

public class CoinSlot implements Subject {
	private Observer observer;
	private int[] coins = { 1, 5, 10, 25, 100, 500 };
	private int[] coinStock;
	private String[] money = { "penny", "nickel", "dime", "quarter", "dollar", "five" };
	private boolean changeAvailable;
	private String outputString;

	// in cents
	private int balance = 0;

	public CoinSlot() {
		coinStock = new int[money.length];
		for (int i = 0; i < coinStock.length; i++) {
			coinStock[i] = 100;
		}
	}

	public boolean isEnough(int price) {
		return balance >= price;
	}

	public void insert(String money) {
		for (int i = 0; i < this.money.length; i++) {
			if (this.money[i].equals(money)) {
				balance += coins[i];
				coinStock[i]++;
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
		if (changeAvailable) {
			//TODO do this is a super sweet swanky nice for loop
			int num;
			if (temp >= 500) {
				num = temp / 500;
				if (coinStock[5] >= num) {
					coinStock[5] -= num;
				}
			}
			if (temp >= 100) {
				num = temp / 100;
				if (coinStock[4] >= num) {
					coinStock[4] -= num;
				}
			}
			if (temp >= 25) {
				num = temp / 25;
				if (coinStock[3] >= num) {
					coinStock[3] -= num;
				}
			}
			if (temp >= 10) {
				num = temp / 10;
				if (coinStock[2] >= num) {
					coinStock[2] -= num;
				}
			}
			if (temp >= 5) {
				num = temp / 5;
				if (coinStock[1] >= num) {
					coinStock[1] -= num;
				}
			}
			if (temp > 0) {
				coinStock[0] -= temp;
			}
		}
		this.balance = 0;
		this.notifyObservers();

		// check if we can give change still if we can't send text to output saying
		// "change not provided"
		if (coinStock[0] < 5 || coinStock[1] < 2 || coinStock[2] < 3 || coinStock[3] < 4 || coinStock[4] < 5) {
			changeAvailable = false;
		} else {
			changeAvailable = true;
		}
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
		this.outputString = String.format("$%d.%02d", this.getBalance() / 100, this.getBalance() % 100);
		this.observer.update("Balance", this.outputString);
	}

	public String getOutput() {
		return this.outputString;
	}
}
