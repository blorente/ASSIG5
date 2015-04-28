package tp.pr5.views.console;

import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;

public class ConsoleView implements GameObserver {

	private ConsoleController controller;

	public ConsoleView(Observable<GameObserver> g, ConsoleController c) {
		g.addObserver(this);
		this.controller = c;
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		System.out.println("Game restarted.");
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		System.out.println(board);
		if (winner == Counter.EMPTY) {
			System.out.println("Game over. Game ended in a draw");
		} else {
			System.out.println("Game over. "
					+ this.convertTurnFirstUpper(winner.toString()) + " wins");
		}
		System.out.println("Closing the game... ");
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		System.out.println(board);
		System.out.println(convertTurnFirstUpper(nextPlayer.toString())
				+ " to move");
	}

	@Override
	public void onMoveError(String msg) {
		System.err.println(msg);
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		System.out.println(board);
		System.out.println(convertTurnFirstUpper(nextPlayer.toString())
				+ " to move");

	}

	@Override
	public void onUndoNotPossible() {
		System.err.println("Nothing to undo.");
	}

    @Override
    public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
        System.out.println(board);
        System.out.println(convertTurnFirstUpper(turn.toString())
                + " to move");
    }

    private String convertTurnFirstUpper(String currentTurn) {
		String out;
		if (currentTurn.equals("WHITE")) {
			out = "White";
		} else {
			out = "Black";
		}
		return out;
	}

}
