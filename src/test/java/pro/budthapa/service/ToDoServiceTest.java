package pro.budthapa.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import pro.budthapa.domain.ToDo;
import pro.budthapa.repository.ToDoRepository;
import pro.budthapa.service.impl.ToDoServiceImpl;

@RunWith(SpringRunner.class)
public class ToDoServiceTest {
	@Mock
	private ToDoRepository toDoRepository;
	
	@InjectMocks
	private ToDoServiceImpl toDoServiceImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllTodoTest() {
		List<ToDo> list = new ArrayList<>();
		list.add(new ToDo((long) 10, "list id 10",false));
		list.add(new ToDo((long) 11, "list id 11",true));
		list.add(new ToDo((long) 12, "list id 12",false));
		list.add(new ToDo((long) 13, "list id 13",true));
		when(toDoRepository.findAll()).thenReturn(list);
		
		List<ToDo> result = toDoServiceImpl.getAllToDo();
		assertEquals(result, list);
	}
	
	@Test
	public void getTodoByIdTest() {
		ToDo todo = new ToDo(10L, "list id 10", false);
		when(toDoRepository.findById(10L)).thenReturn(Optional.of(todo));
		Optional<ToDo> result = toDoServiceImpl.getToDoById(10L);
		assertEquals(Long.valueOf(10), result.get().getId());
		assertEquals("list id 10", result.get().getText());
		assertEquals(false, result.get().isCompleted());
	}
	
	@Test
	public void saveToDoTest() {
		ToDo todo = new ToDo(100L,"list id 100", true);
		when(toDoRepository.save(todo)).thenReturn(todo);
		ToDo result = toDoServiceImpl.saveToDo(todo);
		assertEquals(Long.valueOf(100), result.getId());
		assertEquals("list id 100",result.getText());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void deleteToDoTest() {
		ToDo todo = new ToDo(100L, "list id 100", true);
		toDoServiceImpl.deleteToDo(todo);
		verify(toDoRepository, times(1)).delete(todo);
	}
}
