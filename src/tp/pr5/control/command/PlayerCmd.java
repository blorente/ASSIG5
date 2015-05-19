package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;

public class PlayerCmd implements Command {

	private Counter player;
	private String type;
	
	private PlayerCmd(Counter player, String type) {
		this.player = player;
		this.type = type;
	}
	
	public PlayerCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, ConsoleController c) {
		Counter colour = null;
		try {
			
			if (this.type.equalsIgnoreCase("RANDOM")) {
				c.setPlayer(c.getFactory().createRandomPlayer());
			} else if (this.type.equalsIgnoreCase("HUMAN")) {
				c.setPlayer(c.getFactory().createHumanPlayerAtConsole(c.getScanner()));
			}
			// this.game.reset(this.rules);
			
		} catch (IllegalArgumentException e) {
			//System.err.println("Invalid move, please try again.");
		}
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		if (tokens.length != 3) {
			return null;
		}
		if (!tokens[0].equalsIgnoreCase("PLAYER")) {
			return null;
		}
		return this;
	}

}
