package tp.pr5.control;


public abstract class Controller {

	/*private Scanner in;
	private Player[] players;
	
	private GameTypeFactory factory;*/
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
	
/*
	private Instruction readInstruction(Scanner in) {
		//We initialize to move for the random player
		Instruction inst = Instruction.MOVE;
		String instString = "";
		// Start scanner
		System.out.print("Please enter a command: ");

		instString = in.nextLine();
		String commandRaw = instString;
		instString = instString.toUpperCase();
		String delims = "[ ]+";
		this.tokens = instString.split(delims);
		try { //Catches Exceptions for parseInt and valueOf
			switch (this.tokens.length) {
			case 1:
				break;
			case 2:
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY C4")) {
					instString = "PLAY_C4";
				} else if ((this.tokens[0] + " " + this.tokens[1])
						.equals("PLAY CO")) {
					instString = "PLAY_CO";
				}
				break;
			case 3:
				if ((this.tokens[0] + " " + this.tokens[1] + " " + this.tokens[2])
						.equals("MAKE A MOVE")) {
					instString = "MOVE";
				} else if (this.tokens[0].equals("PLAYER")) {
					instString = "PLAYER";
				}
				break;
			case 4:
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY GR")
						) {
					instString = "PLAY_G";
				}
				break;
			}
		//Transforms the string into an instruction			
			inst = Instruction.valueOf(instString);
		} catch (Exception e) {
			
			System.err.println(commandRaw.toLowerCase()
					+ ": command not understood, please try again");
			inst = Instruction.ERROR;
		}
		
		return inst;
	}
	*/
	public String[] getTokens() {
		return tokens;
	}
	/*
	private int getNextPlayerIndex(Counter colour) {
		int i = 0;
		if (colour == Counter.BLACK) {
			i = 1;
		}
		return i;
	}

	private void initializePlayers() {
		for (int i = 0; i < 2; i++) {
			this.players[i] = this.factory.createHumanPlayerAtConsole(this.in);
		}
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
	}*/

}
