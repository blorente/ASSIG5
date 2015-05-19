package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class ExitCmd implements Command {

	@Override
	public void execute(Game game, ConsoleController c) {
		System.out.println("Exit requested. ");		
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		if (tokens.length != 1) {
			return null;
		}
		if (!tokens[0].equalsIgnoreCase("EXIT")) {
			return null;
		}
		return this;
	}

}
