package tp.pr5.control;

import java.util.Random;

import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class RandomGravityPlayer implements Player {

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col, row;
		do {
			col = rand.nextInt(board.getWidth()) + Board.getMinwidth();
			row = rand.nextInt(board.getHeight()) + Board.getMinheight();
		} while (board.getPosition(col, row) != Counter.EMPTY); // In this while I search for a random column and row
																// such that there is an empty counter
		
		Move newMove = new GravityMove (col, row, colour); // Then generate the move and return
		return newMove;
	}

}
