package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.*;
import tp.pr5.logic.ComplicaMove;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public class HumanComplicaPlayer extends HumanPlayer{

	public HumanComplicaPlayer(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Move newMove = new ComplicaMove(readColumn("column"), colour);
		return newMove;
	}
	
}
