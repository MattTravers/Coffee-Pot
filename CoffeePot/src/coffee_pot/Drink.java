package coffee_pot;

import java.util.Iterator;

public interface Drink {
	public int getPrice();
	public Iterator<String> getIterator(); //to iterate through ingredients and deduct from reserves
}
