package tp.pr5.logic;

import java.io.Serializable;

public class UndoException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4329209507812914566L;

	public UndoException(String msg) {
		super(msg);
	}
}
