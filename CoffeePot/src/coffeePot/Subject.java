package coffeePot;

public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObservers();
}
