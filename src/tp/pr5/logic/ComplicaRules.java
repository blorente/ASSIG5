package tp.pr5.logic;

import tp.pr5.DirectionX;
import tp.pr5.DirectionY;
import tp.pr5.Util;

public class ComplicaRules implements GameRules {

	private static final int DIMX = 4;
	private static final int DIMY = 7;
	private static final Counter STARTPLAYER = Counter.WHITE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.GameRules#initializeBoard()
	 */
	public Board newBoard() {
		Board board = new Board(ComplicaRules.DIMX, ComplicaRules.DIMY);
		return board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.GameRules#initialPlayer()
	 */
	public Counter initialPlayer() {
		return ComplicaRules.STARTPLAYER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.GameRules#isWinner(tp.pr2.logic.Move,
	 * tp.pr2.logic.Board)
	 */
	public Counter winningMove(Move lastMove, Board b) {
		boolean blackWins = false;
		boolean whiteWins = false;
		boolean end = false;
		int x = Board.MINWIDTH;
		int y = b.getHeight();
		Counter winner = Counter.EMPTY;

		while ((y >= Board.MINHEIGHT) && (!whiteWins || !blackWins)) {
			while ((x <= b.getWidth()) && (!whiteWins || !blackWins)) {
				// Check up
				end = Util.checkCellInDirection(b, x, y, DirectionX.NOTHING,
						DirectionY.UP);
				// Check right
				if (!end)
					end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT,
							DirectionY.NOTHING);
				// Check up-left diagonal
				if (!end)
					end = Util.checkCellInDirection(b, x, y, DirectionX.LEFT,
							DirectionY.UP);
				// Check up-right diagonal
				if (!end)
					end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT,
							DirectionY.UP);

				// Assign winner (if any)
				if (end) { // end is true
					if (b.getPosition(x, y) == Counter.BLACK) {
						blackWins = true;
					} else if (b.getPosition(x, y) == Counter.WHITE) {
						whiteWins = true;
					}
				}

				if (((whiteWins && !blackWins) || (!whiteWins && blackWins))
						&& end) {
					winner = b.getPosition(x, y);
				} else if (whiteWins && blackWins) {
					winner = Counter.EMPTY;
				}
				// Increase x counter
				if (!whiteWins || !blackWins) {
					x++;
				}
			}

			x = Board.MINWIDTH;
			y--;
		}

		return winner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.GameRules#draw(tp.pr2.logic.Counter,
	 * tp.pr2.logic.Board)
	 */
	public boolean isDraw(Counter lastPlaced, Board b) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.logic.GameRules#nextTurn(tp.pr2.logic.Counter,
	 * tp.pr2.logic.Board)
	 */
	public Counter nextTurn(Counter lastPlaced, Board b) {
		Counter next = Counter.EMPTY;
		if (lastPlaced == Counter.BLACK) {
			next = Counter.WHITE;
		} else if (lastPlaced == Counter.WHITE) {
			next = Counter.BLACK;
		}
		return next;
	}

}
