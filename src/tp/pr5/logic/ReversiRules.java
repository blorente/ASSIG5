package tp.pr5.logic;

import tp.pr5.Util;

public class ReversiRules implements GameRules {
	
	private static final int DIMX = 8;
	private static final int DIMY = 8;
	private static final Counter STARTPLAYER = Counter.WHITE;
	
	@Override
	public Board newBoard() {
		Board board = new Board(ReversiRules.DIMX, ReversiRules.DIMY);
		board.setPosition(4, 4, Counter.WHITE);
		board.setPosition(5, 5, Counter.WHITE);//I place the 4 counters of the beginning
		board.setPosition(4, 5, Counter.BLACK);
		board.setPosition(5, 4, Counter.BLACK);
		return board;
	}

	@Override
	public Counter initialPlayer() {
		return ReversiRules.STARTPLAYER;
	}

	@Override
	public Counter winningMove(Move lastMove, Board b) {
		Counter winner = Counter.EMPTY;
		if (Util.isFull(b)) {
			int x = Board.MINWIDTH, y = b.getHeight();
			int blacks = 0, whites = 0;
			int numOfCountersToWin = (Board.MINHEIGHT * Board.MINWIDTH) / 2;
			while ((y >= Board.MINHEIGHT) && (winner == Counter.EMPTY)) {
				while ((x <= b.getWidth()) && (winner == Counter.EMPTY)) {
					if (b.getPosition(x, y) == Counter.BLACK) {
						blacks++;
					}
					else {
						whites++;
					}
					if (blacks > numOfCountersToWin){
						winner = Counter.BLACK;
					}
					if (whites > numOfCountersToWin) {
						winner = Counter.WHITE;
					}
					x++;
				}
				x = Board.MINWIDTH;
				y--;
			}
		}
		return winner;
	}

	@Override
	public boolean isDraw(Counter lastPlaced, Board b) {
		boolean draw = false;
		if (Util.isFull(b)) {
			draw = true;
			int x = Board.MINWIDTH, y = b.getHeight();
			int blacks = 0, whites = 0;
			int numOfCountersToWin = (Board.MINHEIGHT * Board.MINWIDTH) / 2;
			while ((y >= Board.MINHEIGHT) && draw) {
				while ((x <= b.getWidth()) && draw) {
					if (b.getPosition(x, y) == Counter.BLACK) {
						blacks++;
					}
					if (b.getPosition(x, y) == Counter.WHITE) {
						whites++;
					}
					if ((whites > numOfCountersToWin) || (blacks > numOfCountersToWin)) {
						draw = false;
					}
					x++;
				}
				x = Board.MINWIDTH;
				y--;
			}
		}
		return draw;
	}

	@Override
	public Counter nextTurn(Counter lastPlaced, Board b) throws InvalidMove {
		if (lastPlaced == Counter.BLACK) {
			if (Util.hasMove(b, lastPlaced.getOpponent(lastPlaced))) {
				lastPlaced = Counter.WHITE; 
			}
		} else if (lastPlaced == Counter.WHITE) {
			if (Util.hasMove(b, lastPlaced.getOpponent(lastPlaced))) {
				lastPlaced = Counter.BLACK;
			}
		}
		return lastPlaced;
	}

}
