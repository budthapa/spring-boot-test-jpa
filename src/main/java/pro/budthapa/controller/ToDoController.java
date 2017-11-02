package pro.budthapa.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pro.budthapa.domain.ToDo;
import pro.budthapa.exception.ResponseMessage;
import pro.budthapa.exception.ToDoException;
import pro.budthapa.service.ToDoService;

@RestController
public class ToDoController {
	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	@Autowired
	private ToDoService todoService;
	@GetMapping("/all")
	public ResponseEntity<List<ToDo>> getAllToDo(){
		logger.info("Returning all todo list");
		return new ResponseEntity<List<ToDo>>(todoService.getAllToDo(),HttpStatus.OK);
	}
	
	@GetMapping("/todo/{id}")
	public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) throws ToDoException{
		logger.info("Getting todo with id "+id);
		
		Optional<ToDo> todo = todoService.getToDoById(id);
		if(!todo.isPresent()) {
			throw new ToDoException("Todo not found");
		}
		
		return new ResponseEntity<ToDo>(todo.get(), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<ResponseMessage> removeTodoById(@PathVariable Long id) throws ToDoException {
		logger.info("deleting todo with id "+id);
		
		Optional<ToDo> todo = todoService.getToDoById(id);
		
		if(!todo.isPresent()) {
			throw new ToDoException("Unable to delete. Todo not found");
		}
		
		todoService.deleteToDo(todo.get());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.value(),"Todo deleted"),HttpStatus.OK);
	}
	
	@PostMapping(value="/todo", consumes="application/json")
	public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo todo){
		logger.info("Saving todo data");
		todoService.saveToDo(todo);
		return new ResponseEntity<ToDo>(todo,HttpStatus.CREATED);
	}
	
	@PutMapping("/todo/5")
	public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo todo){
		logger.info("Updating todo with id "+todo.getId());
		return new ResponseEntity<ToDo>(todoService.saveToDo(todo),HttpStatus.OK);
		
	}
}
