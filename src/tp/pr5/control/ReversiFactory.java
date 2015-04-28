package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

public class ReversiFactory implements GameTypeFactory {

	@Override
	public GameRules createRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move createMove(int col, int row, Counter colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createHumanPlayerAtConsole(Scanner in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createRandomPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
