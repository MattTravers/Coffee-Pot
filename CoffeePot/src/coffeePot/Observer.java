package coffeePot;
/**
 * Observer interface for the coffee pot project.
 * Has three things that it wants to observe, an output, a balance, and condiment amount;
 */
public interface Observer {
	/**
	 * Used to handle output updates
	 * @param message
	 */
	public void updateOutput(String message);
	/**
	 * Used to handle balance updates
	 * @param message
	 */
	public void updateBalance(String string);
	/**
	 * Used to handle condiment amount updates
	 * @param message
	 */
	public void updateCondiment(int n, String name);
}
