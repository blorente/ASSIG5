package tp.pr5.control;

import tp.pr5.logic.*;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

public interface GameTypeFactory {

	GameRules createRules();
	
	Move createMove(int col, int row, Counter colour);

	Player createHumanPlayerAtConsole(java.util.Scanner in);

	Player createRandomPlayer();
}
