package game.web;

import game.text.Result;
import game.text.TextGame;
import game.text.vampire.Vampire;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebControllerSimpleFsm extends HttpServlet {
	private static final long serialVersionUID = -1596530904117564478L;

	private enum GameStatus {
		UNINITIALIZED, 
		INITIALIZED,
		PLAYING,
		PAUSED,
		QUITTING,
		DONE,
		QUIT;
	}
	
	private GameStatus gameStatus;

	private PrintWriter out;

	private TextGame textGame;
	
	public WebControllerSimpleFsm() {
		this.textGame = new Vampire();
		setGameStatus(GameStatus.UNINITIALIZED);
	}
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    	
    	String action = req.getParameter("action");
    	if (action != null && action != "") {
    		action = action.toLowerCase();
    	}
    	
    	resp.setContentType("text/html");
	    
	    out = resp.getWriter();

	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Vampire</TITLE></HEAD>");
	    out.println("<BODY>");
	    
	    // game state machine 
	    if (getGameStatus() == GameStatus.UNINITIALIZED) {
	    	textGame.initialize();
	    	prompt(textGame.welcome());
	    	setGameStatus(GameStatus.INITIALIZED);
	    } 
	    else if (getGameStatus() == GameStatus.INITIALIZED) {
	    	if (action.startsWith("y")) {
	    		output(textGame.instructions());
	    	}
    		output(textGame.takeTurn("look").getMessage());
	    	prompt("\n\nWhat do you want to do? ");
	    	setGameStatus(GameStatus.PLAYING);
	    }
	    else if (getGameStatus() == GameStatus.PLAYING) {

	    	// controller actions
		    if (action.startsWith("quit")) {
		    	prompt("\nAre you sure you want to quit and restart the game (y or n)? ");
		    	setGameStatus(GameStatus.QUITTING);
		    } else {
		    	textGame.startTurn();
	    		Result result = this.textGame.takeTurn(action);
	    		output("\n"+result.getMessage());
	    		textGame.endTurn();
	    		if (textGame.isEnded()) {
	    			prompt("\n\nWould you like to try again? ");
	    			setGameStatus(GameStatus.DONE);
	    		} else {
	    			prompt("\n\nWhat do you want to do? ");
	    		}	    	
		    }	    	
	    }
	    else if (getGameStatus() == GameStatus.QUITTING) {
	    	if (action.startsWith("y")) {
		    	textGame.initialize();
		    	prompt(textGame.welcome());	    	
		    	setGameStatus(GameStatus.INITIALIZED);
	    	} else {
	    		prompt("\n\nWhat do you want to do? ");
		    	setGameStatus(GameStatus.PLAYING);	    	
	    	}
	    }
	    else if (getGameStatus() == GameStatus.DONE) {
	    	if (action.startsWith("y")) {
		    	textGame.initialize();
		    	prompt(textGame.welcome());	    	
		    	setGameStatus(GameStatus.INITIALIZED);
	    	} else if (action.startsWith("r")) {
	    		textGame.resetEnded();
	    		prompt("\n\nWhat do you want to do? ");
		    	setGameStatus(GameStatus.PLAYING);	    	
	    	} else {
		    	output("\n\nGoodbye. And remember real vampires don't sparkle.");
		    	setGameStatus(GameStatus.UNINITIALIZED);
	    	}
	    }
	    else {
	    	output("Real vampires do not sparkle.");
	    	setGameStatus(GameStatus.UNINITIALIZED);
	    }

	    out.println("</BODY></HTML>");		
	}

    private GameStatus getGameStatus()
    {
    	return gameStatus;
    }
    
    private void setGameStatus(GameStatus status)
    {
    	gameStatus = status;
    }

	protected void output(String msg) {
		String webMsg = msg.replaceAll("(\r\n|\n)", "<br />");
		out.write(webMsg);
		out.flush();
	}
	
	protected void prompt(String msg) {
		output(msg);
		
		out.println("<form action=\"\" method=\"get\">");
		out.println("	<input name=\"action\" type=\"text\">");
		out.println("</form>");
		
	}

}
