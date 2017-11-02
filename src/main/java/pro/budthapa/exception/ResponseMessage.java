package pro.budthapa.exception;

public class ResponseMessage {
	private String message;
	private int errorCode;

	public ResponseMessage() {}
	
	public ResponseMessage(int errorCode,String message) {
		this.errorCode=errorCode;
		this.message=message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
