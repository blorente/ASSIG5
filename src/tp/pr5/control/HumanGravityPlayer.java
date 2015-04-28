package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.*;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class HumanGravityPlayer extends HumanPlayer {

	public HumanGravityPlayer(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Move newMove = new GravityMove(readColumn("column"),readColumn("row"), colour );
		return newMove;
	}

	

}
