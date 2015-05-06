package tp.pr5;

public enum DirectionY {
	NOTHING, UP, DOWN;
	
	public DirectionY invertDirection (DirectionY y) {
		switch (y) {
		case DOWN:
			y = UP;
			break;
		case UP:
			y = DOWN;
			break;
		default:
			break;
		}
		return y;
	}
}
