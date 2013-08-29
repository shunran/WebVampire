package game.web;

import game.text.Result;
import game.text.ResultPartial;
import game.text.TextGame;

public class GameStatePlaying extends GameState {
	
	public GameStatePlaying(GameStates states)
	{
		super(GameStates.PLAYING, states);
	}
		
	@Override
	public TurnResult takeTurn(GameContext context) {
		validateContext(context);
		TextGame textGame = context.getGame();
		String action = context.getAction().toString();
		String output = "";
		State nextState = getStates().getState(GameStates.PLAYING);

	    if (action.startsWith("quit")) {
	    	output = "\nAre you sure you want to quit and restart the game (y or n)? ";
	    	nextState = getStates().getState(GameStates.QUITTING);
	    } else {
	    	textGame.startTurn();
    		Result result = textGame.takeTurn(action);
    		output += "\n"+result.getMessage();
    		textGame.endTurn();
    		if (textGame.isEnded()) {
    			output += "\n\nWould you like to try again? ";
    	    	nextState = getStates().getState(GameStates.DONE);
    		} else if (!(result instanceof ResultPartial)) {
    			output += getPrompt();
    		}	    	
	    }	    	

	    return new TurnResult(output, nextState);
	}

}
