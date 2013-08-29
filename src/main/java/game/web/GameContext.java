package game.web;

import game.text.TextGame;

public class GameContext implements Context {
	private TextGame game;
	
	private Action action;

	public GameContext(TextGame game, Action action) {
		super();
		this.game = game;
		this.action = action;
	}

	public TextGame getGame() {
		return game;
	}

	public void setGame(TextGame game) {
		this.game = game;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
