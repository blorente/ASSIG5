package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Connect4Move;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class HumanConnect4Player extends HumanPlayer {

	public HumanConnect4Player(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Move newMove = new Connect4Move(readColumn("column"), colour);
		return newMove;
	}

	

}
