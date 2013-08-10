package game.web;

import game.text.TextGame;

public class GameStateDone extends GameState {
	
	public GameStateDone(GameStates states)
	{
		super(GameStates.DONE, states);
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
    	}	
    	else if (action.startsWith("r")) {
			textGame.resetEnded();
			output += getPrompt();
		} else {
	    	output += "\n\nGoodbye. And remember real vampires don't sparkle.";
	    	nextState = getStates().getState(GameStates.UNINITIALIZED);
		}
		
    	return new TurnResult(output, nextState);
	}

}
