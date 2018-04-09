package coffeePot;

import java.util.Iterator;


public class CondimentReserve {
	
	private static int reserve[]= {10, 10, 10, 1, 10, 10, 10};
	
	private int stringConverter(String name){
		switch(name) {
		case "Coffee":
			return 0;
		case "Tea":
			return 1;
		case "Decaf":
			return 2;
		case "Sugar":
			return 3;
		case "Cream":
			return 4;
		case "Lemon":
			return 5;
		case "ChickenBroth":
			return 6;
		}
		return -1;
	}
		

	public boolean check(Drink drink) {
		
		Iterator<String> it = drink.getIterator();
		
		while(it.hasNext()){
			String name = it.next();
			if(reserve[stringConverter(name)] < 1){
				System.out.println("Error: not enough " + name);
				return false;
			}
		}
		return true;
	}

	public void changeReserve(Drink drink) {
		Iterator<String> it = drink.getIterator();
		
		while(it.hasNext()){
			String name = it.next();
			if(stringConverter(name) == -1){
				
			}else{
				reserve[stringConverter(name)] --;
			}
		}
	}
	
	public int getReserve(String condiment) {
		return reserve[stringConverter(condiment)];
		
	}
	
	public void setreserve(String condiment, int value) {
		this.reserve[stringConverter(condiment)] = value;
	}
}

