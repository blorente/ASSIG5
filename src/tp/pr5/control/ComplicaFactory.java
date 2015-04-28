package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.ComplicaMove;
import tp.pr5.logic.ComplicaRules;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;


public class ComplicaFactory implements GameTypeFactory {

	
	public GameRules createRules() {
		GameRules rules = new ComplicaRules();
		return rules;
	}

	
	public Player createHumanPlayerAtConsole(Scanner in) {
		Player newPlayer = new HumanComplicaPlayer(in);
		return newPlayer;
	}

	
	public Player createRandomPlayer() {
		Player player = new RandomComplicaPlayer();
		return player;
	}


	public Move createMove(int col, int row, Counter colour) {
		Move move = new ComplicaMove(col, colour);
		return move;
	}

}
