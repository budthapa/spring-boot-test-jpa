package pro.budthapa.exception;

public class ToDoException extends Exception {
	private static final long serialVersionUID = -9018314580566395780L;

	public ToDoException() {}
	
	
	public ToDoException(String errorMessage) {
		super(errorMessage);
	}


}
