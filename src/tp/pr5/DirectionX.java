package tp.pr5;

public enum DirectionX {
	NOTHING, RIGHT, LEFT;
	
	public DirectionX invertDirection (DirectionX x) {
		switch (x) {
		case LEFT:
			x = RIGHT;
			break;
		case RIGHT:
			x = LEFT;
			break;
		default:
			break;
		
		}
		return x;
	}
	
}
