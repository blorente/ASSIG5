package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;
import tp.pr5.views.console.ConsoleView;

public class ConsoleController extends Controller {

	private Game game;
	private Scanner in;
	private GameRules rules;
	private Player[] players;

	private GameTypeFactory factory;
	private int numPlayer;
	private String[] tokens;
	
	private ConsoleView view;

	public ConsoleController(GameTypeFactory factory, Game g) {
		this.game = g;
		this.factory = factory;
		this.in = new Scanner(System.in);
		this.rules = factory.createRules();
		this.players = new Player[2];
		this.initializePlayers();
		this.numPlayer = 0;
		this.tokens = null;
		this.view = new ConsoleView(g, this);
	}

	@Override
	public void run() {
		Instruction inst = Instruction.ERROR;
		boolean correctUndo;
		
		while (!this.game.isFinished() && !(inst.equals(Instruction.EXIT))) {
            inst = readInstruction(this.in);
			switch (inst) {
			case MOVE:
				Move mov = players[this.numPlayer].getMove(
						this.game.getBoard(), this.game.getTurn());
			
				this.game.executeMove(mov);
				this.numPlayer = this.getNextPlayerIndex(this.game
						.getTurn());
			
				break;
			case UNDO:
				correctUndo = this.game.undo();			
				break;
			case RESTART:
				this.game.reset(this.rules);
				System.out.println("Game restarted.");
				break;
			case ERROR:
				// // non existing command
				// System.err.println("Invalid move, please try again.");
				break;
			case EXIT:
				System.out.println("Exit requested. ");
				break;
			case PLAY_C4:
				this.factory = new Connect4Factory();
				this.rules = this.factory.createRules();
				this.game.reset(this.rules);
				initializePlayers();
				System.out.println("Game restarted.");
				break;
			case PLAY_CO:
				this.factory = new ComplicaFactory();
				this.rules = this.factory.createRules();
				this.game.reset(this.rules);
				initializePlayers();
				break;
			case PLAY_G:
				try {
					if (tokens.length == 4) {
						this.factory = new GravityFactory(
								Integer.parseInt(this.tokens[2]),
								Integer.parseInt(this.tokens[3]));
						this.rules = this.factory.createRules();
					}
				} catch (NumberFormatException e) {
					//System.err.println("Invalid row or column number.");
				}
				this.game.reset(this.rules);
				initializePlayers();
				break;
			case PLAY_RV:
				this.factory = new ReversiFactory();
				this.rules = this.factory.createRules();
				this.game.reset(this.rules);
				initializePlayers();
				break;
			case PLAYER:
				Counter colour = null;
				try {
					colour = Counter.valueOf(this.tokens[1]);
					if (this.tokens[2].equals("RANDOM")) {
						this.players[this.getNextPlayerIndex(colour)] = factory
								.createRandomPlayer();

					} else if (this.tokens[2].equals("HUMAN")) {
						this.players[this.getNextPlayerIndex(colour)] = factory
								.createHumanPlayerAtConsole(this.in);
					}
					// this.game.reset(this.rules);
					
				} catch (IllegalArgumentException e) {
					//System.err.println("Invalid move, please try again.");
				}
				break;
			case HELP:
				System.out.println(getHelpString());
				break;
			default:
				break;
			}

		}
	}

	private Instruction readInstruction(Scanner in) {
		// We initialize to move for the random player
		Instruction inst = Instruction.MOVE;
		String instString = "";
		// Start scanner
		System.out.print("Please enter a command: ");

		instString = in.nextLine();
		String commandRaw = instString;
		instString = instString.toUpperCase();
		String delims = "[ ]+";
		this.tokens = instString.split(delims);
		try { // Catches Exceptions for parseInt and valueOf
			switch (this.tokens.length) {
			case 1:
				break;
			case 2:
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY C4")) {
					instString = "PLAY_C4";
				} else if ((this.tokens[0] + " " + this.tokens[1])
						.equals("PLAY CO")) {
					instString = "PLAY_CO";
				} else if ((this.tokens[0] + " " + this.tokens[1])
						.equals("PLAY RV")) {
					instString = "PLAY_RV";
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
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY GR")) {
					instString = "PLAY_G";
				}
				break;
			}
			// Transforms the string into an instruction
			inst = Instruction.valueOf(instString);
		} catch (Exception e) {

			System.err.println(commandRaw.toLowerCase()
					+ ": command not understood, please try again");
			inst = Instruction.ERROR;
		}

		return inst;
	}

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

	public String[] getTokens() {
		return tokens;
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
