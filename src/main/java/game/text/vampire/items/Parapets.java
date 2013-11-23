package game.text.vampire.items;

import game.text.ActionInitial;
import game.text.Actor;
import game.text.ItemFixedInPlace;
import game.text.Player;
import game.text.Result;
import game.text.ResultGeneric;
import game.text.TextGame;

public class Parapets extends ItemFixedInPlace {
	private TextGame game;

	public Parapets(TextGame _game) {
		super("Parapets", "");
		this.game = _game;
		this.addAction(new ActionInitial("climb") {
			@Override
			public Result execute(Actor actor) {
					// trying to go to parapets without rope tied is a very bad thing...
					game.end(false);
					return new ResultGeneric(false, " You fell and Died\n");
			}
		});
	}}
