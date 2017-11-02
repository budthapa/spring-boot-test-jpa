package pro.budthapa.service;

import java.util.List;
import java.util.Optional;

import pro.budthapa.domain.ToDo;

public interface ToDoService {
	List<ToDo> getAllToDo();
	Optional<ToDo> getToDoById(Long id);
	ToDo saveToDo(ToDo todo);
	void deleteToDo(ToDo todo);
}
