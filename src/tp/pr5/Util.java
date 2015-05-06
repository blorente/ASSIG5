package tp.pr5;

import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.ReadOnlyBoard;

public class Util {

	// Indicates what is the value below which error codes are assigned
	// Values lower than errorthreshold are error codes
	public static final int ERRORTHRESHOLD = 0;

	/**
	 * Iterates through the column to find the first empty position at the top
	 * Returns the y position of the first empty row If an error is found it
	 * returns ERRORTHRESHOLD - 1 If the column is full, it returns 0
	 */
	/**
	 * @param board
	 * @param col
	 *            : The column to check for the first empty space
	 * @return
	 */
	public static int firstEmptyPosition(ReadOnlyBoard board, int col) {
		int row = Board.MINHEIGHT;
		// Error-checking
		if (!Util.isColumnValid(board, col)) {
			// Column is out of bounds
			row = Util.ERRORTHRESHOLD;
		} else {
			while ((row <= board.getHeight())
					&& (board.getPosition(col, row) == Counter.EMPTY)) {
				row++;
			}
			if (!Util.isRowValid(board, row - 1)) {
				row = Util.ERRORTHRESHOLD;
			}
		}
		return row - 1;
	}

	/**
	 * @param board
	 * @param x
	 * @param y
	 * @param dirx
	 *            : Direction in which we want to check it
	 * @param diry
	 *            : Direction in which we want to check it
	 * @return Whether there is a WINCON-length connection starting from the
	 *         counter at (x,y)
	 */
	public static boolean checkCellInDirection(Board board, int x, int y,
			DirectionX dirx, DirectionY diry) {
		boolean finished = false;
		int count = 0;
		Counter turn = board.getPosition(x, y);
		// Iterate check if the cell is connected
		while ((count < Game.WINCON) && (x >= Board.MINWIDTH)
				&& (y >= Board.MINHEIGHT) && (x <= board.getWidth())
				&& (y <= board.getHeight())
				&& (board.getPosition(x, y) == turn)
				&& (board.getPosition(x, y) != Counter.EMPTY)) {
			/**
			 * Change x and y according to the direction, to get the next cell
			 */
			x = x + Util.convertDirX(dirx);
			y = y + Util.convertDirY(diry);

			count++;
		}
		if (count >= Game.WINCON) {
			finished = true;
		}
		return finished;
	}

	public static int convertDirY(DirectionY diry) {
		int r;
		switch (diry) {
		case DOWN:
			r = 1;
			break;
		case NOTHING:
			r = 0;
			break;
		case UP:
			r = -1;
			break;
		default:
			r = 0;
			break;
		}
		return r;
	}

	public static int convertDirX(DirectionX dirx) {
		int r;
		switch (dirx) {
		case LEFT:
			r = -1;
			break;
		case NOTHING:
			r = 0;
			break;
		case RIGHT:
			r = 1;
			break;
		default:
			r = 0;
			break;

		}
		return r;
	}

	// Check whether the column is inside the boundaries of the board
	public static boolean isColumnValid(ReadOnlyBoard board, int column) {
		boolean valid = true;
		if ((column > board.getWidth()) || (column < Board.MINWIDTH)) {
			valid = false;
		}
		return valid;
	}

	// Check whether the row is inside the boundaries of the board
	public static boolean isRowValid(ReadOnlyBoard board, int row) {
		boolean valid = true;
		if ((row < Board.MINHEIGHT) || (row > board.getHeight())) {
			valid = false;
		}
		return valid;
	}

	public static boolean isCellEmpty(Board board, int posx, int posy) {
		return (board.getPosition(posx, posy) == Counter.EMPTY);
	}

	public static boolean thereIsSmaller(int dist, int[] distances) {
		boolean thereIs = false;
		// Iterates through the list, looking for a distance lower than dist
		for (int a : distances) {
			if (a < dist) {
				thereIs = true;
			}
		}

		return thereIs;
	}

	public static boolean isFull(Board b) {
		boolean full = true;
		int x = Board.MINWIDTH, y = b.getHeight();
		while ((y >= Board.MINHEIGHT) && full) {
			while ((x <= b.getWidth() && full)) {
				if (b.getPosition(x, y) == Counter.EMPTY) {
					full = false;
				}
				x++;
			}
			x = Board.MINWIDTH;
			y--;
		}
		return full;
	}

	public static boolean hasMove(Board b, Counter turn) {
		boolean itHas = false;
		int x = 0, y = 0;
		while (!itHas && (y < b.getHeight())) {
			while (!itHas && (x < b.getWidth())) {
				if(b.getPosition(x, y) == Counter.EMPTY) {
					itHas = isPossibleMove(b, x++, y, turn);// I see if there is a move in the whole board
				}
			}
			y++;
		}
		return itHas;
	}

	public static boolean isPossibleMove(ReadOnlyBoard board, int x, int y, Counter turn) {
		boolean possible = false;
		boolean firstChecked = true;
		int i = 0, j = 0;
		int posx = x, posy = y;
		DirectionX dirx = null;
		DirectionY diry = null;
		Counter opponent = turn.getOpponent(turn);

		while ((i < 3) && !possible) {
			while ((j < 3) && !possible) {
				posx = x; // I refresh the values of the positions every loop
				posy = y;
				firstChecked = true; // I need to check that there is one
										// counter of the other player next to
										// the position

				if (i == 0 && j == 0) {
					j++; // Because if both are 0 the direction is "nothing"
				}
				dirx = DirectionX.values()[i]; // For every loop we check a
												// different direction
				diry = DirectionY.values()[j];
			}
			while ((posx >= Board.MINWIDTH) && (posy >= Board.MINHEIGHT)
					&& (posx <= board.getWidth()) && (posy <= board.getHeight())
					&& !possible) {
				posx = posx + Util.convertDirX(dirx);
				posy = posy + Util.convertDirY(diry);
				if (firstChecked) {
					firstChecked = false;
					if (board.getPosition(posx, posy) != opponent) {
						posx = Board.MINWIDTH - 1; // I exit the loop
					}
				} else {
					if (board.getPosition(posx, posy) == Counter.EMPTY) {
						posx = Board.MINWIDTH - 1; // I exit the loop
					}
					if (board.getPosition(posx, posy) == turn) {
						possible = true; // If after all the opponent counters
											// there is the turn counter it is a
											// valid move
					}
				}

			}
		}
		return possible;
	}
}
