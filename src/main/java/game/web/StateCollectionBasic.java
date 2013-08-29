package game.web;

import java.util.HashMap;
import java.util.Map;

public class StateCollectionBasic<T extends State> implements StateCollection<T> {
	private Map<String, T> states;
	
	public StateCollectionBasic()
	{
		this.states = new HashMap<String, T>();
	}
	
	public void addState(T state)
	{
		states.put(state.getName(), state);
	}
	
	public T getState(String name)
	{
		return states.get(name);
	}
}
