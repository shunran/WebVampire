package game.web;

public class ActionBasic implements Action {
	private String actionText;
	
	public ActionBasic(String actionText) {
		super();
		this.actionText = actionText;
	}

	@Override
	public String toString()
	{
		return actionText;
	}
}
