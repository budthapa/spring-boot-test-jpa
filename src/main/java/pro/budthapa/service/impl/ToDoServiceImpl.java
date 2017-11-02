package pro.budthapa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.ToDo;
import pro.budthapa.repository.ToDoRepository;
import pro.budthapa.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoRepository toDoRepository;
	
	@Override
	public List<ToDo> getAllToDo() {
		return toDoRepository.findAll();
	}

	@Override
	public Optional<ToDo> getToDoById(Long id) {
		return toDoRepository.findById(id);
	}

	@Override
	public ToDo saveToDo(ToDo todo) {
		return toDoRepository.save(todo);
	}

	@Override
	public void deleteToDo(ToDo todo) {
		toDoRepository.delete(todo);
	}


}
