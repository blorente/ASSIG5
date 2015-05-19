package tp.pr5.logic;

import tp.pr5.control.PlayerType;

public enum Counter {
    BLACK, EMPTY, WHITE;
    
    PlayerType mode = PlayerType.HUMAN;
    
    public String toString(Counter counter) {
        String word;
        switch (counter) {
            case BLACK:
                word = "Black";
                break;
            case WHITE:
                word = "White";
                break;
            default:
                word = "Empty";
                break;
        }
        return word;
    }
    
    public PlayerType getMode() { 
    	return mode; 
    }
    
    public void setMode(PlayerType mode) {
    	this.mode = mode;
    }
    
    public Counter getOpponent(Counter counter) {
    	Counter opponent = null;
    	switch (counter) {
		case WHITE:
			opponent = Counter.BLACK;
			break;
		case BLACK:
			opponent = Counter.WHITE;
		default:
			break;
		}
    	return opponent;
    }
    
}