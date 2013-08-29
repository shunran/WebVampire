package game.web;

abstract public class GameState extends StateBasic {

	public GameState(String name, GameStates states) {
		super(name, states);
	}

	/**
	 * Process the context of the game state to complete
	 * a turn, generally by applying an action to the game model.
	 * 
	 * @param context
	 * @return the result of the turn
	 * @throws IllegalArgumentException if context is not valid
	 */	
	abstract public TurnResult takeTurn(GameContext context);
	
	public String getPrompt() {
		return "\n\nWhat do you want to do? ";
	}
	
	/**
	 * Validate the context, throwing exception if invalid.
	 * 
	 * @param context
	 * @return void
	 * @throws IllegalArgumentException
	 */
	protected void validateContext(GameContext context)
	{
		// validate the context
		if (context == null)
		{
			throw new IllegalArgumentException("No context specified");
		}
		if (context.getAction() == null || context.getAction().toString() == null)
		{
			throw new IllegalArgumentException("No action specified");
		}
		if (context.getGame() == null)
		{
			throw new IllegalArgumentException("No game model specified");			
		}
	}
	
}
