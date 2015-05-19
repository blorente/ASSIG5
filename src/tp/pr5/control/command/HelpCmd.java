package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;

public class HelpCmd implements Command {

	@Override
	public void execute(Game game, ConsoleController c) {
		System.out.println(getHelpString());		
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		if (tokens.length != 1) {
			return null;
		}
		if (!tokens[0].equalsIgnoreCase("HELP")) {
			return null;
		}
		return this;
	}
	
	private String getHelpString() {
		String help = "The available commands are:"
				+ "\n"
				+ "\n"
				+

				"MAKE A MOVE: place a counter on the board."
				+ "\n"
				+ "UNDO: undo the last move of the game."
				+ "\n"
				+ "RESTART: restart the game."
				+ "\n"
				+ "PLAY [c4|co|gr|rv] [dimX dimY]: change the type of game."
				+ "\n"
				+ "PLAYER [white|black] [human|random]: change the type of game."
				+ "\n" + "EXIT: exit the application." + "\n"
				+ "HELP: show this help." + "\n";
		return help;
	}

}
