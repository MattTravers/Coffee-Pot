package coffeePot;

import java.util.EnumSet;

/**
 * This class is in charge of all of the money handling, storing and change
 */
public class CoinSlot implements Subject {
	private Observer observer;
	private int[] coinStock;
	private String outputString;

	// in cents
	private int balance = 0;

	private enum money {
		NICKEL("nickel", 5), DIME("dime", 10), QUARTER("quarter", 25), DOLLAR("dollar", 100), FIVE("five", 500);

		private int value;
		private String name;

		private money(String name, int value) {
			this.value = value;
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public int getAmount() {
			return this.value;
		}
	}

	/**
	 *  sets up the  coin slot with 100 of each denomination type
	 */
	public CoinSlot() {
		EnumSet<money> tempMoneySet = EnumSet.allOf(money.class);
		coinStock = new int[tempMoneySet.size()];
		for (int i = 0; i < coinStock.length; i++) {
			coinStock[i] = 100;
		}
	}

	/**
	 *  checks if balance is enough
	 */
	public boolean isEnough(int price) {
		return balance >= price;
	}

	/**
	 *  updates and informs user of balance
	 */
	public void insert(String money) {
		EnumSet<money> tempMoneySet = EnumSet.allOf(money.class);
		int index = 0;
		for (money m : tempMoneySet) {
			if (m.getName().equals(money)) {
				balance += m.getAmount();
				this.updateBalance();
				this.outputString = "Inserted a " + m.getName();
				this.updateOutput();
				coinStock[index]++;
				break;
			}
			index++;
		}
	}

	/**
	 *  deducts price from balance
	 */
	public void deduct(int price) {
		if (this.balance < price) {
			throw new IllegalArgumentException("Tried to deduct more than the balance");
		}
		this.balance -= price;
		this.updateBalance();
	}

	/**
	 * gives remaining balance when called
	 */
	public void coinReturn() {
		int tempBalance = this.balance;
		money[] tempMoneyArr = money.values();
		int index = 0;
		int numCoins;
		this.outputString = "Returned ";
		for (int i = tempMoneyArr.length - 1; i >= 0; i--) {
			if (tempBalance >= tempMoneyArr[i].getAmount()) {
				numCoins = tempBalance / tempMoneyArr[i].getAmount();
				if (numCoins > coinStock[index]) {
					numCoins = coinStock[index];
				}
				tempBalance -= numCoins * tempMoneyArr[i].getAmount();
				this.outputString += numCoins + " "
						+ (numCoins > 1 ? tempMoneyArr[i].getName() + "s" : tempMoneyArr[i].getName()) + ", ";
			}
			index++;
		}
		if (tempBalance > 0) {
			this.outputString = "Cannot provide exact change";
		} else {
			this.outputString = this.outputString.substring(0, this.outputString.length() - 2) + ".";
			this.balance = 0;
		}
		this.updateOutput();
		this.updateBalance();
	}

	/**
	 *  updates the balance
	 */
	private void updateBalance() {
		this.observer.updateBalance(String.format("$%d.%02d", this.getBalance() / 100, this.getBalance() % 100));
	}

	/**
	 *  updates the output string
	 */
	private void updateOutput() {
		this.observer.updateOutput(outputString);
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}

	// getters and setters
	public int getBalance() {
		return this.balance;
	}


	public String getOutput() {
		return this.outputString;
	}
}
