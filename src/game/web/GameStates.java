package game.web;

public class GameStates extends StateCollectionBasic<GameState> {
	public static final String UNINITIALIZED = "uninitialized";
	public static final String INITIALIZED = "initialized";
	public static final String PLAYING = "playing";
	public static final String QUITTING = "quitting";
	public static final String DONE = "done";
}
