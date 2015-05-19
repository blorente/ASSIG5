package tp.pr5.control.command;

import tp.pr5.control.ComplicaFactory;
import tp.pr5.control.Connect4Factory;
import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class PlayCOCmd implements Command {

	@Override
	public void execute(Game game, ConsoleController c) {
		c.setFactory(new ComplicaFactory());
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
		if (!(tokens[0] + " " + tokens[1]).equalsIgnoreCase("PLAY CO")) {
			return null;
		}
		return this;
	}
}
