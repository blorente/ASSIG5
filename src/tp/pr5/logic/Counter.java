package tp.pr5.logic;

public enum Counter {
    BLACK, EMPTY, WHITE;

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