package tp.pr5.control;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReadOnlyBoard;
public interface Player {

	Move getMove(ReadOnlyBoard board, Counter colour);
	
}
