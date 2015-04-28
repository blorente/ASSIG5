package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;

public abstract class HumanPlayer implements Player{
	
	protected Scanner in;
	
	public HumanPlayer(Scanner in) {
		this.in = in;
	}
	
	public int readColumn(String asked) {
		int answer = -1;		
		System.out.print("Please provide the " + asked + " number: ");
		answer = this.in.nextInt();
		in.nextLine();
		return answer;
	}
	
	public abstract Move getMove(ReadOnlyBoard board, Counter colour);
	
}
