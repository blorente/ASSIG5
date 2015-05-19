package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public interface Command {
	public static final String DELIMS = "[ ]+" ;
	public void execute(Game game, ConsoleController c);
	public Command parse(String line);
}
