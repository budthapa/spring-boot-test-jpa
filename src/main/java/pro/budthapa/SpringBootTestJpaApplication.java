package pro.budthapa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pro.budthapa.domain.ToDo;
import pro.budthapa.repository.ToDoRepository;

@SpringBootApplication
public class SpringBootTestJpaApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(SpringBootTestJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestJpaApplication.class, args);
	}
	
	@Autowired
	ToDoRepository toDoRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		toDoRepository.save(new ToDo("Buy milk", true));
		toDoRepository.save(new ToDo("Visit Kathmandu", false));
		toDoRepository.save(new ToDo("Print documents", true));
		toDoRepository.save(new ToDo("Watch movie", false));
		toDoRepository.save(new ToDo("Finish the project", true));
		
		logger.info("Sample data saved");		
	}
}
