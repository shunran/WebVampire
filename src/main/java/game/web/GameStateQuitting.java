package game.web;

import game.text.TextGame;

public class GameStateQuitting extends GameState {
	
	public GameStateQuitting(GameStates states)
	{
		super(GameStates.QUITTING, states);
	}
		
	@Override
	public TurnResult takeTurn(GameContext context) {
		TextGame textGame = context.getGame();
		String action = context.getAction().toString();
		String output = "";
		State nextState = getStates().getState(GameStates.PLAYING);
		
    	if (action.startsWith("y")) {
    		textGame.initialize();
    		output += textGame.welcome();
    		nextState = getStates().getState(GameStates.INITIALIZED);
    	} else {
    		output += getPrompt();
    	}
		
    	return new TurnResult(output, nextState);
	}

}
