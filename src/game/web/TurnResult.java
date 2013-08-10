package game.web;

public class TurnResult {
	private State nextState;
	
	private String output;
	
	public TurnResult(String output, State nextState) {
		super();
		this.output = output;
		this.nextState = nextState;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}	
}
