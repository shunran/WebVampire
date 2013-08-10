package game.web;

import game.text.TextGame;

public class GameStateInitialized extends GameState {
	
	public GameStateInitialized(GameStates states)
	{
		super(GameStates.INITIALIZED, states);
	}
		
	@Override
	public TurnResult takeTurn(GameContext context) {
		TextGame textGame = context.getGame();
		String action = context.getAction().toString();

		String output = "";
		
    	if (action.startsWith("y")) {
			output += textGame.instructions();
    	}
		output += textGame.takeTurn("look").getMessage();
    	output += getPrompt();
		
    	return new TurnResult(output, getStates().getState(GameStates.PLAYING));
	}

}
