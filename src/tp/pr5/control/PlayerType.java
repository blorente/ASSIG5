package tp.pr5.control;

public enum PlayerType {
	HUMAN("Human"), AUTO("Automatic");
	
	private String name;
	
	PlayerType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
