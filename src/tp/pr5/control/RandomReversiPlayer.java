package tp.pr5.control;

import java.util.Random;

import tp.pr5.Util;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;
import tp.pr5.logic.ReversiMove;

public class RandomReversiPlayer implements Player {

	@Override
	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col, row;
		do {
			col = rand.nextInt(board.getWidth()) + Board.getMinwidth();
			row = rand.nextInt(board.getHeight()) + Board.getMinheight();
		} while (Util.isPossibleMove(board, col, row, colour)); // In this while I search for a random column and row
																// such that is a possible move
		Move newMove = new ReversiMove(col, row, colour); // Then generate the move and return
		return newMove;
	}


}
