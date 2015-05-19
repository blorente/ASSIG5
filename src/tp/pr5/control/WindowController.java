package tp.pr5.control;

import org.omg.PortableServer.POAManagerPackage.State;

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
	private Thread autoThread;
	
	public WindowController (GameTypeFactory factory, Game game) {
		this.game = game;
		this.factory = factory;
		this.rules = factory.createRules();
		this.random = factory.createRandomPlayer();

	}
	
	public void makeMove(int col, int row, Counter colour) { 
		Move move = this.factory.createMove(col, row, colour);
		try {
			this.game.executeMove(move);
		} catch (InvalidMove e) {
			// TODO Auto-generated catch block
		} 
		automaticMove();
	}
	
	public void reset(GameRules rules) { 
		this.game.reset(rules);
	}
	
	public void undo() {
		try {
			this.game.undo();
		} catch (InvalidMove e) {
			// TODO Auto-generated catch block
		}
		automaticMove();
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
		case PLAY_RV:
			this.factory = new ReversiFactory();
			this.rules = this.factory.createRules();
			this.game.reset(rules);
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
		try {
			this.game.executeMove(move);
		} catch (InvalidMove e) {
			// TODO Auto-generated catch block
			System.out.println("asdfalskdfha");
		}
	}
	@Override
	public void run() {
		new MainWindow(game, this);
		automaticMove();
	}

    public GameRules getRules() {
        return rules;
    }
    
    public void setPlayerMode(Counter player, PlayerType type) {
    	player.setMode(type);
    	automaticMove();
    }
    
    private void automaticMove () {
    	if (game.getTurn().getMode() == PlayerType.HUMAN)
    		 return;
    	
    	this.autoThread = new Thread () {
    		public void run() {
    			
    			while (game.getTurn().getMode() == PlayerType.AUTO
    					&& !game.isFinished()
    					&& !this.isInterrupted()){
    				try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				if (!this.isInterrupted()) {
    					randomMove();
    				}
    			}
    		}
    	};
    	this.autoThread.start();
    }
       
    private void stopAutoPlayer() {
    	if (this.autoThread != null) {
	    	this.autoThread.interrupt();
	    	//Wait for the thread to end
	    	while (this.autoThread.getState().equals(State.ACTIVE));
    	}
    }
    
}
