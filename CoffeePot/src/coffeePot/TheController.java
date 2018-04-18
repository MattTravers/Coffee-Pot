package coffeePot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TheController {
	// Attributes
	private TheView view;
	private Dispenser dispenser;
	private CoinSlot coinSlot;

	// Constructor
	public TheController() {
		dispenser = new Dispenser();
		coinSlot = new CoinSlot();
	}

	//add coin method
	public void addCoin(int value) {
		System.out.println("Added " + value + " cents to the machine!");
		coinSlot.insert(value);
	}
	//get balance
	public int getBalance() {
		System.out.println("Current balance is " + coinSlot.getBalance());
		return coinSlot.getBalance();
	}
	//coin return
	public int coinReturn() {
		System.out.println("Returned " + coinSlot.getBalance());
		return coinSlot.coinReturn();
	}
	
	//restocks a particular condiment
	public void restockReserve(String condiment, int amount) {
		dispenser.setreserve(condiment, amount);
	}
	
	
	public boolean isEnough(Drink drink) {
		return coinSlot.isEnough(drink.getPrice());
	}
	
	/*
	 * takes a Drink object and checks balance and reserve, if okay, deducts from
	 * balance and reserve and prints drink.
	 */
	public String serveDrink(Drink drink) {
		
		if (!dispenser.check(drink)) {
			String s = "Coffee Machine is out of ingredients for " + drink;
			System.out.println(s);
			return s;
		} else if(!coinSlot.isEnough(drink.getPrice())) {
			String s = "Please insert more money";
			System.out.println(s);
			return s;
		}
		else {
			dispenser.changeReserve(drink);
			coinSlot.deduct(drink.getPrice());
			String s = "Coffee Machine dispenses " + drink;
			System.out.println(s);
			return s;
		}

	}
	
	  ActionListener drinkPressed = new ActionListener(){
		  public void actionPerformed (ActionEvent e) {
			  String drinkName = ((JButton) e.getSource()).getActionCommand();
			  
	

			    }
		  
	  };
}
