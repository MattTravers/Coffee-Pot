package coffeePot;

import java.util.EnumSet;

public class CoinSlot implements Subject {
	private Observer observer;
	private int[] coinStock;
	private boolean changeAvailable;
	private String outputString;

	// in cents
	private int balance = 0;
	
	private enum money{
		NICKEL("nickel",5),DIME("dime",10),QUARTER("quarter",25),DOLLAR("dollar",100),FIVE("five",500);
		
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
	
	public CoinSlot() {
		EnumSet<money> tempMoneySet = EnumSet.allOf(money.class);
		coinStock = new int[tempMoneySet.size()];
		for (int i = 0; i < coinStock.length; i++) {
			coinStock[i] = 100;
		}
	}

	public boolean isEnough(int price) {
		return balance >= price;
	}

	public void insert(String money) {
		EnumSet<money> tempMoneySet = EnumSet.allOf(money.class);
		int index = 0;
		for(money m : tempMoneySet) {
			if(m.getName().equals(money)) {
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

	public void deduct(int price) {
		if (this.balance < price) {
			throw new IllegalArgumentException("Tried to deduct more than the balance");
		}
		this.balance -= price;
		this.updateBalance();
	}

	public void coinReturn() {
		int tempBalance = this.balance;
		if (changeAvailable) {
			// TODO do this in a super sweet swanky nice for loop
			int num;
			if (tempBalance >= 500) {
				num = tempBalance / 500;
				if (coinStock[5] >= num) {
					coinStock[5] -= num;
				}
			}
			if (tempBalance >= 100) {
				num = tempBalance / 100;
				if (coinStock[4] >= num) {
					coinStock[4] -= num;
				}
			}
			if (tempBalance >= 25) {
				num = tempBalance / 25;
				if (coinStock[3] >= num) {
					coinStock[3] -= num;
				}
			}
			if (tempBalance >= 10) {
				num = tempBalance / 10;
				if (coinStock[2] >= num) {
					coinStock[2] -= num;
				}
			}
			if (tempBalance >= 5) {
				num = tempBalance / 5;
				if (coinStock[1] >= num) {
					coinStock[1] -= num;
				}
			}
			if (tempBalance > 0) {
				coinStock[0] -= tempBalance;
			}
		}
		this.balance = 0;
		this.updateBalance();

		// check if we can give change still if we can't send text to output saying
		// "change not provided"
		if (coinStock[0] < 5 || coinStock[1] < 2 || coinStock[2] < 3 || coinStock[3] < 4 || coinStock[4] < 5) {
			changeAvailable = false;
		} else {
			changeAvailable = true;
		}
	}
	
	private void updateBalance() {
		this.observer.updateBalance(String.format("$%d.%02d", this.getBalance() / 100, this.getBalance() % 100));
	}
	
	private void updateOutput() {
		this.observer.updateOutput(outputString);
	}
	// getters and setters
	public int getBalance() {
		return this.balance;
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
	}

	public String getOutput() {
		return this.outputString;
	}
}
