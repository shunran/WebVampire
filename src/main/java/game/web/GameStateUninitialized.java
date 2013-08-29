package game.web;

import game.text.TextGame;

public class GameStateUninitialized extends GameState {

	public GameStateUninitialized(GameStates states)
	{
		super(GameStates.UNINITIALIZED, states);
	}
		
	@Override
	public TurnResult takeTurn(GameContext context) {
		TextGame textGame = context.getGame();
    	textGame.initialize();
    	return new TurnResult(textGame.welcome(), getStates().getState(GameStates.INITIALIZED));
	}

}
