package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;

public abstract class Controller {

	private Scanner in;
	private Player[] players;
	
	private GameTypeFactory factory;
	private String[] tokens;
	/*
	public Controller(GameTypeFactory factory, Game g, java.util.Scanner in) {
		this.game = g;
		this.in = in;
		this.factory = factory;
		this.rules = factory.createRules();
		this.players = new Player[2];
		this.initializePlayers();
		this.numPlayer = 0;
		this.tokens = null;
	}
*/
	public Controller() {}
	
	/**
	 * The main method of the controller.
	 * It is assume to execute the game 
	 * until it finishes 
	 * (depending on the kind of the controller)
	 */
	abstract public void run();
	
	public String[] getTokens() {
		return tokens;
	}
	

}
