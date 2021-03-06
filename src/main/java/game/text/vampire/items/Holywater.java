package game.text.vampire.items;

import game.text.ActionContinued;
import game.text.ActionInitial;
import game.text.Actor;
import game.text.ItemGeneric;
import game.text.Player;
import game.text.Result;
import game.text.ResultGeneric;
import game.text.ResultPartial;
import game.text.TextGame;
import game.text.exceptions.ActionException;

public class Holywater extends ItemGeneric {
	private TextGame game;
	
	public Holywater(TextGame _game) {
		super("Holywater", "");
		this.game = _game;
		this.addAction(new ActionInitial("get") {
				@Override
				public Result execute(Actor actor) {
					if (((Player) actor).getLocation().hasOne("holywater")) {
						return new ResultPartial(true, "      -- In what? ", (ActionContinued) Holywater.this.getAction("get_in"));
					} else {
						return new ResultGeneric(false, "I don't see any "+game.getItem("holywater").getName());									
					}
				}					
			});
		this.addAction(new ActionContinued("get_in") {
				@Override
				public Result execute(Actor actor, String feedback) {
					if (((Player) actor).getLocation().hasOne("holywater")) {
						if (game.matchItem(feedback) == game.getItem("bucket") && 
								((Player) actor).hasOne("bucket")) {
							try {
								((Player) actor).pickUp(game.getItem("holywater"));
								this.incrementCount();
								return new ResultGeneric(true, "You got the "+game.getItem("holywater").getName());
							} catch (ActionException e) {
								return new ResultGeneric(false, e.getMessage());
							}
						}
						return new ResultGeneric(false, "You can't do that\n");	
					} else {
						return new ResultGeneric(false, "I don't see any "+game.getItem("holywater").getName());									
					}
				}					
			});
		ActionInitial dropAction = new ActionInitial("drop") {
			@Override
			public Result execute(Actor actor) {
				if (((Player) actor).hasOne("holywater") &&
						((Player) actor).getLocation() == game.getPlace("study")) {
					// in the study, holy water puts out the fire!
					game.getItem("_fire").changeName("Smoldering Ashes");
					game.getItem("_fire").setData("out", new Boolean(true));
					((Player) actor).removeOne("holywater");  // holywater is consumed				
					return new ResultGeneric(true, ((Player) actor).getLocation().getInitialAction("look").execute(actor).getMessage());
				} else {
					// normal drop
					try {
						((Player) actor).drop(game.getItem("holywater"));
						this.incrementCount();
						return new ResultGeneric(true, "The "+game.getItem("holywater").getName()+" is on the "+((Player) actor).getLocation().getName()+" floor");
					} catch (ActionException e) {
						return new ResultGeneric(false, e.getMessage());
					}
				}								
			}
		};
		this.addAction(dropAction);  
		this.addAction("throw", dropAction);  // set "throw" as alias for "drop"
	}
}
