package game.web;

public class StateBasic implements State {
	private String name;
	private StateCollection<? extends State> states;
	private Context context;
	
	public StateBasic(String name, StateCollection<? extends State> states) {
		this.name = name;
		this.states = states;
	}

	public String getName()	{
		return name;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public Context getContext() {
		return context;
	}
	
	public StateCollection<? extends State> getStates() {
		return states;
	}
}
