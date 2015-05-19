package tp.pr5.control.command;

import tp.pr5.control.Connect4Factory;
import tp.pr5.control.ConsoleController;
import tp.pr5.control.GravityFactory;
import tp.pr5.logic.Game;

public class PlayGRCmd implements Command {

	private int x, y;
	
	private PlayGRCmd(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public PlayGRCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, ConsoleController c) {
		c.setFactory(new GravityFactory(this.x, this.y));
		c.setRules(c.getFactory().createRules());
		game.reset(c.getRules());
		c.initializePlayers();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split(DELIMS);
		
		if ((tokens.length == 2) || (tokens.length == 4)) {
			if (!(tokens[0] + " " + tokens[1]).equalsIgnoreCase("PLAY GR")) {
				return null;
			}
			try {
				return new PlayGRCmd(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			} catch (ArrayIndexOutOfBoundsException f) {
				return new PlayGRCmd(10, 10);
			}catch (NumberFormatException e) {		
				return null;
			} 
		}		
		return null;		
	}

}
