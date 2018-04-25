package coffeePot;
/**
 * Interface for subjects.
 */
public interface Subject {
	/**
	 * Method used to register the observer with the subject
	 * @param observer
	 */
	public void registerObserver(Observer observer);
}
