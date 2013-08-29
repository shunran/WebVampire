package game.web;

import game.text.TextGame;
import game.text.vampire.Vampire;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebController extends HttpServlet {
	private static final long serialVersionUID = -1596530904117564478L;

	private PrintWriter out;
	
	public WebController() {
	}
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

    	HttpSession session = req.getSession();
    	
    	TextGame gameModel = null;
    	GameStates stateModel = null;
    	GameState gameState = null;
    	if (session.isNew()) 
    	{
    		// initialize the model and state of the game
    		gameModel = new Vampire();
    		stateModel = new GameStates();
    		stateModel.addState(new GameStateUninitialized(stateModel));
    		stateModel.addState(new GameStateInitialized(stateModel));
    		stateModel.addState(new GameStatePlaying(stateModel));
    		stateModel.addState(new GameStateQuitting(stateModel));
    		stateModel.addState(new GameStateDone(stateModel));    		
    		gameState = stateModel.getState(GameStates.UNINITIALIZED);
    		
    		// store in the session
    		session.setAttribute("gameModel", gameModel);
    		session.setAttribute("stateModel", stateModel);
    		session.setAttribute("gameState", gameState);
    	}
    	else
    	{
    		// retrieve the model and state from the session
    		gameModel = (TextGame) session.getAttribute("gameModel");
    		stateModel = (GameStates) session.getAttribute("stateModel");
    		gameState = (GameState) session.getAttribute("gameState");
    		
    	}
    	
    	String action = req.getParameter("action");
    	if (action != null && action != "") 
    	{
    		action = action.toLowerCase();
    	}
    	else
    	{
    		action = "look";  // default to "look" action
    	}
    	
    	resp.setContentType("text/html");
	    
	    out = resp.getWriter();

	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Vampire</TITLE></HEAD>");
	    out.println("<BODY>");
	    
	    GameContext context = new GameContext(gameModel, new ActionBasic(action));
	    try
	    {
	    	TurnResult turnResult = gameState.takeTurn(context);
	
	    	prompt(turnResult.getOutput());
		    gameState = (GameState) turnResult.getNextState();
			
		    // save modified model and state in the session
			session.setAttribute("gameModel", gameModel);
			session.setAttribute("gameState", gameState);
	    }
	    catch (IllegalArgumentException e)
	    {
	    	// context was invalid somehow, report the problem and skip turn
	    	prompt("Something odd happened that cannot be explained... try again.");
	    }
    	    	
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
