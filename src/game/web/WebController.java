package game.web;

import game.text.TextGame;
import game.text.vampire.Vampire;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebController extends HttpServlet {
	private static final long serialVersionUID = -1596530904117564478L;

	private PrintWriter out;

	private TextGame textGame;

	private GameStates gameStates;

	private GameState gameState;
	
	public WebController() {
		this.textGame = new Vampire();
		
		this.gameStates = new GameStates();
		this.gameStates.addState(new GameStateUninitialized(this.gameStates));
		this.gameStates.addState(new GameStateInitialized(this.gameStates));
		this.gameStates.addState(new GameStatePlaying(this.gameStates));
		this.gameStates.addState(new GameStateQuitting(this.gameStates));
		this.gameStates.addState(new GameStateDone(this.gameStates));
		
		this.gameState = this.gameStates.getState(GameStates.UNINITIALIZED);
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

	    GameContext context = new GameContext(this.textGame, new ActionBasic(action));
    	TurnResult turnResult = gameState.takeTurn(context);
    	prompt(turnResult.getOutput());
    	gameState = (GameState) turnResult.getNextState();
    	
	    out.println("</BODY></HTML>");		
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
