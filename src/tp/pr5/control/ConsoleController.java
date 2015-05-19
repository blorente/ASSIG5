package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.control.command.Command;
import tp.pr5.control.command.CommandSet;
import tp.pr5.control.command.ErrorCmd;
import tp.pr5.control.command.ExitCmd;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;

public class ConsoleController extends Controller {

	private Game game;
	private Scanner in;
	private GameRules rules;
	private Player[] players;

	private GameTypeFactory factory;
	private int numPlayer;
	private String[] tokens;
	private Command command;
	
	//private ConsoleView view;
	
	public ConsoleController(GameTypeFactory factory, Game g) {
		this.game = g;
		this.factory = factory;
		this.in = new Scanner(System.in);
		this.rules = factory.createRules();
		this.players = new Player[2];
		this.initializePlayers();
		this.numPlayer = 0;
		this.tokens = null;
		//this.view = new ConsoleView(g, this);
		this.command = new ErrorCmd();
	}

	@Override
	public void run() {		
		
		while (!this.game.isFinished() && !(this.command.getClass().equals(ExitCmd.class))) {
            System.out.println(this.game.getBoard());
			
			System.out.print("$~> ");
    		String instString = in.nextLine();
    		this.command = CommandSet.parse(instString);
    		if (this.command != null) {
    			this.command.execute(game, this);   
    		} else {
    			this.command = new ErrorCmd();
    			System.err.println(instString.toLowerCase()
    					+ ": command not understood, please try again");
    		}
    		    		
		}
	}	

	private int getNextPlayerIndex(Counter colour) {
		int i = 0;
		if (colour == Counter.BLACK) {
			i = 1;
		}
		return i;
	}

	public void initializePlayers() {
		for (int i = 0; i < 2; i++) {
			this.players[i] = this.factory.createHumanPlayerAtConsole(this.in);
		}
	}

	public String[] getTokens() {
		return tokens;
	}

	
	
	public Player getCurrentPlayer () {
		return players[this.numPlayer];
	}
	
	public void changeTurn() {
		this.numPlayer = this.getNextPlayerIndex(this.game
				.getTurn());
	}

	public GameRules getRules() {
		return rules;
	}

	public GameTypeFactory getFactory() {
		return factory;
	}

	public void setRules(GameRules rules) {
		this.rules = rules;
	}

	public void setFactory(GameTypeFactory factory) {
		this.factory = factory;
	}

	public Scanner getScanner() {		
		return this.in;
	}
	
	public void setPlayer(Player player) {
		this.players[this.getNextPlayerIndex(this.game.getTurn())]  = player;
	}
	
	

	
	
}
