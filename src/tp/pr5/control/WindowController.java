package tp.pr5.control;

import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.InvalidMove;
import tp.pr5.logic.Move;
import tp.pr5.views.window.MainWindow;

public class WindowController extends Controller {
	
	private Game game;
	private GameTypeFactory factory;
	private GameRules rules;
	private Player random;
	private MainWindow window;
	
	public WindowController (GameTypeFactory factory, Game game) {
		this.game = game;
		this.factory = factory;
		this.rules = factory.createRules();
		this.random = factory.createRandomPlayer();

	}
	
	public void makeMove(int col, int row, Counter colour) { 
		Move move = this.factory.createMove(col, row, colour);
		
		this.game.executeMove(move);		
	}
	
	public void reset(GameRules rules) { 
		this.game.reset(rules);
	}
	
	public void undo() {
		this.game.undo();
	}
	
	public void changeGame (Instruction instruction, int width, int height) {
		switch (instruction) {
		//TODO implementa lo que queda de la window
		case PLAY_C4:
			this.factory = new Connect4Factory();
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		case PLAY_CO:
			this.factory = new ComplicaFactory();
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		case PLAY_G:
			this.factory = new GravityFactory(width, height);
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		default:
			break;
		
		}
	}
	
	public void requestQuit() {
		//TODO implementa
	}
	
	public void randomMove() {
		Move move = random.getMove(this.game.getBoard(), this.game.getTurn());		
		this.game.executeMove(move);		
	}
	@Override
	public void run() {
		this.window = new MainWindow(game, this);
		
	}

    public GameRules getRules() {
        return rules;
    }
}
