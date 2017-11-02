package pro.budthapa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ToDoExceptionHandler {
	
	/*
	 * Throw this exception if ToDo with supplied Id is not found in the database
	 */
	@ExceptionHandler(ToDoException.class)
	public ResponseEntity<ResponseMessage> notFoundException(Exception ex){
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseMessage>(rm,HttpStatus.NOT_FOUND);
	}
	
	/*
	 * Throw this exception for Pathvariable with invalid requests except id
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(Exception ex){
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage("Invalid request. Please check your syntax");
		rm.setErrorCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<ResponseMessage>(rm,HttpStatus.BAD_REQUEST);
	}
}
