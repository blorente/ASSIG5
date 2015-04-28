package tp.pr5.control;

import java.util.Random;

import tp.pr5.logic.Board;
import tp.pr5.logic.ComplicaMove;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class RandomComplicaPlayer implements Player {

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col = rand.nextInt(board.getWidth()) + Board.getMinwidth(); // I generate a random column number for the new move
		Move newMove = new ComplicaMove(col, colour);
		return newMove;
	}
	
}
