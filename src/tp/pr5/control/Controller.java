package tp.pr5.control;


public abstract class Controller {

	private String[] tokens;
	
	public Controller() {}
	
	/**
	 * The main method of the controller.
	 * It is assume to execute the game 
	 * until it finishes 
	 * (depending on the kind of the controller)
	 */
	abstract public void run();
	

	public String[] getTokens() {
		return tokens;
	}

}
