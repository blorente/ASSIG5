package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.control.GravityFactory;
import tp.pr5.control.ReversiFactory;
import tp.pr5.logic.Game;

public class PlayRVCmd implements Command {

	@Override
	public void execute(Game game, ConsoleController c) {
		c.setFactory(new ReversiFactory());
		c.setRules(c.getFactory().createRules());
		game.reset(c.getRules());
		c.initializePlayers();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		
		if (tokens.length != 2) {
			return null;
		}
		if (!(tokens[0] + " " + tokens[1]).equalsIgnoreCase("PLAY RV")) {
			return null;
		}
		return this;
	}

}
