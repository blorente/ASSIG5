package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;
import tp.pr5.logic.ReversiMove;

public class HumanReversiPlayer extends HumanPlayer {
	
	public HumanReversiPlayer (Scanner in) {
		super(in);
	}
	
	@Override
	public Move getMove(ReadOnlyBoard board, Counter colour) {
		return new ReversiMove(readColumn("column"),readColumn("row"), colour );
	}

}
