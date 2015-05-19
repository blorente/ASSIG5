package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;
import tp.pr5.logic.InvalidMove;

public class UndoCmd implements Command {

	@Override
	public void execute(Game game, ConsoleController c) {
		try {
			boolean correctUndo = game.undo();
		} catch (InvalidMove e1) {
		}		
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		if (tokens.length != 1) {
			return null;
		}
		if (!tokens[0].equalsIgnoreCase("UNDO")) {
			return null;
		}
		return this;
	}

}
