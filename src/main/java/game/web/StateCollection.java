package game.web;

public interface StateCollection<T extends State> {

	public void addState(T state);
	
	public T getState(String name);

}
