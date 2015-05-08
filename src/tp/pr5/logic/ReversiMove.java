package tp.pr5.logic;

import tp.pr5.DirectionX;
import tp.pr5.DirectionY;
import tp.pr5.Util;

public class ReversiMove extends Move {

	private int row;
	private int[] colsChanged;
	private int[] rowsChanged;

	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		this.setCol(moveColumn);
		this.setPlayer(moveColour);
		this.setRow(moveRow);
		this.colsChanged = new int[64];
		this.rowsChanged = new int[64];
	}

	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (board.getPosition(this.getCol(), this.row) == Counter.EMPTY) {
			if (Util.isPossibleMove(board, this.getCol(), this.row, this.getPlayer())) {
				this.changeCounters(board);
			} else {
				throw new InvalidMove("Invalid move: position (" + this.getCol() + ", " + this.getRow() + ") is not gaining opponent counters");
			}
		} else {
			throw new InvalidMove("Invalid move: position (" + this.getCol() + ", " + this.getRow() + ") is already occupied.");
		}
	}

	@Override
	public void undo(Board board) {
		for (int i = 0; i < this.colsChanged.length; i++) {
			board.setPosition(this.colsChanged[i], this.rowsChanged[i], this.getPlayer().getOpponent(this.getPlayer()));
		}
		board.setPosition(this.getCol(), this.getRow(), Counter.EMPTY);
	}

	private void changeCounters(Board board) {
		boolean firstChecked = true;
		int posx = this.getCol(), posy = this.row;
		DirectionX dirx = null;
		DirectionY diry = null;
		int changedCounters = 0;
		Counter opponent = this.getPlayer().getOpponent(this.getPlayer());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				posx = this.getCol(); // I refresh the values of the positions
										// every loop
				posy = this.row;
				firstChecked = true;
				if (i == 0 && j == 0) {
					j++; // Because if both are 0 the direction is "nothing"
				}
				dirx = DirectionX.values()[i]; // For every loop we check a
												// different direction
				diry = DirectionY.values()[j];
			while ((posx >= Board.MINWIDTH) && (posy >= Board.MINHEIGHT)
					&& (posx <= board.getWidth())
					&& (posy <= board.getHeight())) {
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
					if (board.getPosition(posx, posy) == this.getPlayer()) {
						// If after all the opponent counters there is the turn
						// counter then I change the counters
						board.setPosition(this.getCol(), this.getRow(),
								this.getPlayer());
						dirx = dirx.invertDirection(dirx);
						diry = diry.invertDirection(diry);

						do {
							posx = posx + Util.convertDirX(dirx);
							posy = posy + Util.convertDirY(diry);
							board.setPosition(posx, posy, this.getPlayer());
							this.colsChanged[changedCounters] = posx;
							this.rowsChanged[changedCounters++] = posy;
						}while (board.getPosition(posx + Util.convertDirX(dirx), posy + Util.convertDirY(diry)) != this.getPlayer());
						posx = Board.MINWIDTH - 1; // I exit the loop
					}
				}

			}
			}
		}
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}
}
