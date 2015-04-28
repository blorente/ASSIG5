package tp.pr5.control;

import tp.pr5.logic.*;

import java.util.Random;

import tp.pr5.Util;
import tp.pr5.logic.Board;
import tp.pr5.logic.Connect4Move;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class RandomConnect4Player implements Player {

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col;
		do {
			col = rand.nextInt(board.getWidth()) + Board.getMinwidth();
		} while (Util.firstEmptyPosition(board, col) < Util.ERRORTHRESHOLD); // In this while I search for and non full random column in the board
		
		Move newMove = new Connect4Move(col, colour); // Then generate the move and return
		
		return newMove;
	}

}
