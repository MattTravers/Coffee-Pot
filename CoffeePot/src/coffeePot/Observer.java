package coffeePot;

public interface Observer {
	public void updateOutput(String message);
	public void updateBalance(String string);
	public void updateIngredient(int n, String name);
}
