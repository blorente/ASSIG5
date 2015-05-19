package tp.pr5.control.command;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Game;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;

public class MoveCmd implements Command {
	
	
	@Override
	public void execute(Game game, ConsoleController c) {
		Move mov = c.getCurrentPlayer().getMove(
				game.getBoard(), game.getTurn());
		try {
			game.executeMove(mov);
			c.changeTurn();
		} catch (InvalidMove e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		
		if (tokens.length != 3) {
			return null;
		}
		if (!(tokens[0] + " " + tokens[1] + " " + tokens[2]).equalsIgnoreCase("MAKE A MOVE")) {			
			return null;
		}
		return this;
	}

}
