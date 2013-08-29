package game.web;

abstract public class GameState extends StateBasic {

	public GameState(String name, GameStates states) {
		super(name, states);
	}

	abstract public TurnResult takeTurn(GameContext context);
	
	public String getPrompt() {
		return "\n\nWhat do you want to do? ";
	}
	
}
